// platform_sdl.cpp — backend SDL2 com renderização 240x320 escalada, áudio SDL_mixer e mapeamento de teclado
#include "platform.h"
#include "midi_synth.h"
#include "midp/midp.h"
#include "vm/vm.h"

#if defined(__has_include)
  #if __has_include(<SDL2/SDL.h>)
    #include <SDL2/SDL.h>
  #else
    #include <SDL.h>
  #endif
  #if __has_include(<SDL2/SDL_mixer.h>)
    #include <SDL2/SDL_mixer.h>
  #elif __has_include(<SDL_mixer.h>)
    #include <SDL_mixer.h>
  #endif
#else
  #include <SDL.h>
  #include <SDL_mixer.h>
#endif
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

#ifdef __SWITCH__
#if defined(__has_include)
  #if __has_include(<switch.h>)
    #include <switch.h>
  #endif
#else
  #include <switch.h>
#endif
#include <sys/stat.h>
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
#ifdef __SWITCH__
    // Switch: o SDL usa layout posicional Xbox (A = botão de baixo = "B" do Switch).
    // Trocamos para seguir os rótulos físicos do Switch: A (direita) confirma, B (baixo) cancela.
    case SDL_CONTROLLER_BUTTON_B: return 53; // Switch A -> Atacar / Interagir / Confirmar ('5')
    case SDL_CONTROLLER_BUTTON_A: return -7; // Switch B -> Status / Cancelar (RSK)
    case SDL_CONTROLLER_BUTTON_Y: return 49; // Switch X (topo) -> Ataque 1 do Guardião ('1')
    case SDL_CONTROLLER_BUTTON_X: return 51; // Switch Y (esquerda) -> Ataque 2 do Guardião ('3')
#else
    case SDL_CONTROLLER_BUTTON_A: return 53; // A (Xbox) / X (PS) -> Atacar com Arma / Interagir / Confirmar ('5')
    case SDL_CONTROLLER_BUTTON_B: return -7; // B (Xbox) / O (PS) -> Status / Cancelar (RSK)
    case SDL_CONTROLLER_BUTTON_X: return 49; // X (Xbox) / Quad (PS) -> Ataque 1 do Guardião ('1')
    case SDL_CONTROLLER_BUTTON_Y: return 51; // Y (Xbox) / Tri (PS) -> Ataque 2 do Guardião ('3')
#endif

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

// =============================================================
// Passo 7: Molduras Temáticas (Bezels), Aspect Ratio e FPS Limiter
// =============================================================
enum BezelMode {
  BEZEL_SOLTIA = 0,
  BEZEL_SLATE = 1,
  BEZEL_BLACK = 2,
  BEZEL_COUNT = 3
};

enum AspectMode {
  ASPECT_ORIGINAL = 0,   // 3:4 Original com Molduras de Soltia
  ASPECT_WIDESCREEN = 1, // 16:9 True Widescreen (Expansão Real de Viewport)
  ASPECT_COUNT = 2
};

static BezelMode s_bezelMode = BEZEL_SOLTIA;
static int s_targetFps = 15; // 15 (padrão nostalgia J2ME) ou 30 (turbo fluido)
static AspectMode s_aspectMode = ASPECT_ORIGINAL;
static bool s_isFullscreen = false;
static int s_savedWinW = 0;
static int s_savedWinH = 0;
static uint64_t s_frameStartCounter = 0;

static void setVmStaticInt(VM& vm, const std::string& className, const std::string& fieldName, int32_t val) {
  auto it = vm.classes.find(className);
  if (it == vm.classes.end()) return;
  ClassInfo* cls = it->second;
  FieldInfo* targetField = nullptr;

  std::vector<std::string> candidateNames = { fieldName };
  if (fieldName.rfind("var_int_", 0) == 0) {
    candidateNames.push_back(fieldName.substr(8)); // "var_int_a" -> "a"
  } else if (fieldName.rfind("var_", 0) == 0) {
    candidateNames.push_back(fieldName.substr(4));
  }

  for (const auto& name : candidateNames) {
    auto fit = cls->fieldMap.find(name + ":I");
    if (fit != cls->fieldMap.end() && fit->second && fit->second->isStatic) {
      targetField = fit->second;
      break;
    }
    fit = cls->fieldMap.find(name);
    if (fit != cls->fieldMap.end() && fit->second && fit->second->isStatic) {
      targetField = fit->second;
      break;
    }
    for (auto& pair : cls->fieldMap) {
      if (pair.second && pair.second->name == name && pair.second->desc == "I" && pair.second->isStatic) {
        targetField = pair.second;
        break;
      }
    }
    if (targetField) break;
  }

  if (targetField && targetField->isStatic && targetField->index >= 0 && targetField->index < (int)cls->statics.size()) {
    int32_t oldVal = cls->statics[targetField->index].i;
    cls->statics[targetField->index].i = val;
    printf("[Viewport] %s.%s:I (%d -> %d)\n", className.c_str(), targetField->name.c_str(), oldVal, val);
  }
}

static int32_t getVmStaticInt(VM& vm, const std::string& className, const std::string& fieldName, int32_t defVal = 0) {
  auto it = vm.classes.find(className);
  if (it == vm.classes.end()) return defVal;
  ClassInfo* cls = it->second;
  FieldInfo* targetField = nullptr;

  std::vector<std::string> candidateNames = { fieldName };
  if (fieldName.rfind("var_int_", 0) == 0) candidateNames.push_back(fieldName.substr(8));
  else if (fieldName.rfind("var_", 0) == 0) candidateNames.push_back(fieldName.substr(4));

  for (const auto& name : candidateNames) {
    auto fit = cls->fieldMap.find(name + ":I");
    if (fit != cls->fieldMap.end() && fit->second && fit->second->isStatic) {
      targetField = fit->second;
      break;
    }
    for (auto& pair : cls->fieldMap) {
      if (pair.second && pair.second->name == name && pair.second->desc == "I" && pair.second->isStatic) {
        targetField = pair.second;
        break;
      }
    }
    if (targetField) break;
  }
  if (targetField && targetField->isStatic && targetField->index >= 0 && targetField->index < (int)cls->statics.size()) {
    return cls->statics[targetField->index].i;
  }
  return defVal;
}

static void setVmInstanceBool(Object* obj, const std::string& fieldName, bool val) {
  if (!obj || obj->kind != K_INST || !obj->cls) return;
  Instance* inst = static_cast<Instance*>(obj);
  ClassInfo* cls = obj->cls;
  FieldInfo* targetField = nullptr;

  std::vector<std::string> candidateNames = { fieldName };
  if (fieldName.rfind("var_boolean_", 0) == 0) {
    candidateNames.push_back(fieldName.substr(12)); // "var_boolean_e" -> "e"
  } else if (fieldName.rfind("var_", 0) == 0) {
    candidateNames.push_back(fieldName.substr(4));
  }

  for (const auto& name : candidateNames) {
    auto fit = cls->fieldMap.find(name + ":Z");
    if (fit != cls->fieldMap.end() && fit->second && !fit->second->isStatic) {
      targetField = fit->second;
      break;
    }
    fit = cls->fieldMap.find(name);
    if (fit != cls->fieldMap.end() && fit->second && !fit->second->isStatic) {
      targetField = fit->second;
      break;
    }
    for (auto& pair : cls->fieldMap) {
      if (pair.second && pair.second->name == name && pair.second->desc == "Z" && !pair.second->isStatic) {
        targetField = pair.second;
        break;
      }
    }
    if (targetField) break;
  }

  if (targetField && !targetField->isStatic && targetField->index >= 0 && targetField->index < (int)inst->f.size()) {
    inst->f[targetField->index].i = val ? 1 : 0;
  }
}

