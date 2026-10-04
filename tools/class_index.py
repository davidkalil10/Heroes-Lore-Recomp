"""Gera docs/CLASS_INDEX.md a partir de reference/decompiled_cfr (fatos objetivos, sem interpretação)."""
import re, pathlib, collections
root = pathlib.Path(__file__).resolve().parent.parent
src = root / "reference" / "decompiled_cfr"
rows = []
users = collections.defaultdict(set)
names = {p.stem for p in src.rglob("*.java")}
for p in sorted(src.rglob("*.java"), key=lambda p: (len(p.stem), p.stem)):
    t = p.read_text(encoding="utf-8", errors="replace")
    m = re.search(r"^(?:public |final |abstract )*(class|interface)\s+(\w+)\s*(?:\n?extends\s+([\w.]+))?\s*(?:\n?implements\s+([\w., ]+))?", t, re.M)
    kind, name, ext, impl = (m.groups() if m else ("?", p.stem, None, None))
    j2me = sorted(set(re.findall(r"javax\.microedition\.[\w.]+", t)))
    j2me = sorted({x.split(".")[-1] for x in j2me})
    refs = {n for n in names if n != p.stem and re.search(r"\b%s\b[.\s(\[)]" % re.escape(n), t)} if len(names) < 200 else set()
    for r in refs: users[r].add(p.stem)
    rows.append((p.stem, kind, ext, impl, j2me, len(t.splitlines())))
out = ["# CLASS_INDEX (gerado por tools/class_index.py — fatos, sem interpretação)\n",
       "| Classe | Tipo | extends | implements | APIs J2ME | linhas |", "|---|---|---|---|---|---|"]
for n, k, e, i, j, l in rows:
    out.append(f"| {n} | {k} | {e or ''} | {i or ''} | {', '.join(j)} | {l} |")
(root / "docs" / "CLASS_INDEX.md").write_text("\n".join(out) + "\n", encoding="utf-8")
print(len(rows), "classes")
