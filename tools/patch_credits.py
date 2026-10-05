#!/usr/bin/env python3
"""
tools/patch_credits.py
Aplica os créditos do desenvolvedor do port (David Kalil) para Windows, Android e Nintendo Switch
diretamente no arquivo de localização binário lang.en-GB (nas telas 'Sobre' e 'Info -> Cred').
"""

import struct
import shutil
import os

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

def main():
    lang_path = "reference/extracted/lang.en-GB"
    with open(lang_path, "rb") as f:
        total_len = struct.unpack('>I', f.read(4))[0]
        orig_data = f.read(total_len)

    # 1. String 1237 (Menu INFO -> CRED)
    offset_pos = 1237 * 4
    rel_offset = struct.unpack('>i', orig_data[offset_pos:offset_pos+4])[0]
    target_pos = offset_pos + 4 + rel_offset
    utf_len = struct.unpack('>H', orig_data[target_pos+2:target_pos+4])[0]
    str1237_orig = orig_data[target_pos+4 : target_pos+4+utf_len]

    credit_info = "                              Port Nativo (Windows, Android, Nintendo Switch): David Kalil.".encode("utf-8")
    new1237 = str1237_orig + credit_info

    # 2. String 3928 (Menu Principal -> SOBRE)
    new3928 = "Traducao BR: Open Mind Team (c)2008 \nPort Nativo (Windows, Android, Switch): David Kalil".encode("utf-8")

    data_step1 = replace_string(orig_data, 1237, new1237)
    data_step2 = replace_string(data_step1, 3928, new3928)

    # Adiciona o cabeçalho de 4 bytes (tamanho total)
    final_data = struct.pack('>I', len(data_step2)) + data_step2

    dest_files = [
        "reference/extracted/lang.en-GB",
        "android/app/src/main/assets/lang.en-GB",
        "build/assets/lang.en-GB"
    ]

    for dest in dest_files:
        if os.path.exists(os.path.dirname(dest)):
            with open(dest, "wb") as f:
                f.write(final_data)
            print(f"[OK] Atualizado créditos em: {dest} ({len(final_data)} bytes)")

if __name__ == "__main__":
    main()
