import struct
from pathlib import Path
import json
import os

class MapParser:
    def __init__(self, data: bytes):
        self.data = data
        self.offset = 0
    
    def parse(self):
        # .map files in Heroes Lore seem to be:
        # byte 0: height
        # byte 1: width
        # byte 2: tileset index
        # byte 3..end: tiles
        if len(self.data) < 3:
            return None
            
        tileset = self.data[0]
        width = self.data[1]
        height = self.data[2]
        
        expected_size = 3 + (width * height)
        if len(self.data) != expected_size:
            print(f"Warning: size mismatch in {len(self.data)} bytes file. Expected {expected_size} (w:{width}, h:{height}), got {len(self.data)}")
            
        tiles = []
        offset = 3
        for y in range(height):
            row = []
            for x in range(width):
                if offset < len(self.data):
                    # In Java it was read into byte, which is signed (-128 to 127).
                    # python bytes are unsigned 0-255. Let's convert to signed.
                    val = self.data[offset]
                    if val > 127: val -= 256
                    row.append(val)
                    offset += 1
                else:
                    row.append(0)
            tiles.append(row)
            
        return {
            'width': width,
            'height': height,
            'tileset': tileset,
            'tiles': tiles
        }

if __name__ == '__main__':
    project_root = Path(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))
    map_dir = project_root / 'assets' / 'game_data' / 'raw' / 'm'
    out_dir = project_root / 'assets' / 'game_data' / 'maps'
    out_dir.mkdir(parents=True, exist_ok=True)
    
    map_files = list(map_dir.glob('*.map'))
    print(f"Found {len(map_files)} map files")
    
    for map_file in map_files:
        with open(map_file, 'rb') as f:
            data = f.read()
        parser = MapParser(data)
        parsed = parser.parse()
        
        if parsed:
            out_path = out_dir / f"{map_file.stem}.json"
            with open(out_path, 'w') as f:
                json.dump(parsed, f, indent=2)
                
    print("Done!")
