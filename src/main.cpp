// main.cpp — ponto de entrada do recomp nativo de Heroes Lore: Wind of Soltia
#include "platform/platform.h"
#include "midp/midp.h"
#include "vm/vm.h"

#if defined(__has_include)
  #if __has_include(<SDL2/SDL.h>)
    #include <SDL2/SDL.h>
  #else
    #include <SDL.h>
  #endif
#else
  #include <SDL.h>
#endif
#include <cstdio>
#include <cstdarg>
#include <string>

static FILE* s_bootLog = nullptr;
void boot_log(const char* fmt, ...) {
  va_list args;
  va_start(args, fmt);
  vprintf(fmt, args);
  va_end(args);
  if (s_bootLog) {
    va_start(args, fmt);
    vfprintf(s_bootLog, fmt, args);
    va_end(args);
    fflush(s_bootLog);
  }
}

namespace hl {
  extern Object* g_serialRunnable;
  std::string describeThrowable(VM& vm, Object* ex);
}

int main(int argc, char** argv) {
  setvbuf(stdout, nullptr, _IONBF, 0);
  setvbuf(stderr, nullptr, _IONBF, 0);
  using namespace hl;

#ifdef __SWITCH__
  s_bootLog = fopen("sdmc:/heroes_lore_boot.log", "w");
  if (!s_bootLog) s_bootLog = fopen("sdmc:/switch/heroes_lore/boot.log", "w");
  if (!s_bootLog) s_bootLog = fopen("heroes_lore_boot.log", "w");
#endif

  boot_log("==================================================\n");
  boot_log(" Heroes Lore: Wind of Soltia — Native Recomp\n");
  boot_log(" C++17 + SDL2 Pixel-Perfect Native Port\n");
  boot_log("==================================================\n");

  std::string dataDir = "reference/extracted";
  if (argc > 1) {
    dataDir = argv[1];
  } else {
#ifdef __ANDROID__
    dataDir = ""; // No Android APK, os assets ficam na raiz do AssetManager
#elif defined(__SWITCH__)
    dataDir = "reference/extracted"; // No Switch, readAsset resolve via romfs:/ ou relativo
#else
    const char* candidates[] = {
      "reference/extracted",
      "../reference/extracted",
      "../../reference/extracted",
      "assets",
      "../assets"
    };
    for (const char* c : candidates) {
      auto testBuf = Platform::readAsset(std::string(c) + "/META-INF/MANIFEST.MF");
      if (!testBuf.empty()) { dataDir = c; break; }
    }
#endif
  }
  boot_log("[Init] Usando pasta de dados: %s\n", dataDir.c_str());

  // Inicializa janela e áudio SDL2 (escala 2x: 480x640)
  boot_log("[Init] Inicializando plataforma SDL2...\n");
  if (!Platform::init(2)) {
    boot_log("[Init] ERRO FATAL: Falha ao inicializar plataforma SDL2.\n");
    if (s_bootLog) fclose(s_bootLog);
    return 1;
  }
  boot_log("[Init] Plataforma SDL2 inicializada!\n");

  VM vm;
  boot_log("[Init] Inicializando VM J2ME...\n");
  vm.init(dataDir);
  boot_log("[Init] VM J2ME inicializada!\n");

  // Inicializa contexto de thread para a thread principal
  ThreadCtx mainCtx;
  mainCtx.id = vm.nextTid++;
  tctx = &mainCtx;
  vm.threads.push_back(&mainCtx);

  // Cria Graphics para a tela principal (240x320)
  ClassInfo* cg = vm.mustClass("javax/microedition/lcdui/Graphics");
  g_screenGraphics = vm.alloc<GraphicsObj>(cg, K_GRAPHICS);
  g_screenGraphics->target = nullptr;
  g_screenGraphics->resetClip();
  vm.roots.push_back(g_screenGraphics);

  boot_log("[Init] Carregando MIDlet principal rpg/GameMIDlet...\n");
  ClassInfo* midletClass = vm.mustClass("rpg/GameMIDlet");
  Object* midlet = vm.newObject(midletClass);
  vm.roots.push_back(midlet);

  vm.gilLock();
  try {
    Value args[1];
    args[0].o = midlet;
    Value ret[2];
    boot_log("[Init] Chamando construtor <init>()...\n");
    vm.invoke(vm.mustMethod(midletClass, "<init>:()V"), args, ret);
    boot_log("[Init] Chamando startApp()...\n");
    vm.invoke(vm.mustMethod(midletClass, "startApp:()V"), args, ret);
  } catch (JavaThrow& jt) {
    boot_log("[Init] Exceção durante inicialização: %s\n", describeThrowable(vm, jt.ex).c_str());
    vm.gilUnlock();
    Platform::shutdown();
    if (s_bootLog) fclose(s_bootLog);
    return 1;
  }
  vm.gilUnlock();

  boot_log("[Game] Loop principal iniciado. Bom jogo!\n");

  auto saveBMP = [](const char* filename, const uint32_t* pixels, int w, int h) {
    uint8_t header[54] = {
      'B', 'M',
      0, 0, 0, 0, 0, 0, 0, 0, 54, 0, 0, 0,
      40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      1, 0, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
    };
    int sz = 54 + w * h * 4;
    header[2] = (uint8_t)(sz & 0xFF); header[3] = (uint8_t)((sz >> 8) & 0xFF);
    header[4] = (uint8_t)((sz >> 16) & 0xFF); header[5] = (uint8_t)((sz >> 24) & 0xFF);
    header[18] = (uint8_t)(w & 0xFF); header[19] = (uint8_t)((w >> 8) & 0xFF);
    int nh = -h;
    header[22] = (uint8_t)(nh & 0xFF); header[23] = (uint8_t)((nh >> 8) & 0xFF);
    header[24] = (uint8_t)((nh >> 16) & 0xFF); header[25] = (uint8_t)((nh >> 24) & 0xFF);
    FILE* fp = fopen(filename, "wb");
    if (fp) {
      fwrite(header, 1, 54, fp);
      fwrite(pixels, 4, w * h, fp);
      fclose(fp);
    }
  };

  int frameCount = 0;

  // Loop principal de renderização e eventos
  while (Platform::pollEvents(vm)) {
    vm.gilLock();

    // Executa serial runnable se agendado por callSerially
    if (g_serialRunnable) {
      Object* r = g_serialRunnable;
      g_serialRunnable = nullptr;
      try {
        Value ret[2];
        vm.invokeVirtual(r, "run:()V", nullptr, 0, ret);
      } catch (JavaThrow& jt) {
        boot_log("[serialRunnable] Exceção: %s\n", describeThrowable(vm, jt.ex).c_str());
      }
    }

    // Renderiza quadro do Canvas ativo
    if (g_display && g_display->current) {
      g_screenGraphics->resetClip();
      g_screenGraphics->transX = 0;
      g_screenGraphics->transY = 0;
      Value args[1];
      args[0].o = g_screenGraphics;
      Value ret[2];
      try {
        vm.invokeVirtual(g_display->current, "paint:(Ljavax/microedition/lcdui/Graphics;)V", args, 1, ret);
      } catch (JavaThrow& jt) {
        boot_log("[paint] Exceção: %s\n", describeThrowable(vm, jt.ex).c_str());
      }
    }

    vm.gilUnlock();

    // Apresenta tela na janela SDL
    Platform::present();

    frameCount++;
#ifndef __SWITCH__
    if (frameCount == 1 || frameCount == 10 || frameCount == 30 || frameCount == 60 || frameCount == 120 ||
        frameCount == 160 || frameCount == 200 || frameCount == 260 || frameCount == 320) {
      char fname[64];
      snprintf(fname, sizeof(fname), "screenshot_frame_%d.bmp", frameCount);
      saveBMP(fname, g_screenBuffer, 240, 320);
      printf("[Frame %d] Salvo screenshot %s (current Canvas: %s)\n",
             frameCount, fname, (g_display && g_display->current) ? g_display->current->cls->name.c_str() : "none");
    }
#endif

    SDL_Delay(8);
  }

  boot_log("[Game] Encerrando graciosamente...\n");
  Platform::shutdown();
  if (s_bootLog) {
    fclose(s_bootLog);
    s_bootLog = nullptr;
  }
  return 0;
}
