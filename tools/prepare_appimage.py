#!/usr/bin/env python3
"""
tools/prepare_appimage.py
Prepara a pasta AppDir/ para o empacotamento em AppImage no Linux (Ubuntu / Steam Deck).
Copia o binário compilado, assets do jogo, ícones, arquivos de desktop e bibliotecas compartilhadas (SDL2).
"""

import os
import sys
import shutil
import subprocess

def prepare_appdir(binary_path=None, appdir_path="AppDir"):
    if binary_path is None:
        if os.path.exists("build/heroes_lore"):
            binary_path = "build/heroes_lore"
        elif os.path.exists("build/heroes_lore.exe"):
            binary_path = "build/heroes_lore.exe"
        else:
            binary_path = "build/heroes_lore"

    if not os.path.exists(binary_path):
        print(f"[ERRO] Binário compilado não encontrado em: {binary_path}")
        sys.exit(1)

    if os.path.exists(appdir_path):
        shutil.rmtree(appdir_path)

    os.makedirs(f"{appdir_path}/usr/bin", exist_ok=True)
    os.makedirs(f"{appdir_path}/usr/lib", exist_ok=True)
    os.makedirs(f"{appdir_path}/assets", exist_ok=True)
    os.makedirs(f"{appdir_path}/reference/extracted", exist_ok=True)

    print("[AppImage] Copiando binário executável...")
    dest_bin = f"{appdir_path}/usr/bin/heroes_lore"
    shutil.copy2(binary_path, dest_bin)
    os.chmod(dest_bin, 0o755)

    print("[AppImage] Copiando metadados de desktop e ícones...")
    shutil.copy2("dist_linux/heroes_lore.desktop", f"{appdir_path}/heroes_lore.desktop")
    shutil.copy2("dist_linux/heroes_lore.png", f"{appdir_path}/heroes_lore.png")
    shutil.copy2("dist_linux/heroes_lore.png", f"{appdir_path}/.DirIcon")
    
    shutil.copy2("dist_linux/AppRun", f"{appdir_path}/AppRun")
    os.chmod(f"{appdir_path}/AppRun", 0o755)

    print("[AppImage] Copiando assets do jogo (reference/extracted)...")
    if os.path.exists("reference/extracted"):
        for item in os.listdir("reference/extracted"):
            s = os.path.join("reference/extracted", item)
            d1 = os.path.join(f"{appdir_path}/reference/extracted", item)
            d2 = os.path.join(f"{appdir_path}/assets", item)
            if os.path.isdir(s):
                shutil.copytree(s, d1, dirs_exist_ok=True)
                shutil.copytree(s, d2, dirs_exist_ok=True)
            else:
                shutil.copy2(s, d1)
                shutil.copy2(s, d2)

    if os.path.exists("assets"):
        print("[AppImage] Copiando assets adicionais (UI/SoundFont)...")
        shutil.copytree("assets", f"{appdir_path}/assets", dirs_exist_ok=True)

    # Empacota bibliotecas compartilhadas usando ldd (se estiver rodando em Linux)
    if sys.platform.startswith("linux"):
        print("[AppImage] Analisando e empacotando dependências com ldd...")
        try:
            out = subprocess.check_output(["ldd", binary_path], text=True)
            core_system_libs = {
                "libc.so", "libm.so", "libdl.so", "libpthread.so", "librt.so",
                "ld-linux", "libresolv.so", "libutil.so"
            }
            copied = set()
            for line in out.splitlines():
                if "=>" in line:
                    parts = line.strip().split("=>")
                    lib_name = parts[0].strip()
                    lib_target = parts[1].strip().split(" ")[0].strip()
                    if lib_target and os.path.exists(lib_target):
                        if any(core in lib_name for core in core_system_libs):
                            continue # Preserva glibc do sistema anfitrião
                        if lib_target not in copied:
                            real_path = os.path.realpath(lib_target)
                            target_dest = f"{appdir_path}/usr/lib/{os.path.basename(lib_target)}"
                            shutil.copy2(real_path, target_dest)
                            copied.add(lib_target)
                            print(f"  + Bundled {lib_name} -> usr/lib/{os.path.basename(lib_target)}")
        except Exception as e:
            print(f"[Aviso] Falha ao inspecionar dependências ldd: {e}")

    print(f"[OK] AppDir preparado com sucesso em: {appdir_path}")

if __name__ == "__main__":
    prepare_appdir()
