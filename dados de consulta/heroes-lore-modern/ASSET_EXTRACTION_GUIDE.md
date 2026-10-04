# Guia de Extração e Conversão de Assets

Este documento detalha como extrair e converter os dados do JAR original para o formato moderno.

## 📦 Estrutura do JAR Original

```
Heroes Lore [BR]240x320.jar (793KB)
├── META-INF/
│   └── MANIFEST.MF
├── *.class (bytecode compilado)
├── /m/ (maps - 81 arquivos .map)
├── /m/*/events/ (210 arquivos .evt)
├── /char/ (character data)
│   ├── hero.tdf (player stats)
│   └── lvup.eif (level up data)
├── /grd/ (ground tiles)
├── /enm/ (enemies)
│   ├── spr/ (32 sprites de inimigos)
│   ├── atef/ (attack effects)
│   ├── die/ (morte)
│   └── data*.bin (dados bináricos)
├── /npc/ (NPCs)
│   └── spr/ (sprites)
├── /boss/ (bosses)
│   ├── spr/ (sprites)
│   └── atef/ (attack effects)
├── /itm/ (items - 24 itens)
│   ├── mixtbl (mix table)
│   └── forshop (shop data)
├── /c1, /c2, /c3 (character classes? sprites)
├── /snd/ (audio)
│   ├── *.mid (MIDI music - 13 tracks)
│   └── *.wav (sound effects - 9 arquivos)
├── fonts/ (bitmap fonts)
├── pt-BR/, en-GB/, de-DE/, es-ES/, fr-FR/ (localization)
└── icon.png
```

**Total: 993 arquivos, ~3 MB**

## 🔍 Formatos Proprietários

### .map (Map Format)
Formato binário customizado contendo:
- **Header** (4 bytes): Magic number + versão
- **Width/Height** (2 bytes cada)
- **Tile Data** (variable): Grid of tile IDs
- **Entity Spawns** (variable): Posições de NPCs/inimigos
- **Event Triggers** (variable): Áreas que disparam eventos

**Aproximação de tamanho**: 2-4 KB por mapa

### .evt (Event Format)
Scripts de eventos em formato binário:
- **Type**: Dialogue, cutscene, battle trigger, etc
- **Parameters**: Strings, IDs, flags
- **Actions**: Sequência de comandos

Provavelmente baseado em stack machine ou tree structure.

### .tdf (Tile/Entity Data Format)
Dados estruturados:
- Stats de personagens (HP, STR, DEX, INT, etc)
- Propriedades de tiles
- Tabelas de efeitos

### .eif (Entity Info Format)
Dados de entidades específicas (NPCs, inimigos, bosses)

## 📝 Estratégia de Conversão

### Fase 1: Análise de Bytecode
```python
# Usar ferramentas como cfr ou fernflower
# para descompilar .class files e entender a lógica

# Procurar por:
# - Constantes mágicas (magic numbers)
# - Loops de parsing
# - Estruturas de dados
```

### Fase 2: Extração de Assets Readáveis
```bash
# Imagens PNG (já podem ser extraídas direto)
unzip -j "Heroes Lore Wind Of Soltia [BR]240x320.jar" "*.png" -d assets/sprites/

# Áudio MIDI/WAV (já estão em formato padrão)
unzip -j "Heroes Lore Wind Of Soltia [BR]240x320.jar" "snd/*" -d assets/audio/

# Arquivos de texto (strings de diálogo, etc)
# - Procurar em .class files por String constants
# - Usar strings analyzer/decompiler
```

### Fase 3: Reverse Engineering de Formatos Binários

**Ferramentas úteis:**
- **Hexdump/xxd**: Inspecionar arquivos binários
- **010 Editor**: Hex editor com templates
- **Ghidra/IDA**: Desassembly/debugging
- **Python struct module**: Parsing binário

**Exemplo - Parseando .map:**
```python
import struct
from pathlib import Path

class MapParser:
    def __init__(self, data: bytes):
        self.data = data
        self.offset = 0
    
    def read_u32(self) -> int:
        val = struct.unpack_from('>I', self.data, self.offset)[0]
        self.offset += 4
        return val
    
    def read_u16(self) -> int:
        val = struct.unpack_from('>H', self.data, self.offset)[0]
        self.offset += 2
        return val
    
    def read_u8(self) -> int:
        val = self.data[self.offset]
        self.offset += 1
        return val
    
    def parse(self) -> dict:
        magic = self.read_u32()
        version = self.read_u16()
        width = self.read_u16()
        height = self.read_u16()
        
        tiles = []
        for y in range(height):
            row = []
            for x in range(width):
                tile_id = self.read_u8()
                row.append(tile_id)
            tiles.append(row)
        
        return {
            'magic': magic,
            'version': version,
            'width': width,
            'height': height,
            'tiles': tiles,
        }

# Usar:
with open('m/00.map', 'rb') as f:
    parser = MapParser(f.read())
    map_data = parser.parse()
```

### Fase 4: Conversão para JSON

