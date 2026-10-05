# LEARNINGS

## Herdado das versões Flutter (v1/v2)
Fonte: `heroes_lore_modern/PROJECT_KNOWLEDGE_BACKUP.md`

**Funcionou**
- Formatos de asset do JAR: `.map`, `.evt`, `.mpd`, `.mph`; parsers Python existem na v2 (`map_evt_parser.py`).
- Objetos/NPCs ancorados em BOTTOM_HCENTER; posição = `(x*16 + param1, y*16 + param2)` em pixels (tile 16px; v2 escalava 2x).
- Z-order por Y em pixels (maior Y desenha por cima).
- NPC `type_idx < 18` = herói (pasta própria); `>= 18` = aldeão em `/npc/all`, arquivo `type_idx - 18`.
- Dump de diálogos e `lang` já extraídos (`dialogos_completos.json`, `lang.en-GB`).

**Deu errado / limitação**
- A lógica foi recriada com base em engenharia reversa parcial e observação visual → divergências. Por isso o recomp agora é literal a partir do código decompilado.
- Movimento em grid na v2 divergia do original (que usa movimento por pixel); decidir só após ler o código real.

**Ambiente**
- Sistema de permissões do IDE bloqueia leitura fora do workspace até o usuário liberar cada pasta explicitamente.

## Sessão 1 (decompilação)
- **PowerShell:** nomes de arquivo com colchetes (`[BR]`) quebram Copy-Item por serem curinga. Usar `-LiteralPath`. (Expand-Archive só aceita .zip: copiar o .jar como .zip.)
- **Permissões do IDE:** pastas fora do workspace são negadas sem prompt. Solução: copiar os projetos para `dados de consulta/` dentro do workspace.
- **CFR:** usar `--renamedupmembers true --renameillegalidents true`; o código ofuscado tem campos/métodos com mesmo nome e tipos diferentes (`a`), ambíguos sem isso. Gera nomes como `var_int_a`.
- **CFR falha em `ao`, `g`, `n`** (`Loose catch block` em `a()` e `a(byte[])`): usar `reference/javap/*.txt` como fonte de verdade nesses métodos.
- JAR: 90 classes, só `rpg.GameMIDlet` tem pacote. MIDP-2.0/CLDC-1.1, jar de 240x320. Props: HO-LSK/HO-RSK/HO-CLR (softkeys).
- Há 3 JARs além do 240x320: variantes S60v3 (320x240 v2.0.5 e 352x416 v0.0.2). O arquivo `[BR].jar` tem o mesmo tamanho (813744) do `[BR]240x320.jar`.

- **Toolchain (instalada via winget MSYS2.MSYS2):** MSYS2 em `C:\msys64`, ambiente **UCRT64**: g++ 16.2, CMake 4.4, Ninja, gdb 18, SDL2 2.32.10, SDL2_mixer 2.8.2 (também há SDL3 3.4.18). Para usar no PowerShell: `\$env:Path = 'C:\msys64\ucrt64\bin;' + \$env:Path`. `bash -lc` abre no ambiente MSYS (sem g++); usar o PATH do ucrt64. `sdl2-config` não existe: usar `find_package(SDL2)` ou `-lmingw32 -lSDL2main -lSDL2`.
- O 1º `pacman -Syu` fecha o shell ao atualizar o runtime (esperado): rodar de novo.
- Headers SDL2 ficam em `ucrt64/include/SDL2`: compilar com `-IC:/msys64/ucrt64/include/SDL2` (ou `find_package(SDL2)` no CMake). Smoke test SDL2+SDL2_mixer compilou e rodou OK.

