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

  // Sistema de Arquivos / Assets Portável (PC, Android APK, Switch RomFS)
  static std::vector<uint8_t> readAsset(const std::string& path);
  static std::string getStorageDir();
};

} // namespace hl
