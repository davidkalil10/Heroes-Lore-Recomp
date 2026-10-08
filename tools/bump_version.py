#!/usr/bin/env python3
"""
tools/bump_version.py
Utilitário para atualizar a versão do jogo de forma sincronizada e automática
em todas as plataformas suportadas:
1. src/platform/updater.h (HL_VERSION_TAG e HL_VERSION_NUM)
2. android/app/build.gradle (versionCode incremental e versionName)
3. Makefile.switch (APP_VERSION para metadados .nacp do Switch)
4. tools/patch_credits.py (re-executa para atualizar tela Sobre / bl.class)

Uso:
    python tools/bump_version.py 1.2.1
    python tools/bump_version.py --current
"""

import sys
import os
import re
import subprocess

def get_current_version():
    tag = "Desconhecida"
    if os.path.exists("src/platform/updater.h"):
        with open("src/platform/updater.h", "r", encoding="utf-8") as f:
            for line in f:
                if "#define HL_VERSION_TAG" in line:
                    tag = line.split('"')[1]
                    break
    return tag

def bump_version(new_ver_clean):
    if new_ver_clean.startswith("v"):
        new_ver_clean = new_ver_clean[1:]

    # Valida formato semver (ex: 1.2.0)
    if not re.match(r"^\d+\.\d+\.\d+$", new_ver_clean):
        print(f"[ERRO] Formato de versão inválido: '{new_ver_clean}'. Use X.Y.Z (ex: 1.2.1).")
        sys.exit(1)

    tag = f"v{new_ver_clean}"
    print(f"\n=== Sincronizando Versão para {tag} ({new_ver_clean}) ===\n")

    # 1. src/platform/updater.h
    updater_path = "src/platform/updater.h"
    if os.path.exists(updater_path):
        with open(updater_path, "r", encoding="utf-8") as f:
            content = f.read()
        content = re.sub(r'#define HL_VERSION_TAG "[^"]+"', f'#define HL_VERSION_TAG "{tag}"', content)
        content = re.sub(r'#define HL_VERSION_NUM "[^"]+"', f'#define HL_VERSION_NUM "{new_ver_clean}"', content)
        with open(updater_path, "w", encoding="utf-8") as f:
            f.write(content)
        print(f"[OK] Atualizado {updater_path} (HL_VERSION_TAG={tag}, HL_VERSION_NUM={new_ver_clean})")
    else:
        print(f"[AVISO] Arquivo não encontrado: {updater_path}")

    # 2. android/app/build.gradle
    gradle_path = "android/app/build.gradle"
    if os.path.exists(gradle_path):
        with open(gradle_path, "r", encoding="utf-8") as f:
            lines = f.readlines()
        new_lines = []
        old_code = None
        new_code = None
        for line in lines:
            if "versionCode" in line and not line.strip().startswith("//"):
                m = re.search(r'versionCode\s+(\d+)', line)
                if m:
                    old_code = int(m.group(1))
                    new_code = old_code + 1
                    line = re.sub(r'versionCode\s+\d+', f'versionCode {new_code}', line)
            elif "versionName" in line and not line.strip().startswith("//"):
                line = re.sub(r'versionName\s+"[^"]+"', f'versionName "{new_ver_clean}"', line)
            new_lines.append(line)
        with open(gradle_path, "w", encoding="utf-8") as f:
            f.writelines(new_lines)
        print(f"[OK] Atualizado {gradle_path} (versionCode: {old_code} -> {new_code}, versionName: \"{new_ver_clean}\")")
    else:
        print(f"[AVISO] Arquivo não encontrado: {gradle_path}")

    # 3. Makefile.switch
    switch_path = "Makefile.switch"
    if os.path.exists(switch_path):
        with open(switch_path, "r", encoding="utf-8") as f:
            content = f.read()
        content = re.sub(r'APP_VERSION\s*:=\s*[^\r\n]+', f'APP_VERSION :=  {new_ver_clean}', content)
        with open(switch_path, "w", encoding="utf-8") as f:
            f.write(content)
        print(f"[OK] Atualizado {switch_path} (APP_VERSION := {new_ver_clean})")
    else:
        print(f"[AVISO] Arquivo não encontrado: {switch_path}")

    # 4. tools/patch_credits.py (re-executa para atualizar tela Sobre / bl.class)
    patch_tool = "tools/patch_credits.py"
    if os.path.exists(patch_tool):
        print(f"[Executando] {patch_tool} para regenerar bl.class...")
        subprocess.run([sys.executable, patch_tool], check=True)
    else:
        print(f"[AVISO] Arquivo não encontrado: {patch_tool}")

    print("\n=== Concluído! Todos os pontos de versão sincronizados. ===")
    print("\nComandos Git recomendados:")
    print(f"  git add src/platform/updater.h android/app/build.gradle Makefile.switch reference/extracted/bl.class android/app/src/main/assets/bl.class build/assets/bl.class")
    print(f"  git commit -m \"chore(release): bump version to {tag}\"")
    print(f"  git tag -a {tag} -m \"Release {tag}\"")
    print(f"  git push origin main")
    print(f"  git push origin {tag}\n")

def main():
    if len(sys.argv) < 2 or sys.argv[1] in ["-h", "--help"]:
        print(__doc__)
        sys.exit(0)

    if sys.argv[1] == "--current":
        print(f"Versão atual do projeto: {get_current_version()}")
        sys.exit(0)

    bump_version(sys.argv[1])

if __name__ == "__main__":
    main()