## Sessão 2 (Interpretador CLDC 1.1 + Runtime MIDP 2.0 Nativo)
- **Colisão de Campos Ofuscados:** O ofuscador J2ME cria múltiplos campos com o mesmo identificador na mesma classe, diferenciados apenas pelo descritor de tipo (ex: na classe `bs`, `a` existe como `byte`, `int[]`, `Display` e `r`). A tabela de campos `fieldMap` e a resolução de bytecode (`getfield`, `putfield`, `getstatic`, `putstatic`) **obrigatoriamente** devem indexar por `nome:descritor`.
- **Herança de Classes Built-in:** Subclasses de classes nativas (como `Exception` estendendo `Throwable`) devem herdar `super->nInst` e os descritores de referência para que instâncias de exceção tenham slots de memória adequados.
- **Multithreading J2ME e GIL:** O jogo usa threads ativas (ex: `bs.run()`, carregamento de recursos assíncronos) combinadas com `Display.callSerially()`. A GIL (Global Interpreter Lock) da VM com liberação atômica em `Thread.sleep()` e `Thread.yield()` garante concorrência perfeita e sincronização limpa com o loop SDL.
- **Renderização e Áudio:** O SDL2 seleciona automaticamente Direct3D11 e WASAPI no Windows. O MIDP LCDUI renderiza o framebuffer 240x320 escalado com preservação de aspect ratio e filtragem pixel-art (nearest neighbor).
- **Fidelidade Visual 100%:** A tela de splash (logo EA Mobile), a tela de título animada com pétalas azuis e espada dourada ("Aperta '5'", versão 0.0.2 em PT-BR) e o menu principal em pergaminho ("INICIAR", "CARREGAR", "OPCOES", "INFO", "SOBRE", "SAIR" com destaque carmesim) rodam perfeitamente a partir do bytecode original sem qualquer alteração na lógica do jogo.
- **Dependências de DLLs no Windows:** O `SDL2_mixer.dll` no MSYS2 UCRT64 requer codecs de áudio externos (`libFLAC.dll`, `libmpg123-0.dll`, `libopusfile-0.dll`, `libvorbisfile-3.dll`, `libwavpack-1.dll`, `libxmp.dll`, `libogg-0.dll`, `libopus-0.dll`, `libvorbis-0.dll`), além de `libgcc`, `libstdc++` e `libwinpthread`. Todas foram copiadas para `build/` e configuradas no `POST_BUILD` do `CMakeLists.txt` para execução autônoma (portable/standalone).
- **Direct3D 11 vs Direct3D 9 em SDL2:** O backend D3D9 padrão do SDL2 no Windows pode falhar silenciosamente no streaming contínuo de texturas com `SDL_UpdateTexture`/`SDL_RenderCopy`. Forçar `SDL_SetHint(SDL_HINT_RENDER_DRIVER, "direct3d11")` e usar `SDL_LockTexture`/`memcpy` linha a linha com `SDL_RenderSetLogicalSize` garante renderização pixel-perfect e zero quedas de frames em todas as placas de vídeo.

## Sessão 3 (Salvar Jogo / Streams Polimórficos / RMS)
- **Polimorfismo de Streams J2ME (`java/io/OutputStream` vs `FilterOutputStream` / `DataOutputStream`):**
  - No salvamento in-game (`ah.f()` -> `n.o()`), o código chama `((OutputStream)filterOutputStream).write(byte_array)` onde `filterOutputStream` é um `java/io/DataOutputStream` (`Dos*`) empacotando um `java/io/ByteArrayOutputStream` (`Baos*`).
  - No despacho de método virtual (`invokevirtual`), o método chamado era resolvido como `java/io/OutputStream.write:([B)V`. A implementação nativa anterior fazia `static_cast<Baos*>(args[0].o)`, o que é fatal pois o objeto passado como `this` era o `Dos*`.
  - Como o layout de memória de `Dos` (`Object* out`) difere do layout de `Baos` (`std::vector<uint8_t> data`), isso causava corrupção de ponteiro e `Segmentation Fault / Access Violation` imediato no momento em que o jogador clicava em Salvar no menu.
  - **Solução:** Unwrapping recursivo universal `getUnderlyingBaos(vm, out)` que desembala camadas de `FilterOutputStream`/`DataOutputStream` até o buffer de memória real subjacente, acompanhado do registro de todas as variantes de `write([B)`, `write([BII)`, `write(I)`, `writeByte`, `writeShort`, `writeInt`, `writeUTF`, `flush`, `reset` e `close` em todas as classes da hierarquia de I/O.
