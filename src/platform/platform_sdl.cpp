// platform_sdl.cpp — backend SDL2 com renderização 240x320 escalada, áudio SDL_mixer e mapeamento de teclado
#include "platform.h"
#include "midp/midp.h"
#include "vm/vm.h"

#include <SDL.h>
#include <SDL_mixer.h>
#include <cstdio>
#include <algorithm>

#ifdef _WIN32
#define WIN32_LEAN_AND_MEAN
#include <windows.h>
#include <SDL_syswm.h>
#endif

namespace hl {

static SDL_Window* s_window = nullptr;
static SDL_Renderer* s_renderer = nullptr;
static SDL_Texture* s_screenTexture = nullptr;
static bool s_quit = false;

static int mapKey(SDL_Keycode k) {
  switch (k) {
    case SDLK_UP: case SDLK_w: return -1;
    case SDLK_DOWN: case SDLK_s: return -2;
    case SDLK_LEFT: case SDLK_a: return -3;
    case SDLK_RIGHT: case SDLK_d: return -4;
    case SDLK_RETURN: case SDLK_SPACE: case SDLK_j: case SDLK_z: return 53; // '5' / Action
    case SDLK_k: case SDLK_x: return 55; // '7'
    case SDLK_l: case SDLK_c: return 57; // '9'
    case SDLK_u: case SDLK_q: return 49; // '1'
    case SDLK_i: case SDLK_e: return 51; // '3'
    case SDLK_o: case SDLK_r: return 48; // '0'
    case SDLK_ESCAPE: case SDLK_BACKSPACE: case SDLK_m: return -8; // CLR
    case SDLK_F1: case SDLK_TAB: return -6; // LSK
    case SDLK_F2: return -7; // RSK

    // Teclado numérico
    case SDLK_KP_0: case SDLK_0: return 48;
    case SDLK_KP_1: case SDLK_1: return 49;
    case SDLK_KP_2: case SDLK_2: return 50;
    case SDLK_KP_3: case SDLK_3: return 51;
    case SDLK_KP_4: case SDLK_4: return 52;
    case SDLK_KP_5: case SDLK_5: return 53;
    case SDLK_KP_6: case SDLK_6: return 54;
    case SDLK_KP_7: case SDLK_7: return 55;
    case SDLK_KP_8: case SDLK_8: return 56;
    case SDLK_KP_9: case SDLK_9: return 57;
    case SDLK_KP_MULTIPLY: case SDLK_ASTERISK: return 42; // '*'
    case SDLK_KP_HASH: case SDLK_HASH: return 35; // '#'
    default: return 0;
  }
}

bool Platform::init(int scale) {
  if (SDL_Init(SDL_INIT_VIDEO | SDL_INIT_AUDIO | SDL_INIT_EVENTS) < 0) {
    fprintf(stderr, "Erro ao inicializar SDL: %s\n", SDL_GetError());
    return false;
  }

  // Inicializa áudio
  if (Mix_OpenAudio(44100, MIX_DEFAULT_FORMAT, 2, 2048) < 0) {
    fprintf(stderr, "Aviso: Mixer audio não inicializou: %s\n", Mix_GetError());
  } else {
    Mix_AllocateChannels(16);
  }

  int winW = 240 * scale;
  int winH = 320 * scale;

  s_window = SDL_CreateWindow(
      "Heroes Lore: Wind of Soltia (Native Recomp)",
      SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED,
      winW, winH,
      SDL_WINDOW_SHOWN | SDL_WINDOW_RESIZABLE);
  if (!s_window) {
    fprintf(stderr, "Erro ao criar janela: %s\n", SDL_GetError());
    return false;
  }

  // Configura hints antes de criar renderizador e texturas
  SDL_SetHint(SDL_HINT_RENDER_SCALE_QUALITY, "0"); // Pixel-perfect nearest neighbor

  // Prioriza direct3d11 no Windows para estabilidade moderna de GPU
  SDL_SetHint(SDL_HINT_RENDER_DRIVER, "direct3d11");
  s_renderer = SDL_CreateRenderer(
      s_window, -1,
      SDL_RENDERER_ACCELERATED | SDL_RENDERER_PRESENTVSYNC);
  if (!s_renderer) {
    SDL_SetHint(SDL_HINT_RENDER_DRIVER, "opengl");
    s_renderer = SDL_CreateRenderer(s_window, -1, SDL_RENDERER_ACCELERATED | SDL_RENDERER_PRESENTVSYNC);
  }
  if (!s_renderer) {
    SDL_SetHint(SDL_HINT_RENDER_DRIVER, "");
    s_renderer = SDL_CreateRenderer(s_window, -1, SDL_RENDERER_ACCELERATED);
  }
  if (!s_renderer) {
    s_renderer = SDL_CreateRenderer(s_window, -1, SDL_RENDERER_SOFTWARE);
  }
  if (!s_renderer) {
    fprintf(stderr, "Erro ao criar renderizador: %s\n", SDL_GetError());
    return false;
  }

  // Resolução lógica fixa de 240x320 com aspect ratio mantido e letterbox automático
  SDL_RenderSetLogicalSize(s_renderer, 240, 320);

  s_screenTexture = SDL_CreateTexture(
      s_renderer,
      SDL_PIXELFORMAT_ARGB8888,
      SDL_TEXTUREACCESS_STREAMING,
      240, 320);
  if (!s_screenTexture) {
    fprintf(stderr, "Erro ao criar textura de tela: %s\n", SDL_GetError());
    return false;
  }
  SDL_SetTextureBlendMode(s_screenTexture, SDL_BLENDMODE_NONE);

  SDL_RaiseWindow(s_window);
#ifdef _WIN32
  SDL_SysWMinfo wm;
  SDL_VERSION(&wm.version);
  if (SDL_GetWindowWMInfo(s_window, &wm)) {
    HWND hwnd = wm.info.win.window;
    ShowWindow(hwnd, SW_SHOWNORMAL);
    SetWindowPos(hwnd, HWND_TOP, 0, 0, 0, 0, SWP_NOMOVE | SWP_NOSIZE | SWP_SHOWWINDOW);
    SetForegroundWindow(hwnd);
    printf("[SDL] Win32 HWND: %p (janela trazida para o primeiro plano)\n", (void*)hwnd);
  }
#endif
  printf("[SDL] Driver de Video: %s, Audio: %s\n", SDL_GetCurrentVideoDriver(), SDL_GetCurrentAudioDriver());
  SDL_RendererInfo rinfo;
  if (SDL_GetRendererInfo(s_renderer, &rinfo) == 0) {
    printf("[SDL] Renderer: %s (flags: 0x%X)\n", rinfo.name, rinfo.flags);
  }

  return true;
}

bool Platform::pollEvents(VM& vm) {
  SDL_Event ev;
  while (SDL_PollEvent(&ev)) {
    if (ev.type == SDL_QUIT) {
      s_quit = true;
      return false;
    }
    if (ev.type == SDL_KEYDOWN && !ev.key.repeat) {
      int key = mapKey(ev.key.keysym.sym);
      if (key != 0 && g_display && g_display->current) {
        vm.gilLock();
        try {
          Value args[1];
          args[0].i = key;
          Value ret[2];
          vm.invokeVirtual(g_display->current, "keyPressed:(I)V", args, 1, ret);
        } catch (...) {}
        vm.gilUnlock();
      }
    } else if (ev.type == SDL_KEYUP) {
      int key = mapKey(ev.key.keysym.sym);
      if (key != 0 && g_display && g_display->current) {
        vm.gilLock();
        try {
          Value args[1];
          args[0].i = key;
          Value ret[2];
          vm.invokeVirtual(g_display->current, "keyReleased:(I)V", args, 1, ret);
        } catch (...) {}
        vm.gilUnlock();
      }
    }
  }
  return !s_quit;
}

void Platform::present() {
  if (!s_renderer || !s_screenTexture) return;

  void* pixels = nullptr;
  int pitch = 0;
  if (SDL_LockTexture(s_screenTexture, nullptr, &pixels, &pitch) == 0) {
    for (int y = 0; y < 320; y++) {
      memcpy((uint8_t*)pixels + y * pitch, g_screenBuffer + y * 240, 240 * sizeof(uint32_t));
    }
    SDL_UnlockTexture(s_screenTexture);
  } else {
    SDL_UpdateTexture(s_screenTexture, nullptr, g_screenBuffer, 240 * sizeof(uint32_t));
  }

  SDL_SetRenderDrawColor(s_renderer, 0, 0, 0, 255);
  SDL_RenderClear(s_renderer);
  SDL_RenderCopy(s_renderer, s_screenTexture, nullptr, nullptr);
  SDL_RenderPresent(s_renderer);
}

void Platform::shutdown() {
  if (s_screenTexture) { SDL_DestroyTexture(s_screenTexture); s_screenTexture = nullptr; }
  if (s_renderer) { SDL_DestroyRenderer(s_renderer); s_renderer = nullptr; }
  if (s_window) { SDL_DestroyWindow(s_window); s_window = nullptr; }
  Mix_CloseAudio();
  SDL_Quit();
}

bool Platform::shouldQuit() {
  return s_quit;
}

} // namespace hl
