/// Constantes globais do jogo
class GameConstants {
  // Versão do jogo
  static const String appVersion = '0.1.0';
  static const String appName = 'Heroes Lore: Wind of Soltia';
  static const String appSubtitle = 'Modern Edition';
  
  // Renderização
  static const double TILE_SIZE = 32.0; // Pixels per tile
  static const int SCREEN_WIDTH_TILES = 20; // Tiles visible horizontally
  static const int SCREEN_HEIGHT_TILES = 15; // Tiles visible vertically
  
  // Mapa
  static const int MAX_MAP_WIDTH = 256;
  static const int MAX_MAP_HEIGHT = 256;
  
  // Salvamento
  static const int MAX_SAVE_SLOTS = 10;
  static const String SAVE_BOX_NAME = 'game_saves';
  
  // Combate
  static const int COMBAT_TURN_TIMEOUT_MS = 30000; // 30 segundos por turno
  static const double COMBAT_DAMAGE_VARIANCE = 0.1; // 10% de variação
  static const int BASE_EXPERIENCE_REWARD = 50;
  static const int BASE_GOLD_REWARD = 100;
  
  // Stats
  static const int MIN_STAT = 1;
  static const int MAX_STAT = 99;
  static const int MAX_LEVEL = 99;
  
  // Inventário
  static const int MAX_INVENTORY_SLOTS = 20;
  
  // Input
  static const double GAMEPAD_DEAD_ZONE = 0.2;
  static const int KEY_HOLD_REPEAT_DELAY_MS = 500;
  static const int KEY_HOLD_REPEAT_RATE_MS = 50;
  
  // UI
  static const Duration ANIMATION_DURATION = Duration(milliseconds: 200);
  static const Duration TRANSITION_DURATION = Duration(milliseconds: 300);
  
  // Audio
  static const double DEFAULT_MASTER_VOLUME = 1.0;
  static const double DEFAULT_MUSIC_VOLUME = 0.8;
  static const double DEFAULT_SFX_VOLUME = 1.0;
}

/// Configurações de responsividade
class ResponsiveConstants {
  // Breakpoints (em logical pixels)
  static const double MOBILE_MAX = 599;
  static const double TABLET_MIN = 600;
  static const double TABLET_MAX = 1199;
  static const double DESKTOP_MIN = 1200;
  
  // Padding/margins responsivos
  static const double MOBILE_PADDING = 16;
  static const double TABLET_PADDING = 24;
  static const double DESKTOP_PADDING = 32;
}

/// Cores do tema
class ThemeColors {
  // Primárias
  static const primaryColor = 0xFFD2691E; // Chocolate/Brown
  static const secondaryColor = 0xFFFF8C00; // Dark Orange
  static const accentColor = 0xFF00CED1; // Dark Turquoise
  
  // Neutras
  static const backgroundColor = 0xFF1A1A1A;
  static const surfaceColor = 0xFF2A2A2A;
  static const surfaceVariant = 0xFF3A3A3A;
  
  // Semânticas
  static const successColor = 0xFF4CAF50;
  static const warningColor = 0xFFFFC107;
  static const errorColor = 0xFFF44336;
  static const infoColor = 0xFF2196F3;
}

/// IDs de mapas (em ordem de campanha)
class MapIds {
  static const String startingVillage = 'map_00';
  static const String forestPath = 'map_01';
  static const String abandonedTemple = 'map_02';
  static const String darkCaves = 'map_03';
  static const String mountainPeak = 'map_04';
  // ... adicionar conforme implementar
}

/// IDs de NPCs importantes
class NPCIds {
  static const String innkeeper = 'npc_inn_001';
  static const String shopkeeper = 'npc_shop_001';
  static const String priest = 'npc_priest_001';
  static const String guard = 'npc_guard_001';
  // ... adicionar conforme implementar
}

/// IDs de enemies/monstros
class EnemyIds {
  static const String goblin = 'enm_goblin_001';
  static const String orc = 'enm_orc_001';
  static const String skeleton = 'enm_skeleton_001';
  static const String zombie = 'enm_zombie_001';
  static const String golem = 'enm_golem_001';
  // ... adicionar conforme implementar
}

/// IDs de boss fights
class BossIds {
  static const String darkLord = 'boss_dark_lord';
  static const String dragonKing = 'boss_dragon_king';
  // ... adicionar conforme implementar
}

/// IDs de itens
class ItemIds {
  // Armas
  static const String ironSword = 'itm_iron_sword';
  static const String steelAxe = 'itm_steel_axe';
  static const String magicStaff = 'itm_magic_staff';
  
  // Armaduras
  static const String ironArmor = 'itm_iron_armor';
  static const String leatherArmor = 'itm_leather_armor';
  
  // Consumíveis
  static const String healthPotion = 'itm_health_potion';
  static const String manaPotion = 'itm_mana_potion';
  static const String antidote = 'itm_antidote';
  
  // Missão
  static const String ancientKey = 'itm_ancient_key';
  static const String mysticalOrb = 'itm_mystical_orb';
}

/// IDs de abilities/spells
class AbilityIds {
  // Guerreiro
  static const String slash = 'ability_slash';
  static const String powerStrike = 'ability_power_strike';
  static const String defend = 'ability_defend';
  
  // Mago
  static const String fireball = 'ability_fireball';
  static const String frostbolt = 'ability_frostbolt';
  static const String magicShield = 'ability_magic_shield';
  
  // Clérigo
  static const String heal = 'ability_heal';
  static const String resurrect = 'ability_resurrect';
  static const String bless = 'ability_bless';
  
  // Geral
  static const String basicAttack = 'ability_basic_attack';
}

/// IDs de tracks de música
class MusicIds {
  static const int mainMenu = 0;
  static const int village = 1;
  static const int forest = 2;
  static const int temple = 3;
  static const int caves = 4;
  static const int mountains = 5;
  static const int bossBattle = 6;
  static const int gameOver = 7;
  // ... adicionar conforme implementar
}

/// Enum de localizações suportadas
enum GameLocale {
  ptBR('pt-BR', 'Português (Brasil)'),
  enGB('en-GB', 'English (UK)'),
  deDE('de-DE', 'Deutsch'),
  esES('es-ES', 'Español'),
  frFR('fr-FR', 'Français');
  
  final String code;
  final String displayName;
  
  const GameLocale(this.code, this.displayName);
}

/// Dificuldades do jogo
enum GameDifficulty {
  easy('easy', 'Fácil', 0.75),
  normal('normal', 'Normal', 1.0),
  hard('hard', 'Difícil', 1.5),
  insane('insane', 'Impossível', 2.0);
  
  final String id;
  final String displayName;
  final double damageMultiplier;
  
  const GameDifficulty(this.id, this.displayName, this.damageMultiplier);
}

/// Estados de saúde
enum HealthStatus {
  healthy(1.0, 'Saudável'),
  wounded(0.75, 'Ferido'),
  critical(0.25, 'Crítico'),
  dead(0.0, 'Morto');
  
  final double threshold;
  final String displayName;
  
  const HealthStatus(this.threshold, this.displayName);
  
  static HealthStatus fromHpPercent(double percent) {
    if (percent <= 0) return dead;
    if (percent < 0.25) return critical;
    if (percent < 0.75) return wounded;
    return healthy;
  }
}
