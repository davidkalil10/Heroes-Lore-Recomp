# STATUS

_Ãšltima atualizaÃ§Ã£o: 2026-10-04_

## Ambiente
- Java 17 (Temurin), Python 3.13, Git: OK.
- Toolchain C++ instalada (MSYS2 UCRT64: g++, CMake, Ninja, gdb, SDL2, SDL2_mixer). Ver LEARNINGS.
- Os dois projetos Flutter estÃ£o copiados em `dados de consulta/` (somente consulta).

## Estado
- [x] Linguagem: C++17 + SDL2 (acordado).
- [x] Docs base: AGENTS.md, STATUS, LEARNINGS, CLASS_MAP, CLASS_INDEX.
- [x] JAR copiado: `reference/heroes.jar`; extraído em `reference/extracted/`.
- [x] Decompilado: `reference/decompiled_cfr/` (90 classes, `--renamedupmembers`); bytecode em `reference/javap/`.
- [x] Ferramentas: `tools/cfr.jar`, `tools/class_index.py`.
- [x] Motor de Execução JVM Nativo: Class loader, Garbage Collector, Interpretador CLDC 1.1 completo (`src/vm/`).
- [x] Camada MIDP 2.0 / LCDUI / RMS / Media: Display, Canvas, Graphics, Image, RecordStore, Sound (`src/midp/`).
- [x] Camada de plataforma SDL2: Janela escalada 240x320 com aspect-ratio mantido, áudio WASAPI/SDL_mixer, mapeamento completo de teclado PC (`src/platform/`).
- [x] Resolução de colisões de identificadores em classes ofuscadas (`name:desc` para campos e métodos).
- [x] Correção de Streams e RMS (Save Game):
  - Resolvido crash de Access Violation ao salvar pelo menu in-game (`ah.f()` -> `n.o()`). O método virtual `OutputStream.write:([B)V` recebia uma instância de `DataOutputStream` (`Dos*`) que era incorretamente castada para `ByteArrayOutputStream` (`Baos*`), corrompendo a memória de `std::vector`.
  - Implementado unwrapping universal recursivo (`getUnderlyingBaos`) e registro completo de todos os métodos de escrita, flush, reset e conversão de stream (`ByteArrayOutputStream`, `DataOutputStream`, `OutputStream`).
  - Suporte completo a `RecordStore` (open, addRecord, setRecord, getRecord, delete, close) com persistência em disco em arquivos binários `.rms`.
  - Validado via teste unitário automatizado (`tests/test_stream_rms.cpp`) com 100% de sucesso.
- [x] Jogabilidade confirmada pelo usuário no Windows ("abriu, consegui jogar e está redondinho").
- [x] README completo e caprichado com instruções de compilação, arquitetura, controles e documentação técnica.
- [x] Passo 1 do Roadmap — Movimentação Contínua Fluida:
  - Fim das paradas ao andar: implementado rastreamento de estado contínuo de teclas (`SDL_GetKeyboardState`) e repetição typematic suave (delay inicial de 160ms para toques simples e repetição a 40ms / ~25Hz enquanto a tecla for mantida).
  - Transições suaves entre direções e parada imediata ao soltar a tecla.
- [x] Passo 2 do Roadmap — Suporte Completo a Gamepads:
  - Plug-and-play e hotplug automático para controles de Xbox, PlayStation, Switch Pro e 8BitDo via `SDL_GameController`.
  - Movimentação analógica e D-Pad integrados ao sistema contínuo sem engasgos.
  - Ergonomia refinada: Mapa no botão Select/Back (<kbd>0</kbd>), alternância bidirecional de poções nos gatilhos analógicos LT/RT (esq/dir via `#`), uso de poção em R1, ataque secundário em L1 e ataques do Guardião em X/Y.
  - Suporte a feedback tátil de vibração (rumble).

