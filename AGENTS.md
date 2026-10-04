# AGENTS.md — Heroes Lore: Wind of Soltia (recompilação nativa)

Leia este arquivo PRIMEIRO em toda sessão (Antigravity, Codex, etc.). Depois leia `docs/STATUS.md`.

## Objetivo
Portar o jogo J2ME `Heroes Lore Wind Of Soltia [BR]240x320.jar` para C++17 + SDL2,
de forma **fiel e literal** (tradução do bytecode/decompilação, SEM reescrever lógica "no olhômetro"),
com camada de plataforma fina para futuros ports (web/Emscripten, mobile, Switch).

## Regras
1. Nunca adivinhar o que uma classe faz: decompilar (CFR/javap), ler, documentar em `docs/CLASS_MAP.md`.
2. Portar método a método mantendo a estrutura original; nome ofuscado fica em comentário.
3. APIs J2ME ficam atrás de uma interface em `src/platform/` (Graphics, Canvas, RecordStore, Audio, Input).
4. Toda descoberta/erro/acerto vai para `docs/LEARNINGS.md`. Todo avanço atualiza `docs/STATUS.md`.
5. Validar contra o original rodando em emulador J2ME (FreeJ2ME) quando possível.

## Fontes
- JAR original: `C:\Users\david\Desktop\Projetos_Flutter\heroes-lore-modern\heroes-lore-pt-br\Heroes Lore Wind Of Soltia [BR]240x320.jar` (somente leitura; cópia de trabalho em `reference/`)
- v1 Flutter: `...\heroes-lore-modern` (somente leitura)
- v2 Flutter: `...\heroes_lore_modern` (somente leitura) — melhor fonte de docs:
  `PROJECT_KNOWLEDGE_BACKUP.md`, `docs/reverse_engineering/.../GUIA_SISTEMA_EVENTOS_DIALOGOS_MAPAS.md`,
  scripts `map_evt_parser.py`, `extract_dialogues.py`, `cfr.jar`.

## Layout planejado
```
AGENTS.md  docs/  reference/ (jar, decompilado, extraído)  tools/  src/{platform,game}  assets/
```
