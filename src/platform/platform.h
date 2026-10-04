// platform.h — interface de plataforma (janela, entrada, renderização)
#pragma once
#include <cstdint>

namespace hl {

struct VM;

class Platform {
public:
  static bool init(int scale = 2);
  static bool pollEvents(VM& vm);
  static void present();
  static void shutdown();
  static bool shouldQuit();
};

} // namespace hl
