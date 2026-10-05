#pragma once
#include <cstdint>
#include <cstddef>
#include <string>

namespace hl {

class MidiSynth {
public:
  static bool init();
  static void shutdown();
  static bool play(const uint8_t* data, size_t size, int loopCount = -1);
  static void stop();
  static void setVolume(int vol); // 0 - 128 (MIDP/SDL standard)
  static bool isPlaying();
};

} // namespace hl