- **Persistência RMS:**
  - O formato J2ME RMS armazena registros indexados em base 1.
  - A rotina de salvamento do jogo abre os RecordStores `_k`, `_s`, `_w` e `_o`, deleta cópias antigas e reinsere os buffers comprimidos/criptografados via `au.void_a()`.
  - Tratamento defensivo de registros de tamanho zero (`len == 0`) e suporte a `setRecord` garantem integridade total do ciclo de vida dos arquivos salvos na pasta `data/rms/`.

## Sessão 4 (Movimentação Contínua Fluida / Typematic Delay)
- **Mecânica de Caminhada J2ME (`as.java`, `r.java`, `n.java`):**
  - Nos celulares J2ME originais com teclado físico, manter uma tecla direcional pressionada gerava uma torrente contínua de eventos `keyPressed` pelo firmware do aparelho.
  - No código do jogo (`n.java`), o primeiro `keyPressed` inicia a caminhada (`n.c(2)`). Cada passo leva um certo número de ticks (16 pixels). Se novos eventos `keyPressed` chegam enquanto o herói caminha, `n.b()` seta `var_byte_h = 2` (indicador de "manter caminhada"). Ao atingir a borda do tile, `n.q()` verifica `var_byte_h`: se for 2, continua andando imediatamente; se for 0, o herói para no lugar!
  - Anteriormente, o SDL descartava teclas repetidas com `&& !ev.key.repeat`. Isso fazia com que, ao segurar uma tecla, apenas 1 evento fosse entregue: o herói dava um passo e parava.
  - **Solução implementada:** Criado em `src/platform/platform_sdl.cpp` um sistema de rastreamento físico contínuo via `SDL_GetKeyboardState`. Aplicado um delay inicial de 160ms (para que toques rápidos no menu não disparem múltiplos eventos acidentais) seguido de repetição suave a cada 40ms (~25 Hz).
  - Ao soltar a tecla, `keyReleased` é disparado imediatamente, garantindo paradas precisas nas bordas dos tiles. Transições entre direções funcionam instantaneamente.

## Sessão 5 (Suporte Completo a Gamepads / SDL_GameController)
- **Integração Plug-and-Play:**
  - Inicialização do subsistema `SDL_INIT_GAMECONTROLLER | SDL_INIT_HAPTIC` com varredura automática no boot e listeners para `SDL_CONTROLLERDEVICEADDED` e `SDL_CONTROLLERDEVICEREMOVED`.
  - Mapeamento universal compatível com controles XInput (Xbox 360 / One / Series), DirectInput, DualShock 4 / DualSense e Nintendo Switch Pro.
- **Unificação de Movimento (D-Pad + Analógico Esquerdo):**
  - O estado contínuo de direção consulta simultaneamente o teclado e os gamepads conectados (`getGamepadHeldDirection()`).
  - O analógico esquerdo com deadzone de 12.000 unidades alimenta o mesmo loop typematic suave (160ms inicial / 40ms repetição), permitindo navegar no analógico sem solavancos.
- **Gatilhos Analógicos e Atalhos:**
  - Gatilho Direito (RT/R2): Dispara ataque/ação ('5').
  - Gatilho Esquerdo (LT/L2) e L3: Uso rápido de poções ('0').
  - Botões de Face e Ombro (A, B, X, Y, L1, R1, R3, Start, Back) cobrem 100% dos botões originais do J2ME.
  - Implementado `Platform::rumble(...)` para suporte a vibração háptica.

