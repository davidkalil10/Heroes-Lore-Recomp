#!/usr/bin/env python3
"""
tools/patch_credits.py
Aplica os créditos do desenvolvedor do port (David Kalil Braga (2026)) para Windows, Android e Nintendo Switch
diretamente no arquivo de localização binário lang.en-GB (na tela 'Info -> Cred')
e na classe bl.class (tela 'Sobre' do Menu Principal), preservando a versão de tradução de 2008 (v.0.0.2).
"""

import struct
import shutil
import os
import zipfile

def replace_string(data: bytes, idx_to_replace: int, new_text_bytes: bytes) -> bytes:
    num_strings = 3951
    offset_pos = idx_to_replace * 4
    rel_offset = struct.unpack('>i', data[offset_pos:offset_pos+4])[0]
    target_pos = offset_pos + 4 + rel_offset
    
    old_utf_len = struct.unpack('>H', data[target_pos+2 : target_pos+4])[0]
    old_entry_len = 2 + 2 + old_utf_len
    
    new_utf_len = len(new_text_bytes)
    old_block_len = struct.unpack('>H', data[target_pos:target_pos+2])[0]
    diff = new_utf_len - old_utf_len
    new_block_len = old_block_len + diff
    
    new_entry = struct.pack('>H', new_block_len) + struct.pack('>H', new_utf_len) + new_text_bytes
    prefix = data[:target_pos]
    suffix = data[target_pos + old_entry_len:]
    new_data = prefix + new_entry + suffix
    
    new_table = bytearray(new_data[:num_strings*4])
    for i in range(num_strings):
        i_off_pos = i * 4
        i_rel = struct.unpack('>i', new_table[i_off_pos : i_off_pos+4])[0]
        i_target = i_off_pos + 4 + i_rel
        if i_target > target_pos:
            i_rel += diff
            new_table[i_off_pos : i_off_pos+4] = struct.pack('>i', i_rel)
            
    return bytes(new_table) + new_data[num_strings*4:]

def patch_lang():
    # Extrai o lang.en-GB original do JAR de referência para garantir integridade
    with zipfile.ZipFile("reference/heroes.jar") as zf:
        raw = zf.read("lang.en-GB")
    
    total_len = struct.unpack('>I', raw[:4])[0]
    orig_data = raw[4:4+total_len]

    # String 1237 (Menu INFO -> CRED)
    offset_pos = 1237 * 4
    rel_offset = struct.unpack('>i', orig_data[offset_pos:offset_pos+4])[0]
    target_pos = offset_pos + 4 + rel_offset
    utf_len = struct.unpack('>H', orig_data[target_pos+2:target_pos+4])[0]
    str1237_orig = orig_data[target_pos+4 : target_pos+4+utf_len]

    credit_info = "                              Port Nativo (Windows, Linux, Android, Nintendo Switch): David Kalil Braga (2026).".encode("utf-8")
    new1237 = str1237_orig + credit_info

    data_patched = replace_string(orig_data, 1237, new1237)
    final_data = struct.pack('>I', len(data_patched)) + data_patched

    dest_files = [
        "reference/extracted/lang.en-GB",
        "android/app/src/main/assets/lang.en-GB",
        "build/assets/lang.en-GB"
    ]

    for dest in dest_files:
        if os.path.exists(os.path.dirname(dest)):
            with open(dest, "wb") as f:
                f.write(final_data)
            print(f"[OK] Atualizado lang.en-GB em: {dest} ({len(final_data)} bytes)")

def patch_bl_class():
    # Extrai o bl.class original do JAR de referência
    with zipfile.ZipFile("reference/heroes.jar") as zf:
        raw_class = zf.read("bl.class")

    d = bytearray(raw_class)

    # 1. Substitui a constante UTF-8 '\nv.' por:
    # '\nv.0.0.2\n\nPort Nativo (Windows, Linux, Android, Switch):\nDavid Kalil Braga (2026)'
    old_str_entry = b'\x01\x00\x03\nv.'
    new_str = "\nv.0.0.2\n\nPort Nativo (Windows, Linux, Android, Switch):\nDavid Kalil Braga (2026)".encode("utf-8")
    new_str_entry = b'\x01' + len(new_str).to_bytes(2, "big") + new_str

    if old_str_entry not in d:
        raise RuntimeError("Constante Utf8 '\\nv.' não encontrada em bl.class")
    d = bytearray(bytes(d).replace(old_str_entry, new_str_entry, 1))

    # 2. No bytecode de bl.<init>, substitui:
    # 'aload 7' (19 07) + 'invokevirtual #40' (b6 00 28) por 5 NOPs (00 00 00 00 00)
    # Isso preserva o tamanho e offsets do método, fazendo o append da versão e créditos
    # acontecerem de forma atômica e mantendo a versão v.0.0.2 antes de David Kalil Braga.
    code_pattern = b'\x19\x07\xb6\x00\x28'
    if code_pattern not in d:
        raise RuntimeError("Padrão de bytecode aload 7; append não encontrado em bl.class")
    d = bytearray(bytes(d).replace(code_pattern, b'\x00\x00\x00\x00\x00', 1))

    dest_files = [
        "reference/extracted/bl.class",
        "android/app/src/main/assets/bl.class",
        "build/assets/bl.class"
    ]

    for dest in dest_files:
        if os.path.exists(os.path.dirname(dest)):
            with open(dest, "wb") as f:
                f.write(d)
            print(f"[OK] Atualizado bl.class em: {dest} ({len(d)} bytes)")

def main():
    patch_lang()
    patch_bl_class()

if __name__ == "__main__":
    main()
