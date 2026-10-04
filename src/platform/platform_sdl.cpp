// platform_sdl.cpp — backend SDL2 com renderização 240x320 escalada, áudio SDL_mixer e mapeamento de teclado
#include "platform.h"
#include "midp/midp.h"
#include "vm/vm.h"

#include <SDL.h>
#include <SDL_mixer.h>
#include <cstdio>
#include <algorithm>
#include <vector>
#include <cmath>
#include <cstdlib>

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

static std::vector<SDL_GameController*> s_controllers;
static bool s_ltHeld = false;
static bool s_rtHeld = false;

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
    case SDLK_m: case SDLK_o: case SDLK_r: return 48; // '0' / Mapa
    case SDLK_ESCAPE: case SDLK_BACKSPACE: case SDLK_TAB: case SDLK_F1: return -8; // Menu Principal / Cancelar / Fechar (CLR)
    case SDLK_F2: return -7; // RSK

    // Alternância de Poção / Item Rápido
    case SDLK_LEFTBRACKET: case SDLK_COMMA: return -101; // Poção Anterior (Esquerda)
    case SDLK_RIGHTBRACKET: case SDLK_PERIOD: return 35; // Próxima Poção (Direita)

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

static int mapControllerButton(Uint8 btn) {
  switch (btn) {
    // D-Pad
    case SDL_CONTROLLER_BUTTON_DPAD_UP: return -1;
    case SDL_CONTROLLER_BUTTON_DPAD_DOWN: return -2;
    case SDL_CONTROLLER_BUTTON_DPAD_LEFT: return -3;
    case SDL_CONTROLLER_BUTTON_DPAD_RIGHT: return -4;

    // Botões de Ação Frontais
    case SDL_CONTROLLER_BUTTON_A: return 53; // A (Xbox) / X (PS) -> Atacar com Arma / Interagir / Confirmar ('5')
    case SDL_CONTROLLER_BUTTON_B: return -7; // B (Xbox) / O (PS) -> Status / Cancelar (RSK)
    case SDL_CONTROLLER_BUTTON_X: return 49; // X (Xbox) / Quad (PS) -> Ataque 1 do Guardião ('1')
    case SDL_CONTROLLER_BUTTON_Y: return 51; // Y (Xbox) / Tri (PS) -> Ataque 2 do Guardião ('3')

    // Botões de Ombro (Shoulders)
    case SDL_CONTROLLER_BUTTON_LEFTSHOULDER: return 55;  // L1 -> Ataque Secundário / Habilidade ('7')
    case SDL_CONTROLLER_BUTTON_RIGHTSHOULDER: return 57; // R1 -> Usar Poção / Item Rápido ('9')

    // Menus de Sistema e Mapa
    case SDL_CONTROLLER_BUTTON_START: return -8; // Start / + -> Menu Principal / Inventário (CLR)
    case SDL_CONTROLLER_BUTTON_BACK: return 48;  // Back / Select / Touchpad / - -> Abrir/Fechar Minimapa ('0')

    // Cliques dos Analógicos
    case SDL_CONTROLLER_BUTTON_LEFTSTICK: return 48;  // L3 -> Abrir Minimapa da Região ('0')
    case SDL_CONTROLLER_BUTTON_RIGHTSTICK: return 35; // R3 -> Próxima Poção ('#')

    default: return 0;
  }
}