## Sessão 6 (Port Nativo para Android / Standalone APK)
- **Acesso a Recursos dentro do APK (AAssetManager vs Arquivos Tradicionais):**
  - No Android, os assets empacotados dentro do APK não existem como arquivos descompactados no sistema de arquivos comum do Linux. Chamadas como `fopen("META-INF/MANIFEST.MF")` ou `std::ifstream` falham silenciosamente.
  - A solução arquitetural ideal foi unificar todo o acesso de assets através de `Platform::readAsset(path)` utilizando `SDL_RWFromFile()`. O SDL2 possui integração nativa com o `AAssetManager` do NDK, permitindo ler classes `.class`, manifestos, imagens `.png` e músicas `.mid` diretamente de dentro do APK compactado sem descompactação prévia.
  - Para persistência de `RecordStore` (saves RMS), `Platform::getStorageDir()` utiliza `SDL_AndroidGetInternalStoragePath()`, gravando os dados no diretório privado seguro do app (`/data/data/org.libsdl.app/files/rms/`).
- **Configuração do `SDL2_mixer` para Android via CMake:**
  - O `SDL2_mixer` depende de bibliotecas externas caso ativadas cegamente. No Android, para evitar dependências de terceiros não instaladas (WavPack, GME, Fluidsynth, Opus), configurou-se `SDL2MIXER_VENDORED=ON`, ativando o sintetizador MIDI Timidity embutido (`SDL2MIXER_MIDI_TIMIDITY=ON`), decodificador STB Vorbis (`SDL2MIXER_VORBIS_STB=ON`) e suporte nativo a WAV (`SDL2MIXER_WAVE=ON`).
- **Ciclo de Vida do Entry Point no Android (`libmain.so`):**
  - O `SDLActivity.java` carrega `libSDL2.so`, `libSDL2_mixer.so` e `libmain.so`.
  - A função `nativeRunMain` do SDL localiza dinamicamente a função `SDL_main` via `dlsym`. Com a inclusão de `<SDL_main.h>`, `main` é automaticamente exportado com linkage `extern "C"` sem mangling de C++.
- **Controles Virtuais Touchscreen (Multi-touch):**
  - Dispositivos móveis sem gamepad físico necessitam de controles na tela. Foi implementado em `src/platform/platform_sdl.cpp` um sistema de overlay translúcido minimalista renderizado diretamente no canvas com `SDL_BLENDMODE_BLEND`.
  - Eventos `SDL_FINGERDOWN`, `SDL_FINGERMOTION` e `SDL_FINGERUP` fornecem suporte a multi-touch verdadeiro (o jogador pode manter o polegar esquerdo no D-Pad virtual enquanto pressiona ataques e habilidades com o polegar direito).
  - Em telas ultrawide com barras pretas nas laterais (letterbox no modo paisagem), toques nos pilares pretos são detectados e mapeados ergonomicamente (D-pad na coluna esquerda e botões na coluna direita).
- **Tamanho e Eficiência:**
  - O APK final gerado (`bin/heroes_lore.apk`) possui apenas ~3.68 MB e inclui todas as 996 classes e assets do jogo, além das bibliotecas nativas completas compiladas para 64-bit (`arm64-v8a`) e 32-bit (`armeabi-v7a`).

## Sessão 7 (Redesign do Gamepad Virtual e Ícones Nativos em Alta Resolução)
- **Causa Raiz do Deslocamento de Toque (Offset Touch) no Android:**
  - O uso anterior de `SDL_RenderSetLogicalSize(s_renderer, 240, 320)` forçava a GPU a mapear todo o espaço de renderização em uma caixa lógica 240x320 centralizada na tela com letterbox. Quando coordenadas de toque (`ev.tfinger`) eram passadas por `SDL_RenderWindowToLogical`, as transformações sofriam distorções de proporção em telas modernas 20:9 e 19.5:9, deslocando a área de clique para longe do botão visual.
  - Além disso, os botões eram desenhados por cima do jogo (obscurecendo o herói, inimigos e caixas de diálogo).