static void updateJavaViewportVariables(VM& vm) {
  // 1. Classe r (base de Canvas, compartilhada por todo o motor J2ME)
  setVmStaticInt(vm, "r", "g", g_screenWidth);
  setVmStaticInt(vm, "r", "i", g_screenWidth / 2);
  setVmStaticInt(vm, "r", "h", g_screenHeight);
  setVmStaticInt(vm, "r", "j", g_screenHeight / 2);

  // 2. Classe as (Canvas principal do jogo e HUD)
  // No bytecode original: a = var_int_a, b = b, c = c, d = var_int_d, n = n, o = o, p = p
  int32_t oldAsc = getVmStaticInt(vm, "as", "c", g_screenWidth / 2 - 8);
  int32_t newAsc = g_screenWidth / 2 - 8;
  int32_t deltaC = newAsc - oldAsc;

  setVmStaticInt(vm, "as", "a", g_screenWidth);
  setVmStaticInt(vm, "as", "var_int_a", g_screenWidth);
  setVmStaticInt(vm, "as", "b", g_screenHeight - 21);
  setVmStaticInt(vm, "as", "c", newAsc);
  setVmStaticInt(vm, "as", "d", (g_screenHeight - 21) / 2);
  setVmStaticInt(vm, "as", "var_int_d", (g_screenHeight - 21) / 2);
  setVmStaticInt(vm, "as", "n", (g_screenWidth - 74) / 6);
  setVmStaticInt(vm, "as", "o", g_screenWidth - 67);
  setVmStaticInt(vm, "as", "p", g_screenWidth - 6);

  // 3. Força redesenho completo de HUD no Canvas ativo
  if (g_display && g_display->current) {
    setVmInstanceBool(g_display->current, "e", true); // var_boolean_e
    setVmInstanceBool(g_display->current, "var_boolean_e", true);
    setVmInstanceBool(g_display->current, "f", true);
    setVmInstanceBool(g_display->current, "g", true);
    setVmInstanceBool(g_display->current, "h", true);
  }

  // 4. Se estiver em jogo ativo, desloca a câmera pelo delta do centro da tela sem invocar bytecode
  if (deltaC != 0) {
    int32_t camX = getVmStaticInt(vm, "n", "a");
    int32_t prevCamX = getVmStaticInt(vm, "n", "c");
    setVmStaticInt(vm, "n", "a", camX + deltaC);
    setVmStaticInt(vm, "n", "c", prevCamX + deltaC);
  }

  // 5. Redefine o clip do Graphics principal
  if (g_screenGraphics) {
    g_screenGraphics->resetClip();
  }
}

void Platform::updateViewport(VM& vm) {
  updateJavaViewportVariables(vm);
}

static SDL_Texture* s_texBezelSoltia = nullptr;
static SDL_Texture* s_texBezelSlate = nullptr;
static SDL_Texture* s_texFontOsd = nullptr;

static std::string s_osdMessage = "";
static uint32_t s_osdExpireTime = 0;

static void loadSettings();

