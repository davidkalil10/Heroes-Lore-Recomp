// vm.cpp — carregador de classes, objetos, GC, exceções, threads
#include "vm.h"
#include "../platform/platform.h"
#if defined(__has_include)
  #if __has_include(<SDL2/SDL.h>)
    #include <SDL2/SDL.h>
    #include <SDL2/SDL_thread.h>
  #else
    #include <SDL.h>
    #include <SDL_thread.h>
  #endif
#else
  #include <SDL.h>
  #include <SDL_thread.h>
#endif
#include <cstdio>
#include <cstdlib>
#include <fstream>
#include <sstream>
#include <chrono>
#include <algorithm>

namespace hl {

ThreadCtx* tctx = nullptr;
static std::unordered_map<std::u16string, Object*> g_interned;

int64_t nowMs() {
  using namespace std::chrono;
  return duration_cast<milliseconds>(system_clock::now().time_since_epoch()).count();
}

// ---------- UTF ----------
std::string toUtf8(const std::u16string& s) {
  std::string o;
  for (size_t i = 0; i < s.size(); i++) {
    uint32_t c = s[i];
    if (c >= 0xD800 && c < 0xDC00 && i + 1 < s.size() && s[i + 1] >= 0xDC00 && s[i + 1] < 0xE000) {
      c = 0x10000 + ((c - 0xD800) << 10) + (s[i + 1] - 0xDC00); i++;
    }
    if (c < 0x80) o += (char)c;
    else if (c < 0x800) { o += (char)(0xC0 | (c >> 6)); o += (char)(0x80 | (c & 0x3F)); }
    else if (c < 0x10000) { o += (char)(0xE0 | (c >> 12)); o += (char)(0x80 | ((c >> 6) & 0x3F)); o += (char)(0x80 | (c & 0x3F)); }
    else { o += (char)(0xF0 | (c >> 18)); o += (char)(0x80 | ((c >> 12) & 0x3F)); o += (char)(0x80 | ((c >> 6) & 0x3F)); o += (char)(0x80 | (c & 0x3F)); }
  }
  return o;
}
std::u16string fromUtf8(const std::string& s) {
  std::u16string o;
  for (size_t i = 0; i < s.size();) {
    uint8_t c = s[i];
    if (c < 0x80) { o += (char16_t)c; i++; }
    else if ((c & 0xE0) == 0xC0 && i + 1 < s.size()) { o += (char16_t)(((c & 0x1F) << 6) | (s[i + 1] & 0x3F)); i += 2; }
    else if ((c & 0xF0) == 0xE0 && i + 2 < s.size()) { o += (char16_t)(((c & 0x0F) << 12) | ((s[i + 1] & 0x3F) << 6) | (s[i + 2] & 0x3F)); i += 3; }
    else if ((c & 0xF8) == 0xF0 && i + 3 < s.size()) {
      uint32_t cp = ((c & 7) << 18) | ((s[i + 1] & 0x3F) << 12) | ((s[i + 2] & 0x3F) << 6) | (s[i + 3] & 0x3F);
      cp -= 0x10000; o += (char16_t)(0xD800 + (cp >> 10)); o += (char16_t)(0xDC00 + (cp & 0x3FF)); i += 4;
    } else { o += (char16_t)c; i++; }   // byte inválido: trata como latin-1
  }
  return o;
}

int slotsOfDesc(const std::string& d, int& argSlots) {
  argSlots = 0; size_t i = 1;
  while (i < d.size() && d[i] != ')') {
    char c = d[i];
    if (c == 'J' || c == 'D') { argSlots += 2; i++; }
    else if (c == 'L') { argSlots++; i = d.find(';', i) + 1; }
    else if (c == '[') { while (d[i] == '[') i++; if (d[i] == 'L') i = d.find(';', i) + 1; else i++; argSlots++; }
    else { argSlots++; i++; }
  }
  char r = d[i + 1];
  return r == 'V' ? 0 : (r == 'J' || r == 'D') ? 2 : 1;
}

Str* asStr(Object* o) { return o && o->kind == K_STRING ? static_cast<Str*>(o) : nullptr; }

void Instance::trace(std::vector<Object*>& out) {
  const auto& r = cls->instRef;
  for (size_t i = 0; i < f.size() && i < r.size(); i++) if (r[i] && f[i].o) out.push_back(f[i].o);
}

// ---------- classes builtin ----------
struct BC { const char* name; const char* super; const char* ifaces; Kind kind; int nf; bool iface; };
static const BC kBuiltin[] = {
  {"java/lang/Object", nullptr, "", K_INST, 0, false},
  {"java/lang/Runnable", "java/lang/Object", "", K_INST, 0, true},
  {"java/lang/String", "java/lang/Object", "", K_STRING, 0, false},
  {"java/lang/StringBuffer", "java/lang/Object", "", K_SB, 0, false},
  {"java/lang/Class", "java/lang/Object", "", K_CLASS, 0, false},
  {"java/lang/Thread", "java/lang/Object", "java/lang/Runnable", K_THREAD, 0, false},
  {"java/lang/Runtime", "java/lang/Object", "", K_MISC, 0, false},
  {"java/lang/System", "java/lang/Object", "", K_MISC, 0, false},
  {"java/lang/Math", "java/lang/Object", "", K_MISC, 0, false},
  {"java/lang/Integer", "java/lang/Object", "", K_MISC, 0, false},
  {"java/lang/Throwable", "java/lang/Object", "", K_INST, 1, false},
  {"java/lang/Exception", "java/lang/Throwable", "", K_INST, 0, false},
  {"java/lang/Error", "java/lang/Throwable", "", K_INST, 0, false},
  {"java/lang/OutOfMemoryError", "java/lang/Error", "", K_INST, 0, false},
  {"java/lang/RuntimeException", "java/lang/Exception", "", K_INST, 0, false},
  {"java/lang/IndexOutOfBoundsException", "java/lang/RuntimeException", "", K_INST, 0, false},
  {"java/lang/ArrayIndexOutOfBoundsException", "java/lang/IndexOutOfBoundsException", "", K_INST, 0, false},
  {"java/lang/StringIndexOutOfBoundsException", "java/lang/IndexOutOfBoundsException", "", K_INST, 0, false},
  {"java/lang/NullPointerException", "java/lang/RuntimeException", "", K_INST, 0, false},
  {"java/lang/ArithmeticException", "java/lang/RuntimeException", "", K_INST, 0, false},
  {"java/lang/ClassCastException", "java/lang/RuntimeException", "", K_INST, 0, false},
  {"java/lang/NegativeArraySizeException", "java/lang/RuntimeException", "", K_INST, 0, false},
  {"java/lang/ArrayStoreException", "java/lang/RuntimeException", "", K_INST, 0, false},
  {"java/lang/IllegalArgumentException", "java/lang/RuntimeException", "", K_INST, 0, false},
  {"java/lang/NumberFormatException", "java/lang/IllegalArgumentException", "", K_INST, 0, false},
  {"java/lang/IllegalStateException", "java/lang/RuntimeException", "", K_INST, 0, false},
  {"java/lang/InterruptedException", "java/lang/Exception", "", K_INST, 0, false},
  {"java/io/IOException", "java/lang/Exception", "", K_INST, 0, false},
  {"java/io/EOFException", "java/io/IOException", "", K_INST, 0, false},
  {"javax/microedition/rms/RecordStoreException", "java/lang/Exception", "", K_INST, 0, false},
  {"javax/microedition/rms/RecordStoreNotFoundException", "javax/microedition/rms/RecordStoreException", "", K_INST, 0, false},
  {"javax/microedition/rms/RecordStoreNotOpenException", "javax/microedition/rms/RecordStoreException", "", K_INST, 0, false},
  {"javax/microedition/rms/RecordStoreFullException", "javax/microedition/rms/RecordStoreException", "", K_INST, 0, false},
  {"javax/microedition/rms/InvalidRecordIDException", "javax/microedition/rms/RecordStoreException", "", K_INST, 0, false},
  {"javax/microedition/media/MediaException", "java/lang/Exception", "", K_INST, 0, false},
  {"java/io/InputStream", "java/lang/Object", "", K_INST, 0, false},
  {"java/io/ByteArrayInputStream", "java/io/InputStream", "", K_BAIS, 0, false},
  {"java/io/DataInputStream", "java/io/InputStream", "", K_DIS, 0, false},
  {"java/io/OutputStream", "java/lang/Object", "", K_INST, 0, false},
  {"java/io/ByteArrayOutputStream", "java/io/OutputStream", "", K_BAOS, 0, false},
  {"java/io/DataOutputStream", "java/io/OutputStream", "", K_DOS, 0, false},
  {"java/io/PrintStream", "java/io/OutputStream", "", K_INST, 0, false},
  {"java/util/Enumeration", "java/lang/Object", "", K_INST, 0, true},
  {"java/util/VectorEnumeration", "java/lang/Object", "java/util/Enumeration", K_ENUM, 0, false},
  {"java/util/Vector", "java/lang/Object", "", K_VECTOR, 0, false},
  {"java/util/Random", "java/lang/Object", "", K_RANDOM, 0, false},
  {"javax/microedition/lcdui/Displayable", "java/lang/Object", "", K_INST, 0, false},
  {"javax/microedition/lcdui/Canvas", "javax/microedition/lcdui/Displayable", "", K_INST, 0, false},
  {"javax/microedition/lcdui/Display", "java/lang/Object", "", K_DISPLAY, 0, false},
  {"javax/microedition/lcdui/Graphics", "java/lang/Object", "", K_GRAPHICS, 0, false},
  {"javax/microedition/lcdui/Image", "java/lang/Object", "", K_IMAGE, 0, false},
  {"javax/microedition/midlet/MIDlet", "java/lang/Object", "", K_INST, 0, false},
  {"javax/microedition/rms/RecordStore", "java/lang/Object", "", K_RS, 0, false},
  {"javax/microedition/media/Manager", "java/lang/Object", "", K_MISC, 0, false},
  {"javax/microedition/media/Control", "java/lang/Object", "", K_INST, 0, true},
  {"javax/microedition/media/Controllable", "java/lang/Object", "", K_INST, 0, true},
  {"javax/microedition/media/PlayerListener", "java/lang/Object", "", K_INST, 0, true},
  {"javax/microedition/media/Player", "java/lang/Object", "javax/microedition/media/Controllable", K_INST, 0, true},
  {"javax/microedition/media/control/VolumeControl", "java/lang/Object", "javax/microedition/media/Control", K_INST, 0, true},
  {"javax/microedition/media/PlayerImpl", "java/lang/Object", "javax/microedition/media/Player", K_PLAYER, 0, false},
  {"javax/microedition/media/VolumeControlImpl", "java/lang/Object", "javax/microedition/media/control/VolumeControl", K_VOLCTL, 0, false},
  {"[", "java/lang/Object", "", K_ARRAY, 0, false},
};

void VM::fatal(const std::string& msg) {
  boot_log("\n[FATAL] %s\n", msg.c_str());
  fprintf(stderr, "[FATAL] %s\n", msg.c_str());
  fflush(stderr);
  fflush(stdout);
#ifdef __SWITCH__
  std::this_thread::sleep_for(std::chrono::milliseconds(500));
#endif
  exit(2);
}

void VM::init(const std::string& dir) {
  dataDir = dir;
  for (const BC& b : kBuiltin) {
    ClassInfo* c = new ClassInfo(); c->name = b.name; c->builtin = true; c->initState = 2;
    c->factory = b.kind; c->isInterface = b.iface; c->nInst = b.nf; c->instRef.assign(b.nf, 1);
    if (b.super) {
      c->super = classes[b.super];
      if (c->super) {
        c->nInst += c->super->nInst;
        c->instRef.insert(c->instRef.begin(), c->super->instRef.begin(), c->super->instRef.end());
      }
    }
    std::string ifs = b.ifaces; size_t p = 0;
    while (!ifs.empty()) {
      size_t q = ifs.find(',', p); std::string n = ifs.substr(p, q == std::string::npos ? q : q - p);
      if (!n.empty()) c->interfaces.push_back(classes[n]);
      if (q == std::string::npos) break;
      p = q + 1;
    }
    classes[c->name] = c;
  }
  cObject = classes["java/lang/Object"]; cString = classes["java/lang/String"]; cArray = classes["["];
  // System.out
  ClassInfo* sys = classes["java/lang/System"];
  FieldInfo fi; fi.name = "out"; fi.desc = "Ljava/io/PrintStream;"; fi.isStatic = true; fi.index = 0; fi.owner = sys; fi.isRef = true;
  sys->fields.push_back(fi); sys->fieldMap["out:Ljava/io/PrintStream;"] = &sys->fields.back();
  sys->statics.resize(1); sys->staticRef.assign(1, 1);
  sys->statics[0].o = newInstance(classes["java/io/PrintStream"]);
  // manifest
  auto mfBytes = Platform::readAsset(dataDir.empty() ? "META-INF/MANIFEST.MF" : (dataDir + "/META-INF/MANIFEST.MF"));
  if (!mfBytes.empty()) {
    std::string mfStr((const char*)mfBytes.data(), mfBytes.size());
    std::istringstream mf(mfStr);
    std::string line;
    while (std::getline(mf, line)) {
      while (!line.empty() && (line.back() == '\r' || line.back() == '\n')) line.pop_back();
      size_t c = line.find(": ");
      if (c != std::string::npos) props[line.substr(0, c)] = line.substr(c + 2);
    }
  }
  registerNatives(); registerMidp();
}

// ---------- parser de .class ----------
namespace {
struct Rd {
  const std::vector<uint8_t>& d; size_t p = 0;
  uint8_t u1() { return d.at(p++); }
  uint16_t u2() { uint16_t v = (d.at(p) << 8) | d.at(p + 1); p += 2; return v; }
  uint32_t u4() { uint32_t v = ((uint32_t)u2() << 16); v |= u2(); return v; }
};
}

ClassInfo* VM::findClass(const std::string& name) {
  auto it = classes.find(name);
  if (it != classes.end()) return it->second;
  if (!name.empty() && name[0] == '[') return cArray;
  std::string classPath = dataDir.empty() ? (name + ".class") : (dataDir + "/" + name + ".class");
  std::vector<uint8_t> buf = Platform::readAsset(classPath);
  if (buf.empty()) return nullptr;
  Rd r{buf};
  if (r.u4() != 0xCAFEBABE) fatal("classe inválida: " + name);
  r.u2(); r.u2();
  ClassInfo* c = new ClassInfo(); c->name = name;
  classes[name] = c;
  uint16_t n = r.u2(); c->cp.resize(n);
  for (int i = 1; i < n; i++) {
    CPEntry& e = c->cp[i]; e.tag = r.u1();
    switch (e.tag) {
      case 1: { uint16_t len = r.u2(); e.s.assign((const char*)&buf[r.p], len); r.p += len; break; }
      case 3: e.i = (int32_t)r.u4(); break;
      case 4: e.i = (int32_t)r.u4(); break;
      case 5: case 6: { uint32_t hi = r.u4(), lo = r.u4(); e.l = ((int64_t)hi << 32) | lo; i++; break; }
      case 7: case 8: e.a = r.u2(); break;
      case 9: case 10: case 11: case 12: e.a = r.u2(); e.b = r.u2(); break;
      default: fatal("tag cp desconhecida " + std::to_string(e.tag) + " em " + name);
    }
  }
  c->access = r.u2(); c->isInterface = (c->access & 0x200) != 0;
  r.u2();  // this
  uint16_t sup = r.u2();
  if (sup) c->super = mustClass(c->cp[c->cp[sup].a].s);
  else if (name != "java/lang/Object") c->super = cObject;
  uint16_t ni = r.u2();
  for (int i = 0; i < ni; i++) c->interfaces.push_back(mustClass(c->cp[c->cp[r.u2()].a].s));
  if (c->super) { c->nInst = c->super->nInst; c->instRef = c->super->instRef; c->factory = c->super->factory; }
  uint16_t nf = r.u2(); c->fields.reserve(nf);
  for (int i = 0; i < nf; i++) {
    FieldInfo fi; fi.access = r.u2(); fi.name = c->cp[r.u2()].s; fi.desc = c->cp[r.u2()].s; fi.owner = c;
    fi.isStatic = (fi.access & 8) != 0; fi.isRef = fi.desc[0] == 'L' || fi.desc[0] == '[';
    uint16_t na = r.u2();
    for (int a = 0; a < na; a++) {
      std::string an = c->cp[r.u2()].s; uint32_t al = r.u4();
      if (an == "ConstantValue") { fi.cvIndex = (buf[r.p] << 8) | buf[r.p + 1]; }
      r.p += al;
    }
    if (fi.isStatic) { fi.index = (int)c->statics.size(); c->statics.push_back(Value{}); c->staticRef.push_back(fi.isRef); }
    else { fi.index = c->nInst++; c->instRef.push_back(fi.isRef); }
    c->fields.push_back(fi);
  }
  for (auto& f : c->fields) c->fieldMap[f.name + ":" + f.desc] = &f;   // fields vetor já estável
  for (auto& s : c->statics) s.l = 0;
  uint16_t nm = r.u2();
  for (int i = 0; i < nm; i++) {
    auto m = std::make_unique<Method>(); m->owner = c; m->access = r.u2();
    m->name = c->cp[r.u2()].s; m->desc = c->cp[r.u2()].s; m->isStatic = (m->access & 8) != 0;
    m->retSlots = slotsOfDesc(m->desc, m->argSlots);
    uint16_t na = r.u2();
    for (int a = 0; a < na; a++) {
      std::string an = c->cp[r.u2()].s; uint32_t al = r.u4(); size_t end = r.p + al;
      if (an == "Code") {
        m->maxStack = r.u2(); m->maxLocals = r.u2(); uint32_t cl = r.u4();
        m->code.assign(buf.begin() + r.p, buf.begin() + r.p + cl); r.p += cl;
        uint16_t ne = r.u2();
        for (int e = 0; e < ne; e++) { ExcEntry x; x.start = r.u2(); x.end = r.u2(); x.handler = r.u2(); x.catchType = r.u2(); m->exc.push_back(x); }
      }
      r.p = end;
    }
    c->declared[m->name + ":" + m->desc] = m.get();
    c->methods.push_back(std::move(m));
  }
  for (auto& mPtr : c->methods) {
    auto nit = natives.find(c->name + "." + mPtr->name + ":" + mPtr->desc);
    if (nit != natives.end()) {
      mPtr->native = nit->second;
    }
  }
  return c;
}

ClassInfo* VM::mustClass(const std::string& name) {
  ClassInfo* c = findClass(name);
  if (!c) fatal("classe não encontrada: " + name);
  return c;
}

Method* VM::findMethod(ClassInfo* c, const std::string& key) {
  for (ClassInfo* k = c; k; k = k->super) {
    auto it = k->declared.find(key);
    if (it != k->declared.end()) return it->second;
    if (k->builtin) {
      auto nt = natives.find(k->name + "." + key);
      if (nt != natives.end()) {
        auto m = std::make_unique<Method>(); m->owner = k; m->native = nt->second;
        size_t col = key.find(':'); m->name = key.substr(0, col); m->desc = key.substr(col + 1);
        m->retSlots = slotsOfDesc(m->desc, m->argSlots);
        Method* mp = m.get(); k->declared[key] = mp; k->methods.push_back(std::move(m)); return mp;
      }
    }
  }
  // interfaces (métodos default/abstratos herdados): busca em largura simples
  for (ClassInfo* k = c; k; k = k->super)
    for (ClassInfo* i : k->interfaces) { Method* m = findMethod(i, key); if (m) return m; }
  return nullptr;
}
Method* VM::mustMethod(ClassInfo* c, const std::string& key) {
  Method* m = findMethod(c, key);
  if (!m) fatal("método não encontrado: " + c->name + "." + key);
  return m;
}

FieldInfo* VM::findField(ClassInfo* c, const std::string& name) {
  for (ClassInfo* k = c; k; k = k->super) {
    auto it = k->fieldMap.find(name);
    if (it != k->fieldMap.end()) return it->second;
    for (ClassInfo* i : k->interfaces) { FieldInfo* f = findField(i, name); if (f) return f; }
  }
  return nullptr;
}

bool VM::isSubclass(ClassInfo* s, ClassInfo* c) {
  for (ClassInfo* k = s; k; k = k->super) {
    if (k == c) return true;
    for (ClassInfo* i : k->interfaces) if (isSubclass(i, c)) return true;
  }
  return false;
}
bool VM::isInstance(Object* o, ClassInfo* c) {
  if (!o) return false;
  if (c == cObject) return true;
  return isSubclass(o->cls, c);
}

void VM::initClass(ClassInfo* c) {
  if (c->initState == 2) return;
  if (c->initState == 1 && c->initThread == tctx->id) return;
  c->initState = 1; c->initThread = tctx->id;
  if (c->super) initClass(c->super);
  for (auto& f : c->fields) {
    if (!f.isStatic || !f.cvIndex) continue;
    CPEntry& e = c->cp[f.cvIndex]; Value& v = c->statics[f.index];
    if (e.tag == 3) v.i = e.i;
    else if (e.tag == 5) v.l = e.l;
    else if (e.tag == 8) v.o = internStr(fromUtf8(c->cp[e.a].s));
    else if (e.tag == 4) v.i = e.i;
    else if (e.tag == 6) v.l = e.l;
  }
  auto it = c->declared.find("<clinit>:()V");
  if (it != c->declared.end()) { Value ret[2]; invoke(it->second, nullptr, ret); }
  c->initState = 2;
}

// ---------- alocação ----------
Instance* VM::newInstance(ClassInfo* c) {
  Instance* o = alloc<Instance>(c, K_INST); o->f.assign(c->nInst, Value{}); for (auto& v : o->f) v.l = 0; return o;
}
Object* VM::newObject(ClassInfo* c) {
  initClass(c);
  switch (c->factory) {
    case K_STRING: return alloc<Str>(c, K_STRING);
    case K_SB: return alloc<SB>(c, K_SB);
    case K_VECTOR: return alloc<Vec>(c, K_VECTOR);
    case K_ENUM: return alloc<Enum>(c, K_ENUM);
    case K_RANDOM: return alloc<Rand>(c, K_RANDOM);
    case K_BAIS: return alloc<Bais>(c, K_BAIS);
    case K_DIS: return alloc<Dis>(c, K_DIS);
    case K_BAOS: return alloc<Baos>(c, K_BAOS);
    case K_DOS: return alloc<Dos>(c, K_DOS);
    case K_THREAD: return alloc<ThreadObj>(c, K_THREAD);
    case K_MISC: return alloc<Object>(c, K_MISC);
    default: return newInstance(c);
  }
}
Array* VM::newArray(char type, int len) {
  if (len < 0) throwNew("java/lang/NegativeArraySizeException", std::to_string(len));
  Array* a = alloc<Array>(cArray, K_ARRAY); a->type = type; a->len = len;
  a->data.assign((size_t)len * arrayElemSize(type), 0); allocBytes += a->data.size(); return a;
}
Array* VM::newRefArray(int len) { return newArray('L', len); }
Str* VM::newStr(const std::u16string& s) { Str* o = alloc<Str>(cString, K_STRING); o->s = s; allocBytes += s.size() * 2; return o; }
Str* VM::newStrUtf8(const std::string& s) { return newStr(fromUtf8(s)); }
Object* VM::internStr(const std::u16string& s) {
  auto it = g_interned.find(s);
  if (it != g_interned.end()) return it->second;
  Str* o = newStr(s); g_interned[s] = o; return o;
}
ClassObj* VM::classObjOf(ClassInfo* c) {
  if (!c->classObj) { c->classObj = alloc<ClassObj>(classes["java/lang/Class"], K_CLASS); c->classObj->of = c; }
  return c->classObj;
}

// ---------- GC ----------
void VM::maybeGC() { if (allocBytes > gcThreshold) gc(); }
void VM::gc() {
  std::vector<Object*> work;
  auto push = [&](Object* o) { if (o && !o->mark) { o->mark = true; work.push_back(o); } };
  for (Object* o : roots) push(o);
  for (auto& kv : g_interned) push(kv.second);
  for (auto& kv : classes) {
    ClassInfo* c = kv.second;
    for (size_t i = 0; i < c->statics.size(); i++) if (c->staticRef[i]) push(c->statics[i].o);
    if (c->classObj) push(c->classObj);
  }
  for (ThreadCtx* t : threads)
    for (size_t i = 0; i < t->sp; i++) { Object* o = t->stack[i].o; if (o && objSet.count(o)) push(o); }
  std::vector<Object*> kids;
  while (!work.empty()) {
    Object* o = work.back(); work.pop_back(); kids.clear(); o->trace(kids);
    for (Object* k : kids) push(k);
  }
  std::vector<Object*> live; live.reserve(allObjs.size()); size_t bytes = 0; size_t freed = 0;
  for (Object* o : allObjs) {
    if (o->mark) { o->mark = false; live.push_back(o); bytes += o->bytes(); }
    else { objSet.erase(o); delete o; freed++; }
  }
  allObjs.swap(live); allocBytes = bytes;
  gcThreshold = std::max<size_t>(96u << 20, bytes * 2);
  if (trace) fprintf(stderr, "[gc] freed=%zu live=%zu bytes=%zu\n", freed, allObjs.size(), bytes);
}

// ---------- exceções ----------
void VM::throwNew(const char* cls, const std::string& msg) {
  ClassInfo* c = mustClass(cls);
  Instance* e = newInstance(c);
  if (!msg.empty() && !e->f.empty()) e->f[0].o = newStrUtf8(msg);
  throw JavaThrow{e};
}

// ---------- threads / monitores ----------
void VM::gilLock() {
  gil.lock();
  if (mainCtx && (unsigned long)SDL_ThreadID() == mainTid) tctx = mainCtx;
}

void VM::sleepMs(int64_t ms) {
  ThreadCtx* saved = tctx;
  tctx = nullptr;
  gilUnlock();
  if (ms > 0) {
    SDL_Delay(static_cast<uint32_t>(ms));
  } else {
    SDL_Delay(0);
  }
  gilLock();
  tctx = saved;
}

void VM::monitorEnter(Object* o) {
  if (!o) npe();
  uint32_t me = tctx ? tctx->id : 0;
  while (o->monOwner != 0 && o->monOwner != me) {
    ThreadCtx* saved = tctx;
    tctx = nullptr;
    gilUnlock();
    SDL_Delay(1);
    gilLock();
    tctx = saved;
  }
  o->monOwner = me;
  o->monCount++;
}

void VM::monitorExit(Object* o) {
  if (!o) npe();
  if (o->monCount > 0 && --o->monCount == 0) o->monOwner = 0;
}

static std::string throwableText(VM& vm, Object* ex) {
  std::string s = ex->cls->name;
  for (auto& ch : s) if (ch == '/') ch = '.';
  if (ex->kind == K_INST) {
    Instance* i = static_cast<Instance*>(ex);
    if (!i->f.empty() && i->f[0].o && i->f[0].o->kind == K_STRING) s += ": " + toUtf8(static_cast<Str*>(i->f[0].o)->s);
  }
  return s;
}

struct JavaThreadArgs {
  VM* vm;
  ThreadObj* threadObj;
};

static int SDLCALL javaThreadRunner(void* data) {
  auto* args = static_cast<JavaThreadArgs*>(data);
  VM* vm = args->vm;
  ThreadObj* t = args->threadObj;
  delete args;

  boot_log("[Thread] Iniciando execução de thread secundária J2ME...\n");

  vm->gilLock();
  ThreadCtx ctx;
  ctx.id = vm->nextTid++;
  tctx = &ctx;
  vm->threads.push_back(&ctx);

  boot_log("[Thread %u] GIL obtida, executando runnable: %s\n",
           ctx.id, (t->runnable && t->runnable->cls) ? t->runnable->cls->name.c_str() : "null");

  try {
    if (t->runnable) {
      Value r[2];
      vm->invokeVirtual(t->runnable, "run:()V", nullptr, 0, r);
    }
    boot_log("[Thread %u] Execução de run() finalizada normalmente.\n", ctx.id);
  } catch (JavaThrow& jt) {
    boot_log("[Thread %u] Exceção Java não tratada: %s\n", ctx.id, throwableText(*vm, jt.ex).c_str());
  } catch (const std::exception& e) {
    boot_log("[Thread %u] std::exception em thread: %s\n", ctx.id, e.what());
  } catch (...) {
    boot_log("[Thread %u] Exceção desconhecida em thread!\n", ctx.id);
  }

  vm->threads.erase(std::remove(vm->threads.begin(), vm->threads.end(), &ctx), vm->threads.end());
  vm->roots.erase(std::remove(vm->roots.begin(), vm->roots.end(), static_cast<Object*>(t)), vm->roots.end());
  tctx = nullptr;
  vm->gilUnlock();

  boot_log("[Thread] Thread secundária encerrada.\n");
  return 0;
}

void VM::startThread(ThreadObj* t) {
  if (t->started) throwNew("java/lang/IllegalStateException");
  t->started = true;
  roots.push_back(t);

  boot_log("[startThread] Criando thread nativa SDL para runnable=%p (%s)...\n",
           t->runnable, (t->runnable && t->runnable->cls) ? t->runnable->cls->name.c_str() : "none");

  auto* args = new JavaThreadArgs{this, t};
  SDL_Thread* th = SDL_CreateThreadWithStackSize(javaThreadRunner, "HL_JavaThread", 2 * 1024 * 1024, args);
  if (!th) {
    th = SDL_CreateThread(javaThreadRunner, "HL_JavaThread", args);
  }

  if (!th) {
    boot_log("[startThread] ERRO FATAL ao criar thread SDL: %s\n", SDL_GetError());
    delete args;
    fatal(std::string("Falha ao criar thread: ") + SDL_GetError());
  } else {
    SDL_DetachThread(th);
    boot_log("[startThread] Thread SDL criada e desanexada com sucesso!\n");
  }
}

void VM::invokeVirtual(Object* self, const std::string& key, Value* extra, int nextra, Value* ret) {
  if (!self) npe();
  Method* m = findMethod(self->cls, key);
  if (!m) fatal("invokeVirtual: " + self->cls->name + "." + key);
  Value args[16]; args[0].o = self;
  for (int i = 0; i < nextra; i++) args[1 + i] = extra[i];
  invoke(m, args, ret);
}

std::string describeThrowable(VM& vm, Object* ex) { return throwableText(vm, ex); }

}  // namespace hl