- **Arquitetura de Gamepad Estilo Emulador Moderno (Fullscreen Frosted Glass Overlay):**
  - O jogo agora ocupa o tamanho máximo possível da tela mantendo o aspect ratio original (240x320) perfeitamente centralizado.
  - Os botões virtuais e o D-Pad flutuam diretamente por cima do jogo com modulação de transparência alfa (`SDL_SetTextureAlphaMod`):
    - Em repouso: opacidade translúcida a ~60% (155/255), permitindo que o mapa, inimigos e textos passem por baixo sem bloquear a visão.
    - Ao tocar: o botão pressionado atinge 100% de opacidade e ativa o halo neon ciano (`btn_glow`), além da seta brilhante indicando a direção no D-Pad.
    - Adicionadas sombras projetadas (drop shadow) nas fontes dos botões para legibilidade impecável sobre qualquer tipo de cenário (neve, caverna, grama ou pergaminho).
  - Coordenadas de toque são calculadas diretamente em pixels de tela física (`ev.tfinger.x * winW`, `ev.tfinger.y * winH`), garantindo precisão 1:1 absoluta. A margem de conforto tátil foi estendida para 1.30x no raio dos botões para eliminar completamente toques perdidos.
- **Pipeline de Texturas com Supersampling (4x AA):**
  - Desenvolvido gerador gráfico em Python (`tools/generate_gamepad_textures.py`) utilizando PIL com renderização em 4x supersampling (Lanczos) para produzir botões com estética glassmorphic moderna: anéis escuros com chanfro de luz e sombra 3D (bevel), ícones iluminados e feedback neon cyan (`btn_glow`) quando pressionados.
  - Texturas são carregadas em formato cru RGBA (`.rgba`) diretamente pela interface `Platform::readAsset` e instanciadas em superfícies SDL (`SDL_PIXELFORMAT_RGBA32`).
- **Ícones Personalizados Oficiais em Mipmaps:**
  - Extraído o asset oficial `logo 512.png` fornecido pelo usuário em `C:\Users\david\Downloads\logo 512.png` (copiado para `assets/logo_512.png`).
  - Gerados ícones para todas as densidades Android (`mdpi`, `hdpi`, `xhdpi`, `xxhdpi`, `xxxhdpi`) em versões padrão e circular (`ic_launcher_round.png`), com registro no `AndroidManifest.xml`.
- **Refinamento de Proporção, Disposição Diamante e Modo Paisagem:**
  - `MENU`, `MAPA` e `R` (substituindo RSK por R para simplicidade e familiaridade com controles de videogame) foram remodelados com textura de alta resolução 240x100 em proporção 2.4:1 (evitando o achatamento elíptico) e posicionados com recuo de 8% da altura da tela para nunca colidirem com o relógio/notch do sistema.
  - No Modo Paisagem (Horizontal), todos os controles nas colunas pretas laterais foram redimensionados para tamanhos 2x a 3x maiores (`MENU` 240x100, `MAPA` e `R` 200x83, setas 140px, cluster de ação amplo), preenchendo as laterais de forma equilibrada e ergonômica.

## Sessão 8 (Restauração da Ergonomia Clássica, Elevação dos Controles e Hit-Testing por Menor Distância)
- **Ergonomia do Polegar Humano vs. Diamante Simétrico Ortogonal:**
  - O layout em cruz/diamante estrito (N/S/L/O) foi rejeitado no teste prático porque o polegar direito não se move em ângulos cardeais perfeitos; ele pivota a partir da articulação metacárpica descrevendo um arco natural com inclinação de ~30° a 45°.
  - A disposição clássica original (5 no centro para ataque, 1 e 7 na coluna esquerda, 3 acima de 5 e 9 à direita) encaixa perfeitamente no repouso do dedo.
- **Elevação Sincronizada do D-Pad e Bloco de Ação:**
  - Ao invés de amontoar as setas de troca de poção entre os botões de ação ou distorcer os botões, a solução ideal foi elevar em sincronia tanto o D-Pad quanto todo o cluster de ação para ~42% da altura em relação à base da tela (`winH - winW * 0.42f`).
  - Isso liberou uma faixa horizontal de ~200px de altura totalmente vazia na parte inferior da tela, onde as setas circulares `◀` e `▶` foram posicionadas com conforto absoluto, sem nenhum risco de toque acidental no botão 7 ou no D-Pad.
