import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:hive_flutter/hive_flutter.dart';
import '../models/game_models.dart';

/// Service for handling game saves with persistent storage
class SaveService {
  static const String _boxName = 'game_saves';
  static const int _maxSaveSlots = 10;
  
  late Box<Map> _saveBox;
  
  /// Initialize the save service
  Future<void> initialize() async {
    _saveBox = await Hive.openBox<Map>(_boxName);
  }
  
  /// Get all available save files
  List<GameSave?> getSaveSlots() {
    final slots = <GameSave?>[];
    for (int i = 0; i < _maxSaveSlots; i++) {
      final key = 'save_$i';
      final data = _saveBox.get(key);
      
      if (data != null) {
        try {
          slots.add(_deserializeSave(data.cast<String, dynamic>()));
        } catch (e) {
          debugPrint('Error loading save slot $i: $e');
          slots.add(null);
        }
      } else {
        slots.add(null);
      }
    }
    return slots;
  }
  
  /// Save game to a specific slot
  Future<void> saveGame({
    required int slot,
    required String name,
    required PlayerCharacter player,
    required String currentMapId,
    required int playTimeSeconds,
  }) async {
    if (slot < 0 || slot >= _maxSaveSlots) {
      throw ArgumentError('Invalid save slot: $slot');
    }
    
    final gameSave = GameSave(
      name: name,
      savedAt: DateTime.now(),
      player: player,
      currentMapId: currentMapId,
      playTimeSeconds: playTimeSeconds,
    );
    
    final key = 'save_$slot';
    await _saveBox.put(key, _serializeSave(gameSave));
  }
  
  /// Load game from a specific slot
  GameSave? loadGame(int slot) {
    if (slot < 0 || slot >= _maxSaveSlots) {
      return null;
    }
    
    final key = 'save_$slot';
    final data = _saveBox.get(key);
    
    if (data == null) return null;
    
    try {
      return _deserializeSave(data.cast<String, dynamic>());
    } catch (e) {
      debugPrint('Error loading save slot $slot: $e');
      return null;
    }
  }
  
  /// Delete a save file
  Future<void> deleteSave(int slot) async {
    if (slot < 0 || slot >= _maxSaveSlots) {
      throw ArgumentError('Invalid save slot: $slot');
    }
    
    final key = 'save_$slot';
    await _saveBox.delete(key);
  }
  
  /// Serialize GameSave to Map
  Map<String, dynamic> _serializeSave(GameSave save) {
    return {
      'name': save.name,
      'savedAt': save.savedAt.toIso8601String(),
      'currentMapId': save.currentMapId,
      'playTimeSeconds': save.playTimeSeconds,
      'player': _serializePlayerCharacter(save.player),
    };
  }
  
  /// Deserialize Map to GameSave
  GameSave _deserializeSave(Map<String, dynamic> data) {
    return GameSave(
      name: data['name'] as String,
      savedAt: DateTime.parse(data['savedAt'] as String),
      currentMapId: data['currentMapId'] as String,
      playTimeSeconds: data['playTimeSeconds'] as int,
      player: _deserializePlayerCharacter(
        (data['player'] as Map).cast<String, dynamic>(),
      ),
    );
  }
  
  /// Serialize PlayerCharacter to Map
  Map<String, dynamic> _serializePlayerCharacter(PlayerCharacter player) {
    return {
      'id': player.id,
      'name': player.name,
      'characterClass': player.characterClass.toString(),
      'x': player.position.x,
      'y': player.position.y,
      'maxHp': player.maxHp,
      'currentHp': player.currentHp,
      'level': player.level,
      'experience': player.experience,
      'gold': player.gold,
      'strength': player.strength,
      'dexterity': player.dexterity,
      'constitution': player.constitution,
      'intelligence': player.intelligence,
      'wisdom': player.wisdom,
      'charisma': player.charisma,
      'inventory': player.inventory.map(_serializeItem).toList(),
      'abilities': player.abilities.map(_serializeAbility).toList(),
    };
  }
  
  /// Deserialize Map to PlayerCharacter
  PlayerCharacter _deserializePlayerCharacter(Map<String, dynamic> data) {
    final characterClass = CharacterClass.values.firstWhere(
      (cc) => cc.toString() == data['characterClass'],
    );
    
    final player = PlayerCharacter(
      id: data['id'] as String,
      name: data['name'] as String,
      x: data['x'] as int,
      y: data['y'] as int,
      characterClass: characterClass,
      maxHp: data['maxHp'] as int,
      level: data['level'] as int,
      strength: data['strength'] as int,
      dexterity: data['dexterity'] as int,
      constitution: data['constitution'] as int,
      intelligence: data['intelligence'] as int,
      wisdom: data['wisdom'] as int,
      charisma: data['charisma'] as int,
    );
    
    player.currentHp = data['currentHp'] as int;
    player.experience = data['experience'] as int;
    player.gold = data['gold'] as int;
    
    // Load inventory
    if (data['inventory'] != null) {
      final inventory = (data['inventory'] as List)
          .map((i) => _deserializeItem((i as Map).cast<String, dynamic>()))
          .toList();
      player.inventory.addAll(inventory);
    }
    
    // Load abilities
    if (data['abilities'] != null) {
      final abilities = (data['abilities'] as List)
          .map((a) => _deserializeAbility((a as Map).cast<String, dynamic>()))
          .toList();
      player.abilities.addAll(abilities);
    }
    
    return player;
  }
  
  Map<String, dynamic> _serializeItem(Item item) {
    return {
      'id': item.id,
      'name': item.name,
      'description': item.description,
      'type': item.type.toString(),
      'value': item.value,
      'quantity': item.quantity,
      'effects': item.effects,
    };
  }
  
  Item _deserializeItem(Map<String, dynamic> data) {
    return Item(
      id: data['id'] as String,
      name: data['name'] as String,
      description: data['description'] as String,
      type: ItemType.values.firstWhere(
        (t) => t.toString() == data['type'],
      ),
      value: data['value'] as int,
      quantity: data['quantity'] as int? ?? 1,
      effects: (data['effects'] as Map?)?.cast<String, int>() ?? {},
    );
  }
  
  Map<String, dynamic> _serializeAbility(Ability ability) {
    return {
      'id': ability.id,
      'name': ability.name,
      'description': ability.description,
      'type': ability.type.toString(),
      'manaCost': ability.manaCost,
      'cooldown': ability.cooldown,
      'range': ability.range,
      'effects': ability.effects,
    };
  }
  
  Ability _deserializeAbility(Map<String, dynamic> data) {
    return Ability(
      id: data['id'] as String,
      name: data['name'] as String,
      description: data['description'] as String,
      type: AbilityType.values.firstWhere(
        (t) => t.toString() == data['type'],
      ),
      manaCost: data['manaCost'] as int,
      cooldown: data['cooldown'] as int,
      range: data['range'] as int,
      effects: (data['effects'] as Map?)?.cast<String, int>() ?? {},
    );
  }
  
  /// Clear all saves (careful!)
  Future<void> clearAllSaves() async {
    await _saveBox.clear();
  }
}
