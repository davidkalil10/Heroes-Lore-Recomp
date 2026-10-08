#include "vm/vm.h"
#include "platform/platform.h"
#include <cstdio>
#include <cstdarg>
#include <cassert>

using namespace hl;

static std::string getBabbleString(const std::vector<uint8_t>& data, int id) {
  if (id < 0 || (size_t)(id * 4 + 4) > data.size()) return "";
  uint32_t relOffset = ((uint32_t)data[id * 4] << 24) |
                       ((uint32_t)data[id * 4 + 1] << 16) |
                       ((uint32_t)data[id * 4 + 2] << 8) |
                       ((uint32_t)data[id * 4 + 3]);
  size_t pos = (size_t)(id * 4) + 4 + (size_t)(int32_t)relOffset;
  if (pos + 4 > data.size()) return "";
  pos += 2; // pula block_len
  uint16_t utfLen = ((uint16_t)data[pos] << 8) | data[pos + 1];
  pos += 2;
  if (pos + utfLen > data.size()) return "";
  std::string s(reinterpret_cast<const char*>(data.data() + pos), utfLen);
  for (char& c : s) if (c == ';') c = '\n';
  return s;
}

namespace hl {
void boot_log(const char* fmt, ...) {
  va_list args;
  va_start(args, fmt);
  vprintf(fmt, args);
  va_end(args);
}
}

int main() {
  printf("[Test] Iniciando teste de recarregamento de idioma...\n");

  VM vm;
  vm.init("reference/extracted");

  ThreadCtx mainCtx;
  mainCtx.id = vm.nextTid++;
  tctx = &mainCtx;
  vm.threads.push_back(&mainCtx);

  try {
    // 1. Carrega idioma padrão (PT)
    ClassInfo* cjClass = vm.mustClass("cj");
    ClassInfo* bhClass = vm.mustClass("bh");
    vm.initClass(cjClass);
    vm.initClass(bhClass);
    FieldInfo* fCjA = vm.findField(cjClass, "a:Lcj;");
    Object* cjInst = cjClass->statics[fCjA->index].o;

    printf("[Test] cjInst = %p\n", (void*)cjInst);

    // Inicializa cj como na inicialização do jogo (bg.java)
    Method* mCjA = vm.mustMethod(cjClass, "a:(Ljava/lang/String;Ljava/lang/String;I)V");
    Value args[4];
    args[0].o = cjInst;
    args[1].o = vm.newStrUtf8("/lang");
    args[2].o = vm.newStrUtf8("");
    args[3].i = 0;
    Value ret[2];
    printf("[Test] Chamando cj.a('/lang', '', 0)...\n");
    vm.invoke(mCjA, args, ret);
    printf("[Test] cj.a concluído!\n");

    Method* mBhA = vm.mustMethod(bhClass, "a:(Lcj;)V");
    Value bArgs[1];
    bArgs[0].o = cjInst;
    printf("[Test] Chamando bh.a(cj)...\n");
    vm.invoke(mBhA, bArgs, ret);
    printf("[Test] bh.a concluído!\n");

    // Testa leitura de uma string em PT
    Method* mCjGetStr = vm.mustMethod(cjClass, "a:(I)Ljava/lang/String;");
    args[0].o = cjInst;
    args[1].i = 3945; // Audio Ligado / Desligado
    vm.invoke(mCjGetStr, args, ret);
    Str* s = static_cast<Str*>(ret[0].o);
    printf("[Test] PT string 3945 via Java: %s\n", toUtf8(s->s).c_str());

    // Compara com getBabbleString em C++
    FieldInfo* fDisA = vm.findField(cjClass, "a:Ljava/io/DataInputStream;");
    Dis* dis = static_cast<Dis*>(static_cast<Instance*>(cjInst)->f[fDisA->index].o);
    Bais* bais = static_cast<Bais*>(dis->in);
    std::string sCpp = getBabbleString(bais->data, 3945);
    printf("[Test] PT string 3945 via C++:  %s\n", sCpp.c_str());
    assert(sCpp == toUtf8(s->s));

    // 2. Agora testa recarregar para EN
    printf("[Test] Mudando para 'en'...\n");
    Platform::setLanguage("en");
    Platform::reloadLanguage(vm);

    args[0].o = cjInst;
    args[1].i = 3945;
    vm.invoke(mCjGetStr, args, ret);
    s = static_cast<Str*>(ret[0].o);
    printf("[Test] EN string 3945: %s\n", toUtf8(s->s).c_str());

    // 3. Testa recarregar para IT
    printf("[Test] Mudando para 'it'...\n");
    Platform::setLanguage("it");
    Platform::reloadLanguage(vm);

    args[0].o = cjInst;
    args[1].i = 3945;
    vm.invoke(mCjGetStr, args, ret);
    s = static_cast<Str*>(ret[0].o);
    printf("[Test] IT string 3945: %s\n", toUtf8(s->s).c_str());

    // 4. Testa recarregar para ES
    printf("[Test] Mudando para 'es'...\n");
    Platform::setLanguage("es");
    Platform::reloadLanguage(vm);

    args[0].o = cjInst;
    args[1].i = 3945;
    vm.invoke(mCjGetStr, args, ret);
    s = static_cast<Str*>(ret[0].o);
    printf("[Test] ES string 3945: %s\n", toUtf8(s->s).c_str());

    printf("[Test] Teste concluído com SUCESSO!\n");
  } catch (JavaThrow& jt) {
    printf("[Test] JavaThrow capturada! Exceção: %s\n", (jt.ex && jt.ex->cls) ? jt.ex->cls->name.c_str() : "null");
    if (jt.ex && jt.ex->kind == K_INST) {
      Instance* inst = static_cast<Instance*>(jt.ex);
      if (!inst->f.empty() && inst->f[0].o) {
        printf("[Test] Mensagem: %s\n", toUtf8(static_cast<Str*>(inst->f[0].o)->s).c_str());
      }
    }
    return 1;
  }
  return 0;
}
