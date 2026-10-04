# CLASS_MAP — Heroes Lore (nomes ofuscados → papel)

Somente fatos **verificados lendo o código**. `?` = ainda não lido. Índice completo gerado: `CLASS_INDEX.md`.
Fonte: `reference/decompiled_cfr/` (CFR, `--renamedupmembers`) e `reference/javap/` (bytecode; fonte de verdade onde o CFR falha: `ao`, `g`, `n`).

## Estrutura geral (90 classes, 20k linhas)
- Pacote `rpg`: só `GameMIDlet`. Todo o resto está no pacote default.
- Hierarquia de classes com mais herdeiros: `cb` (base de ~35 telas/componentes, `implements u`), `av`/`al`/`o`/`ck` (outras bases), `f`.

## Verificadas
| Classe | Papel | Evidência |
|---|---|---|
| `rpg.GameMIDlet` | MIDlet. `startApp`: `bs.a(display)`, `bs.var_bs_a.c()` (inicia thread); lê props `HO-LSK`(default -6), `HO-RSK`(-7), `HO-CLR`(-8) → `bh.b/c/a` (códigos das softkeys) | GameMIDlet.java |
| `bs` | **Game loop / gerenciador de Display**. Thread Runnable: cria `bg` (tela inicial), `setCurrent`, a cada frame: `i()` (flush input), `j()` (repaint), `callSerially(this)`. Controla FPS via `1000/n`; tabela `{8,10,14,18}`. `d()` cria `as` (canvas do jogo) e chama `n.p()`; `e()` volta ao `bg` (menu). Salva/carrega 6 bytes de config no RecordStore `"/c"` (volume, flags, FPS idx) | bs.java |
| `r` | **Canvas base abstrata** (extends `Canvas`, fullscreen). Estáticos `g`=largura, `h`=altura, `i`=g/2, `j`=h/2. Utilitários: desenho de números com fonte bitmap (`ce.*` imagens), caixa de texto, **tela de loading** (`m`,`k`,`l`) | r.java |
| `as` | Canvas do jogo (extends `r`), 857 linhas | índice (a ler) |
| `bg` | Canvas do menu/título (extends `r`, Runnable), 320 linhas | índice (a ler) |
| `u` | Interface só de constantes (tabelas de direção/offsets) — usada por `ae`, `ah`, `ao`, `n`, `cb`, `ck`, `p`, `o`, `t`, `f`… | u.java |
| `au` | Wrapper de `RecordStore` (`new au("/c", modo)`; `a()` escreve, `b()` lê, `void_a()` fecha) | bs.java / índice |
| `ci` | Áudio (`Manager`, `Player`, `VolumeControl`, `PlayerListener`) | índice |
| `bh` | Fontes/texto (`int_a(char[])` largura, `int_a(g,x,y,chars,flags)` desenha); guarda códigos de softkey | r.java |
| `ce`, `br` | Imagens (`ce` guarda dezenas de `Image` estáticas: sprites de UI/números) | índice/r.java |

## Pendentes de leitura (maiores / mais importantes)
`ao`(1272) `ae`(1038) `p`(784) `as`(860) `ah`(923) `ce`(863) `n`(803) `az`(591) `bu`(573) `br`(474) `al`(550) `cb`(435) `g`(450)

## Assets (reference/extracted)
`.map`×81, `.mph/.mpd`×178 cada, `.evt`×210, `.png`×63, `.tdf`×19, `.eif`×18, `.mid`×13, `.wav`×9, `.mf`×9, 1 `lang.en-GB`, ~124 sem extensão.
Formatos documentados na v2: `dados de consulta/heroes_lore_modern/docs/reverse_engineering/.../GUIA_SISTEMA_EVENTOS_DIALOGOS_MAPAS.md`.
