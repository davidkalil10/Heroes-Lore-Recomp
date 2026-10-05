#!/usr/bin/env python3
"""
Prepara a pasta romfs/ para ser embutida diretamente no heroes_lore.nro do Nintendo Switch.
Garante que todos os assets estejam disponíveis tanto na raiz de romfs:/ quanto nos subdiretórios esperados.
"""
import os
import shutil

def prepare_romfs():
    romfs_dir = "romfs"
    if os.path.exists(romfs_dir):
        shutil.rmtree(romfs_dir)
    os.makedirs(romfs_dir, exist_ok=True)

    # 1. Copia conteúdo de reference/extracted para a raiz de romfs/
    if os.path.exists("reference/extracted"):
        print("[RomFS] Copiando arquivos para raiz de romfs/...")
        for item in os.listdir("reference/extracted"):
            s = os.path.join("reference/extracted", item)
            d = os.path.join(romfs_dir, item)
            if os.path.isdir(s):
                shutil.copytree(s, d, dirs_exist_ok=True)
            else:
                shutil.copy2(s, d)

        # 2. Espelha reference/extracted para romfs/reference/extracted e romfs/extracted
        print("[RomFS] Espelhando para romfs/reference/extracted...")
        shutil.copytree("reference/extracted", os.path.join(romfs_dir, "reference", "extracted"), dirs_exist_ok=True)
        print("[RomFS] Espelhando para romfs/extracted...")
        shutil.copytree("reference/extracted", os.path.join(romfs_dir, "extracted"), dirs_exist_ok=True)

    # 3. Copia assets para romfs/assets e romfs/ui
    if os.path.exists("assets"):
        print("[RomFS] Copiando assets para romfs/assets...")
        shutil.copytree("assets", os.path.join(romfs_dir, "assets"), dirs_exist_ok=True)
        if os.path.exists("assets/ui"):
            print("[RomFS] Copiando assets/ui para romfs/ui...")
            shutil.copytree("assets/ui", os.path.join(romfs_dir, "ui"), dirs_exist_ok=True)
        if os.path.exists("assets/soundfont"):
            print("[RomFS] Copiando assets/soundfont para romfs/soundfont...")
            shutil.copytree("assets/soundfont", os.path.join(romfs_dir, "soundfont"), dirs_exist_ok=True)

    print("[RomFS] Pasta romfs/ gerada com sucesso e pronta para o elf2nro!")

if __name__ == "__main__":
    prepare_romfs()
