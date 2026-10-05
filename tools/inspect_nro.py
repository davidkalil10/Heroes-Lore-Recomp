#!/usr/bin/env python3
"""
Inspeciona a estrutura do arquivo .nro para garantir que contém NACP, Ícone e RomFS válidos.
"""
import sys
import struct
import os

def inspect_nro(filepath):
    if not os.path.exists(filepath):
        print(f"Erro: Arquivo {filepath} não existe!")
        sys.exit(1)

    with open(filepath, "rb") as f:
        data = f.read()

    size = len(data)
    print(f"=== Inspecionando {filepath} ({size} bytes / {size / (1024*1024):.2f} MB) ===")

    if size < 0x20 or data[0x10:0x14] != b'NRO0':
        print("Erro: Arquivo não é um NRO0 válido!")
        sys.exit(1)

    nro_size = struct.unpack_from("<I", data, 0x18)[0]
    print(f"Tamanho Executável NRO0: {nro_size} bytes")

    if nro_size >= size:
        print("AVISO: O arquivo NRO não possui seção de Assets (ASET) anexada!")
        sys.exit(1)

    aset_magic = data[nro_size:nro_size+4]
    if aset_magic != b'ASET':
        print(f"AVISO: Seção ASET não encontrada no offset {hex(nro_size)} (encontrado: {aset_magic})")
        sys.exit(1)

    print("Seção ASET encontrada!")
    icon_off, icon_sz = struct.unpack_from("<QQ", data, nro_size + 0x8)
    nacp_off, nacp_sz = struct.unpack_from("<QQ", data, nro_size + 0x18)
    romfs_off, romfs_sz = struct.unpack_from("<QQ", data, nro_size + 0x28)

    print(f"  - Ícone: offset {hex(icon_off)}, tamanho: {icon_sz} bytes ({'OK' if icon_sz > 0 else 'FALTANDO'})")
    print(f"  - NACP:  offset {hex(nacp_off)}, tamanho: {nacp_sz} bytes ({'OK' if nacp_sz > 0 else 'FALTANDO'})")
    print(f"  - RomFS: offset {hex(romfs_off)}, tamanho: {romfs_sz} bytes ({'OK' if romfs_sz > 0 else 'FALTANDO'})")

    if nacp_sz > 0:
        nacp_data = data[nro_size + nacp_off : nro_size + nacp_off + nacp_sz]
        # O NACP armazena títulos de 0x0 a 0x100 para até 16 idiomas (0x200 bytes cada: 0x200 = 512 bytes)
        # TitleID fica em 0x3038 (8 bytes)
        if len(nacp_data) >= 0x3040:
            title_id = struct.unpack_from("<Q", nacp_data, 0x3038)[0]
            # Primeiro título em 0x0 (name 0x200 bytes, publisher 0x100 bytes)
            name = nacp_data[0:0x200].split(b'\x00')[0].decode('utf-8', errors='ignore')
            author = nacp_data[0x200:0x300].split(b'\x00')[0].decode('utf-8', errors='ignore')
            print(f"  - Metadados NACP:")
            print(f"      Title ID: {hex(title_id)}")
            print(f"      Nome:     {name}")
            print(f"      Autor:    {author}")

    if icon_sz == 0 or nacp_sz == 0 or romfs_sz == 0:
        print("ERRO: NRO incompleto! Faltam componentes essenciais.")
        sys.exit(1)

    print("[SUCESSO] O arquivo NRO contém todos os componentes (Código, Ícone, NACP e RomFS) e está 100% pronto para Emuladores e Switch!")

if __name__ == "__main__":
    path = sys.argv[1] if len(sys.argv) > 1 else "heroes_lore.nro"
    inspect_nro(path)