**Exemplo de mapa em JSON:**
```json
{
  "id": "map_00",
  "name": "Starting Village",
  "width": 40,
  "height": 40,
  "tileset": "grd_00",
  "musicTrackId": 0,
  "tiles": [
    [0, 0, 0, ...],
    [0, 1, 1, ...],
    ...
  ],
  "entities": [
    {
      "id": "npc_001",
      "type": "npc",
      "name": "Village Elder",
      "x": 20,
      "y": 20,
      "sprite": "npc_spr_00",
      "dialogue": "npc_001_dialogue",
      "isHostile": false
    },
    {
      "id": "enm_group_00",
      "type": "enemy_spawn",
      "x": 35,
      "y": 15,
      "enemyType": "goblin",
      "count": 3,
      "respawnTime": 300
    }
  ],
  "events": [
    {
      "id": "evt_00",
      "trigger": "on_enter_area",
      "area": {"x": 10, "y": 10, "width": 5, "height": 5},
      "script": "evt_00_script"
    }
  ]
}
```

**Exemplo de enemy em JSON:**
```json
{
  "id": "goblin_001",
  "name": "Goblin Warrior",
  "level": 2,
  "maxHp": 25,
  "stats": {
    "strength": 12,
    "dexterity": 10,
    "constitution": 11,
    "intelligence": 8,
    "wisdom": 9,
    "charisma": 7
  },
  "skills": ["basic_attack", "slash"],
  "loot": {
    "gold": [10, 20],
    "items": [
      {"itemId": "iron_sword", "chance": 0.2},
      {"itemId": "health_potion", "chance": 0.5}
    ]
  },
  "sprite": "enm_spr_00",
  "attackEffect": "enm_atef_00"
}
```

## 🐍 Script de Extração Automatizado

Criar `scripts/extract_assets.py`:

```python
#!/usr/bin/env python3
"""
Extrai assets do JAR e converte para formatos modernos
"""

import zipfile
import json
import struct
import shutil
from pathlib import Path
from typing import Dict, List, Any

class HeroesLoreExtractor:
    def __init__(self, jar_path: str, output_dir: str):
        self.jar_path = jar_path
        self.output_dir = Path(output_dir)
        self.output_dir.mkdir(exist_ok=True)
    
    def extract_images(self):
        """Extrai todas as imagens PNG"""
        with zipfile.ZipFile(self.jar_path, 'r') as jar:
            png_files = [f for f in jar.namelist() if f.endswith('.png')]
            for png in png_files:
                data = jar.read(png)
                out_path = self.output_dir / 'sprites' / Path(png).name
                out_path.parent.mkdir(parents=True, exist_ok=True)
                out_path.write_bytes(data)
        print(f"✓ Extraídas {len(png_files)} imagens")
    
    def extract_audio(self):
        """Extrai áudio (MIDI e WAV)"""
        with zipfile.ZipFile(self.jar_path, 'r') as jar:
            audio_files = [f for f in jar.namelist() 
                          if f.endswith('.mid') or f.endswith('.wav')]
            for audio in audio_files:
                data = jar.read(audio)
                out_path = self.output_dir / 'audio' / Path(audio).name
                out_path.parent.mkdir(parents=True, exist_ok=True)
                out_path.write_bytes(data)
        print(f"✓ Extraído áudio")
    
    def extract_game_data(self):
        """Extrai dados do jogo (maps, npcs, items, etc)"""
        with zipfile.ZipFile(self.jar_path, 'r') as jar:
            # Extrair arquivos de dados
            data_files = [f for f in jar.namelist() 
                         if any(f.endswith(ext) for ext in ['.map', '.evt', '.tdf', '.eif'])]
            
            for data_file in data_files:
                content = jar.read(data_file)
                out_path = self.output_dir / 'game_data' / data_file
                out_path.parent.mkdir(parents=True, exist_ok=True)
                out_path.write_bytes(content)
        print(f"✓ Extraídos dados de jogo")
    
    def generate_metadata(self):
        """Gera arquivo de metadados"""
        metadata = {
            "version": "0.0.2",
            "vendor": "Hands-On Mobile",
            "languages": ["pt-BR", "en-GB", "de-DE", "es-ES", "fr-FR"],
            "maps": 81,
            "enemies": 32,
            "npcs": 17,
            "items": 24,
            "music_tracks": 13,
        }
        
        out_path = self.output_dir / 'game_data' / 'metadata.json'
        out_path.write_text(json.dumps(metadata, indent=2))
        print("✓ Metadados gerados")

# Usar:
if __name__ == '__main__':
    extractor = HeroesLoreExtractor(
        jar_path='heroes-lore-pt-br/Heroes Lore Wind Of Soltia [BR]240x320.jar',
        output_dir='assets/'
    )
    extractor.extract_images()
    extractor.extract_audio()
    extractor.extract_game_data()
    extractor.generate_metadata()
```

## 🎯 Próximos Passos

1. **Analisar bytecode** - usar CFR para descompilar classes principais
2. **Parsejar formatos** - começar com mapas (.map)
3. **Extrair strings** - diálogos, nomes de NPCs/itens
4. **Converter gradualmente** - testar cada formato no jogo
5. **Validar dados** - comparar com original J2ME

## 📚 Recursos Úteis

- **Cfr** (decompiler): https://www.benf.org/other/cfr/
- **010 Editor** (hex): https://www.sweetscape.com/010editor/
- **Ghidra** (reverse engineering): https://ghidra-sre.org/
- **xxd/hexdump** (Linux built-in)
- **Python struct docs**: https://docs.python.org/3/library/struct.html

---

**Nota**: Este processo exigirá bastante análise manual e testes iterativos. Recomenda-se iniciar com assets mais simples (imagens, áudio) e progredir para formatos binários complexos.
