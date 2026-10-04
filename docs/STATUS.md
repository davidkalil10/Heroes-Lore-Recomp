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

## Próximo passo
- README completo e caprichado com instruções de compilação, arquitetura, controles e documentação técnica.


