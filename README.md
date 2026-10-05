# ⚔️ Heroes Lore: Wind of Soltia — Native Recompilation (C++17 + SDL2)

[![C++17](https://img.shields.io/badge/Language-C%2B%2B17-blue.svg)](https://en.cppreference.com/w/cpp/17)
[![Platform](https://img.shields.io/badge/Platform-Windows%20%7C%20Linux%20%7C%20Android%20%7C%20Switch-informational.svg)]()
[![Rendering](https://img.shields.io/badge/Renderer-Direct3D%2011%20%2F%20SDL2-success.svg)](https://www.libsdl.org/)
[![Status](https://img.shields.io/badge/Status-100%25%20Playable%20%26%20Save%20Working-brightgreen.svg)]()
[![Language](https://img.shields.io/badge/Game%20Lang-Portugu%C3%AAs--BR-yellow.svg)]()

Port nativo, determinístico e de alta performance do lendário RPG J2ME **Heroes Lore: Wind of Soltia** (*Hands-On Mobile / EA Mobile*), baseado na versão oficial brasileira de resolução 240x320.

Diferente de remakes feitos "no olhômetro", este projeto executa o **bytecode original decompilado e reinterpretado diretamente em C++17** com uma camada de abstração fina para MIDP 2.0 / LCDUI / RMS sobre SDL2, preservando com precisão absoluta cada fórmula de dano, status, comportamento de IA, diálogos, tabelas de eventos e taxas de drop.

---

## 🌟 Destaques

- **Fidelidade Matemática e Lógica 1:1:** O jogo roda a partir dos binários `.class` originais do JAR, garantindo que a física, colisões, progressão de nível e IA de chefes sejam idênticos ao hardware original.
- **Renderização Pixel-Perfect com Direct3D 11:** Resolução nativa de 240x320 renderizada via hardware com integer scaling, preservando a estética pixel-art original sem borrões ou distorções de aspecto.
- **Persistência Completa (Save & Load):** Suporte nativo ao subsistema de RMS (*Record Management System*) com gravação e leitura em tempo real dos 3 slots de personagens (`_k`, `_s`, `_w`) e estado global (`_o`) na pasta `data/rms/`.
- **Áudio de Baixa Latência:** Efeitos sonoros e trilha musical executados nativamente via SDL2_mixer / WASAPI.
- **Controles de PC Modernizados:** Mapeamento ergonômico no teclado com suporte tanto ao padrão WASD/Espaço quanto ao teclado numérico clássico dos celulares J2ME.
- **Portabilidade Standalone:** O executável `heroes_lore.exe` é totalmente autônomo com todas as dependências de DLLs incluídas no diretório de build.

---

## 🎮 Controles no Teclado e Gamepad

O mapeamento foi planejado com ergonomia moderna para PC (teclado e controles USB/Bluetooth) e também suporte clássico ao teclado numérico dos celulares:

### ⌨️ Teclado

| Ação no Jogo | Teclas Modernas (PC) | Teclado Numérico (J2ME) | Tecla Original |
| :--- | :--- | :--- | :--- |
| **Mover para Cima** | <kbd>W</kbd> ou <kbd>↑</kbd> (Seta Cima) | <kbd>Num 2</kbd> | `UP` / `2` |
| **Mover para Baixo** | <kbd>S</kbd> ou <kbd>↓</kbd> (Seta Baixo) | <kbd>Num 8</kbd> | `DOWN` / `8` |
| **Mover para a Esquerda** | <kbd>A</kbd> ou <kbd>←</kbd> (Seta Esquerda) | <kbd>Num 4</kbd> | `LEFT` / `4` |
| **Mover para a Direita** | <kbd>D</kbd> ou <kbd>→</kbd> (Seta Direita) | <kbd>Num 6</kbd> | `RIGHT` / `6` |
| **Atacar (Arma) / Confirmar / Interagir** | <kbd>Espaço</kbd>, <kbd>Enter</kbd>, <kbd>J</kbd>, <kbd>Z</kbd> | <kbd>Num 5</kbd> | `FIRE` / `5` |
| **Menu Principal / Inventário** | <kbd>Tab</kbd>, <kbd>Esc</kbd>, <kbd>F1</kbd> | — | `CLR` (-8) |
| **Status / Cancelar / Fechar** | <kbd>F2</kbd>, <kbd>Backspace</kbd> | — | `RSK` (-7) |
| **Ataque 1 do Guardião** | <kbd>Q</kbd> ou <kbd>U</kbd> | <kbd>Num 1</kbd> | `1` |
| **Ataque 2 do Guardião** | <kbd>E</kbd> ou <kbd>I</kbd> | <kbd>Num 3</kbd> | `3` |
| **Ataque Secundário / Habilidade** | <kbd>K</kbd> ou <kbd>X</kbd> | <kbd>Num 7</kbd> | `7` |
| **Usar Poção / Item Rápido** | <kbd>L</kbd> ou <kbd>C</kbd> | <kbd>Num 9</kbd> | `9` |
| **Poção Anterior (Esquerda)** | <kbd>[</kbd> ou <kbd>,</kbd> | — | `35` (3×) |
| **Próxima Poção (Direita)** | <kbd>]</kbd>, <kbd>.</kbd> ou <kbd>#</kbd> | <kbd>#</kbd> (Numpad) | `#` |
| **Abrir/Fechar Minimapa** | <kbd>M</kbd>, <kbd>R</kbd>, <kbd>O</kbd> ou <kbd>0</kbd> | <kbd>Num 0</kbd> | `0` |
| **Atalhos Rápidos** | <kbd>*</kbd> | <kbd>*</kbd> (Numpad) | `*` |

### 🕹️ Gamepad (Xbox, PlayStation, Switch Pro, 8BitDo)

| Ação no Jogo | Controle Xbox / Genérico | Controle PlayStation | Controle Switch Pro |
| :--- | :--- | :--- | :--- |
| **Movimentação (360° Contínua)** | <kbd>D-Pad</kbd> ou <kbd>Analógico Esquerdo</kbd> | <kbd>D-Pad</kbd> ou <kbd>Analógico Esquerdo</kbd> | <kbd>D-Pad</kbd> ou <kbd>Analógico Esquerdo</kbd> |
| **Atacar (Arma) / Confirmar ('5')** | <kbd>A</kbd> | <kbd>✕</kbd> | <kbd>B</kbd> |
| **Status / Cancelar (RSK)** | <kbd>B</kbd> | <kbd>○</kbd> | <kbd>A</kbd> |
| **Menu Principal / Inventário** | <kbd>Start</kbd> | <kbd>Options</kbd> | <kbd>+</kbd> |
| **Abrir / Fechar Minimapa ('0')** | <kbd>Select</kbd> / <kbd>Back</kbd> / <kbd>L3</kbd> | <kbd>Share</kbd> / <kbd>Touchpad</kbd> / <kbd>L3</kbd> | <kbd>-</kbd> / <kbd>L3</kbd> |
| **Ataque 1 do Guardião ('1')** | <kbd>X</kbd> | <kbd>□</kbd> | <kbd>Y</kbd> |
| **Ataque 2 do Guardião ('3')** | <kbd>Y</kbd> | <kbd>△</kbd> | <kbd>X</kbd> |
| **Ataque Secundário / Habilidade ('7')**| <kbd>LB</kbd> (L1) | <kbd>L1</kbd> | <kbd>L</kbd> |
| **Usar Poção / Item Rápido ('9')** | <kbd>RB</kbd> (R1) | <kbd>R1</kbd> | <kbd>R</kbd> |
| **Alternar Poção $\leftarrow$ Anterior**| <kbd>LT</kbd> (Gatilho Esquerdo) | <kbd>L2</kbd> | <kbd>ZL</kbd> |
| **Alternar Poção $\rightarrow$ Seguinte**| <kbd>RT</kbd> (Gatilho Direito) / <kbd>R3</kbd> | <kbd>R2</kbd> / <kbd>R3</kbd> | <kbd>ZR</kbd> / <kbd>R3</kbd> |
| **Vibração / Haptics** | Suporte a rumble em combate | Suporte a rumble em combate | Suporte a rumble em combate |

---

## 🏗️ Arquitetura do Projeto

O projeto é dividido em camadas modulares para facilitar futuras compilações em outras plataformas (como WebAssembly/Emscripten, Android, Linux e Nintendo Switch):

```
heroes_lore_recomp/
├── src/
│   ├── vm/                     # Motor de Execução JVM / CLDC 1.1 em C++17
│   │   ├── vm.h / vm.cpp       # Gerenciamento de classes, GC com tracing de raízes, GIL
│   │   ├── interp.cpp          # Interpretador de bytecodes otimizado (sem wide/float)
│   │   ├── classfile.cpp       # Parser binário de arquivos .class Java
│   │   └── natives.cpp         # Implementação de java/lang, java/io e java/util
│   ├── midp/                   # Emulação da camada J2ME MIDP 2.0
│   │   ├── midp.h / midp.cpp   # Displayable, Canvas, Graphics, Image e RecordStore (RMS)
│   │   └── stb_image.h         # Decodificação de imagens PNG e texturas
│   ├── platform/               # Camada de Plataforma (Hardware Abstraction Layer)
│   │   ├── platform.h          # Interface de janela, loop de eventos e áudio
│   │   └── platform_sdl.cpp    # Backend SDL2 com Direct3D 11 e WASAPI
│   └── main.cpp                # Ponto de entrada, boot do GameMIDlet e loop principal
├── reference/
│   ├── extracted/              # Assets extraídos do JAR (paletas, fontes, mapas, sons)
│   ├── decompiled_cfr/         # 90 classes originais decompiladas com CFR
│   └── javap/                  # Bytecode original desmontado com javap
├── tests/
│   └── test_stream_rms.cpp     # Teste unitário automatizado de streams e persistência RMS
├── docs/                       # Documentação técnica, engenharia reversa e learnings
└── CMakeLists.txt              # Configuração de build moderna para CMake e Ninja
```

---

## ⚙️ Compilação e Execução

### Pré-requisitos (Windows)
Recomenda-se o ambiente **MSYS2 UCRT64** com os seguintes pacotes instalados:
- `gcc` / `g++` (suporte a C++17)
- `cmake` (>= 3.20)
- `ninja`
- `mingw-w64-ucrt-x86_64-SDL2`
- `mingw-w64-ucrt-x86_64-SDL2_mixer`

### Compilando via Terminal

1. Abra o terminal (PowerShell ou Prompt de Comando) e adicione o compilador UCRT64 ao PATH:
```powershell
$env:Path = "C:\msys64\ucrt64\bin;$env:Path"
```

2. Configure e compile o projeto com CMake:
```powershell
cmake -B build -G Ninja -DCMAKE_BUILD_TYPE=Release
cmake --build build --config Release
```

3. Execute o jogo:
```powershell
.\build\heroes_lore.exe
```

> **Nota:** As DLLs necessárias (`SDL2.dll`, `SDL2_mixer.dll`, bibliotecas do GCC e codecs de áudio) são copiadas automaticamente para a pasta `build/` no pós-build do CMake. Você pode rodar o jogo diretamente dando dois cliques em `heroes_lore.exe` dentro da pasta `build`.

---

## 📱 Versão Nativa Android (APK Standalone)

O jogo conta com port nativo standalone em **C++17 + SDL2** compilado para Android, sem necessidade de emuladores ou Winlator!

### 📥 Instalação Direta
Os APKs compilados com **Split per ABI** encontram-se em `bin/`:
- **`bin/heroes_lore-arm64-v8a.apk`** (ou `bin/heroes_lore.apk`): **~2.35 MB** — Versão nativa pura de 64-bit (`arm64-v8a`), recomendada para todos os smartphones modernos!
- **`bin/heroes_lore-armeabi-v7a.apk`**: **~2.18 MB** — Versão de 32-bit para aparelhos legados.
- **`bin/heroes_lore-universal.apk`**: **~3.49 MB** — Versão universal (contém ambas as arquiteturas).

### 🕹️ Controles no Android
- **Controles por Toque (Touchscreen Multi-touch):**
  - D-Pad virtual translúcido no canto inferior esquerdo (suporta movimentação contínua a 60 FPS).
  - Botão de Ataque/Confirmar (<kbd>5</kbd>) e botões de Habilidade (<kbd>1</kbd>, <kbd>3</kbd>), Poção (<kbd>7</kbd>) e Item (<kbd>9</kbd>) no canto inferior direito.
  - Botões superiores de acesso rápido: <kbd>MENU</kbd> (Menu in-game), <kbd>MAP</kbd> (Mapa mundi) e <kbd>RSK</kbd>.
  - Alternância rápida de poções: botões <kbd>&lt;</kbd> e <kbd>&gt;</kbd>.
  - Em telas widescreen no modo paisagem, as barras pretas laterais (letterbox) funcionam como painéis de toque adicionais para os polegares.
- **Controles Físicos (Bluetooth / USB-C):**
  - Plug-and-play imediato com controles de Xbox, PlayStation, Gamesir, Razer Kishi, etc., com vibração háptica (rumble).

### 🛠️ Como recompilar o APK (opcional):
```powershell
cd android
.\gradlew.bat assembleDebug
```
O APK gerado ficará em `android/app/build/outputs/apk/debug/app-debug.apk`.

---

## 🧪 Verificação e Testes Automatizados

O projeto inclui suíte de testes de estresse para validar operações críticas como fluxos de stream polimórficos e persistência binária do RMS:

```powershell
$env:Path = "C:\msys64\ucrt64\bin;$env:Path"
g++ -Isrc -Ithird_party -std=gnu++17 tests/test_stream_rms.cpp build/CMakeFiles/heroes_lore.dir/src/midp/midp.cpp.obj build/CMakeFiles/heroes_lore.dir/src/platform/platform_sdl.cpp.obj build/CMakeFiles/heroes_lore.dir/src/vm/interp.cpp.obj build/CMakeFiles/heroes_lore.dir/src/vm/natives.cpp.obj build/CMakeFiles/heroes_lore.dir/src/vm/vm.cpp.obj -lmingw32 -lSDL2main -lSDL2 -lSDL2_mixer -o build/test_stream_rms.exe
.\build\test_stream_rms.exe
```

---

## 📜 Histórico & Engenharia Reversa

Para detalhes sobre a engenharia reversa das 90 classes ofuscadas, resolução de colisões de identificadores no carregador de classes e detalhes de implementação do RMS, consulte:
- [`docs/STATUS.md`](docs/STATUS.md) — Marco atual e checklist de desenvolvimento.
- [`docs/LEARNINGS.md`](docs/LEARNINGS.md) — Aprendizados técnicos, armadilhas superadas e decisões de arquitetura.
- [`docs/CLASS_MAP.md`](docs/CLASS_MAP.md) — Mapeamento detalhado das classes do jogo.

## 👥 Créditos & Agradecimentos

- **Port Nativo & Recompilação (Windows, Linux, Android, Nintendo Switch):** [David Kalil Braga](https://github.com/davidkalil10) (2026)
- **Desenvolvimento Original J2ME:** *Hands-On Mobile* & *Electronic Arts (EA Mobile)*
- **Tradução Português-BR (J2ME Original):** *Open Mind Team* (Bruno Freire, Bruno Vilhena, John Peres)

---

## ⚖️ Licença e Aviso Legal

Este projeto é uma obra de preservação histórica de software e engenharia reversa educacional. Todas as marcas registradas, títulos, gráficos e áudios originais de *Heroes Lore: Wind of Soltia* pertencem à *Hands-On Mobile* e *Electronic Arts (EA)*.