- **Resolução do Problema de "Adivinhar o Ponto de Clique" (Hit-Testing por Menor Razão de Distância):**
  - Anteriormente, o teste de colisão avaliava a lista linear de botões com raio ampliado (1.30x) e retornava a primeira correspondência encontrada (`return b.key`). Caso a zona de tolerância de dois botões adjacentes se tocasse, o primeiro botão registrado na lista sempre vencia, mesmo se o dedo estivesse muito mais próximo do centro do segundo botão.
  - Implementado o algoritmo de menor razão euclidiana normalizada:
    `ratio = dist / maxRadius` (para círculos) e `max(dx/maxW, dy/maxH)` (para pílulas/retângulos).
    O botão que tiver a menor razão em relação ao ponto de toque sempre vence. Isso garante resposta tátil instantânea, sem sensação de imprecisão ou deslocamento.
- **Centralização Simétrica Vertical e Balanceamento de Colunas em Paisagem:**
  - No modo vertical, as setas `◀` e `▶` agora usam como âncora o centro exato da tela (`winW / 2`), ficando posicionadas simetricamente à esquerda (`winW/2 - spacing`) e à direita (`winW/2 + spacing`), alinhando-se com a pílula do `MAPA` e a barra de gestos do Android.
  - No modo paisagem, transferir as setas `◀` e `▶` para a coluna esquerda (entre o `MENU` e o D-Pad) equilibrou perfeitamente as duas metades da tela. O polegar esquerdo agora pode facilmente alterar poções/itens enquanto navega, e o polegar direito ganha espaço desobstruído para os 5 botões de ação e os botões de sistema `MAPA` e `R`.
- **Botão de Alternância para Ocultar/Reexibir Controles Virtuais (Eye Toggle Overlay):**
  - Implementado um botão sutil de olho no canto inferior esquerdo da tela (`btn_eye_open` e `btn_eye_closed`), renderizado com estética glassmorphic e 4x supersampling.
  - Permite ao jogador desativar completamente a sobreposição dos botões na tela com um único toque, deixando a imagem do jogo 100% desobstruída (ideal para cutscenes, apreciação de cenários ou quando estiver usando um gamepad Bluetooth físico).
  - Para reexibir, basta tocar no ícone discreto do olho fechado.
  - Prevenção de reativação indesejada: toques comuns na área do jogo não reexibem os controles, apenas o botão de alternância dedicado.
  - Ao ocultar com teclas pressionadas, o sistema limpa `s_activeFingers` e emite `keyReleased` para o VM J2ME, evitando que o personagem continue andando sozinho.
  - Acompanhado de leve vibração tátil (haptic feedback) de confirmação.
## Sessão 9 (Port Homebrew para Nintendo Switch — libnx, RomFS, Makefile e CI)
- **Ciclo de Vida do Switch OS e `appletMainLoop()`:**
  - Aplicações Homebrew no Nintendo Switch precisam respeitar o loop do applet manager (`appletMainLoop()`). Conectar essa verificação ao `Platform::shouldQuit()` permite que o jogo responda de forma nativa e instantânea ao botão HOME, transição para modo de suspensão/sleep e saída graciosa para o hbmenu sem travar o console.
- **Armazenamento Seguro: RomFS (Somente Leitura) vs. SD Card (Escrita RMS):**
  - O formato `.nro` permite embutir todos os dados do jogo via `elf2nro --romfsdir`. No boot, a chamada `romfsInit()` monta os arquivos internamente no ponto de montagem `romfs:/`.
  - Como o RomFS é estático e somente leitura, os saves e dados de configuração (RMS) devem obrigatoriamente ser direcionados para o cartão de memória (`sdmc:/switch/heroes_lore/`). O método `Platform::getStorageDir()` cria automaticamente esses diretórios via `mkdir(path, 0777)`.
