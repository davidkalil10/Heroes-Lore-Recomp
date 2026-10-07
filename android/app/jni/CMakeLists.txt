cmake_minimum_required(VERSION 3.16)
project(heroes_lore_android CXX C)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

set(PROJECT_ROOT "${CMAKE_CURRENT_SOURCE_DIR}/../../..")

# 1. Compilação do SDL2 para Android
set(SDL_STATIC OFF CACHE BOOL "" FORCE)
set(SDL_SHARED ON CACHE BOOL "" FORCE)
add_subdirectory("${PROJECT_ROOT}/third_party/SDL2" SDL2)

# 2. Compilação do SDL2_mixer para Android (MIDI Timidity + WAV + Vorbis STB)
set(SDL2MIXER_VENDORED ON CACHE BOOL "" FORCE)
set(SDL2MIXER_WAVE ON CACHE BOOL "" FORCE)
set(SDL2MIXER_MIDI ON CACHE BOOL "" FORCE)
set(SDL2MIXER_MIDI_TIMIDITY ON CACHE BOOL "" FORCE)
set(SDL2MIXER_MIDI_NATIVE OFF CACHE BOOL "" FORCE)
set(SDL2MIXER_MIDI_FLUIDSYNTH OFF CACHE BOOL "" FORCE)
set(SDL2MIXER_VORBIS_STB ON CACHE BOOL "" FORCE)
set(SDL2MIXER_WAVPACK OFF CACHE BOOL "" FORCE)
set(SDL2MIXER_OPUS OFF CACHE BOOL "" FORCE)
set(SDL2MIXER_FLAC OFF CACHE BOOL "" FORCE)
set(SDL2MIXER_MOD OFF CACHE BOOL "" FORCE)
set(SDL2MIXER_MP3 OFF CACHE BOOL "" FORCE)
set(SDL2MIXER_GME OFF CACHE BOOL "" FORCE)
set(SDL2MIXER_SAMPLES OFF CACHE BOOL "" FORCE)
add_subdirectory("${PROJECT_ROOT}/third_party/SDL2_mixer" SDL2_mixer)

# 3. Biblioteca nativa principal (libmain.so) invocada pelo SDLActivity
add_library(main SHARED
    "${PROJECT_ROOT}/src/main.cpp"
    "${PROJECT_ROOT}/src/vm/vm.cpp"
    "${PROJECT_ROOT}/src/vm/interp.cpp"
    "${PROJECT_ROOT}/src/vm/natives.cpp"
    "${PROJECT_ROOT}/src/midp/midp.cpp"
    "${PROJECT_ROOT}/src/platform/platform_sdl.cpp"
    "${PROJECT_ROOT}/src/platform/midi_synth.cpp"
    "${PROJECT_ROOT}/src/platform/updater.cpp"
)

target_include_directories(main PRIVATE
    "${PROJECT_ROOT}/src"
    "${PROJECT_ROOT}/third_party"
    "${PROJECT_ROOT}/third_party/SDL2/include"
    "${PROJECT_ROOT}/third_party/SDL2_mixer/include"
)

target_link_libraries(main PRIVATE
    SDL2
    SDL2_mixer
    GLESv2
    log
    android
)
