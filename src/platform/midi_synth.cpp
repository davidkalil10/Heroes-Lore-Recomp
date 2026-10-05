// midi_synth.cpp — sintetizador MIDI em tempo real usando TinySoundFont + TinyMidiLoader
#include "midi_synth.h"
#include "platform.h"

#if defined(__has_include)
  #if __has_include(<SDL2/SDL.h>)
    #include <SDL2/SDL.h>
    #include <SDL2/SDL_mixer.h>
  #else
    #include <SDL.h>
    #include <SDL_mixer.h>
  #endif
#else
  #include <SDL.h>
  #include <SDL_mixer.h>
#endif

#define TSF_IMPLEMENTATION
#include "../../third_party/tsf.h"
#define TML_IMPLEMENTATION
#include "../../third_party/tml.h"

#include <vector>
#include <cstring>
#include <algorithm>

namespace hl {

static tsf* s_tsf = nullptr;
static tml_message* s_midi = nullptr;
static tml_message* s_currentMsg = nullptr;
static double s_currentTimeMs = 0.0;
static int s_loopCount = -1;
static bool s_isPlaying = false;
static std::vector<uint8_t> s_sfData;

static void SDLCALL audioCallback(void*, Uint8* stream, int len) {
  if (!s_tsf || !s_isPlaying) {
    std::memset(stream, 0, len);
    return;
  }

  int samples = len / (2 * (int)sizeof(short)); // estéreo 16-bit interleaved

  while (samples > 0) {
    if (s_currentMsg) {
      double timeUntil = (double)s_currentMsg->time - s_currentTimeMs;
      if (timeUntil <= 0.0) {
        switch (s_currentMsg->type) {
          case TML_PROGRAM_CHANGE:
            tsf_channel_set_presetnumber(s_tsf, s_currentMsg->channel, s_currentMsg->program, (s_currentMsg->channel == 9));
            break;
          case TML_NOTE_ON:
            tsf_channel_note_on(s_tsf, s_currentMsg->channel, s_currentMsg->key, s_currentMsg->velocity / 127.0f);
            break;
          case TML_NOTE_OFF:
            tsf_channel_note_off(s_tsf, s_currentMsg->channel, s_currentMsg->key);
            break;
          case TML_PITCH_BEND:
            tsf_channel_set_pitchwheel(s_tsf, s_currentMsg->channel, s_currentMsg->pitch_bend);
            break;
          case TML_CONTROL_CHANGE:
            tsf_channel_midi_control(s_tsf, s_currentMsg->channel, s_currentMsg->control, s_currentMsg->control_value);
            break;
          default:
            break;
        }
        s_currentMsg = s_currentMsg->next;
      } else {
        int samplesUntil = (int)(timeUntil * (44100.0 / 1000.0));
        int block = std::min(samples, samplesUntil);
        if (block <= 0) block = 1;
        tsf_render_short(s_tsf, (short*)stream, block, 0);
        stream += block * 2 * sizeof(short);
        samples -= block;
        s_currentTimeMs += (double)block * (1000.0 / 44100.0);
      }
    } else {
      // Fim da trilha MIDI
      if (s_loopCount != 0) {
        if (s_loopCount > 0) s_loopCount--;
        tsf_reset(s_tsf);
        s_currentMsg = s_midi;
        s_currentTimeMs = 0.0;
      } else {
        std::memset(stream, 0, samples * 2 * sizeof(short));
        samples = 0;
        s_isPlaying = false;
        Mix_HookMusic(nullptr, nullptr);
      }
    }
  }
}

bool MidiSynth::init() {
  if (s_tsf) return true;

  boot_log("[MidiSynth] Carregando banco SoundFont TimGM6mb.sf2...\n");
  s_sfData = Platform::readAsset("soundfont/TimGM6mb.sf2");
  if (s_sfData.empty()) s_sfData = Platform::readAsset("assets/soundfont/TimGM6mb.sf2");
  if (s_sfData.empty()) s_sfData = Platform::readAsset("reference/extracted/soundfont/TimGM6mb.sf2");

  if (s_sfData.empty()) {
    boot_log("[MidiSynth] AVISO: Banco de instrumentos soundfont/TimGM6mb.sf2 não encontrado.\n");
    return false;
  }

  s_tsf = tsf_load_memory(s_sfData.data(), (int)s_sfData.size());
  if (!s_tsf) {
    boot_log("[MidiSynth] ERRO: Falha ao inicializar TinySoundFont a partir da memória.\n");
    return false;
  }

  tsf_set_output(s_tsf, TSF_STEREO_INTERLEAVED, 44100, 0.0f);
  tsf_set_volume(s_tsf, 0.85f);
  boot_log("[MidiSynth] Sintetizador MIDI inicializado com sucesso! (%zu bytes SF2)\n", s_sfData.size());
  return true;
}

void MidiSynth::shutdown() {
  stop();
  if (s_tsf) {
    tsf_close(s_tsf);
    s_tsf = nullptr;
  }
  s_sfData.clear();
}

bool MidiSynth::play(const uint8_t* data, size_t size, int loopCount) {
  if (!s_tsf) {
    if (!init()) return false;
  }

  SDL_LockAudio();
  if (s_midi) {
    tml_free(s_midi);
    s_midi = nullptr;
  }

  tsf_reset(s_tsf);
  s_midi = tml_load_memory(data, (int)size);
  if (!s_midi) {
    SDL_UnlockAudio();
    boot_log("[MidiSynth] ERRO: Falha ao parsear arquivo MIDI (%zu bytes)\n", size);
    return false;
  }

  s_currentMsg = s_midi;
  s_currentTimeMs = 0.0;
  s_loopCount = loopCount;
  s_isPlaying = true;
  Mix_HookMusic(audioCallback, nullptr);
  SDL_UnlockAudio();

  boot_log("[MidiSynth] Música MIDI iniciada com sucesso (%zu bytes, loop=%d)\n", size, loopCount);
  return true;
}

void MidiSynth::stop() {
  SDL_LockAudio();
  Mix_HookMusic(nullptr, nullptr);
  if (s_tsf) tsf_reset(s_tsf);
  if (s_midi) {
    tml_free(s_midi);
    s_midi = nullptr;
  }
  s_currentMsg = nullptr;
  s_isPlaying = false;
  SDL_UnlockAudio();
}

void MidiSynth::setVolume(int vol) {
  if (s_tsf) {
    float gain = (std::clamp(vol, 0, 100) / 100.0f) * 0.85f;
    tsf_set_volume(s_tsf, gain);
  }
}

bool MidiSynth::isPlaying() {
  return s_isPlaying;
}

} // namespace hl