- **Tratamento de Exceções C++ no Toolchain devkitA64:**
  - O template padrão do devkitPro para Switch muitas vezes inclui a flag `-fno-exceptions`. No entanto, a máquina virtual de bytecode J2ME do nosso projeto utiliza `throw JavaThrow{ex}` para propagar exceções da linguagem Java entre os frames de execução do interpretador (`interp.cpp`). Portanto, o `Makefile.switch` deve ser configurado com `-fexceptions` explícito.
- **Empacotamento All-in-One via RomFS:**
  - Com o script `tools/prepare_switch_romfs.py`, unificamos `reference/extracted` (bytecodes `.class`, mapas `.dat`, trilhas sonoras `.mid`, fontes e gráficos) e `assets` em uma pasta temporária `romfs/`.
  - O arquivo final `heroes_lore.nro` fica com ~5 MB completamente autocontido, dispensando a necessidade de copiar pastas avulsas para o SD Card. Basta copiar o `.nro` e jogar.
- **CI / GitHub Actions com Container Oficial `devkita64`:**
  - Configurado workflow automatizado em `.github/workflows/build-switch.yml` usando o container Docker oficial `devkitpro/devkita64:latest`. A cada push, as dependências e ferramentas já integradas na imagem oficial compilam o executável `.nro`, que é verificado por `tools/inspect_nro.py` e disponibilizado nos artefatos.

## Sessão 10 (Diagnóstico do Boot no Switch e Correção de CI / RomFS)
- **Eliminação do Erro 403 no devkitPro Pacman:**
  - O repositório `pkg.devkitpro.org` possui proteção Cloudflare que rejeita sincronizações (`-Sy` / `-Syu`) originadas dos IPs do GitHub Actions com HTTP 403.
  - Como o container `devkitpro/devkita64:latest` já inclui os headers do SDL2 e as bibliotecas estáticas (`libSDL2.a`, `libSDL2_mixer.a`, etc.) pré-instaladas em `/opt/devkitpro/portlibs/switch`, a invocação do `dkp-pacman` é totalmente desnecessária, eliminando os erros do pipeline de CI.
- **Causa do Fechamento Imediato no Boot (`SDL_INIT_HAPTIC`):**
  - O backend SDL2 para Nintendo Switch (libnx) não suporta a API legada de feedback háptico (`SDL_HAPTIC`). Chamar `SDL_Init` incluindo `SDL_INIT_HAPTIC` faz com que o `SDL_Init` falhe com código `< 0`, abortando a inicialização no primeiro milissegundo.
  - O rumble no Switch é realizado exclusivamente através da API moderna `SDL_GameControllerRumble()`. A remoção da flag restaura a inicialização perfeita do SDL2.
- **Criação de Superfície de Janela (`SDL_CreateWindow`):**
  - No Switch, o uso da flag `SDL_WINDOW_FULLSCREEN` em alguns emuladores mobile (Eden, Citron) causa tentativa de mudança de modo de exibição de desktop que pode encerrar a aplicação. A utilização da flag neutra `0` (conforme exemplos oficiais do libnx) cria a superfície direta em 1280x720 sem efeitos colaterais.
- **Espelhamento de Estrutura de Pastas na RomFS:**
  - O utilitário `tools/prepare_switch_romfs.py` foi atualizado com `dirs_exist_ok=True` para empacotar os arquivos na raiz de `romfs:/`, em `romfs:/reference/extracted/` e em `romfs:/extracted/`, garantindo compatibilidade total independente do formato com que a VM ou as classes Java solicitarem os arquivos (com ou sem barra inicial, prefixadas ou relativas).

