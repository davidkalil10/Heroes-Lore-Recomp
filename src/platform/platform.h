// platform.h — interface de plataforma (janela, entrada, renderização)
#pragma once
#include <cstdint>
#include <vector>
#include <string>

namespace hl {

struct VM;

void boot_log(const char* fmt, ...);

class Platform {
public:
  static bool init(int scale = 2);
  static bool pollEvents(VM& vm);
  static void present();
  static void shutdown();
  static bool shouldQuit();
  static void rumble(float strength = 0.5f, int durationMs = 150);

  // Recursos Passo 7: Molduras Temáticas (Bezels), Aspect Ratio e FPS Limiter
  static void toggleBezel();
  static void toggleFps();
  static void toggleAspect(VM* vm = nullptr);
  static void updateViewport(VM& vm);
  static void toggleFullscreen();
  static void showOsdMessage(const std::string& msg);
  static void framePacerWait();
  static int getTargetFps();

  // Recursos Passo 8: Detecção e Atualização OTA via GitHub Releases
  static void checkForUpdates();
  static void drawText(void* renderer, const std::string& text, int x, int y, int charW, int charH, uint8_t alpha = 255);

  // Sistema de Arquivos / Assets Portável (PC, Android APK, Switch RomFS)
  static std::vector<uint8_t> readAsset(const std::string& path);
  static std::string getStorageDir();
};

} // namespace hl
