import struct
from pathlib import Path
import json
import os

class EvtParser:
    def __init__(self, data: bytes, width: int, height: int):
        self.data = data
        self.offset = 0
        self.width = width
        self.height = height
    
    def read_u8(self):
        val = self.data[self.offset]
        self.offset += 1
        return val

    def parse(self):
        if len(self.data) == 0:
            return None
            
        try:
            # 1. Collision grid (height * width)
            collision_grid = []
            for y in range(self.height):
                row = []
                for x in range(self.width):
                    val = self.read_u8()
                    if val > 127: val -= 256
                    row.append(val)
                collision_grid.append(row)
                
            # 2. Objects / Interactables
            objects_types_count = self.read_u8()
            object_types = []
            for _ in range(objects_types_count):
                object_types.append(self.read_u8())
                
            objects_count = self.read_u8()
            objects = []
            for _ in range(objects_count):
                obj = {
                    'x': self.read_u8(),
                    'y': self.read_u8(),
                    'param1': self.read_u8(),
                    'param2': self.read_u8(),
                    'type_idx': self.read_u8()
                }
                objects.append(obj)
                
            # 3. NPCs
            npc_types_count = self.read_u8()
            npc_types = []
            for _ in range(npc_types_count):
                npc_types.append(self.read_u8())
                
            npcs_count = self.read_u8()
            npcs = []
            for _ in range(npcs_count):
                npc = {
                    'x': self.read_u8(),
                    'y': self.read_u8(),
                    'type_idx': self.read_u8()
                }
                npcs.append(npc)
                
            # 4. Enemies
            enemy_types_count = self.read_u8()
            enemy_types = []
            for _ in range(enemy_types_count):
                enemy_types.append(self.read_u8())
                
            enemies_count = self.read_u8()
            enemies = []
            for _ in range(enemies_count):
                enemy = {
                    'x': self.read_u8(),
                    'y': self.read_u8(),
                    'type_idx': self.read_u8()
                }
                enemies.append(enemy)
                
            # 5. Face/Portraits
            faces_count = self.read_u8()
            faces = []
            for _ in range(faces_count):
                faces.append(self.read_u8())
                
            # 6. Events (Triggers, Scripts)
            event_type1_count = self.read_u8()
            events1 = []
            for _ in range(event_type1_count):
                inner_count = self.read_u8()
                inner_list = []
                for _ in range(inner_count):
                    # read 7 bytes
                    data = [self.read_u8() for _ in range(7)]
                    inner_list.append(data)
                events1.append(inner_list)
                
            event_type2_count = self.read_u8()
            events2 = []
            for _ in range(event_type2_count):
                inner_count = self.read_u8()
                inner_list = []
                for _ in range(inner_count):
                    # read 3 bytes
                    data = [self.read_u8() for _ in range(3)]
                    inner_list.append(data)
                events2.append(inner_list)
                
            strings_count = self.read_u8()
            strings = []
            for _ in range(strings_count):
                strlen = self.read_u8()
                strdata = self.data[self.offset : self.offset + strlen]
                self.offset += strlen
                try:
                    strings.append(strdata.decode('utf-8', errors='ignore'))
                except:
                    strings.append(strdata.hex())
                    
            # 7. Event Logic / Triggers
            triggers_count = self.read_u8()
            triggers = []
            for _ in range(triggers_count):
                b1 = self.read_u8()
                b2 = self.read_u8()
                b3 = self.read_u8()
                triggers.append({'b1': b1, 'b2': b2, 'b3': b3})
                
            actions_count = self.read_u8()
            actions = []
            for _ in range(actions_count):
                count = self.read_u8()
                action_list = []
                for _ in range(count):
                    act = {
                        'cmd': self.read_u8(),
                        'param1': self.read_u8(),
                        'param2': self.read_u8(),
                        'param3': self.read_u8()
                    }
                    action_list.append(act)
                actions.append(action_list)

            return {
                'collision_grid': collision_grid,
                'objects': objects,
                'npcs': npcs,
                'enemies': enemies,
                'faces': faces,
                'events_data_1': events1,
                'events_data_2': events2,
                'strings': strings,
                'triggers': triggers,
                'actions': actions
            }
        except Exception as e:
            print(f"Error parsing at offset {self.offset}: {e}")
            return None

if __name__ == '__main__':
    project_root = Path(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))
    evt_dir = project_root / 'assets' / 'game_data' / 'raw' / 'm' / '0'
    out_dir = project_root / 'assets' / 'game_data' / 'events'
    out_dir.mkdir(parents=True, exist_ok=True)
    
    # We need to know width and height from the .map files
    maps_dir = project_root / 'assets' / 'game_data' / 'maps'
    
    # Find all evt files
    import glob
    evt_files = list(Path(project_root / 'assets' / 'game_data' / 'raw' / 'm').rglob('*.evt'))
    print(f"Found {len(evt_files)} event files")
    
    success = 0
    for evt_file in evt_files:
        # get map name, usually parent folder name and file name match some map
        # in Heroes Lore, evt files are in /m/<map_id_group>/<map_id>.evt
        map_name = evt_file.stem
        map_json_path = maps_dir / f"{map_name}.json"
        
        if not map_json_path.exists():
            continue
            
        with open(map_json_path, 'r') as f:
            map_data = json.load(f)
            
        with open(evt_file, 'rb') as f:
            data = f.read()
            
        parser = EvtParser(data, map_data['width'], map_data['height'])
        parsed = parser.parse()
        
        if parsed:
            out_path = out_dir / f"{map_name}.json"
            with open(out_path, 'w', encoding='utf-8') as f:
                json.dump(parsed, f, indent=2, ensure_ascii=False)
            success += 1
            
    print(f"Successfully parsed {success} / {len(evt_files)} event files")