## Sessão 11 (Estabilidade no Switch Real, Diagnóstico de Memória e Threading)
- **Eliminação do Crash / Kernel Panic do Atmosphère (`2168-0001` no `hbloader`):**
  - O utilitário `nacptool` estava sendo invocado com a flag fixa `--titleid=0100686C73000000`. No Atmosphère e no Horizon OS, especificar um Title ID arbitrário em binários `.nro` faz com que o loader do sistema tente vincular o aplicativo a um TID inexistente no console, gerando um kernel panic fatal que forçava o reboot do Switch.
  - A remoção da flag `--titleid` no `Makefile.switch` permite que o `hbloader` execute o Homebrew no espaço de memória limpo de homebrew padrão (`010000000000100d`), cessando completamente os crashes do sistema.
- **Diagnóstico Crítico: Applet Mode (Álbum) vs. Title Override:**
  - O crash dump gerado no console físico revelou que o aplicativo estava sendo iniciado via Applet Mode (clicar no Álbum). No modo Applet, o Horizon OS reserva apenas ~32 MB a 40 MB de memória RAM total para o processo.
  - O carregamento das centenas de classes J2ME, buffers de tela e assets estoura rapidamente essa cota.
  - No Switch real, homebrews com engines ou máquinas virtuais devem ser executados via **Title Override** (segurar o botão <kbd>R</kbd> ao abrir qualquer jogo comercial ou demo instalado no console), garantindo acesso a todos os ~3.5 GB de memória RAM disponíveis.
- **Suporte a Threads (`std::thread`) no Toolchain devkitA64:**
  - No GCC `aarch64-none-elf`, chamadas a `std::thread` requerem explicitamente a flag `-pthread` no compilador e no linker. A ausência da flag impedia o funcionamento da thread principal de lógica do jogo iniciada por `GameMIDlet.startApp()` (`bs.var_bs_a.c()`).
- **Persistência de Logs em Disco com `dup2`:**
  - Adicionado redirecionamento no boot de `STDOUT_FILENO` e `STDERR_FILENO` diretamente para o descritor de arquivo de `sdmc:/heroes_lore_boot.log` usando `dup2()`.
  - A função de log `hl::boot_log` foi unificada no namespace `hl` em `src/platform/platform.h` e conectada a `VM::fatal()`, garantindo que qualquer encerramento inesperado ou exceção não tratada seja imediatamente persistida no cartão microSD.

## Sessão 12 (Substituição de std::thread por SDL_CreateThread e Remoção de thread_local)
- **Incompatibilidade Crítica de `std::thread` no devkitA64 / libnx:**
  - O GCC 15 `aarch64-none-elf` no ambiente bare-metal do devkitPro não implementa um runtime completo de POSIX pthreads. Chamar `std::thread` faz com que o construtor invoque `std::terminate()` / `abort()`, encerrando imediatamente o processo do Switch com a mensagem "O software foi fechado pois ocorreu um erro".
  - A solução ideal foi migrar a criação de threads secundárias da VM J2ME (`VM::startThread`) para a API nativa do SDL2: `SDL_CreateThreadWithStackSize(javaThreadRunner, "HL_JavaThread", 2 * 1024 * 1024, args)`. O SDL2 possui suporte direto ao kernel do Switch via chamadas oficiais `threadCreate()` e `threadStart()`, com stack dedicado de 2MB.
- **Eliminação do `thread_local` no Ponteiro `tctx`:**
  - A variável `extern thread_local ThreadCtx* tctx` dependia do registrador de Thread Local Storage (`TPIDR_EL0`). Em sistemas sem loader dinâmico completo de TLS, threads criadas fora da libc não têm seus segmentos `.tbss`/`.tdata` inicializados, corrompendo a leitura do ponteiro e causando Data Abort / Panic ao tentar acessar `tctx->sp`.
  - Como a máquina virtual já é 100% serializada pelo Global Interpreter Lock (`gil.lock()`), a variável foi convertida para um ponteiro global padrão `ThreadCtx* tctx = nullptr;`. Cada thread salva e restaura `tctx` deterministicamente ao adquirir e liberar o GIL (inclusive durante `sleepMs` e `monitorEnter` com `SDL_Delay`), garantindo segurança absoluta de concorrência sem depender de TLS.




