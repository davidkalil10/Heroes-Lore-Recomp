import 'package:flutter/foundation.dart';

/// Position in the game world (tile-based)
class Position {
  int x;
  int y;
  
  Position(this.x, this.y);
  
  Position copy() => Position(x, y);
  
  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is Position && runtimeType == other.runtimeType && x == other.x && y == other.y;
  
  @override
  int get hashCode => x.hashCode ^ y.hashCode;
}

/// Represents a character class/profession
enum CharacterClass {
  warrior,
  mage,
  archer,
  cleric,
  rogue,
  paladin,
}

/// Base entity in the game world
abstract class Entity {
  final String id;
  final String name;
  late Position position;
  final int maxHp;
  late int currentHp;
  final int level;
  final int strength;
  final int dexterity;
  final int constitution;
  final int intelligence;
  final int wisdom;
  final int charisma;
  
  Entity({
    required this.id,
    required this.name,
    required int x,
    required int y,
    required this.maxHp,
    required this.level,
    required this.strength,
    required this.dexterity,
    required this.constitution,
    required this.intelligence,
    required this.wisdom,
    required this.charisma,
  }) {
    position = Position(x, y);
    currentHp = maxHp;
  }
  
  void takeDamage(int damage) {
    currentHp = (currentHp - damage).clamp(0, maxHp);
  }
  
  void heal(int amount) {
    currentHp = (currentHp + amount).clamp(0, maxHp);
  }
  
  bool get isAlive => currentHp > 0;
}

/// Represents a player character
class PlayerCharacter extends Entity {
  final CharacterClass characterClass;
  int experience = 0;
  int gold = 0;
  final List<Item> inventory = [];
  final List<Ability> abilities = [];
  
  PlayerCharacter({
    required String id,
    required String name,
    required int x,
    required int y,
    required this.characterClass,
    required int maxHp,
    required int level,
    required int strength,
    required int dexterity,
    required int constitution,
    required int intelligence,
    required int wisdom,
    required int charisma,
  }) : super(
    id: id,
    name: name,
    x: x,
    y: y,
    maxHp: maxHp,
    level: level,
    strength: strength,
    dexterity: dexterity,
    constitution: constitution,
    intelligence: intelligence,
    wisdom: wisdom,
    charisma: charisma,
  );
  
  void addExperience(int amount) {
    experience += amount;
  }
  
  void addGold(int amount) {
    gold += amount;
  }
  
  void addItem(Item item) {
    inventory.add(item);
  }
  
  void removeItem(Item item) {
    inventory.remove(item);
  }
  
  void learnAbility(Ability ability) {
    if (!abilities.contains(ability)) {
      abilities.add(ability);
    }
  }
}

/// Represents an NPC or enemy
class NonPlayerCharacter extends Entity {
  final String dialogue;
  final bool isHostile;
  final List<Item> lootTable;
  
  NonPlayerCharacter({
    required String id,
    required String name,
    required int x,
    required int y,
    required int maxHp,
    required int level,
    required int strength,
    required int dexterity,
    required int constitution,
    required int intelligence,
    required int wisdom,
    required int charisma,
    required this.dialogue,
    required this.isHostile,
    required this.lootTable,
  }) : super(
    id: id,
    name: name,
    x: x,
    y: y,
    maxHp: maxHp,
    level: level,
    strength: strength,
    dexterity: dexterity,
    constitution: constitution,
    intelligence: intelligence,
    wisdom: wisdom,
    charisma: charisma,
  );
  
  List<Item> generateLoot() {
    // TODO: Implement loot generation based on lootTable
    return [];
  }
}

/// Item that can be carried in inventory
class Item {
  final String id;
  final String name;
  final String description;
  final ItemType type;
  final int value;
  int quantity;
  final Map<String, int> effects; // e.g., {hp: 50, mana: 30}
  
  Item({
    required this.id,
    required this.name,
    required this.description,
    required this.type,
    required this.value,
    this.quantity = 1,
    this.effects = const {},
  });
  
  Item copy() => Item(
    id: id,
    name: name,
    description: description,
    type: type,
    value: value,
    quantity: quantity,
    effects: Map.from(effects),
  );
}

enum ItemType {
  weapon,
  armor,
  consumable,
  quest,
  miscellaneous,
}

/// Represents an ability/spell the player can use
class Ability {
  final String id;
  final String name;
  final String description;
  final AbilityType type;
  final int manaCost;
  final int cooldown;
  final int range;
  final Map<String, int> effects;
  
  Ability({
    required this.id,
    required this.name,
    required this.description,
    required this.type,
    required this.manaCost,
    required this.cooldown,
    required this.range,
    this.effects = const {},
  });
}

enum AbilityType {
  attack,
  defense,
  healing,
  buff,
  debuff,
  utility,
}

/// Represents a tile in a map
class Tile {
  final int id;
  final String name;
  final bool isWalkable;
  final String spriteAsset;
  
  Tile({
    required this.id,
    required this.name,
    required this.isWalkable,
    required this.spriteAsset,
  });
}

/// Represents a game map
class GameMap {
  final String id;
  final String name;
  final int width;
  final int height;
  final List<List<Tile>> tiles;
  final List<Entity> entities;
  final int musicTrackId;
  
  GameMap({
    required this.id,
    required this.name,
    required this.width,
    required this.height,
    required this.tiles,
    required this.entities,
    required this.musicTrackId,
  });
  
  bool isWalkable(int x, int y) {
    if (x < 0 || x >= width || y < 0 || y >= height) return false;
    return tiles[y][x].isWalkable;
  }
  
  Entity? getEntityAt(int x, int y) {
    try {
      return entities.firstWhere(
        (e) => e.position.x == x && e.position.y == y,
      );
    } catch (e) {
      return null;
    }
  }
  
  void moveEntity(Entity entity, int newX, int newY) {
    if (isWalkable(newX, newY) && getEntityAt(newX, newY) == null) {
      entity.position.x = newX;
      entity.position.y = newY;
    }
  }
}

/// Represents a battle scenario
class Battle {
  final String id;
  final List<Entity> playerParty;
  final List<Entity> enemyParty;
  final int reward;
  bool isActive = true;
  
  Battle({
    required this.id,
    required this.playerParty,
    required this.enemyParty,
    required this.reward,
  });
  
  bool get playerWon => enemyParty.every((e) => !e.isAlive);
  bool get playerLost => playerParty.every((e) => !e.isAlive);
  bool get isOver => playerWon || playerLost;
}

/// Game save file data
class GameSave {
  final String name;
  final DateTime savedAt;
  final PlayerCharacter player;
  final String currentMapId;
  final int playTimeSeconds;
  
  GameSave({
    required this.name,
    required this.savedAt,
    required this.player,
    required this.currentMapId,
    required this.playTimeSeconds,
  });
}
