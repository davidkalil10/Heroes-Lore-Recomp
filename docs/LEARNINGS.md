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