- [x] Passo 3 do Roadmap — Port Nativo para Android:
  - Projeto Android nativo completo em `android/` com Gradle 8.1.1, NDK 26 (`26.1.10909125`), CMake 3.22.1 e Java 17.
  - Compilação multi-arquitetura (`arm64-v8a` para smartphones modernos e `armeabi-v7a` para compatibilidade total).
  - Bibliotecas nativas compiladas: `libSDL2.so`, `libSDL2_mixer.so` (MIDI Timidity integrado, Vorbis STB, WAV) e `libmain.so` (C++17, VM CLDC 1.1, MIDP 2.0).
  - I/O unificado e transparente: `Platform::readAsset` acessa diretamente o `AAssetManager` do APK via `SDL_RWFromFile`; `Platform::getStorageDir` persiste saves RMS no armazenamento interno do app (`SDL_AndroidGetInternalStoragePath`).
  - Controles virtuais na tela (Virtual Gamepad Fullscreen Overlay):
    - Layout de emulador moderno (Overlay Translúcido sobre o Jogo): o jogo ocupa o tamanho máximo da tela mantendo o aspect ratio original (240x320) perfeitamente centralizado.
    - Controles estilo vidro fosco (*frosted glass* translúcido a ~60% de opacidade quando em repouso), permitindo enxergar toda a ação por baixo dos botões sem obstruir a visão.
    - Ao tocar em qualquer botão ou direção do D-Pad, o controle ilumina a 100% de opacidade com efeito de brilho neon ciano e setas ativas.
    - Redesign de MENU, MAPA e R (substituindo o antigo RSK por um nome mais intuitivo) com proporção 2.4:1 sem distorção e posicionamento seguro abaixo da barra de status.
    - Disposição de ação clássica ergonômica restaurada: 5 no centro (ataque), 1 (Skill 1) à esquerda-cima, 3 (Skill 2) no topo, 7 (Poção) à esquerda-baixo e 9 (Item) à direita-cima, respeitando a curvatura natural do polegar.
    - D-Pad e cluster de botões elevados em sincronia (~42% a partir da borda inferior), liberando uma faixa inferior limpa e ampla para as setas circulares de alteração de poção (◀ e ▶), agora perfeitamente centralizadas no eixo horizontal da tela (`winW / 2`).
    - Algoritmo de hit-test por menor razão de distância (`d / maxR`): elimina qualquer ambiguidade de clique ou necessidade de "adivinhar o ponto", registrando sempre com 100% de precisão o botão mais próximo do polegar.
    - Modo Paisagem (Horizontal) totalmente proporcional e balanceado: setas ◀ e ▶ movidas para a coluna esquerda (entre MENU e D-Pad), deixando o lado direito limpo e espaçoso para os 5 botões de ação e os botões MAPA e R.
    - Botão de Ocultar/Reexibir Controles Virtuais (Ícone discreto de olho no canto inferior esquerdo): permite alternar entre a sobreposição de controles e uma visualização 100% limpa da tela do jogo (ideal para jogar com gamepad Bluetooth/USB ou apreciar cutscenes/cenários), com resposta tátil háptica e persistência elegante.
  - Ícone personalizado oficial extraído de `logo 512.png` integrado em todas as densidades (`mipmap-mdpi`, `hdpi`, `xhdpi`, `xxhdpi`, `xxxhdpi`) em versões padrão e circular (`ic_launcher_round`).
  - Suporte nativo a gamepads Bluetooth/USB (Xbox, PlayStation, Gamesir, Razer Kishi) com rumble.
  - APKs Release assinados e otimizados (~2.74 MB em `arm64-v8a`) gerados e prontos em `bin/heroes_lore-arm64-v8a.apk`, `bin/heroes_lore.apk` e `bin/heroes_lore-universal.apk`.

## Próximos passos (Roadmap)
- [ ] Passo 4: Port Homebrew para Nintendo Switch (Arquivo `.nro` via devkitPro / libnx).
- [ ] Passo 5: Aspect Ratio & Taxa de Quadros (Widescreen, Molduras e seletor 30 FPS Clássico vs 60 FPS Fluido).
- [ ] Passo 6: Cloud Save & Sincronização Cruzada (PC <-> Celular <-> Switch).
- [ ] Passo 7: Seletor de Idiomas / Localização (PT-BR, EN, KO, ES).


