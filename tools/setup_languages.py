#!/usr/bin/env python3
"""
tools/setup_languages.py
Extrai e organiza os arquivos de idioma oficiais para Heroes Lore:
- PT-BR (do reference/heroes.jar / reference/extracted/lang.en-GB)
- EN (original em inglês baixado de arquivo de varejo com 3951 strings)
- IT (original em italiano de BiNPDA com 3951 strings)
"""

import os
import io
import struct
import zipfile
import urllib.request

def parse_babble(data):
    if len(data) < 4: return []
    total_len = struct.unpack('>I', data[:4])[0]
    payload = data[4:4+total_len]
    first_off = struct.unpack('>i', payload[:4])[0]
    num_strings = (4 + first_off) // 4
    strings = []
    for i in range(num_strings):
        off_pos = i * 4
        rel = struct.unpack('>i', payload[off_pos:off_pos+4])[0]
        target = off_pos + 4 + rel
        if target + 4 <= len(payload):
            utf_len = struct.unpack('>H', payload[target+2:target+4])[0]
            str_bytes = payload[target+4:target+4+utf_len]
            strings.append(str_bytes.decode('utf-8', errors='replace'))
        else:
            strings.append('')
    return strings

def main():
    os.makedirs('assets/lang', exist_ok=True)
    os.makedirs('reference/lang', exist_ok=True)

    # 1. PT-BR (do reference/extracted/lang.en-GB)
    pt_path = 'reference/extracted/lang.en-GB'
    if not os.path.exists(pt_path):
        with zipfile.ZipFile('reference/heroes.jar') as zf:
            pt_raw = zf.read('lang.en-GB')
    else:
        with open(pt_path, 'rb') as f:
            pt_raw = f.read()

    with open('reference/lang/lang_pt.bin', 'wb') as f:
        f.write(pt_raw)
    with open('assets/lang/lang_pt.bin', 'wb') as f:
        f.write(pt_raw)
    pt_strings = parse_babble(pt_raw)
    print(f"[OK] PT-BR: {len(pt_raw)} bytes, {len(pt_strings)} strings.")

    # 2. IT (de BiNPDA)
    binpda_path = r'dados de consulta/heroes-lore-modern/heroes-lore-pt-br/Heroes.Lore.Wind.Of.Soltia.320x240.v2.0.5.S60v3.J2ME.Retail-BiNPDA.jar'
    with zipfile.ZipFile(binpda_path, 'r') as zf:
        it_raw = zf.read('lang.it-IT')
    with open('reference/lang/lang_it.bin', 'wb') as f:
        f.write(it_raw)
    with open('assets/lang/lang_it.bin', 'wb') as f:
        f.write(it_raw)
    it_strings = parse_babble(it_raw)
    print(f"[OK] IT: {len(it_raw)} bytes, {len(it_strings)} strings.")

    # 3. EN (da versão original de varejo 79299)
    url_en = 'http://dedomil.net/games/4199/download-jar/79299'
    req = urllib.request.Request(url_en, headers={'User-Agent': 'Mozilla/5.0'})
    with urllib.request.urlopen(req, timeout=15) as resp:
        jar_data = resp.read()
    with zipfile.ZipFile(io.BytesIO(jar_data), 'r') as zf:
        en_raw = zf.read('lang.en-GB')
    with open('reference/lang/lang_en.bin', 'wb') as f:
        f.write(en_raw)
    with open('assets/lang/lang_en.bin', 'wb') as f:
        f.write(en_raw)
    en_strings = parse_babble(en_raw)
    print(f"[OK] EN: {len(en_raw)} bytes, {len(en_strings)} strings.")

    print("\n--- AMOSTRA DE COMPARAÇÃO (String 0, 10, 1230, 2375, 3000) ---")
    samples = [0, 10, 1230, 2375, 3000]
    for idx in samples:
        print(f"[{idx}]")
        print(f"  PT: {repr(pt_strings[idx])}")
        print(f"  EN: {repr(en_strings[idx])}")
        print(f"  IT: {repr(it_strings[idx])}")

if __name__ == '__main__':
    main()
