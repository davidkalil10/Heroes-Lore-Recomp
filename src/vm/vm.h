// Heroes Lore recomp — runtime J2ME (interpretador do bytecode original + APIs nativas)
#pragma once
#include <cstdint>
#include <cstring>
#include <string>
#include <vector>
#include <unordered_map>
#include <unordered_set>
#include <mutex>
#include <atomic>
#include <memory>

namespace hl {

struct Object; struct ClassInfo; struct VM; struct Method;

union Value { int32_t i; int64_t l; float f; double d; Object* o; };
typedef void (*NativeFn)(VM&, Value* args, Value* ret);

enum Kind : uint8_t {
  K_INST, K_ARRAY, K_STRING, K_SB, K_VECTOR, K_ENUM, K_RANDOM, K_IMAGE, K_GRAPHICS,
  K_BAIS, K_DIS, K_BAOS, K_DOS, K_CLASS, K_PLAYER, K_VOLCTL, K_RS, K_THREAD, K_DISPLAY, K_MISC
};

struct Object {
  ClassInfo* cls = nullptr;
  Kind kind = K_INST;
  bool mark = false;
  uint32_t monOwner = 0, monCount = 0;
  virtual ~Object() {}
  virtual void trace(std::vector<Object*>&) {}
  virtual size_t bytes() const { return 48; }
};

struct Instance : Object {
  std::vector<Value> f;
  void trace(std::vector<Object*>& out) override;
  size_t bytes() const override { return 48 + f.size() * 8; }
};

inline int arrayElemSize(char t) {
  switch (t) { case 'B': case 'Z': return 1; case 'C': case 'S': return 2; case 'I': case 'F': return 4; default: return 8; }
}

struct Array : Object {
  char type = 'L';   // B Z C S I F J D L(ref)
  int32_t len = 0;
  std::vector<uint8_t> data;
  template <class T> T* as() { return reinterpret_cast<T*>(data.data()); }
  Object** refs() { return reinterpret_cast<Object**>(data.data()); }
  void trace(std::vector<Object*>& out) override {
    if (type == 'L') for (int i = 0; i < len; i++) if (refs()[i]) out.push_back(refs()[i]);
  }
  size_t bytes() const override { return 48 + data.size(); }
};

struct Str : Object { std::u16string s; size_t bytes() const override { return 48 + s.size() * 2; } };
struct SB : Object { std::u16string s; size_t bytes() const override { return 48 + s.size() * 2; } };
struct Vec : Object {
  std::vector<Object*> v;
  void trace(std::vector<Object*>& out) override { for (auto* o : v) if (o) out.push_back(o); }
  size_t bytes() const override { return 48 + v.size() * 8; }
};
struct Enum : Object { Vec* vec = nullptr; int idx = 0; void trace(std::vector<Object*>& o) override { if (vec) o.push_back(vec); } };
struct Rand : Object { int64_t seed = 0; };
struct Bais : Object { std::vector<uint8_t> data; size_t pos = 0; size_t bytes() const override { return 48 + data.size(); } };
struct Dis : Object { Object* in = nullptr; void trace(std::vector<Object*>& o) override { if (in) o.push_back(in); } };
struct Baos : Object { std::vector<uint8_t> data; size_t bytes() const override { return 48 + data.size(); } };
struct Dos : Object { Object* out = nullptr; void trace(std::vector<Object*>& o) override { if (out) o.push_back(out); } };
struct ClassObj : Object { ClassInfo* of = nullptr; };
struct ThreadObj : Object {
  Object* runnable = nullptr; bool started = false;
  void trace(std::vector<Object*>& o) override { if (runnable) o.push_back(runnable); }
};

struct FieldInfo { std::string name, desc; uint16_t access = 0; int index = 0; bool isStatic = false; ClassInfo* owner = nullptr; bool isRef = false; int cvIndex = 0; };
struct ExcEntry { uint16_t start, end, handler, catchType; };

struct Method {
  std::string name, desc; uint16_t access = 0; ClassInfo* owner = nullptr;
  std::vector<uint8_t> code; int maxStack = 0, maxLocals = 0;
  std::vector<ExcEntry> exc;
  int argSlots = 0;   // sem 'this'
  int retSlots = 0;   // 0,1,2
  bool isStatic = false;
  NativeFn native = nullptr;
};

struct CPEntry {
  uint8_t tag = 0; uint16_t a = 0, b = 0;
  int32_t i = 0; int64_t l = 0; std::string s;
  // resolvidos
  ClassInfo* cls = nullptr; Object* str = nullptr;
  FieldInfo* fld = nullptr; Method* meth = nullptr;
  std::string mkey;                  // "nome:desc"
  ClassInfo* icCls = nullptr; Method* icM = nullptr;   // inline cache
  int nargs = 0, retSlots = 0; bool resolved = false;
};

struct ClassInfo {
  std::string name; ClassInfo* super = nullptr; std::vector<ClassInfo*> interfaces;
  uint16_t access = 0; bool builtin = false; bool isInterface = false;
  std::vector<CPEntry> cp;
  std::vector<FieldInfo> fields;
  std::vector<std::unique_ptr<Method>> methods;
  std::unordered_map<std::string, Method*> declared;      // "nome:desc"
  std::unordered_map<std::string, FieldInfo*> fieldMap;   // "nome"
  int nInst = 0; std::vector<uint8_t> instRef;            // refs de instância (inclui super)
  std::vector<Value> statics; std::vector<uint8_t> staticRef;
  int initState = 0; uint32_t initThread = 0;
  Kind factory = K_INST; ClassObj* classObj = nullptr;
};

struct JavaThrow { Object* ex; };

struct ThreadCtx {
  std::vector<Value> stack; size_t sp = 0; uint32_t id = 0;
  ThreadCtx() : stack(1 << 18) {}
};
extern ThreadCtx* tctx;

struct VM {
  std::string dataDir;
  std::unordered_map<std::string, ClassInfo*> classes;
  std::unordered_map<std::string, NativeFn> natives;
  std::vector<Object*> allObjs; std::unordered_set<Object*> objSet;
  size_t allocBytes = 0, gcThreshold = 96u << 20;
  std::vector<Object*> roots;
  std::mutex gil; std::vector<ThreadCtx*> threads; uint32_t nextTid = 1;
  std::unordered_map<std::string, std::string> props;   // manifest (getAppProperty)
  ClassInfo *cObject = nullptr, *cString = nullptr, *cArray = nullptr;
  bool trace = false;