bool Platform::init(int scale) {
  if (SDL_Init(SDL_INIT_VIDEO | SDL_INIT_AUDIO | SDL_INIT_EVENTS | SDL_INIT_GAMECONTROLLER | SDL_INIT_HAPTIC) < 0) {
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

  // Inicializa controles conectados no momento do boot
  int numJoysticks = SDL_NumJoysticks();
  for (int i = 0; i < numJoysticks; i++) {
    if (SDL_IsGameController(i)) {
      SDL_GameController* pad = SDL_GameControllerOpen(i);
      if (pad) {
        s_controllers.push_back(pad);
        printf("[Gamepad] Controle detectado na inicializacao: %s\n", SDL_GameControllerName(pad));
        SDL_GameControllerRumble(pad, 0x3000, 0x3000, 120);
      }
    }
  }

  return true;
}

static int s_activeDirection = 0;
static uint32_t s_directionPressTime = 0;
static uint32_t s_directionLastRepeatTime = 0;

static bool isDirectionKey(int key) {
  return key == -1 || key == -2 || key == -3 || key == -4 ||
         key == 50 || key == 52 || key == 54 || key == 56;
}

static int getGamepadHeldDirection() {
  for (auto* pad : s_controllers) {
    if (!pad) continue;
    // D-Pad
    if (SDL_GameControllerGetButton(pad, SDL_CONTROLLER_BUTTON_DPAD_UP)) return -1;
    if (SDL_GameControllerGetButton(pad, SDL_CONTROLLER_BUTTON_DPAD_DOWN)) return -2;
    if (SDL_GameControllerGetButton(pad, SDL_CONTROLLER_BUTTON_DPAD_LEFT)) return -3;
    if (SDL_GameControllerGetButton(pad, SDL_CONTROLLER_BUTTON_DPAD_RIGHT)) return -4;

    // Analog Stick Esquerdo (Left X & Y)
    int16_t ax = SDL_GameControllerGetAxis(pad, SDL_CONTROLLER_AXIS_LEFTX);
    int16_t ay = SDL_GameControllerGetAxis(pad, SDL_CONTROLLER_AXIS_LEFTY);
    const int16_t DEADZONE = 12000;
    if (std::abs(ax) > DEADZONE || std::abs(ay) > DEADZONE) {
      if (std::abs(ax) > std::abs(ay)) {
        return (ax < 0) ? -3 : -4; // Left / Right
      } else {
        return (ay < 0) ? -1 : -2; // Up / Down
      }
    }
  }
  return 0;
}

struct ActiveFinger {
  SDL_FingerID id;
  int key;
};

static std::vector<ActiveFinger> s_activeFingers;
static int s_touchHeldDirection = 0;
#ifdef __ANDROID__
static bool s_touchOverlayEnabled = true;
#else
static bool s_touchOverlayEnabled = false;
#endif

static bool isTouchKeyHeld(int key) {
  for (const auto& f : s_activeFingers) {
    if (f.key == key) return true;
  }
  return false;
}

static int hitTestTouch(float logX, float logY, int winX, int winY, int winW, int winH) {
  // 1. Pilares pretos de letterbox nas laterais (em telas ultrawide de smartphones no modo paisagem)
  SDL_Rect vp;
  SDL_RenderGetViewport(s_renderer, &vp);
  if (vp.w > 0 && vp.h > 0 && vp.x > 30) {
    if (winX < vp.x) {
      // Pilar esquerdo -> Virtual D-Pad
      float relX = (float)winX / vp.x;
      float relY = (float)winY / winH;
      float dx = relX - 0.5f;
      float dy = relY - 0.65f;
      if (std::abs(dx) > std::abs(dy)) {
        return (dx < 0) ? -3 : -4;
      } else {
        return (dy < 0) ? -1 : -2;
      }
    } else if (winX > vp.x + vp.w) {
      // Pilar direito -> Ações
      float relY = (float)winY / winH;
      float relX = (float)(winX - (vp.x + vp.w)) / (winW - (vp.x + vp.w));
      if (relY > 0.65f) return 53; // '5' Ataque
      if (relY > 0.40f) return (relX < 0.5f) ? 49 : 51; // '1' ou '3' Habilidades
      return (relX < 0.5f) ? 55 : 57; // '7' ou '9'
    }
  }

  // 2. Canvas lógico 240x320
  // Botões de sistema superiores
  if (logY >= 4 && logY <= 28) {
    if (logX >= 6 && logX <= 48) return -8; // MENU / L
    if (logX >= 96 && logX <= 144) return 48; // MAP ('0')
    if (logX >= 192 && logX <= 234) return -7; // RSK / R
  }

  // Virtual D-pad (Centro: 44, 264, Raio: 42)
  float ddx = logX - 44.0f;
  float ddy = logY - 264.0f;
  float distDpad = sqrt(ddx * ddx + ddy * ddy);
  if (distDpad < 42.0f && distDpad > 6.0f) {
    if (std::abs(ddx) > std::abs(ddy)) {
      return (ddx < 0) ? -3 : -4; // Left / Right
    } else {
      return (ddy < 0) ? -1 : -2; // Up / Down
    }
  }

  // Botões de Ação (Lado direito)
  // 5 (Ataque / Confirmar): Centro (195, 265), raio 22
  float d5x = logX - 195.0f, d5y = logY - 265.0f;
  if (sqrt(d5x * d5x + d5y * d5y) < 22.0f) return 53;

  // 1 (Habilidade Guardião 1): Centro (155, 245), raio 16
  float d1x = logX - 155.0f, d1y = logY - 245.0f;
  if (sqrt(d1x * d1x + d1y * d1y) < 16.0f) return 49;

  // 3 (Habilidade Guardião 2): Centro (195, 220), raio 16
  float d3x = logX - 195.0f, d3y = logY - 220.0f;
  if (sqrt(d3x * d3x + d3y * d3y) < 16.0f) return 51;

  // 7 (Poção / Item): Centro (155, 290), raio 15
  float d7x = logX - 155.0f, d7y = logY - 290.0f;
  if (sqrt(d7x * d7x + d7y * d7y) < 15.0f) return 55;

  // 9 (Item Rápido): Centro (225, 230), raio 15
  float d9x = logX - 225.0f, d9y = logY - 230.0f;
  if (sqrt(d9x * d9x + d9y * d9y) < 15.0f) return 57;

  // Alternar Poção Anterior (<): (96, 292, 20, 20)
  if (logX >= 94 && logX <= 116 && logY >= 290 && logY <= 314) return -101;
  // Alternar Poção Próxima (>): (124, 292, 20, 20)
  if (logX >= 122 && logX <= 144 && logY >= 290 && logY <= 314) return 35; // '#'

  return 0;
}

static void drawCircleHelper(SDL_Renderer* ren, int cx, int cy, int r, bool filled) {
  if (filled) {
    for (int dy = -r; dy <= r; dy++) {
      int dx = (int)sqrt(r * r - dy * dy);
      SDL_RenderDrawLine(ren, cx - dx, cy + dy, cx + dx, cy + dy);
    }
  } else {
    for (int a = 0; a < 360; a += 15) {
      float r1 = a * 3.14159265f / 180.0f;
      float r2 = (a + 15) * 3.14159265f / 180.0f;
      SDL_RenderDrawLine(ren, cx + (int)(cos(r1) * r), cy + (int)(sin(r1) * r),
                              cx + (int)(cos(r2) * r), cy + (int)(sin(r2) * r));
    }
  }
}

static void drawTouchOverlay() {
  if (!s_renderer) return;

  SDL_SetRenderDrawBlendMode(s_renderer, SDL_BLENDMODE_BLEND);

  // 1. Botões de Sistema Superiores (MENU, MAP, RSK)
  auto drawBtnRect = [&](int x, int y, int w, int h, int key, const char* label) {
    bool held = isTouchKeyHeld(key);
    SDL_Rect r = {x, y, w, h};
    SDL_SetRenderDrawColor(s_renderer, 0, 0, 0, held ? 160 : 70);
    SDL_RenderFillRect(s_renderer, &r);
    SDL_SetRenderDrawColor(s_renderer, 255, 255, 255, held ? 220 : 120);
    SDL_RenderDrawRect(s_renderer, &r);
  };
  drawBtnRect(8, 6, 38, 16, -8, "MENU");
  drawBtnRect(102, 6, 36, 16, 48, "MAP");
  drawBtnRect(194, 6, 38, 16, -7, "RSK");

  // 2. D-pad Virtual (Centro: 44, 264)
  int dcx = 44, dcy = 264;
  SDL_SetRenderDrawColor(s_renderer, 0, 0, 0, 60);
  drawCircleHelper(s_renderer, dcx, dcy, 38, true);
  SDL_SetRenderDrawColor(s_renderer, 255, 255, 255, 100);
  drawCircleHelper(s_renderer, dcx, dcy, 38, false);

  auto drawDirArrow = [&](int dirKey, int ax, int ay, int dx, int dy) {
    bool held = (s_touchHeldDirection == dirKey);
    SDL_SetRenderDrawColor(s_renderer, 255, 255, 255, held ? 255 : 130);
    if (held) {
      drawCircleHelper(s_renderer, ax, ay, 12, true);
      SDL_SetRenderDrawColor(s_renderer, 0, 0, 0, 255);
    }
    SDL_RenderDrawLine(s_renderer, ax, ay, ax + dx, ay + dy);
    SDL_RenderDrawLine(s_renderer, ax, ay, ax - dy, ay + dx);
    SDL_RenderDrawLine(s_renderer, ax, ay, ax + dy, ay - dx);
  };
  drawDirArrow(-1, dcx, dcy - 22, 0, -5); // CIMA
  drawDirArrow(-2, dcx, dcy + 22, 0, 5);  // BAIXO
  drawDirArrow(-3, dcx - 22, dcy, -5, 0); // ESQUERDA
  drawDirArrow(-4, dcx + 22, dcy, 5, 0);  // DIREITA

  // 3. Botões de Ação Redondos
  auto drawRoundBtn = [&](int cx, int cy, int r, int key, const char* label) {
    bool held = isTouchKeyHeld(key);
    SDL_SetRenderDrawColor(s_renderer, 0, 0, 0, held ? 160 : 70);
    drawCircleHelper(s_renderer, cx, cy, r, true);
    SDL_SetRenderDrawColor(s_renderer, 255, 255, 255, held ? 255 : 140);
    drawCircleHelper(s_renderer, cx, cy, r, false);
    if (held) {
      SDL_SetRenderDrawColor(s_renderer, 255, 255, 255, 200);
      drawCircleHelper(s_renderer, cx, cy, r / 2, true);
    }
  };

  drawRoundBtn(195, 265, 20, 53, "5"); // Ataque
  drawRoundBtn(155, 245, 14, 49, "1"); // Habilidade 1
  drawRoundBtn(195, 220, 14, 51, "3"); // Habilidade 2
  drawRoundBtn(155, 290, 13, 55, "7"); // Poção
  drawRoundBtn(225, 230, 13, 57, "9"); // Item

  // 4. Botões de Alternar Poção (< e >)
  drawBtnRect(96, 292, 18, 18, -101, "<");
  drawBtnRect(124, 292, 18, 18, 35, ">");
}

static int getHeldDirection() {
  if (s_touchHeldDirection != 0) return s_touchHeldDirection;
  const Uint8* k = SDL_GetKeyboardState(nullptr);
  if (k[SDL_SCANCODE_W] || k[SDL_SCANCODE_UP]) return -1;
  if (k[SDL_SCANCODE_S] || k[SDL_SCANCODE_DOWN]) return -2;
  if (k[SDL_SCANCODE_A] || k[SDL_SCANCODE_LEFT]) return -3;
  if (k[SDL_SCANCODE_D] || k[SDL_SCANCODE_RIGHT]) return -4;
  if (k[SDL_SCANCODE_KP_2]) return 50;
  if (k[SDL_SCANCODE_KP_8]) return 56;
  if (k[SDL_SCANCODE_KP_4]) return 52;
  if (k[SDL_SCANCODE_KP_6]) return 54;

  return getGamepadHeldDirection();
}

static void cyclePotionPrev(VM& vm) {
  if (g_display && g_display->current) {
    vm.gilLock();
    try {
      Value args[1]; args[0].i = 35; Value ret[2];
      // '#' invocado 3 vezes avança 3 slots, equivalente a recuar 1 slot (4 - 1 = 3)
      for (int rep = 0; rep < 3; ++rep) {
        vm.invokeVirtual(g_display->current, "keyPressed:(I)V", args, 1, ret);
        vm.invokeVirtual(g_display->current, "keyReleased:(I)V", args, 1, ret);
      }
    } catch (...) {}
    vm.gilUnlock();
  }
}

static void cyclePotionNext(VM& vm) {
  if (g_display && g_display->current) {
    vm.gilLock();
    try {
      Value args[1]; args[0].i = 35; Value ret[2];
      vm.invokeVirtual(g_display->current, "keyPressed:(I)V", args, 1, ret);
      vm.invokeVirtual(g_display->current, "keyReleased:(I)V", args, 1, ret);
    } catch (...) {}
    vm.gilUnlock();
  }
}

bool Platform::pollEvents(VM& vm) {
  SDL_Event ev;
  while (SDL_PollEvent(&ev)) {
    if (ev.type == SDL_QUIT) {
      s_quit = true;
      return false;
    }

    // Gerenciamento de conexão quente de Gamepads (Hotplug)
    if (ev.type == SDL_CONTROLLERDEVICEADDED) {
      int idx = ev.cdevice.which;
      if (SDL_IsGameController(idx)) {
        SDL_GameController* pad = SDL_GameControllerOpen(idx);
        if (pad) {
          s_controllers.push_back(pad);
          printf("[Gamepad] Controle conectado: %s (slot %d)\n", SDL_GameControllerName(pad), idx);
          SDL_GameControllerRumble(pad, 0x4000, 0x4000, 150);
        }
      }
    } else if (ev.type == SDL_CONTROLLERDEVICEREMOVED) {
      SDL_JoystickID jid = ev.cdevice.which;
      for (auto it = s_controllers.begin(); it != s_controllers.end(); ) {
        SDL_Joystick* j = SDL_GameControllerGetJoystick(*it);
        if (j && SDL_JoystickInstanceID(j) == jid) {
          printf("[Gamepad] Controle desconectado: %s\n", SDL_GameControllerName(*it));
          SDL_GameControllerClose(*it);
          it = s_controllers.erase(it);
        } else {
          ++it;
        }
      }
    }

    // Botões do Gamepad
    else if (ev.type == SDL_CONTROLLERBUTTONDOWN) {
      int key = mapControllerButton(ev.cbutton.button);
      if (key != 0 && g_display && g_display->current) {
        if (isDirectionKey(key)) {
          if (s_activeDirection != key) {
            s_activeDirection = key;
            s_directionPressTime = SDL_GetTicks();
            s_directionLastRepeatTime = s_directionPressTime;
            vm.gilLock();
            try {
              Value args[1]; args[0].i = key; Value ret[2];
              vm.invokeVirtual(g_display->current, "keyPressed:(I)V", args, 1, ret);
            } catch (...) {}
            vm.gilUnlock();
          }
        } else {
          vm.gilLock();
          try {
            Value args[1]; args[0].i = key; Value ret[2];
            vm.invokeVirtual(g_display->current, "keyPressed:(I)V", args, 1, ret);
          } catch (...) {}
          vm.gilUnlock();
        }
      }
    } else if (ev.type == SDL_CONTROLLERBUTTONUP) {
      int key = mapControllerButton(ev.cbutton.button);
      if (key != 0 && g_display && g_display->current) {
        if (isDirectionKey(key)) {
          int held = getHeldDirection();
          if (held != 0) {
            s_activeDirection = held;
            s_directionPressTime = SDL_GetTicks();
            s_directionLastRepeatTime = s_directionPressTime;
            vm.gilLock();
            try {
              Value args[1]; args[0].i = held; Value ret[2];
              vm.invokeVirtual(g_display->current, "keyPressed:(I)V", args, 1, ret);
            } catch (...) {}
            vm.gilUnlock();
          } else {
            s_activeDirection = 0;
            vm.gilLock();
            try {
              Value args[1]; args[0].i = key; Value ret[2];
              vm.invokeVirtual(g_display->current, "keyReleased:(I)V", args, 1, ret);
            } catch (...) {}
            vm.gilUnlock();
          }
        } else {
          vm.gilLock();
          try {
            Value args[1]; args[0].i = key; Value ret[2];
            vm.invokeVirtual(g_display->current, "keyReleased:(I)V", args, 1, ret);
          } catch (...) {}
          vm.gilUnlock();
        }
      }
    }

    // Eixos Analógicos do Gamepad (Gatilhos L2/R2 e Analógico Esquerdo)
    else if (ev.type == SDL_CONTROLLERAXISMOTION) {
      if (ev.caxis.axis == SDL_CONTROLLER_AXIS_TRIGGERLEFT) {
        bool down = ev.caxis.value > 16000;
        if (down != s_ltHeld) {
          s_ltHeld = down;
          if (down) {
            cyclePotionPrev(vm); // LT -> Alternar Poção / Item para a ESQUERDA (slot anterior)
          }
        }
      } else if (ev.caxis.axis == SDL_CONTROLLER_AXIS_TRIGGERRIGHT) {
        bool down = ev.caxis.value > 16000;
        if (down != s_rtHeld) {
          s_rtHeld = down;
          if (down) {
            cyclePotionNext(vm); // RT -> Alternar Poção / Item para a DIREITA (slot seguinte)
          }
        }
      } else if (ev.caxis.axis == SDL_CONTROLLER_AXIS_LEFTX || ev.caxis.axis == SDL_CONTROLLER_AXIS_LEFTY) {
        int held = getGamepadHeldDirection();
        if (held != s_activeDirection) {
          if (held != 0) {
            s_activeDirection = held;
            s_directionPressTime = SDL_GetTicks();
            s_directionLastRepeatTime = s_directionPressTime;
            if (g_display && g_display->current) {
              vm.gilLock();
              try {
                Value args[1]; args[0].i = held; Value ret[2];
                vm.invokeVirtual(g_display->current, "keyPressed:(I)V", args, 1, ret);
              } catch (...) {}
              vm.gilUnlock();
            }
          } else if (s_activeDirection != 0 && getHeldDirection() == 0) {
            int oldKey = s_activeDirection;
            s_activeDirection = 0;
            if (g_display && g_display->current) {
              vm.gilLock();
              try {
                Value args[1]; args[0].i = oldKey; Value ret[2];
                vm.invokeVirtual(g_display->current, "keyReleased:(I)V", args, 1, ret);
              } catch (...) {}
              vm.gilUnlock();
            }
          }
        }
      }
    }

    // Teclado
    else if (ev.type == SDL_KEYDOWN) {
      int key = mapKey(ev.key.keysym.sym);
      if (key == -101) {
        if (!ev.key.repeat) {
          cyclePotionPrev(vm);
        }
      } else if (key != 0 && g_display && g_display->current) {
        if (isDirectionKey(key)) {
          if (!ev.key.repeat || s_activeDirection != key) {
            s_activeDirection = key;
            s_directionPressTime = SDL_GetTicks();
            s_directionLastRepeatTime = s_directionPressTime;
            vm.gilLock();
            try {
              Value args[1];
              args[0].i = key;
              Value ret[2];
              vm.invokeVirtual(g_display->current, "keyPressed:(I)V", args, 1, ret);
            } catch (...) {}
            vm.gilUnlock();
          }
        } else if (!ev.key.repeat) {
          vm.gilLock();
          try {
            Value args[1];
            args[0].i = key;
            Value ret[2];
            vm.invokeVirtual(g_display->current, "keyPressed:(I)V", args, 1, ret);
          } catch (...) {}
          vm.gilUnlock();
        }
      }
    } else if (ev.type == SDL_KEYUP) {
      int key = mapKey(ev.key.keysym.sym);
      if (key != 0 && g_display && g_display->current) {
        if (isDirectionKey(key)) {
          int held = getHeldDirection();
          if (held != 0) {
            s_activeDirection = held;
            s_directionPressTime = SDL_GetTicks();
            s_directionLastRepeatTime = s_directionPressTime;
            vm.gilLock();
            try {
              Value args[1];
              args[0].i = held;
              Value ret[2];
              vm.invokeVirtual(g_display->current, "keyPressed:(I)V", args, 1, ret);
            } catch (...) {}
            vm.gilUnlock();
          } else {
            s_activeDirection = 0;
            vm.gilLock();
            try {
              Value args[1];
              args[0].i = key;
              Value ret[2];
              vm.invokeVirtual(g_display->current, "keyReleased:(I)V", args, 1, ret);
            } catch (...) {}
            vm.gilUnlock();
          }
        } else {
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

    // Toques na tela (Touchscreen Mobile / Virtual Controller)
    else if (ev.type == SDL_FINGERDOWN) {
      s_touchOverlayEnabled = true;
      int winW = 0, winH = 0;
      SDL_GetWindowSize(s_window, &winW, &winH);
      int winX = (int)(ev.tfinger.x * winW);
      int winY = (int)(ev.tfinger.y * winH);
      float logX = 0, logY = 0;
      SDL_RenderWindowToLogical(s_renderer, winX, winY, &logX, &logY);

      int key = hitTestTouch(logX, logY, winX, winY, winW, winH);
      if (key != 0) {
        s_activeFingers.push_back({ev.tfinger.fingerId, key});
        if (key == -101) {
          cyclePotionPrev(vm);
        } else if (key == 35) {
          cyclePotionNext(vm);
        } else if (isDirectionKey(key)) {
          s_touchHeldDirection = key;
          if (s_activeDirection != key) {
            s_activeDirection = key;
            s_directionPressTime = SDL_GetTicks();
            s_directionLastRepeatTime = s_directionPressTime;
            if (g_display && g_display->current) {
              vm.gilLock();
              try {
                Value args[1]; args[0].i = key; Value ret[2];
                vm.invokeVirtual(g_display->current, "keyPressed:(I)V", args, 1, ret);
              } catch (...) {}
              vm.gilUnlock();
            }
          }
        } else {
          if (g_display && g_display->current) {
            vm.gilLock();
            try {
              Value args[1]; args[0].i = key; Value ret[2];
              vm.invokeVirtual(g_display->current, "keyPressed:(I)V", args, 1, ret);
            } catch (...) {}
            vm.gilUnlock();
          }
        }
      }
    } else if (ev.type == SDL_FINGERMOTION) {
      for (auto& f : s_activeFingers) {
        if (f.id == ev.tfinger.fingerId) {
          int winW = 0, winH = 0;
          SDL_GetWindowSize(s_window, &winW, &winH);
          int winX = (int)(ev.tfinger.x * winW);
          int winY = (int)(ev.tfinger.y * winH);
          float logX = 0, logY = 0;
          SDL_RenderWindowToLogical(s_renderer, winX, winY, &logX, &logY);

          int newKey = hitTestTouch(logX, logY, winX, winY, winW, winH);
          if (newKey != f.key) {
            int oldKey = f.key;
            f.key = newKey;
            if (isDirectionKey(oldKey)) {
              if (isDirectionKey(newKey)) {
                s_touchHeldDirection = newKey;
                s_activeDirection = newKey;
                s_directionPressTime = SDL_GetTicks();
                s_directionLastRepeatTime = s_directionPressTime;
                if (g_display && g_display->current) {
                  vm.gilLock();
                  try {
                    Value args[1]; args[0].i = newKey; Value ret[2];
                    vm.invokeVirtual(g_display->current, "keyPressed:(I)V", args, 1, ret);
                  } catch (...) {}
                  vm.gilUnlock();
                }
              } else {
                s_touchHeldDirection = 0;
                if (g_display && g_display->current) {
                  vm.gilLock();
                  try {
                    Value args[1]; args[0].i = oldKey; Value ret[2];
                    vm.invokeVirtual(g_display->current, "keyReleased:(I)V", args, 1, ret);
                  } catch (...) {}
                  vm.gilUnlock();
                }
              }
            } else if (oldKey != 0 && oldKey != -101 && oldKey != 35) {
              if (g_display && g_display->current) {
                vm.gilLock();
                try {
                  Value args[1]; args[0].i = oldKey; Value ret[2];
                  vm.invokeVirtual(g_display->current, "keyReleased:(I)V", args, 1, ret);
                } catch (...) {}
                vm.gilUnlock();
              }
            }

            if (newKey != 0 && !isDirectionKey(newKey) && newKey != -101 && newKey != 35) {
              if (g_display && g_display->current) {
                vm.gilLock();
                try {
                  Value args[1]; args[0].i = newKey; Value ret[2];
                  vm.invokeVirtual(g_display->current, "keyPressed:(I)V", args, 1, ret);
                } catch (...) {}
                vm.gilUnlock();
              }
            }
          }
          break;
        }
      }
    } else if (ev.type == SDL_FINGERUP) {
      for (auto it = s_activeFingers.begin(); it != s_activeFingers.end(); ) {
        if (it->id == ev.tfinger.fingerId) {
          int key = it->key;
          it = s_activeFingers.erase(it);
          if (isDirectionKey(key)) {
            int otherDir = 0;
            for (const auto& af : s_activeFingers) {
              if (isDirectionKey(af.key)) { otherDir = af.key; break; }
            }
            s_touchHeldDirection = otherDir;
            if (otherDir == 0) {
              s_activeDirection = 0;
              if (g_display && g_display->current) {
                vm.gilLock();
                try {
                  Value args[1]; args[0].i = key; Value ret[2];
                  vm.invokeVirtual(g_display->current, "keyReleased:(I)V", args, 1, ret);
                } catch (...) {}
                vm.gilUnlock();
              }
            }
          } else if (key != 0 && key != -101 && key != 35) {
            if (g_display && g_display->current) {
              vm.gilLock();
              try {
                Value args[1]; args[0].i = key; Value ret[2];
                vm.invokeVirtual(g_display->current, "keyReleased:(I)V", args, 1, ret);
              } catch (...) {}
              vm.gilUnlock();
            }
          }
        } else {
          ++it;
        }
      }
    }
  }

  // Movimentação contínua fluida: enquanto uma direção for mantida pressionada (seja por teclado ou gamepad),
  // alimenta o loop do jogo com repetições suaves no ritmo exato da taxa de passos
  if (s_activeDirection != 0 && g_display && g_display->current) {
    int held = getHeldDirection();
    if (held == 0) {
      int oldKey = s_activeDirection;
      s_activeDirection = 0;
      vm.gilLock();
      try {
        Value args[1];
        args[0].i = oldKey;
        Value ret[2];
        vm.invokeVirtual(g_display->current, "keyReleased:(I)V", args, 1, ret);
      } catch (...) {}
      vm.gilUnlock();
    } else {
      if (held != s_activeDirection) {
        s_activeDirection = held;
        s_directionPressTime = SDL_GetTicks();
        s_directionLastRepeatTime = s_directionPressTime;
        vm.gilLock();
        try {
          Value args[1];
          args[0].i = held;
          Value ret[2];
          vm.invokeVirtual(g_display->current, "keyPressed:(I)V", args, 1, ret);
        } catch (...) {}
        vm.gilUnlock();
      } else {
        uint32_t now = SDL_GetTicks();
        const uint32_t INITIAL_DELAY_MS = 160;
        const uint32_t REPEAT_INTERVAL_MS = 40;
        if (now - s_directionPressTime >= INITIAL_DELAY_MS && now - s_directionLastRepeatTime >= REPEAT_INTERVAL_MS) {
          s_directionLastRepeatTime = now;
          vm.gilLock();
          try {
            Value args[1];
            args[0].i = s_activeDirection;
            Value ret[2];
            vm.invokeVirtual(g_display->current, "keyPressed:(I)V", args, 1, ret);
          } catch (...) {}
          vm.gilUnlock();
        }
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
  if (s_touchOverlayEnabled) {
    drawTouchOverlay();
  }
  SDL_RenderPresent(s_renderer);
}

void Platform::shutdown() {
  for (auto* pad : s_controllers) {
    if (pad) SDL_GameControllerClose(pad);
  }
  s_controllers.clear();
  if (s_screenTexture) { SDL_DestroyTexture(s_screenTexture); s_screenTexture = nullptr; }
  if (s_renderer) { SDL_DestroyRenderer(s_renderer); s_renderer = nullptr; }
  if (s_window) { SDL_DestroyWindow(s_window); s_window = nullptr; }
  Mix_CloseAudio();
  SDL_Quit();
}

void Platform::rumble(float strength, int durationMs) {
  uint16_t low = (uint16_t)(std::min(1.0f, std::max(0.0f, strength)) * 0xFFFF);
  uint16_t high = (uint16_t)(std::min(1.0f, std::max(0.0f, strength)) * 0x7FFF);
  for (auto* pad : s_controllers) {
    if (pad) {
      SDL_GameControllerRumble(pad, low, high, durationMs);
    }
  }
}

bool Platform::shouldQuit() {
  return s_quit;
}

std::vector<uint8_t> Platform::readAsset(const std::string& path) {
  if (path.empty()) return {};

  // 1. Tenta o caminho exato
  SDL_RWops* rw = SDL_RWFromFile(path.c_str(), "rb");
  
  // 2. Se falhar e começar com "./", tenta sem
  if (!rw && path.rfind("./", 0) == 0) {
    rw = SDL_RWFromFile(path.substr(2).c_str(), "rb");
  }
  
  // 3. Tenta prefixando "assets/"
  if (!rw) {
    std::string alt = "assets/" + path;
    rw = SDL_RWFromFile(alt.c_str(), "rb");
  }

  // 4. Tenta prefixando "reference/extracted/"
  if (!rw) {
    std::string alt = "reference/extracted/" + path;
    rw = SDL_RWFromFile(alt.c_str(), "rb");
  }

  // 5. Tenta em ../assets/ ou ../reference/extracted/
  if (!rw) {
    std::string alt = "../" + path;
    rw = SDL_RWFromFile(alt.c_str(), "rb");
  }

  if (!rw) return {};

  Sint64 size = SDL_RWsize(rw);
  if (size <= 0) {
    SDL_RWclose(rw);
    return {};
  }

  std::vector<uint8_t> data(static_cast<size_t>(size));
  size_t readBytes = SDL_RWread(rw, data.data(), 1, static_cast<size_t>(size));
  SDL_RWclose(rw);

  if (readBytes != static_cast<size_t>(size)) {
    data.resize(readBytes);
  }
  return data;
}

std::string Platform::getStorageDir() {
#ifdef __ANDROID__
  const char* path = SDL_AndroidGetInternalStoragePath();
  if (path && path[0] != '\0') return std::string(path);
  return ".";
#else
  return ".";
#endif
}

} // namespace hl