bool Platform::init(int scale) {
#ifdef __SWITCH__
  Result rc = romfsInit();
  if (R_FAILED(rc)) {
    fprintf(stderr, "[RomFS] Falha ao inicializar romfsInit: 0x%x\n", rc);
  } else {
    printf("[RomFS] RomFS inicializado com sucesso!\n");
  }
#endif
  if (SDL_Init(SDL_INIT_VIDEO | SDL_INIT_AUDIO | SDL_INIT_EVENTS | SDL_INIT_GAMECONTROLLER) < 0) {
    fprintf(stderr, "Aviso: Falha ao inicializar SDL completo: %s. Tentando apenas video e eventos...\n", SDL_GetError());
    if (SDL_Init(SDL_INIT_VIDEO | SDL_INIT_EVENTS) < 0) {
      fprintf(stderr, "Erro ao inicializar SDL video: %s\n", SDL_GetError());
      return false;
    }
  }

  // Inicializa áudio
  if (Mix_OpenAudio(44100, MIX_DEFAULT_FORMAT, 2, 2048) < 0) {
    fprintf(stderr, "Aviso: Mixer audio não inicializou: %s\n", Mix_GetError());
  } else {
    Mix_AllocateChannels(16);
    MidiSynth::init();
  }

#ifdef __SWITCH__
  s_window = SDL_CreateWindow(
      "Heroes Lore: Wind of Soltia",
      0, 0,
      1280, 720,
      0);
  if (!s_window) {
    s_window = SDL_CreateWindow(
        "Heroes Lore: Wind of Soltia",
        SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED,
        1280, 720,
        SDL_WINDOW_FULLSCREEN);
  }
#else
  loadSettings();
  int winW = (s_savedWinW >= 240) ? s_savedWinW : 960;
  int winH = (s_savedWinH >= 320) ? s_savedWinH : 720;

  s_window = SDL_CreateWindow(
      "Heroes Lore: Wind of Soltia (Native Recomp)",
      SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED,
      winW, winH,
      SDL_WINDOW_SHOWN | SDL_WINDOW_RESIZABLE);
#endif
  if (!s_window) {
    fprintf(stderr, "Erro ao criar janela: %s\n", SDL_GetError());
    return false;
  }

  // Configura hints antes de criar renderizador e texturas
  SDL_SetHint(SDL_HINT_RENDER_SCALE_QUALITY, "0"); // Pixel-perfect nearest neighbor

#ifdef _WIN32
  // Prioriza direct3d11 no Windows para estabilidade moderna de GPU
  SDL_SetHint(SDL_HINT_RENDER_DRIVER, "direct3d11");
#endif
  s_renderer = SDL_CreateRenderer(
      s_window, -1,
      SDL_RENDERER_ACCELERATED | SDL_RENDERER_PRESENTVSYNC);
  if (!s_renderer) {
#ifndef __SWITCH__
    SDL_SetHint(SDL_HINT_RENDER_DRIVER, "opengl");
#else
    SDL_SetHint(SDL_HINT_RENDER_DRIVER, "opengles2");
#endif
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

  // Textura streaming dinâmica com resolução nativa (240x320 clássico ou 568x320 widescreen)
  g_screenWidth = (s_aspectMode == ASPECT_WIDESCREEN) ? SCREEN_W_WIDESCREEN : SCREEN_W_ORIGINAL;
  g_screenHeight = SCREEN_HEIGHT;

  s_screenTexture = SDL_CreateTexture(
      s_renderer,
      SDL_PIXELFORMAT_ARGB8888,
      SDL_TEXTUREACCESS_STREAMING,
      g_screenWidth, g_screenHeight);
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

  // Carrega preferências do usuário (Bezel, FPS, Aspect, Fullscreen)
  loadSettings();
#ifndef __SWITCH__
  if (s_isFullscreen) {
    SDL_SetWindowFullscreen(s_window, SDL_WINDOW_FULLSCREEN_DESKTOP);
  }
#endif
  s_frameStartCounter = SDL_GetPerformanceCounter();
  Platform::showOsdMessage("Heroes Lore [F5: Moldura | F6: FPS | F7: Widescreen | F11: Tela Cheia]");

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

// --- Texturas e Layout do Gamepad Virtual (Console Handheld UI) ---
static SDL_Texture* s_texDpadBase = nullptr;
static SDL_Texture* s_texDpadArrowActive = nullptr;
static SDL_Texture* s_texBtn5 = nullptr;
static SDL_Texture* s_texBtn1 = nullptr;
static SDL_Texture* s_texBtn3 = nullptr;
static SDL_Texture* s_texBtn7 = nullptr;
static SDL_Texture* s_texBtn9 = nullptr;
static SDL_Texture* s_texBtnMenu = nullptr;
static SDL_Texture* s_texBtnMap = nullptr;
static SDL_Texture* s_texBtnRsk = nullptr;
static SDL_Texture* s_texBtnPrev = nullptr;
static SDL_Texture* s_texBtnNext = nullptr;
static SDL_Texture* s_texBtnEyeOpen = nullptr;
static SDL_Texture* s_texBtnEyeClosed = nullptr;
static SDL_Texture* s_texBtnRotate = nullptr;
static SDL_Texture* s_texBtnGlow = nullptr;
static bool s_gamepadTexturesLoaded = false;

static const int KEY_TOGGLE_TOUCH_UI = -999;
static const int KEY_TOGGLE_ORIENTATION = -998;

// Orientação de tela no Switch:
// 0 = Paisagem horizontal normal (1280x720)
// 1 = Retrato vertical (TATE 90° CW, 720x1280)
// 2 = Retrato vertical invertido (TATE 270° CCW / Flip Grip, 720x1280)
static int s_screenRotation = 0;
static SDL_Texture* s_rotateTarget = nullptr;

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

static SDL_Texture* loadRgbaTexture(const char* name) {
  auto bytes = Platform::readAsset(std::string("ui/") + name + ".rgba");
  if (bytes.size() < 8) return nullptr;
  uint32_t w = *(const uint32_t*)(bytes.data());
  uint32_t h = *(const uint32_t*)(bytes.data() + 4);
  if (bytes.size() < 8 + (size_t)w * h * 4) return nullptr;

  SDL_Surface* surf = SDL_CreateRGBSurfaceWithFormatFrom(
      (void*)(bytes.data() + 8), w, h, 32, w * 4, SDL_PIXELFORMAT_RGBA32);
  if (!surf) return nullptr;

  SDL_Texture* tex = SDL_CreateTextureFromSurface(s_renderer, surf);
  SDL_FreeSurface(surf);
  if (tex) {
    SDL_SetTextureBlendMode(tex, SDL_BLENDMODE_BLEND);
  }
  return tex;
}

static void loadSettings() {
  std::string path = Platform::getStorageDir() + "/hl_settings.ini";
  FILE* f = fopen(path.c_str(), "r");
  if (!f) return;
  char line[256];
  while (fgets(line, sizeof(line), f)) {
    int val = 0;
    if (sscanf(line, "bezel_mode=%d", &val) == 1) {
      if (val >= 0 && val < BEZEL_COUNT) s_bezelMode = static_cast<BezelMode>(val);
    } else if (sscanf(line, "target_fps=%d", &val) == 1) {
      if (val == 15 || val == 30) s_targetFps = val;
      else s_targetFps = 15;
    } else if (sscanf(line, "aspect_mode=%d", &val) == 1) {
      if (val >= 0 && val < ASPECT_COUNT) s_aspectMode = static_cast<AspectMode>(val);
    } else if (sscanf(line, "fullscreen=%d", &val) == 1) {
      s_isFullscreen = (val != 0);
    } else if (sscanf(line, "window_w=%d", &val) == 1) {
      if (val >= 240) s_savedWinW = val;
    } else if (sscanf(line, "window_h=%d", &val) == 1) {
      if (val >= 320) s_savedWinH = val;
    }
  }
  fclose(f);
}

static void saveSettings() {
  std::string path = Platform::getStorageDir() + "/hl_settings.ini";
  FILE* f = fopen(path.c_str(), "w");
  if (!f) return;
  int curW = s_savedWinW, curH = s_savedWinH;
  if (s_window && !s_isFullscreen) {
    int w = 0, h = 0;
    SDL_GetWindowSize(s_window, &w, &h);
    if (w >= 240 && h >= 320) {
      curW = w;
      curH = h;
    }
  }
  fprintf(f, "[Video]\n");
  fprintf(f, "bezel_mode=%d\n", static_cast<int>(s_bezelMode));
  fprintf(f, "target_fps=%d\n", s_targetFps);
  fprintf(f, "aspect_mode=%d\n", static_cast<int>(s_aspectMode));
  fprintf(f, "fullscreen=%d\n", s_isFullscreen ? 1 : 0);
  if (curW > 0 && curH > 0) {
    fprintf(f, "window_w=%d\n", curW);
    fprintf(f, "window_h=%d\n", curH);
  }
  fclose(f);
}

static void loadBezelTextures() {
  if (!s_renderer) return;
  if (!s_texFontOsd) {
    s_texFontOsd = loadRgbaTexture("font_osd");
  }
  if (s_bezelMode == BEZEL_SOLTIA && !s_texBezelSoltia) {
    s_texBezelSoltia = loadRgbaTexture("bezel_soltia");
  } else if (s_bezelMode == BEZEL_SLATE && !s_texBezelSlate) {
    s_texBezelSlate = loadRgbaTexture("bezel_slate");
  }
}

static void drawOsd(int winW, int winH) {
  if (s_osdMessage.empty() || SDL_GetTicks() >= s_osdExpireTime || !s_renderer) return;

  uint32_t now = SDL_GetTicks();
  uint32_t timeLeft = s_osdExpireTime - now;
  Uint8 alpha = 255;
  if (timeLeft < 500) {
    alpha = static_cast<Uint8>((timeLeft * 255) / 500);
  }

  float fontScale = (winH >= 720) ? 0.60f : 0.45f;
  int charW = static_cast<int>(22.0f * fontScale);
  int charH = static_cast<int>(36.0f * fontScale);
  int textW = (int)s_osdMessage.length() * charW;
  int padX = 18, padY = 8;
  int bannerW = textW + padX * 2;
  int bannerH = charH + padY * 2;
  int bannerX = (winW - bannerW) / 2;
  int bannerY = (winH >= 720) ? 28 : 14;

  SDL_SetRenderDrawBlendMode(s_renderer, SDL_BLENDMODE_BLEND);

  // Sombra suave do banner
  SDL_Rect shadowRect = { bannerX + 4, bannerY + 4, bannerW, bannerH };
  SDL_SetRenderDrawColor(s_renderer, 0, 0, 0, (Uint8)((alpha * 160) / 255));
  SDL_RenderFillRect(s_renderer, &shadowRect);

  // Fundo escuro do banner (Frosted Dark Glass)
  SDL_Rect bgRect = { bannerX, bannerY, bannerW, bannerH };
  SDL_SetRenderDrawColor(s_renderer, 14, 18, 26, (Uint8)((alpha * 235) / 255));
  SDL_RenderFillRect(s_renderer, &bgRect);

  // Borda brilhante em cyan neon
  SDL_SetRenderDrawColor(s_renderer, 0, 210, 255, (Uint8)((alpha * 220) / 255));
  SDL_RenderDrawRect(s_renderer, &bgRect);

  // Texto centralizado
  if (s_texFontOsd) {
    SDL_SetTextureAlphaMod(s_texFontOsd, alpha);
    int startX = bannerX + padX;
    int startY = bannerY + padY;
    for (size_t i = 0; i < s_osdMessage.length(); i++) {
      char c = s_osdMessage[i];
      if (c < 32 || c > 126) c = ' ';
      int idx = c - 32;
      int col = idx % 16;
      int row = idx / 16;
      SDL_Rect src = { col * 22, row * 36, 22, 36 };
      SDL_Rect dst = { startX + (int)i * charW, startY, charW, charH };
      SDL_RenderCopy(s_renderer, s_texFontOsd, &src, &dst);
    }
  }
}

static void loadGamepadTextures() {
  if (s_gamepadTexturesLoaded || !s_renderer) return;
  s_texDpadBase = loadRgbaTexture("dpad_base");
  s_texDpadArrowActive = loadRgbaTexture("dpad_arrow_active");
  s_texBtn5 = loadRgbaTexture("btn_5");
  s_texBtn1 = loadRgbaTexture("btn_1");
  s_texBtn3 = loadRgbaTexture("btn_3");
  s_texBtn7 = loadRgbaTexture("btn_7");
  s_texBtn9 = loadRgbaTexture("btn_9");
  s_texBtnMenu = loadRgbaTexture("btn_menu");
  s_texBtnMap = loadRgbaTexture("btn_map");
  s_texBtnRsk = loadRgbaTexture("btn_rsk");
  s_texBtnPrev = loadRgbaTexture("btn_prev");
  s_texBtnNext = loadRgbaTexture("btn_next");
  s_texBtnEyeOpen = loadRgbaTexture("btn_eye_open");
  s_texBtnEyeClosed = loadRgbaTexture("btn_eye_closed");
  s_texBtnRotate = loadRgbaTexture("btn_rotate");
  s_texBtnGlow = loadRgbaTexture("btn_glow");
  loadBezelTextures();
  s_gamepadTexturesLoaded = true;
}

struct TouchButtonDef {
  int key;
  int cx, cy;
  int r;
  int w, h;
  SDL_Texture* tex;
};

struct GamepadLayout {
  SDL_Rect gameRect;
  SDL_Rect controllerBgRect;
  bool isPortrait;
  int dpadX, dpadY, dpadR;
  std::vector<TouchButtonDef> buttons;
};

static GamepadLayout calculateLayout(int winW, int winH) {
  GamepadLayout layout;
  layout.isPortrait = (winH > winW);
  layout.dpadR = 0;

  if (layout.isPortrait) {
    // Modo Retrato (Smartphone em pé)
    // O jogo ocupa a tela no maior tamanho possível mantendo a proporção exata 240x320
    float scaleW = (float)winW / 240.0f;
    float scaleH = (float)winH / 320.0f;
    float scale = std::min(scaleW, scaleH);
    int gameW = (int)(240.0f * scale);
    int gameH = (int)(320.0f * scale);
    int gameX = (winW - gameW) / 2;
    int gameY = (winH - gameH) / 2;
    layout.gameRect = { gameX, gameY, gameW, gameH };
    layout.controllerBgRect = { 0, 0, 0, 0 }; // Sem fundo opaco, overlay translúcido sobre o jogo!

    // Botão de Ocultar/Reexibir Controles Virtuais (Olho discreto no canto inferior esquerdo)
    int rToggle = (int)(winW * 0.055f);
    int toggleX = (int)(winW * 0.09f);
    int toggleY = winH - (int)(winW * 0.09f);
    layout.buttons.push_back({ KEY_TOGGLE_TOUCH_UI, toggleX, toggleY, rToggle, rToggle * 2, rToggle * 2,
                               s_touchOverlayEnabled ? s_texBtnEyeOpen : s_texBtnEyeClosed });

#ifdef __SWITCH__
    // Botão de Alternar Orientação (ao lado do olho)
    int rotX = toggleX + (int)(rToggle * 2.3f);
    int rotY = toggleY;
    layout.buttons.push_back({ KEY_TOGGLE_ORIENTATION, rotX, rotY, rToggle, rToggle * 2, rToggle * 2, s_texBtnRotate });
#endif

    if (s_touchOverlayEnabled) {
      // D-Pad e Cluster de Ação subidos para winW * 0.42f (ergonomia perfeita e abre espaço inferior limpo)
      layout.dpadX = (int)(winW * 0.22f);
      layout.dpadY = winH - (int)(winW * 0.42f);
      layout.dpadR = (int)(winW * 0.175f);

      // Disposição original clássica ergonômica (5 no centro, 1 e 7 na coluna esquerda, 3 no topo, 9 no topo-direito):
      int actX = (int)(winW * 0.74f);
      int actY = winH - (int)(winW * 0.42f);
      int r5 = (int)(winW * 0.11f);
      int rSub = (int)(r5 * 0.74f);

      // 5 - Ataque (Centro, grande)
      layout.buttons.push_back({ 53, actX, actY, r5, r5 * 2, r5 * 2, s_texBtn5 });
      // 1 - Skill 1 (Esquerda-cima)
      layout.buttons.push_back({ 49, actX - (int)(r5 * 1.95f), actY - (int)(r5 * 0.95f), rSub, rSub * 2, rSub * 2, s_texBtn1 });
      // 3 - Skill 2 (Topo)
      layout.buttons.push_back({ 51, actX - (int)(r5 * 0.20f), actY - (int)(r5 * 2.10f), rSub, rSub * 2, rSub * 2, s_texBtn3 });
      // 7 - Poção (Esquerda-baixo, abaixo de 1)
      layout.buttons.push_back({ 55, actX - (int)(r5 * 1.85f), actY + (int)(r5 * 1.15f), rSub, rSub * 2, rSub * 2, s_texBtn7 });
      // 9 - Item (Direita-cima)
      layout.buttons.push_back({ 57, actX + (int)(r5 * 1.35f), actY - (int)(r5 * 1.65f), rSub, rSub * 2, rSub * 2, s_texBtn9 });

      // Barra de Sistema no topo (MENU, MAPA, R) com proporção 2.4:1 perfeita e abaixo da status bar
      int pillW = (int)(winW * 0.25f);
      int pillH = (int)(pillW * (100.0f / 240.0f));
      int topY = std::max((int)(winH * 0.080f), pillH / 2 + 36);

      layout.buttons.push_back({ -8, (int)(winW * 0.17f), topY, 0, pillW, pillH, s_texBtnMenu });
      layout.buttons.push_back({ 48, (int)(winW * 0.50f), topY, 0, pillW, pillH, s_texBtnMap });
      layout.buttons.push_back({ -7, (int)(winW * 0.83f), topY, 0, pillW, pillH, s_texBtnRsk });

      // Alternância de Poção (◀ e ▶) perfeitamente centralizadas no eixo horizontal da tela
      int rArrow = (int)(winW * 0.060f);
      int arrowSpacing = (int)(rArrow * 1.25f);
      int potY = winH - (int)(winW * 0.095f);
      layout.buttons.push_back({ -101, winW / 2 - arrowSpacing, potY, rArrow, rArrow * 2, rArrow * 2, s_texBtnPrev });
      layout.buttons.push_back({ 35, winW / 2 + arrowSpacing, potY, rArrow, rArrow * 2, rArrow * 2, s_texBtnNext });
    }

  } else {
    // Modo Paisagem (Landscape)
    int gameH = winH;
    int gameW = (int)(gameH * (240.0f / 320.0f));
    int gameX = (winW - gameW) / 2;
    int gameY = 0;
    layout.gameRect = { gameX, gameY, gameW, gameH };
    layout.controllerBgRect = { 0, 0, 0, 0 };

    int leftW = gameX;
    int rightX = gameX + gameW;
    int rightW = winW - rightX;

    // Botão de Ocultar/Reexibir Controles Virtuais (Canto inferior esquerdo da coluna esquerda)
    int rToggleLand = (int)(winH * 0.065f);
    int toggleX = (int)(leftW * 0.18f);
    int toggleY = winH - (int)(winH * 0.12f);
    layout.buttons.push_back({ KEY_TOGGLE_TOUCH_UI, toggleX, toggleY, rToggleLand, rToggleLand * 2, rToggleLand * 2,
                               s_touchOverlayEnabled ? s_texBtnEyeOpen : s_texBtnEyeClosed });

#ifdef __SWITCH__
    // Botão de Alternar Orientação (ao lado do olho na coluna esquerda)
    int rotX = toggleX + (int)(rToggleLand * 2.3f);
    int rotY = toggleY;
    layout.buttons.push_back({ KEY_TOGGLE_ORIENTATION, rotX, rotY, rToggleLand, rToggleLand * 2, rToggleLand * 2, s_texBtnRotate });
#endif

    if (s_touchOverlayEnabled) {
      // D-Pad na coluna esquerda (grande e confortável)
      layout.dpadX = leftW / 2;
      layout.dpadY = (int)(winH * 0.68f);
      layout.dpadR = std::min((int)(leftW * 0.35f), (int)(winH * 0.25f));

      // MENU proporcional e grande no topo da coluna esquerda
      int menuW = std::min(240, (int)(leftW * 0.48f));
      int menuH = (int)(menuW * (100.0f / 240.0f));
      layout.buttons.push_back({ -8, leftW / 2, (int)(winH * 0.14f), 0, menuW, menuH, s_texBtnMenu });

      // Setas circulares para poções (◀ e ▶) na coluna ESQUERDA (entre MENU e D-Pad, super ergonômico)
      int rArrowLand = (int)(winH * 0.075f);
      int arrowSpacingLand = (int)(rArrowLand * 1.35f);
      layout.buttons.push_back({ -101, leftW / 2 - arrowSpacingLand, (int)(winH * 0.33f), rArrowLand, rArrowLand * 2, rArrowLand * 2, s_texBtnPrev });
      layout.buttons.push_back({ 35, leftW / 2 + arrowSpacingLand, (int)(winH * 0.33f), rArrowLand, rArrowLand * 2, rArrowLand * 2, s_texBtnNext });

      // MAPA e R no topo da coluna direita (grandes e legíveis)
      int topPillW = std::min(200, (int)(rightW * 0.38f));
      int topPillH = (int)(topPillW * (100.0f / 240.0f));
      layout.buttons.push_back({ 48, rightX + (int)(rightW * 0.28f), (int)(winH * 0.14f), 0, topPillW, topPillH, s_texBtnMap });
      layout.buttons.push_back({ -7, rightX + (int)(rightW * 0.72f), (int)(winH * 0.14f), 0, topPillW, topPillH, s_texBtnRsk });

      // Botões de ação no formato ergonômico favorito na coluna direita (amplo e espaçoso sem as setas!)
      int actX = rightX + (int)(rightW * 0.50f);
      int actY = (int)(winH * 0.65f);
      int r5 = std::min((int)(rightW * 0.17f), (int)(winH * 0.13f));
      int rSub = (int)(r5 * 0.74f);

      layout.buttons.push_back({ 53, actX, actY, r5, r5 * 2, r5 * 2, s_texBtn5 });
      layout.buttons.push_back({ 49, actX - (int)(r5 * 1.95f), actY - (int)(r5 * 0.95f), rSub, rSub * 2, rSub * 2, s_texBtn1 });
      layout.buttons.push_back({ 51, actX - (int)(r5 * 0.20f), actY - (int)(r5 * 2.10f), rSub, rSub * 2, rSub * 2, s_texBtn3 });
      layout.buttons.push_back({ 55, actX - (int)(r5 * 1.85f), actY + (int)(r5 * 1.15f), rSub, rSub * 2, rSub * 2, s_texBtn7 });
      layout.buttons.push_back({ 57, actX + (int)(r5 * 1.35f), actY - (int)(r5 * 1.65f), rSub, rSub * 2, rSub * 2, s_texBtn9 });
    }
  }

  return layout;
}

static int hitTestTouch(float touchX, float touchY, const GamepadLayout& layout) {
  // 1. Testa D-Pad primeiro se o toque estiver na sua vizinhança e D-Pad estiver ativo
  if (layout.dpadR > 0) {
    float ddx = touchX - layout.dpadX;
    float ddy = touchY - layout.dpadY;
    float distDpad = sqrt(ddx * ddx + ddy * ddy);
    if (distDpad <= layout.dpadR * 1.25f && distDpad >= layout.dpadR * 0.08f) {
      if (std::abs(ddx) > std::abs(ddy)) {
        return (ddx < 0) ? -3 : -4; // Left / Right
      } else {
        return (ddy < 0) ? -1 : -2; // Up / Down
      }
    }
  }

  // 2. Para os botões: seleciona o botão com menor proporção de distância (o mais próximo exato do dedo)
  int bestKey = 0;
  float bestDistRatio = 1.0f;

  for (const auto& b : layout.buttons) {
    if (b.r > 0) {
      float bx = touchX - b.cx;
      float by = touchY - b.cy;
      float d = sqrt(bx * bx + by * by);
      float maxR = b.r * 1.30f;
      if (d <= maxR) {
        float ratio = d / maxR;
        if (ratio < bestDistRatio) {
          bestDistRatio = ratio;
          bestKey = b.key;
        }
      }
    } else {
      float dx = std::abs(touchX - b.cx);
      float dy = std::abs(touchY - b.cy);
      float maxW = b.w * 0.70f;
      float maxH = b.h * 0.70f;
      if (dx <= maxW && dy <= maxH) {
        float ratio = std::max(dx / maxW, dy / maxH);
        if (ratio < bestDistRatio) {
          bestDistRatio = ratio;
          bestKey = b.key;
        }
      }
    }
  }

  return bestKey;
}

static void drawGamepad(const GamepadLayout& layout) {
  if (!s_renderer) return;

  // 1. D-Pad Virtual (Translúcido / Frosted Glass Overlay) se controles habilitados
  if (s_touchOverlayEnabled && s_texDpadBase && layout.dpadR > 0) {
    bool dpadActive = (s_touchHeldDirection != 0);
    SDL_SetTextureAlphaMod(s_texDpadBase, dpadActive ? 230 : 155);

    SDL_Rect dstDpad = {
      layout.dpadX - layout.dpadR,
      layout.dpadY - layout.dpadR,
      layout.dpadR * 2,
      layout.dpadR * 2
    };
    SDL_RenderCopy(s_renderer, s_texDpadBase, nullptr, &dstDpad);

    // Seta direcional ativa iluminada em cyan neon
    if (dpadActive && s_texDpadArrowActive) {
      SDL_SetTextureAlphaMod(s_texDpadArrowActive, 255);
      int arrDist = (int)(layout.dpadR * 0.58f);
      int arrSz = (int)(layout.dpadR * 0.40f);
      double angle = 0;
      int ax = layout.dpadX, ay = layout.dpadY;
      if (s_touchHeldDirection == -1) { ay -= arrDist; angle = 0; }
      else if (s_touchHeldDirection == -2) { ay += arrDist; angle = 180; }
      else if (s_touchHeldDirection == -3) { ax -= arrDist; angle = 270; }
      else if (s_touchHeldDirection == -4) { ax += arrDist; angle = 90; }

      SDL_Rect dstArr = { ax - arrSz / 2, ay - arrSz / 2, arrSz, arrSz };
      SDL_RenderCopyEx(s_renderer, s_texDpadArrowActive, nullptr, &dstArr, angle, nullptr, SDL_FLIP_NONE);
    }
  }

  // 2. Botões de Ação e Sistema (Translúcidos em repouso, iluminados ao toque)
  for (const auto& b : layout.buttons) {
    if (!b.tex) continue;
    bool isHeld = isTouchKeyHeld(b.key);
    
    if (b.key == KEY_TOGGLE_TOUCH_UI || b.key == KEY_TOGGLE_ORIENTATION) {
      Uint8 alpha = isHeld ? 255 : (s_touchOverlayEnabled ? 150 : 110);
      SDL_SetTextureAlphaMod(b.tex, alpha);
    } else {
      SDL_SetTextureAlphaMod(b.tex, isHeld ? 255 : 155);
    }

    SDL_Rect dst = { b.cx - b.w / 2, b.cy - b.h / 2, b.w, b.h };
    SDL_RenderCopy(s_renderer, b.tex, nullptr, &dst);

    // Halo / Brilho Neon Cyan quando o botão é pressionado
    if (isHeld && s_texBtnGlow) {
      SDL_SetTextureAlphaMod(s_texBtnGlow, 220);
      int glowSz = (int)(std::max(b.w, b.h) * 1.35f);
      SDL_Rect dstGlow = { b.cx - glowSz / 2, b.cy - glowSz / 2, glowSz, glowSz };
      SDL_RenderCopy(s_renderer, s_texBtnGlow, nullptr, &dstGlow);
    }
  }
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

static void getTouchCoords(const SDL_TouchFingerEvent& tf, int winW, int winH, float& outX, float& outY, int& outW, int& outH) {
#ifdef __SWITCH__
  if (s_screenRotation == 1) { // 90° CW
    outW = 720;
    outH = 1280;
    outX = tf.y * 720.0f;
    outY = (1.0f - tf.x) * 1280.0f;
    return;
  } else if (s_screenRotation == 2) { // 270° CCW / Flip Grip
    outW = 720;
    outH = 1280;
    outX = (1.0f - tf.y) * 720.0f;
    outY = tf.x * 1280.0f;
    return;
  }
#endif
  outW = winW;
  outH = winH;
  outX = tf.x * winW;
  outY = tf.y * winH;
}

bool Platform::pollEvents(VM& vm) {
#ifdef __SWITCH__
  if (!appletMainLoop()) {
    s_quit = true;
    return false;
  }
#endif
  SDL_Event ev;
  while (SDL_PollEvent(&ev)) {
    if (ev.type == SDL_QUIT) {
      s_quit = true;
      return false;
    }

    else if (ev.type == SDL_WINDOWEVENT) {
      if (ev.window.event == SDL_WINDOWEVENT_RESIZED || ev.window.event == SDL_WINDOWEVENT_SIZE_CHANGED) {
        if (!s_isFullscreen) {
          s_savedWinW = ev.window.data1;
          s_savedWinH = ev.window.data2;
          saveSettings();
        }
      }
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
      if (ev.cbutton.button == SDL_CONTROLLER_BUTTON_LEFTSTICK) {
        Platform::toggleBezel();
        continue;
      } else if (ev.cbutton.button == SDL_CONTROLLER_BUTTON_RIGHTSTICK) {
        Platform::toggleFps();
        continue;
      } else if (ev.cbutton.button == SDL_CONTROLLER_BUTTON_GUIDE) {
        Platform::toggleAspect(&vm);
        continue;
      }
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
      if (ev.key.keysym.sym == SDLK_F5 || ev.key.keysym.sym == SDLK_F9) {
        if (!ev.key.repeat) Platform::toggleBezel();
        continue;
      } else if (ev.key.keysym.sym == SDLK_F6 || ev.key.keysym.sym == SDLK_F8) {
        if (!ev.key.repeat) Platform::toggleFps();
        continue;
      } else if (ev.key.keysym.sym == SDLK_F7 || ev.key.keysym.sym == SDLK_F10) {
        if (!ev.key.repeat) Platform::toggleAspect(&vm);
        continue;
      } else if (ev.key.keysym.sym == SDLK_F11 ||
                 (ev.key.keysym.sym == SDLK_RETURN && (ev.key.keysym.mod & KMOD_ALT))) {
        if (!ev.key.repeat) Platform::toggleFullscreen();
        continue;
      }
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
      int winW = 0, winH = 0;
      SDL_GetRendererOutputSize(s_renderer, &winW, &winH);
      if (winW <= 0 || winH <= 0) SDL_GetWindowSize(s_window, &winW, &winH);
      float touchX = 0, touchY = 0;
      int layoutW = 0, layoutH = 0;
      getTouchCoords(ev.tfinger, winW, winH, touchX, touchY, layoutW, layoutH);
      GamepadLayout layout = calculateLayout(layoutW, layoutH);

      int key = hitTestTouch(touchX, touchY, layout);
      if (key == KEY_TOGGLE_TOUCH_UI) {
        s_touchOverlayEnabled = !s_touchOverlayEnabled;
        if (!s_touchOverlayEnabled) {
          // Solta direcional ou teclas mantidas para não travar andando
          if (s_activeDirection != 0 && g_display && g_display->current) {
            vm.gilLock();
            try {
              Value args[1]; args[0].i = s_activeDirection; Value ret[2];
              vm.invokeVirtual(g_display->current, "keyReleased:(I)V", args, 1, ret);
            } catch (...) {}
            vm.gilUnlock();
          }
          s_activeDirection = 0;
          s_touchHeldDirection = 0;
          s_activeFingers.clear();
        }
        Platform::rumble(0.20f, 40);
      } else if (key == KEY_TOGGLE_ORIENTATION) {
        s_screenRotation = (s_screenRotation + 1) % 3;
        Platform::rumble(0.25f, 50);
      } else if (key != 0 && s_touchOverlayEnabled) {
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
      int winW = 0, winH = 0;
      SDL_GetRendererOutputSize(s_renderer, &winW, &winH);
      if (winW <= 0 || winH <= 0) SDL_GetWindowSize(s_window, &winW, &winH);
      float touchX = 0, touchY = 0;
      int layoutW = 0, layoutH = 0;
      getTouchCoords(ev.tfinger, winW, winH, touchX, touchY, layoutW, layoutH);
      GamepadLayout layout = calculateLayout(layoutW, layoutH);

      for (auto& f : s_activeFingers) {
        if (f.id == ev.tfinger.fingerId) {
          int newKey = hitTestTouch(touchX, touchY, layout);
          if (newKey == KEY_TOGGLE_TOUCH_UI || newKey == KEY_TOGGLE_ORIENTATION) newKey = 0;
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
    for (int y = 0; y < g_screenHeight; y++) {
      memcpy((uint8_t*)pixels + y * pitch, g_screenBuffer + y * g_screenWidth, g_screenWidth * sizeof(uint32_t));
    }
    SDL_UnlockTexture(s_screenTexture);
  } else {
    SDL_UpdateTexture(s_screenTexture, nullptr, g_screenBuffer, g_screenWidth * sizeof(uint32_t));
  }

  int winW = 0, winH = 0;
  SDL_GetRendererOutputSize(s_renderer, &winW, &winH);
  if (winW <= 0 || winH <= 0) {
    SDL_GetWindowSize(s_window, &winW, &winH);
  }

  loadGamepadTextures();

#ifdef __SWITCH__
  if (s_screenRotation != 0) {
    if (!s_rotateTarget) {
      s_rotateTarget = SDL_CreateTexture(
          s_renderer,
          SDL_PIXELFORMAT_RGBA8888,
          SDL_TEXTUREACCESS_TARGET,
          720, 1280);
    }
    if (s_rotateTarget) {
      SDL_SetRenderTarget(s_renderer, s_rotateTarget);
      SDL_SetRenderDrawColor(s_renderer, 10, 12, 16, 255);
      SDL_RenderClear(s_renderer);

      GamepadLayout layout = calculateLayout(720, 1280);
      SDL_RenderCopy(s_renderer, s_screenTexture, nullptr, &layout.gameRect);
      drawGamepad(layout);

      SDL_SetRenderTarget(s_renderer, nullptr);
      SDL_SetRenderDrawColor(s_renderer, 0, 0, 0, 255);
      SDL_RenderClear(s_renderer);

      double angle = (s_screenRotation == 1) ? 90.0 : 270.0;
      SDL_Rect dstRect = { 280, -280, 720, 1280 };
      SDL_RenderCopyEx(s_renderer, s_rotateTarget, nullptr, &dstRect, angle, nullptr, SDL_FLIP_NONE);
      SDL_RenderPresent(s_renderer);
      return;
    }
  }
#endif

  SDL_SetRenderDrawColor(s_renderer, 0, 0, 0, 255);
  SDL_RenderClear(s_renderer);

  // Calcula geometria da tela de jogo com escala perfeitamente quadrada (pixel-perfect) sem distorção
  float scaleX = (float)winW / (float)g_screenWidth;
  float scaleY = (float)winH / (float)g_screenHeight;
  float scale = std::min(scaleX, scaleY);
  int dstW = (int)((float)g_screenWidth * scale);
  int dstH = (int)((float)g_screenHeight * scale);
  SDL_Rect dstGame = { (winW - dstW) / 2, (winH - dstH) / 2, dstW, dstH };

  // Renderiza moldura temática nas laterais (pillarbox) se houver espaço
  if (s_aspectMode == ASPECT_ORIGINAL && dstGame.x > 0) {
    SDL_Texture* activeBezel = nullptr;
    if (s_bezelMode == BEZEL_SOLTIA) activeBezel = s_texBezelSoltia;
    else if (s_bezelMode == BEZEL_SLATE) activeBezel = s_texBezelSlate;

    if (activeBezel) {
      // Textura base de 1920x1080: coluna esquerda = [0..555], coluna direita = [1365..1920]
      SDL_Rect srcLeft = { 0, 0, 555, 1080 };
      SDL_Rect dstLeft = { 0, 0, dstGame.x, winH };
      SDL_RenderCopy(s_renderer, activeBezel, &srcLeft, &dstLeft);

      SDL_Rect srcRight = { 1365, 0, 555, 1080 };
      SDL_Rect dstRight = { dstGame.x + dstGame.w, 0, winW - (dstGame.x + dstGame.w), winH };
      SDL_RenderCopy(s_renderer, activeBezel, &srcRight, &dstRight);
    }
  }

  // Renderiza o buffer do jogo J2ME 240x320
  SDL_RenderCopy(s_renderer, s_screenTexture, nullptr, &dstGame);

#if defined(__ANDROID__) || defined(__SWITCH__)
  if (s_touchOverlayEnabled) {
    GamepadLayout layout = calculateLayout(winW, winH);
    drawGamepad(layout);
  }
#else
  if (s_touchOverlayEnabled) {
    GamepadLayout layout = calculateLayout(winW, winH);
    drawGamepad(layout);
  }
#endif

  // Desenha banner de notificação OSD
  drawOsd(winW, winH);

  SDL_RenderPresent(s_renderer);
}

void Platform::shutdown() {
  MidiSynth::shutdown();
  for (auto* pad : s_controllers) {
    if (pad) SDL_GameControllerClose(pad);
  }
  s_controllers.clear();
  if (s_rotateTarget) { SDL_DestroyTexture(s_rotateTarget); s_rotateTarget = nullptr; }
  if (s_texDpadBase) { SDL_DestroyTexture(s_texDpadBase); s_texDpadBase = nullptr; }
  if (s_texDpadArrowActive) { SDL_DestroyTexture(s_texDpadArrowActive); s_texDpadArrowActive = nullptr; }
  if (s_texBtn5) { SDL_DestroyTexture(s_texBtn5); s_texBtn5 = nullptr; }
  if (s_texBtn1) { SDL_DestroyTexture(s_texBtn1); s_texBtn1 = nullptr; }
  if (s_texBtn3) { SDL_DestroyTexture(s_texBtn3); s_texBtn3 = nullptr; }
  if (s_texBtn7) { SDL_DestroyTexture(s_texBtn7); s_texBtn7 = nullptr; }
  if (s_texBtn9) { SDL_DestroyTexture(s_texBtn9); s_texBtn9 = nullptr; }
  if (s_texBtnMenu) { SDL_DestroyTexture(s_texBtnMenu); s_texBtnMenu = nullptr; }
  if (s_texBtnMap) { SDL_DestroyTexture(s_texBtnMap); s_texBtnMap = nullptr; }
  if (s_texBtnRsk) { SDL_DestroyTexture(s_texBtnRsk); s_texBtnRsk = nullptr; }
  if (s_texBtnPrev) { SDL_DestroyTexture(s_texBtnPrev); s_texBtnPrev = nullptr; }
  if (s_texBtnNext) { SDL_DestroyTexture(s_texBtnNext); s_texBtnNext = nullptr; }
  if (s_texBtnEyeOpen) { SDL_DestroyTexture(s_texBtnEyeOpen); s_texBtnEyeOpen = nullptr; }
  if (s_texBtnEyeClosed) { SDL_DestroyTexture(s_texBtnEyeClosed); s_texBtnEyeClosed = nullptr; }
  if (s_texBtnRotate) { SDL_DestroyTexture(s_texBtnRotate); s_texBtnRotate = nullptr; }
  if (s_texBtnGlow) { SDL_DestroyTexture(s_texBtnGlow); s_texBtnGlow = nullptr; }
  if (s_texBezelSoltia) { SDL_DestroyTexture(s_texBezelSoltia); s_texBezelSoltia = nullptr; }
  if (s_texBezelSlate) { SDL_DestroyTexture(s_texBezelSlate); s_texBezelSlate = nullptr; }
  if (s_texFontOsd) { SDL_DestroyTexture(s_texFontOsd); s_texFontOsd = nullptr; }
  s_gamepadTexturesLoaded = false;
  if (s_screenTexture) { SDL_DestroyTexture(s_screenTexture); s_screenTexture = nullptr; }
  if (s_renderer) { SDL_DestroyRenderer(s_renderer); s_renderer = nullptr; }
  if (s_window) { SDL_DestroyWindow(s_window); s_window = nullptr; }
  Mix_CloseAudio();
  SDL_Quit();
#ifdef __SWITCH__
  romfsExit();
#endif
}

void Platform::toggleBezel() {
  s_bezelMode = static_cast<BezelMode>((s_bezelMode + 1) % BEZEL_COUNT);
  saveSettings();
  loadBezelTextures();
  const char* names[] = {
    "Moldura: Soltia Ancestral",
    "Moldura: Ardosia Escura",
    "Moldura: Preto Classico"
  };
  showOsdMessage(names[s_bezelMode]);
}

void Platform::toggleFps() {
  s_targetFps = (s_targetFps == 15) ? 30 : 15;
  saveSettings();
  if (s_targetFps == 15) {
    showOsdMessage("Taxa de Quadros: 15 FPS (Padrao Original J2ME)");
  } else {
    showOsdMessage("Taxa de Quadros: 30 FPS (Modo Turbo / Fluido)");
  }
}

int Platform::getTargetFps() {
  return s_targetFps;
}

void Platform::toggleAspect(VM* pVm) {
  s_aspectMode = static_cast<AspectMode>((s_aspectMode + 1) % ASPECT_COUNT);
  g_screenWidth = (s_aspectMode == ASPECT_WIDESCREEN) ? SCREEN_W_WIDESCREEN : SCREEN_W_ORIGINAL;
  g_screenHeight = SCREEN_HEIGHT;
  saveSettings();

  if (s_renderer) {
    if (s_screenTexture) SDL_DestroyTexture(s_screenTexture);
    s_screenTexture = SDL_CreateTexture(
        s_renderer,
        SDL_PIXELFORMAT_ARGB8888,
        SDL_TEXTUREACCESS_STREAMING,
        g_screenWidth, g_screenHeight);
    if (s_screenTexture) {
      SDL_SetTextureBlendMode(s_screenTexture, SDL_BLENDMODE_NONE);
    }
  }

  if (pVm) {
    updateJavaViewportVariables(*pVm);
  }

  memset(g_screenBuffer, 0, sizeof(g_screenBuffer));

  if (s_aspectMode == ASPECT_ORIGINAL) {
    showOsdMessage("Proporcao: 3:4 Original (Molduras Soltia)");
  } else {
    showOsdMessage("Proporcao: 16:9 True Widescreen (Mais Mapa)");
  }
}

void Platform::toggleFullscreen() {
#ifndef __SWITCH__
  if (!s_window) return;
  s_isFullscreen = !s_isFullscreen;
  SDL_SetWindowFullscreen(s_window, s_isFullscreen ? SDL_WINDOW_FULLSCREEN_DESKTOP : 0);
  saveSettings();
  showOsdMessage(s_isFullscreen ? "Tela Cheia: Ativada" : "Tela Cheia: Modo Janela");
#endif
}

void Platform::showOsdMessage(const std::string& msg) {
  s_osdMessage = msg;
  s_osdExpireTime = SDL_GetTicks() + 2500;
  printf("[OSD] %s\n", msg.c_str());
}

void Platform::framePacerWait() {
  if (s_frameStartCounter == 0) {
    s_frameStartCounter = SDL_GetPerformanceCounter();
    return;
  }

  uint64_t freq = SDL_GetPerformanceFrequency();
  double targetSec = 1.0 / static_cast<double>(s_targetFps);
  uint64_t targetCounts = static_cast<uint64_t>(targetSec * static_cast<double>(freq));

  uint64_t now = SDL_GetPerformanceCounter();
  uint64_t elapsedCounts = now - s_frameStartCounter;

  if (elapsedCounts < targetCounts) {
    double remainingSec = static_cast<double>(targetCounts - elapsedCounts) / static_cast<double>(freq);
    double remainingMs = remainingSec * 1000.0;
    if (remainingMs > 2.0) {
      SDL_Delay(static_cast<Uint32>(remainingMs - 1.5));
    }
    while (true) {
      now = SDL_GetPerformanceCounter();
      if ((now - s_frameStartCounter) >= targetCounts) break;
      SDL_Delay(0);
    }
  }

  s_frameStartCounter = SDL_GetPerformanceCounter();
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
#ifdef __SWITCH__
  if (!appletMainLoop()) return true;
#endif
  return s_quit;
}

std::vector<uint8_t> Platform::readAsset(const std::string& path) {
  if (path.empty()) return {};

  std::string clean = path;
  while (!clean.empty() && (clean[0] == '/' || clean[0] == '\\')) {
    clean = clean.substr(1);
  }

  // 1. Tenta o caminho exato
  SDL_RWops* rw = SDL_RWFromFile(clean.c_str(), "rb");
  
  // 2. Se falhar e começar com "./", tenta sem
  if (!rw && clean.rfind("./", 0) == 0) {
    rw = SDL_RWFromFile(clean.substr(2).c_str(), "rb");
  }
  
  // 3. Tenta prefixando "assets/"
  if (!rw) {
    std::string alt = "assets/" + clean;
    rw = SDL_RWFromFile(alt.c_str(), "rb");
  }

  // 4. Tenta prefixando "../assets/"
  if (!rw) {
    std::string alt = "../assets/" + clean;
    rw = SDL_RWFromFile(alt.c_str(), "rb");
  }

  // 5. Tenta prefixando "reference/extracted/"
  if (!rw) {
    std::string alt = "reference/extracted/" + clean;
    rw = SDL_RWFromFile(alt.c_str(), "rb");
  }

  // 6. Tenta prefixando "../reference/extracted/"
  if (!rw) {
    std::string alt = "../reference/extracted/" + clean;
    rw = SDL_RWFromFile(alt.c_str(), "rb");
  }

#ifdef __SWITCH__
  // 5. No Nintendo Switch, tenta via romfs:/
  if (!rw) {
    std::string alt = "romfs:/" + clean;
    rw = SDL_RWFromFile(alt.c_str(), "rb");
  }
  if (!rw) {
    std::string alt = "romfs:/reference/extracted/" + clean;
    rw = SDL_RWFromFile(alt.c_str(), "rb");
  }
  if (!rw) {
    std::string alt = "romfs:/extracted/" + clean;
    rw = SDL_RWFromFile(alt.c_str(), "rb");
  }
  if (!rw) {
    std::string alt = "romfs:/assets/" + clean;
    rw = SDL_RWFromFile(alt.c_str(), "rb");
  }
  if (!rw) {
    std::string alt = "romfs:/ui/" + clean;
    rw = SDL_RWFromFile(alt.c_str(), "rb");
  }
#endif

  // 6. Tenta em ../assets/ ou ../reference/extracted/
  if (!rw) {
    std::string alt = "../" + clean;
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
#ifdef __SWITCH__
  mkdir("sdmc:/switch", 0777);
  mkdir("sdmc:/switch/heroes_lore", 0777);
  return "sdmc:/switch/heroes_lore";
#elif defined(__ANDROID__)
  const char* path = SDL_AndroidGetInternalStoragePath();
  if (path && path[0] != '\0') return std::string(path);
  return ".";
#else
  return ".";
#endif
}

} // namespace hl