  void init(const std::string& dir);
  ClassInfo* findClass(const std::string& name);
  ClassInfo* mustClass(const std::string& name);
  void initClass(ClassInfo* c);
  Method* findMethod(ClassInfo* c, const std::string& key);
  Method* mustMethod(ClassInfo* c, const std::string& key);
  FieldInfo* findField(ClassInfo* c, const std::string& name);
  bool isInstance(Object* o, ClassInfo* c);
  bool isSubclass(ClassInfo* s, ClassInfo* c);

  // alocação
  template <class T> T* alloc(ClassInfo* c, Kind k) {
    T* o = new T(); o->cls = c; o->kind = k; track(o); return o;
  }
  void track(Object* o) { allObjs.push_back(o); objSet.insert(o); allocBytes += o->bytes(); }
  Instance* newInstance(ClassInfo* c);
  Object* newObject(ClassInfo* c);      // respeita factory
  Array* newArray(char type, int len);
  Array* newRefArray(int len);
  Str* newStr(const std::u16string& s);
  Str* newStrUtf8(const std::string& s);
  Object* internStr(const std::u16string& s);
  void maybeGC();
  void gc();

  // exceções
  [[noreturn]] void throwNew(const char* cls, const std::string& msg = "");
  [[noreturn]] void npe() { throwNew("java/lang/NullPointerException"); }
  [[noreturn]] void fatal(const std::string& msg);

  // execução
  void invoke(Method* m, Value* args, Value* ret);
  void invokeVirtual(Object* self, const std::string& key, Value* extraArgs, int nextra, Value* ret);
  void monitorEnter(Object* o); void monitorExit(Object* o);
  void gilUnlock();
  void gilLock();
  bool isGilOwner() const;
  std::atomic<unsigned long> gilOwner{0};
  ThreadCtx* mainCtx = nullptr; unsigned long mainTid = 0;
  void sleepMs(int64_t ms);
  void startThread(ThreadObj* t);
  ClassObj* classObjOf(ClassInfo* c);
  void registerNatives();
  void registerMidp();
};

std::string toUtf8(const std::u16string& s);
std::u16string fromUtf8(const std::string& s);
int slotsOfDesc(const std::string& d, int& argSlots);  // retorna slots do retorno
int64_t nowMs();
Str* asStr(Object* o);

}  // namespace hl
