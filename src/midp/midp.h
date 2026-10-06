// midp.h — tipos e estruturas para APIs MIDP (Graphics, Image, Display, RecordStore, Audio)
#pragma once
#include "vm/vm.h"
#include <cstdint>
#include <vector>
#include <string>

namespace hl {

struct GraphicsObj;

struct ImageObj : Object {
  int width = 0;
  int height = 0;
  std::vector<uint32_t> pixels; // 0xAARRGGBB
  bool mutable_img = false;
  GraphicsObj* graphics = nullptr;

  void trace(std::vector<Object*>& o) override;
  size_t bytes() const override { return 48 + pixels.size() * 4; }
};

struct GraphicsObj : Object {
  ImageObj* target = nullptr; // nullptr = framebuffer principal (240x320)
  int transX = 0;
  int transY = 0;
  int clipX = 0;
  int clipY = 0;
  int clipW = 240;
  int clipH = 320;
  uint32_t color = 0xFF000000;

  void resetClip();
  void setColor(uint32_t rgb);
  void setClip(int x, int y, int w, int h);
  void clipRect(int x, int y, int w, int h);
  void fillRect(int x, int y, int w, int h);
  void drawRect(int x, int y, int w, int h);
  void drawLine(int x0, int y0, int x1, int y1);
  void fillArc(int x, int y, int w, int h, int startAngle, int arcAngle);
  void drawImage(ImageObj* img, int x, int y, int anchor);

  void trace(std::vector<Object*>& o) override;
};

struct DisplayObj : Object {
  Object* current = nullptr; // Canvas ou Displayable ativo
  void trace(std::vector<Object*>& o) override { if (current) o.push_back(current); }
};

struct RecordStoreObj : Object {
  std::string name;
  std::vector<std::vector<uint8_t>> records; // 1-based (index 0 vazio)
  bool open = true;
};

struct PlayerObj : Object {
  std::vector<uint8_t> data;
  std::string type;
  int loopCount = 1;
  int state = 100; // UNREALIZED=100, REALIZED=200, PREFETCHED=300, STARTED=400, CLOSED=0
  int volume = 100;
  void* handle = nullptr; // Mix_Music* ou Mix_Chunk*
  bool isMusic = false;

  ~PlayerObj() override;
};

struct VolumeControlObj : Object {
  PlayerObj* player = nullptr;
  void trace(std::vector<Object*>& o) override { if (player) o.push_back(player); }
};

constexpr int SCREEN_W_ORIGINAL = 240;
constexpr int SCREEN_W_WIDESCREEN = 568; // 16:9 exato para altura 320 (320 * 16 / 9 = 568.88)
constexpr int SCREEN_HEIGHT = 320;
constexpr int MAX_SCREEN_WIDTH = 640;

// Framebuffer principal compartilhado com a plataforma SDL2
extern uint32_t g_screenBuffer[MAX_SCREEN_WIDTH * SCREEN_HEIGHT];
extern int g_screenWidth;
extern int g_screenHeight;
extern GraphicsObj* g_screenGraphics;
extern DisplayObj* g_display;
extern Object* g_serialRunnable;

} // namespace hl
