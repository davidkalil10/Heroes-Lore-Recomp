# ⚔️ Heroes Lore: Wind of Soltia — Native Recompilation (C++17 + SDL2)

[![C++17](https://img.shields.io/badge/Language-C%2B%2B17-blue.svg)](https://en.cppreference.com/w/cpp/17)
[![Platform](https://img.shields.io/badge/Platform-Windows%20(Native)-informational.svg)](https://www.microsoft.com/windows)
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

## 🎮 Controles no Teclado

O mapeamento foi planejado tanto para jogadores que preferem a ergonomia de teclado moderno de PC quanto para quem está habituado ao layout do teclado numérico dos celulares antigos:

| Ação no Jogo | Teclas Modernas (PC) | Teclado Numérico (J2ME) | Tecla Original |
| :--- | :--- | :--- | :--- |
| **Mover para Cima** | <kbd>W</kbd> ou <kbd>↑</kbd> (Seta Cima) | <kbd>Num 2</kbd> | `UP` / `2` |
| **Mover para Baixo** | <kbd>S</kbd> ou <kbd>↓</kbd> (Seta Baixo) | <kbd>Num 8</kbd> | `DOWN` / `8` |
| **Mover para a Esquerda** | <kbd>A</kbd> ou <kbd>←</kbd> (Seta Esquerda) | <kbd>Num 4</kbd> | `LEFT` / `4` |
| **Mover para a Direita** | <kbd>D</kbd> ou <kbd>→</kbd> (Seta Direita) | <kbd>Num 6</kbd> | `RIGHT` / `6` |
| **Ação / Confirmar / Atacar** | <kbd>Espaço</kbd>, <kbd>Enter</kbd>, <kbd>J</kbd>, <kbd>Z</kbd> | <kbd>Num 5</kbd> | `FIRE` / `5` |
| **Menu Principal (Softkey Esquerda)** | <kbd>F1</kbd> ou <kbd>Tab</kbd> | — | `LSK` (-6) |
| **Status / Cancelar (Softkey Direita)**| <kbd>F2</kbd> | — | `RSK` (-7) |
| **Limpar / Fechar Menu** | <kbd>Esc</kbd>, <kbd>Backspace</kbd>, <kbd>M</kbd> | — | `CLR` (-8) |
| **Atalho Habilidade / Magia 1** | <kbd>Q</kbd> | <kbd>Num 1</kbd> | `1` |
| **Atalho Habilidade / Magia 2** | <kbd>E</kbd> | <kbd>Num 3</kbd> | `3` |
| **Atalho Habilidade / Magia 3** | <kbd>K</kbd> ou <kbd>X</kbd> | <kbd>Num 7</kbd> | `7` |
| **Atalho Habilidade / Magia 4** | <kbd>L</kbd> ou <kbd>C</kbd> | <kbd>Num 9</kbd> | `9` |
| **Usar Poção / Item Rápido** | <kbd>R</kbd> | <kbd>Num 0</kbd> | `0` |
| **Menu Rápido / Alternar** | — | <kbd>*</kbd> / <kbd>#</kbd> | `*` / `#` |

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

---

## ⚖️ Licença e Aviso Legal

Este projeto é uma obra de preservação histórica de software e engenharia reversa educacional. Todas as marcas registradas, títulos, gráficos e áudios originais de *Heroes Lore: Wind of Soltia* pertencem à *Hands-On Mobile* e *Electronic Arts (EA)*.
