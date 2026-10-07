# ⚔️ Heroes Lore: Wind of Soltia — Native Recompilation (C++17 + SDL2)

[![Build & Release](https://github.com/davidkalil10/Heroes-Lore-Recomp/actions/workflows/build.yml/badge.svg)](https://github.com/davidkalil10/Heroes-Lore-Recomp/actions/workflows/build.yml)
[![GitHub Release](https://img.shields.io/github/v/release/davidkalil10/Heroes-Lore-Recomp?color=blue&label=Latest%20Release)](https://github.com/davidkalil10/Heroes-Lore-Recomp/releases)
[![Language](https://img.shields.io/badge/Language-C%2B%2B17-blue.svg)](https://en.cppreference.com/w/cpp/17)
[![Game Lang](https://img.shields.io/badge/Game%20Lang-Portugu%C3%AAs--BR%20(Open%20Mind%20v0.0.2)-yellow.svg)]()
[![Status](https://img.shields.io/badge/Status-100%25%20Playable%20%26%20Save%20Working-brightgreen.svg)]()

Port nativo, determinístico e de alta performance do lendário RPG J2ME **Heroes Lore: Wind of Soltia** (*Hands-On Mobile / EA Mobile*), baseado na versão oficial brasileira de resolução 240x320.

Diferente de remakes feitos "no olhômetro", este projeto executa o **bytecode original decompilado e reinterpretado diretamente em C++17** com uma camada de abstração fina para MIDP 2.0 / LCDUI / RMS sobre SDL2, preservando com precisão matemática absoluta cada fórmula de dano, atributos, comportamento de IA, diálogos, tabelas de eventos e taxas de drop.

---

## 📥 Downloads & Plataformas Suportadas

Os binários compilados oficiais e prontos para jogar de cada plataforma estão disponíveis na aba **[Releases](https://github.com/davidkalil10/Heroes-Lore-Recomp/releases)**:

| Plataforma | Formato | Tamanho | Como Jogar |
| :--- | :--- | :--- | :--- |
| 🎮 **Nintendo Switch** | `.nro` | ~7 MB | Copie `heroes_lore.nro` para `/switch/heroes_lore/` no cartão SD e abra via Homebrew Menu. |
| 🪟 **Windows x64** | `.zip` | ~12 MB | Baixe `heroes_lore_windows_x64.zip`, extraia e dê dois cliques em `heroes_lore.exe` (portátil, com DLLs e assets inclusos). |
| 🐧 **Linux / Steam Deck** | `.AppImage` | ~14 MB | Dê permissão com `chmod +x heroes_lore_linux_x86_64.AppImage` e execute com dois cliques ou terminal. |
| 📱 **Android** | `.apk` | ~2.5 MB | Instale `heroes_lore_android_arm64.apk` (smartphones modernos) ou `heroes_lore_android_universal.apk`. |

---

## 🌟 Destaques do Projeto

- **Fidelidade Matemática e Lógica 1:1:** O jogo executa a partir dos binários `.class` originais do JAR, garantindo que a física, colisões, progressão de nível e IA de chefes sejam idênticos ao jogo original de celular.
- **True Widescreen (16:9 — 568x320):** Expansão real da área de visão do mapa, exibindo até **35 colunas de tiles simultaneamente** (em comparação com 15 colunas do original de 240p), pixel-perfect e sem qualquer distorção anamórfica de sprites.
- **Molduras Temáticas Clássicas (Bezels):** Para quem prefere a nostalgia da proporção 3:4 original, estão disponíveis artes laterais dedicadas (*Soltia Ancestral*, *Ardósia Escura* e *Preto Clássico*).
- **Limitador de Taxa de Quadros Preciso:** Alterne instantaneamente entre **15 FPS** (cadência clássica J2ME com frame pacer preciso) e **30 FPS Turbo** (movimentação e combates com fluidez aprimorada).
- **HUD On-Screen Display (OSD):** Banner informativo flutuante estilo console moderno com visual em vidro fosco (*frosted glass*) que confirma as alterações em tempo real.
- **Sintetizador MIDI Nativo de Alta Fidelidade:** Trilhas sonoras e efeitos MIDI executados em tempo real utilizando TinySoundFont (`tsf`) com o banco orquestrado SoundFont TimGM6mb embutido.
- **Persistência Completa (Save & Load):** Suporte nativo ao subsistema J2ME RMS (*Record Management System*) com gravação e leitura em tempo real dos 3 slots de personagens (`_k`, `_s`, `_w`) e estado global (`_o`), além de salvar configurações em `hl_settings.ini`.
- **Controles Modernizados:** Suporte plug-and-play para controles de Xbox, PlayStation, Switch Pro Controller, teclado de PC (WASD + Espaço ou Teclado Numérico) e touchscreen capacitivo.
- **Vibração Háptica (Rumble):** Feedback de impacto nos ataques e habilidades em controles compatíveis (Joy-Cons, DualSense, Xbox Controller).

---

## 🎮 Controles no Teclado, Gamepad e Touchscreen

O mapeamento foi planejado com ergonomia moderna para controles USB/Bluetooth, teclado e tela sensível ao toque, mantendo compatibilidade total com os atalhos originais do celular J2ME:

### 🕹️ Gamepad (Xbox, PlayStation, Switch Pro, 8BitDo, Steam Deck)

| Ação no Jogo | Controle Xbox / Steam Deck | Controle PlayStation | Controle Switch Pro / Joy-Con |
| :--- | :--- | :--- | :--- |
| **Movimentação (360° Contínua)** | <kbd>D-Pad</kbd> ou <kbd>Analógico Esquerdo</kbd> | <kbd>D-Pad</kbd> ou <kbd>Analógico Esquerdo</kbd> | <kbd>D-Pad</kbd> ou <kbd>Analógico Esquerdo</kbd> |
| **Atacar (Arma) / Confirmar ('5')** | <kbd>A</kbd> | <kbd>✕</kbd> | <kbd>A</kbd> |
| **Status / Cancelar (RSK)** | <kbd>B</kbd> | <kbd>○</kbd> | <kbd>B</kbd> |
| **Menu Principal / Inventário (CLR)** | <kbd>Start</kbd> *(isolado)* | <kbd>Options</kbd> *(isolado)* | <kbd>+</kbd> *(isolado)* |
| **Abrir / Fechar Minimapa ('0')** | <kbd>Select</kbd> *(toque rápido)* ou <kbd>L3</kbd> | <kbd>Share/Touchpad</kbd> *(toque rápido)* ou <kbd>L3</kbd> | <kbd>-</kbd> *(toque rápido)* ou <kbd>L3</kbd> |
| **Ataque 1 do Guardião ('1')** | <kbd>X</kbd> | <kbd>□</kbd> | <kbd>X</kbd> |
| **Ataque 2 do Guardião ('3')** | <kbd>Y</kbd> | <kbd>△</kbd> | <kbd>Y</kbd> |
| **Ataque Secundário / Habilidade ('7')**| <kbd>LB</kbd> (L1) | <kbd>L1</kbd> | <kbd>L</kbd> |
| **Usar Poção / Item Rápido ('9')** | <kbd>RB</kbd> (R1) | <kbd>R1</kbd> | <kbd>R</kbd> |
| **Alternar Poção $\leftarrow$ Anterior**| <kbd>LT</kbd> (Gatilho Esquerdo) | <kbd>L2</kbd> | <kbd>ZL</kbd> |
| **Alternar Poção $\rightarrow$ Seguinte**| <kbd>RT</kbd> (Gatilho Direito) | <kbd>R2</kbd> | <kbd>ZR</kbd> |
| 🖥️ **Proporção (3:4 <-> 16:9 Widescreen)**| <kbd>Select</kbd> + <kbd>Start</kbd> | <kbd>Share</kbd> + <kbd>Options</kbd> | <kbd>-</kbd> + <kbd>+</kbd> |
| 🖼️ **Moldura (Soltia / Ardósia / Preto)**| <kbd>Select</kbd> + <kbd>R3</kbd> | <kbd>Share</kbd> + <kbd>R3</kbd> | <kbd>-</kbd> + <kbd>R3</kbd> |
| ⚡ **Velocidade (15 FPS <-> 30 FPS Turbo)**| <kbd>R3</kbd> *(clique isolado)* | <kbd>R3</kbd> *(clique isolado)* | <kbd>R3</kbd> *(clique isolado)* |
| 📳 **Vibração / Haptics** | Rumble dinâmico | Rumble dinâmico | Rumble dinâmico |

---

### ⌨️ Teclado de Computador (PC / Linux)

| Ação no Jogo | Teclas Modernas (PC) | Teclado Numérico (J2ME) | Tecla Original |
| :--- | :--- | :--- | :--- |
| **Mover para Cima** | <kbd>W</kbd> ou <kbd>↑</kbd> | <kbd>Num 2</kbd> | `UP` / `2` |
| **Mover para Baixo** | <kbd>S</kbd> ou <kbd>↓</kbd> | <kbd>Num 8</kbd> | `DOWN` / `8` |
| **Mover para a Esquerda** | <kbd>A</kbd> ou <kbd>←</kbd> | <kbd>Num 4</kbd> | `LEFT` / `4` |
| **Mover para a Direita** | <kbd>D</kbd> ou <kbd>→</kbd> | <kbd>Num 6</kbd> | `RIGHT` / `6` |
| **Atacar (Arma) / Confirmar / Interagir** | <kbd>Espaço</kbd>, <kbd>Enter</kbd>, <kbd>J</kbd>, <kbd>Z</kbd> | <kbd>Num 5</kbd> | `FIRE` / `5` |
| **Menu Principal / Inventário** | <kbd>Tab</kbd>, <kbd>Esc</kbd>, <kbd>F1</kbd>, <kbd>C</kbd> | — | `CLR` (-8) |
| **Status / Cancelar / Fechar** | <kbd>F2</kbd>, <kbd>Backspace</kbd> | — | `RSK` (-7) |
| **Ataque 1 do Guardião** | <kbd>Q</kbd> ou <kbd>U</kbd> | <kbd>Num 1</kbd> | `1` |
| **Ataque 2 do Guardião** | <kbd>E</kbd> ou <kbd>I</kbd> | <kbd>Num 3</kbd> | `3` |
| **Ataque Secundário / Habilidade** | <kbd>K</kbd> ou <kbd>X</kbd> | <kbd>Num 7</kbd> | `7` |
| **Usar Poção / Item Rápido** | <kbd>L</kbd> ou <kbd>V</kbd> | <kbd>Num 9</kbd> | `9` |
| **Poção Anterior (Esquerda)** | <kbd>[</kbd> ou <kbd>,</kbd> | — | `35` (3×) |
| **Próxima Poção (Direita)** | <kbd>]</kbd>, <kbd>.</kbd> ou <kbd>#</kbd> | <kbd>#</kbd> (Numpad) | `#` |
| **Abrir/Fechar Minimapa** | <kbd>M</kbd>, <kbd>R</kbd>, <kbd>O</kbd> ou <kbd>0</kbd> | <kbd>Num 0</kbd> | `0` |
| **Atalhos Rápidos** | <kbd>*</kbd> | <kbd>*</kbd> (Numpad) | `*` |
| 🖼️ **Alternar Moldura (Bezel)** | <kbd>F5</kbd> | — | — |
| ⚡ **Alternar FPS (15 <-> 30 Turbo)** | <kbd>F6</kbd> ou <kbd>F8</kbd> | — | — |
| 🖥️ **Alternar Widescreen (3:4 <-> 16:9)** | <kbd>F7</kbd> ou <kbd>F10</kbd> | — | — |
| 🔄 **Checar Atualização (OTA In-App)** | <kbd>F9</kbd> ou Menu Sobre (<kbd>5</kbd> / <kbd>A</kbd>) | — | — |
| 📺 **Tela Cheia (Fullscreen)** | <kbd>F11</kbd> ou <kbd>Alt + Enter</kbd> | — | — |

---

### 📱 Controles Virtuais Touchscreen (Celular Android / Nintendo Switch)

Na tela sensível ao toque, além do D-Pad direcional analógico e dos botões ergonômicos de combate e menu, utilitários práticos ficam disponíveis na base da tela:
- **Canto Inferior Esquerdo:**
  - **Botão Olho (`👁️`):** Oculta ou reexibe os botões na tela (ideal para jogar com gamepad ou assistir cutscenes limpas).
  - **Botão Rotação (`🔄`, no Switch):** Alterna entre orientação horizontal (Paisagem 1280x720) e vertical (Retrato TATE 90° e 270° Flip Grip).
- **Canto Inferior Direito (Espelhado e Ergonômico):**
  - **Botão 16:9 (`btn_aspect`):** Alterna em tempo real entre a proporção 3:4 clássica e o modo True Widescreen expandido.
  - **Botão FPS (`btn_fps`):** Alterna instantaneamente entre 15 FPS (nostalgia original) e 30 FPS (turbo ágil).
- Os botões utilitários permanecem acessíveis mesmo com o gamepad translúcido desativado, permitindo restaurar os controles ou alterar gráficos/velocidade a qualquer momento.

---

## 🕹️ Funcionalidades Específicas por Plataforma

### 🎮 Nintendo Switch (.nro Homebrew)
- **Instalação:** Copie `heroes_lore.nro` para `sdmc:/switch/heroes_lore/heroes_lore.nro` no seu Switch desbloqueado (Atmosphere).
- **3 Modos de Rotação da Tela (Orientação Dinâmica):**
  - **Modo 0 (Paisagem Padrão - 1280x720):** Jogo centralizado na proporção clássica 240x320 com barras decorativas laterais.
  - **Modo 1 (Retrato 90° Horário):** Segure o console na vertical como um smartphone gigante.
  - **Modo 2 (Retrato 270° Anti-horário / Flip Grip):** Otimizado especialmente para acessórios como o **Flip Grip**, posicionando os Joy-Cons confortavelmente nas laterais.
- **Alternar Orientação:** Pressione <kbd>-</kbd> (Minus) a qualquer momento para ciclar entre os modos.
- **Ocultar/Reexibir Botões Virtuais:** Pressione <kbd>L3</kbd> (pressionar o analógico esquerdo).
- **Touchscreen Capacitivo:** Toques na tela capacitiva do Switch são mapeados matematicamente em 1:1, mesmo com a tela rotacionada.

### 🐧 Linux & Steam Deck (.AppImage)
- **Execução Direta:** Pacote AppImage portátil que não requer instalação de dependências:
  ```bash
  chmod +x heroes_lore_linux_x86_64.AppImage
  ./heroes_lore_linux_x86_64.AppImage
  ```
- **Steam Deck (SteamOS):** No modo Desktop, adicione o arquivo como "Jogo não-Steam" à sua biblioteca. O jogo roda diretamente no modo Gaming com suporte completo aos controles embutidos do portátil!

### 📱 Android (APK Nativo Standalone)
- **Instalação Direta:** Baixe o APK release da seção de Releases e instale no seu celular ou tablet.
- **Controles na Tela:** D-Pad virtual analógico e botões de ação com feedback tátil e suporte a multitoque suave.
- **Controles Bluetooth/USB:** Plug-and-play imediato com controles de Xbox, PlayStation, Gamesir e Razer Kishi.

---

## 🏗️ Arquitetura do Projeto

O código-fonte foi estruturado de forma desacoplada em módulos nativos limpos:

```
heroes_lore_recomp/
├── src/
│   ├── vm/                     # Motor de Execução JVM / CLDC 1.1 em C++17
│   │   ├── vm.h / vm.cpp       # Gerenciamento de classes, pool de constantes, GC com tracing de raízes
│   │   ├── interp.cpp          # Interpretador de bytecodes otimizado (focado nas instruções do jogo)
│   │   └── natives.cpp         # Implementação das APIs nativas de java/lang, java/io e java/util
│   ├── midp/                   # Emulação da camada J2ME MIDP 2.0
│   │   ├── midp.h / midp.cpp   # Displayable, Canvas, Graphics 2D, Image e RecordStore (RMS)
│   │   └── stb_image.h         # Decodificação de imagens PNG e texturas
│   ├── platform/               # Camada de Plataforma (Hardware Abstraction Layer)
│   │   ├── platform.h          # Interface de janela, loop de eventos e áudio
│   │   ├── platform_sdl.cpp    # Backend SDL2 com renderização acelerada, escala e entrada
│   │   ├── midi_synth.h        # Interface do sintetizador de áudio
│   │   └── midi_synth.cpp      # Sintetizador MIDI com TinySoundFont (TSF) + TinyMidiLoader (TML)
│   └── main.cpp                # Ponto de entrada, boot do GameMIDlet e loop principal
├── reference/
│   ├── extracted/              # Assets extraídos do JAR (paletas, fontes, mapas, sons, classes)
│   ├── decompiled_cfr/         # 90 classes originais decompiladas com CFR para auditoria
│   └── javap/                  # Bytecode original desmontado com javap
├── dist_linux/                 # Metadados de desktop, ícones e AppRun para empacotamento Linux
├── switch/                     # Ícone oficial e assets específicos do Nintendo Switch
├── android/                    # Projeto Gradle + NDK para compilação do APK Android
├── tools/                      # Scripts auxiliares de empacotamento, créditos e RomFS
├── docs/                       # Documentação técnica detalhada, mapa de classes e learnings
└── CMakeLists.txt              # Configuração de build moderna para CMake e Ninja
```

---

## ⚙️ Como Compilar o Projeto

O projeto utiliza um pipeline unificado no GitHub Actions que compila automaticamente todas as plataformas a cada push ou tag. Se preferir compilar localmente:

### 🪟 Windows (MSYS2 UCRT64)
```powershell
# Pré-requisitos: pacotes mingw-w64-ucrt-x86_64-gcc, cmake, ninja, SDL2 e SDL2_mixer
cmake -B build -G Ninja -DCMAKE_BUILD_TYPE=Release
cmake --build build
.\build\heroes_lore.exe
```

### 🐧 Linux (Ubuntu / Debian / Arch / Fedora)
```bash
# Pré-requisitos: build-essential, cmake, ninja-build, libsdl2-dev, libsdl2-mixer-dev, pkg-config
cmake -B build -G Ninja -DCMAKE_BUILD_TYPE=Release
cmake --build build
python3 tools/prepare_appimage.py
```

### 🎮 Nintendo Switch (devkitA64 + libnx)
```bash
python3 tools/prepare_switch_romfs.py
make -f Makefile.switch
# Gera: heroes_lore.nro
```

### 📱 Android (Gradle + NDK)
```bash
cd android
./gradlew assembleRelease
# Gera os APKs em android/app/build/outputs/apk/release/
```

---

## 📜 Engenharia Reversa & Documentação Técnica

Para detalhes sobre a engenharia reversa das 90 classes ofuscadas, resolução de colisões de identificadores no carregador de classes e detalhes de implementação do RMS, consulte:
- [`docs/STATUS.md`](docs/STATUS.md) — Marco atual e checklist de desenvolvimento.
- [`docs/LEARNINGS.md`](docs/LEARNINGS.md) — Aprendizados técnicos, armadilhas superadas e decisões de arquitetura.
- [`docs/CLASS_MAP.md`](docs/CLASS_MAP.md) — Mapeamento detalhado das classes do jogo.

---

## 👥 Créditos & Agradecimentos

- **Port Nativo & Recompilação (Windows, Linux, Android, Nintendo Switch):** [David Kalil Braga](https://github.com/davidkalil10) (2026)
- **Desenvolvimento Original J2ME:** *Hands-On Mobile* & *Electronic Arts (EA Mobile)*
- **Tradução Português-BR (J2ME Original):** *Open Mind Team* (Bruno Freire, Bruno Vilhena, John Peres) — Versão `v.0.0.2` (2008)

---

## ⚖️ Licença e Aviso Legal

Este projeto é uma obra de preservação histórica de software e engenharia reversa educacional. Todas as marcas registradas, títulos, gráficos e áudios originais de *Heroes Lore: Wind of Soltia* pertencem à *Hands-On Mobile* e *Electronic Arts (EA)*.
