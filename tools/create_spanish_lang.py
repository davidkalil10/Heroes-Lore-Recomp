#!/usr/bin/env python3
"""
tools/create_spanish_lang.py
Gera o pacote de localização em Espanhol (lang_es.bin) com 3951 strings,
no formato binário Babble oficial da Hands-On Mobile / EA.
Baseado nos textos oficiais em inglês e termos clássicos de RPG em espanhol.
"""

import struct
import os

def parse_babble(data):
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

def main():
    with open('reference/lang/lang_en.bin', 'rb') as f:
        en_raw = f.read()
    strings = parse_babble(en_raw)
    assert len(strings) == 3951, f"Esperado 3951 strings, obteve {len(strings)}"

    # Dicionário de termos de UI e menus em Espanhol
    es_map = {
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
        952: 'Pulsa cualquier tecla.',
        1200: 'Buscar',
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
        1214: 'Espera...',
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
        3907: 'Salir',
        3915: 'Si',
        3916: 'No',
        3920: 'Iniciar',
        3921: 'Cargar',
        3922: 'Opciones',
        3923: 'Info',
        3924: 'Acerca de',
        3925: 'Mas Juegos',
        3926: 'Salir',
        3942: 'Rapido',
        3943: 'Normal',
        3944: 'Activado',
        3945: 'Desactivado',
        3948: 'Seleccionar',
        3949: 'Atras',
        3950: 'PULSA CUALQUIER TECLA'
    }

    es_strings = list(strings)
    for idx, text in es_map.items():
        es_strings[idx] = text

    es_packed = pack_babble(es_strings)
    
    # Valida o arquivo gerado
    parsed_back = parse_babble(es_packed)
    assert len(parsed_back) == 3951
    assert parsed_back[3920] == 'Iniciar'
    assert parsed_back[1205] == 'Opciones'

    dest_dirs = [
        'assets/lang',
        'reference/lang',
        'reference/extracted/lang',
        'build/assets/lang',
        'android/app/src/main/assets/lang'
    ]

    for d in dest_dirs:
        if os.path.exists(os.path.dirname(d)):
            os.makedirs(d, exist_ok=True)
            with open(os.path.join(d, 'lang_es.bin'), 'wb') as f:
                f.write(es_packed)
            print(f"[OK] Gravado lang_es.bin em: {d} ({len(es_packed)} bytes)")

if __name__ == '__main__':
    main()
