#!/usr/bin/env python3
"""
Prepara a pasta romfs/ para ser embutida diretamente no heroes_lore.nro do Nintendo Switch.
"""
import os
import shutil

def prepare_romfs():
    romfs_dir = "romfs"
    if os.path.exists(romfs_dir):
        shutil.rmtree(romfs_dir)
    os.makedirs(romfs_dir, exist_ok=True)

    # 1. Copia reference/extracted para romfs/reference/extracted
    if os.path.exists("reference/extracted"):
        print("[RomFS] Copiando reference/extracted...")
        shutil.copytree("reference/extracted", os.path.join(romfs_dir, "reference", "extracted"))

    # 2. Copia assets para romfs/assets
    if os.path.exists("assets"):
        print("[RomFS] Copiando assets...")
        shutil.copytree("assets", os.path.join(romfs_dir, "assets"))

    print("[RomFS] Pasta romfs/ gerada com sucesso e pronta para o elf2nro!")

if __name__ == "__main__":
    prepare_romfs()
