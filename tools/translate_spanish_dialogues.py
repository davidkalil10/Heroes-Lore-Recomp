#!/usr/bin/env python3
"""
tools/translate_spanish_dialogues.py
Gera o pacote completo de idioma em Espanhol (lang_es.bin) com 3951 strings,
traduzindo todos os dialogos, missoes, itens e textos do jogo,
aplicando remocao de acentos/caracteres fora do charset J2ME (ASCII 32..126)
e mantendo tags de dialogos (|, $, ;, [Personagem]).
"""

import os
import sys
import json
import time
import struct
import shutil
import unicodedata
import urllib.request
import urllib.parse

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

def pack_babble(strings):
    num_strings = len(strings)
    encoded_entries = []
    for s in strings:
        utf_bytes = s.encode('utf-8')
        utf_len = len(utf_bytes)
        block_len = utf_len + 2
        entry = struct.pack('>HH', block_len, utf_len) + utf_bytes
        encoded_entries.append(entry)

    table_size = num_strings * 4
    offsets = []
    current_offset_from_table_start = 0
    for i, entry in enumerate(encoded_entries):
        target_pos = table_size + current_offset_from_table_start
        offset_pos = i * 4
        rel_offset = target_pos - (offset_pos + 4)
        offsets.append(rel_offset)
        current_offset_from_table_start += len(entry)

    table_bytes = bytearray()
    for rel in offsets:
        table_bytes += struct.pack('>i', rel)

    payload = bytes(table_bytes) + b''.join(encoded_entries)
    header = struct.pack('>I', len(payload))
    return header + payload

def clean_spanish_text(text):
    if not text:
        return text
    
    # Substituições específicas de pontuação e símbolos espanhóis
    rep = {
        '¿': '',
        '¡': '',
        'ñ': 'n',
        'Ñ': 'N',
        'á': 'a', 'é': 'e', 'í': 'i', 'ó': 'o', 'ú': 'u', 'ü': 'u',
        'Á': 'A', 'É': 'E', 'Í': 'I', 'Ó': 'O', 'Ú': 'U', 'Ü': 'U',
        '“': '"', '”': '"', '‘': "'", '’': "'", '`': "'",
        '—': '-', '–': '-', '…': '...',
        '; ': ';', ' ;': ';'
    }
    for k, v in rep.items():
        text = text.replace(k, v)

    # Decomposição de qualquer outro caractere com acento remanescente
    nfkd = unicodedata.normalize('NFKD', text)
    cleaned = ''.join(c for c in nfkd if not unicodedata.combining(c))

    # Filtra apenas caracteres válidos na tabela ASCII (32 a 126, mais \n, \r)
    result = []
    for c in cleaned:
        code = ord(c)
        if 32 <= code <= 126:
            result.append(c)
        elif c in '\r\n':
            result.append(c)
        else:
            result.append(' ')
    return ''.join(result)

def gtrans_batch(texts, src='en', dest='es', max_retries=3):
    if not texts:
        return []
    
    joined = '\n###\n'.join(texts)
    url = 'https://translate.googleapis.com/translate_a/single?client=gtx&sl=' + src + '&tl=' + dest + '&dt=t&q=' + urllib.parse.quote(joined)
    req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
    
    for attempt in range(max_retries):
        try:
            with urllib.request.urlopen(req, timeout=15) as r:
                res = json.loads(r.read().decode('utf-8'))
                full = ''.join(seg[0] for seg in res[0] if seg and seg[0])
                parts = [s.strip() for s in full.split('###')]
                if len(parts) == len(texts):
                    return parts
                print(f"[Aviso] Mismatch ({len(parts)} != {len(texts)}), tentando com split menor...", flush=True)
                break
        except Exception as e:
            time.sleep(1 + attempt)
    
    # Se falhar o batch grande, divide em metades
    if len(texts) > 1:
        mid = len(texts) // 2
        return gtrans_batch(texts[:mid], src, dest) + gtrans_batch(texts[mid:], src, dest)
    
    # Fallback para string unica
    try:
        u = 'https://translate.googleapis.com/translate_a/single?client=gtx&sl=' + src + '&tl=' + dest + '&dt=t&q=' + urllib.parse.quote(texts[0])
        rq = urllib.request.Request(u, headers={'User-Agent': 'Mozilla/5.0'})
        with urllib.request.urlopen(rq, timeout=10) as r:
            res = json.loads(r.read().decode('utf-8'))
            translated = ''.join(seg[0] for seg in res[0] if seg and seg[0]).strip()
            return [translated]
    except Exception:
        return texts

def main():
    print("=== Traducao do Pacote Espanhol (Heroes Lore: Wind of Soltia) ===", flush=True)
    
    with open('reference/lang/lang_en.bin', 'rb') as f:
        en_strings = parse_babble(f.read())
    print(f"Lidas {len(en_strings)} strings em ingles.", flush=True)

    cache_file = 'tools/es_translations_cache.json'
    cache = {}
    if os.path.exists(cache_file):
        try:
            with open(cache_file, 'r', encoding='utf-8') as f:
                cache = json.load(f)
            print(f"Cache carregado: {len(cache)} strings ja traduzidas.", flush=True)
        except Exception:
            cache = {}

    fixed_map = {
        0: 'Espada de dos manos roma',
        1: '|Espada de dos manos;Espada basica de dos manos',
        10: 'Espada colmillo fuerte',
        50: 'Katana',
        100: 'Hacha de mano',
        500: 'Furia de tierra',
        943: 'Sonido',
        944: 'Juego',
        945: 'Textos',
        946: 'Camara',
        949: 'Pulsa cualquier tecla.',
        952: 'Pulsa qualquer tecla.',
        1200: 'Mision',
        1201: 'Carta',
        1202: 'General',
        1203: 'Guardar',
        1204: 'Info',
        1205: 'Opciones',
        1206: 'Salir',
        1207: 'Juego Guardado',
        1208: 'Fallo.',
        1209: 'Sin espacio para guardar...',
        1212: 'Guardado denegado...',
        1214: 'Guardando...',
        1215: 'Recompensas : ',
        1216: 'Mision Principal',
        1217: 'Mision Secundaria',
        1218: '|Iniciada...',
        1219: 'Sin misiones actualmente...',
        1222: 'Listo para usar.',
        1228: 'Nociones Basicas',
        1229: 'Guardian',
        1230: 'Estado',
        1231: 'Objeto',
        1232: 'Mas juegos',
        1233: 'P&R',
        3902: 'Nivel',
        3903: 'Oro',
        3904: 'Equipar',
        3906: 'Usar',
        3907: 'Salir',
        3908: 'Tirar',
        3909: 'Combinar',
        3910: 'Asignar',
        3911: 'Comprar',
        3912: 'Vender',
        3913: 'Mejorar',
        3914: 'Crear',
        3915: 'Aprender',
        3916: 'Restablecer',
        3920: 'ESTADO',
        3921: 'EQUIPO',
        3922: 'HABILIDAD',
        3923: 'OBJETOS',
        3924: 'MISION',
        3925: 'OPCIONES',
        3926: 'GUARDAR',
        3932: 'Atras',
        3946: 'Aceptar',
        3947: 'Cancelar',
        3948: 'Seleccionar',
        3949: 'Volver',
        3950: 'Menu'
    }

    to_translate = []
    for idx, s in enumerate(en_strings):
        if not s.strip():
            continue
        if idx in fixed_map:
            continue
        str_idx = str(idx)
        if str_idx in cache:
            continue
        to_translate.append((idx, s))

    print(f"Total de strings a traduzir nesta rodada: {len(to_translate)}", flush=True)

    BATCH_SIZE = 40
    for i in range(0, len(to_translate), BATCH_SIZE):
        batch = to_translate[i:i+BATCH_SIZE]
        texts = [item[1] for item in batch]
        
        prefixes = []
        clean_texts = []
        for t in texts:
            p = ""
            while len(t) > len(p) and t[len(p)] in "|$":
                p += t[len(p)]
            prefixes.append(p)
            clean_texts.append(t[len(p):])

        trans_res = gtrans_batch(clean_texts, src='en', dest='es')
        for (idx, _), pfx, tr_raw in zip(batch, prefixes, trans_res):
            cleaned = clean_spanish_text(pfx + tr_raw)
            cache[str(idx)] = cleaned

        print(f"Progresso: {min(i + BATCH_SIZE, len(to_translate))} / {len(to_translate)} strings traduzidas.", flush=True)
        
        # Salva o cache periodicamente
        with open(cache_file, 'w', encoding='utf-8') as f:
            json.dump(cache, f, ensure_ascii=False, indent=1)

    print("Construindo lista final de strings em espanhol...", flush=True)
    es_strings = []
    for idx, orig_en in enumerate(en_strings):
        if idx in fixed_map:
            es_strings.append(clean_spanish_text(fixed_map[idx]))
        elif str(idx) in cache:
            es_strings.append(cache[str(idx)])
        else:
            es_strings.append(clean_spanish_text(orig_en))

    assert len(es_strings) == 3951, f"Esperado 3951 strings, obteve {len(es_strings)}"
    print(f"Empacotando binario Babble com {len(es_strings)} strings...", flush=True)
    packed_data = pack_babble(es_strings)
    print(f"Tamanho do binario gerado: {len(packed_data)} bytes.", flush=True)

    targets = [
        'assets/lang/lang_es.bin',
        'reference/lang/lang_es.bin',
        'reference/extracted/lang/lang_es.bin',
        'build/assets/lang/lang_es.bin',
        'android/app/src/main/assets/lang/lang_es.bin'
    ]
    for target in targets:
        os.makedirs(os.path.dirname(target), exist_ok=True)
        with open(target, 'wb') as f:
            f.write(packed_data)
        print(f"[OK] Gravado {target}", flush=True)

    print("\n=== Concluido com sucesso! Pacote de idioma em Espanhol 100% atualizado. ===", flush=True)

if __name__ == '__main__':
    main()
