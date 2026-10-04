import 'package:flutter/foundation.dart';
import '../models/game_models.dart';
import '../services/save_service.dart';

/// Represents different game states
enum GameState {
  initializing,
  menu,
  loading,
  playing,
  paused,
  inBattle,
  gameOver,
  talking, // NPC dialogue
}

/// Main game state management provider
class GameStateProvider extends ChangeNotifier {
  late SaveService _saveService;
  
  // Game state
  GameState _currentState = GameState.initializing;
  PlayerCharacter? _player;
  GameMap? _currentMap;
  Battle? _currentBattle;
  List<GameSave?> _availableSaves = [];
  
  // Game time tracking
  int _playTimeSeconds = 0;
  late Stopwatch _playtimeStopwatch;
  
  // Settings
  bool _soundEnabled = true;
  bool _musicEnabled = true;
  double _masterVolume = 1.0;
  
  // Getters
  GameState get currentState => _currentState;
  PlayerCharacter? get player => _player;
  GameMap? get currentMap => _currentMap;
  Battle? get currentBattle => _currentBattle;
  List<GameSave?> get availableSaves => _availableSaves;
  int get playTimeSeconds => _playTimeSeconds;
  bool get soundEnabled => _soundEnabled;
  bool get musicEnabled => _musicEnabled;
  double get masterVolume => _masterVolume;
  
  /// Initialize the game state provider
  Future<void> initialize(SaveService saveService) async {
    _saveService = saveService;
    _playtimeStopwatch = Stopwatch();
    _availableSaves = _saveService.getSaveSlots();
    notifyListeners();
  }
  
  /// Start a new game
  Future<void> startNewGame(String playerName, CharacterClass playerClass) async {
    _changeState(GameState.loading);
    
    // Create player character
    _player = PlayerCharacter(
      id: 'hero_001',
      name: playerName,
      x: 10,
      y: 10,
      characterClass: playerClass,
      maxHp: 100,
      level: 1,
      strength: 15,
      dexterity: 12,
      constitution: 14,
      intelligence: 13,
      wisdom: 11,
      charisma: 10,
    );
    
    // Load first map
    await loadMap('map_00');
    
    _playTimeSeconds = 0;
    _playtimeStopwatch.reset();
    _playtimeStopwatch.start();
    
    _changeState(GameState.playing);
  }
  
  /// Load a game from a save slot
  Future<void> loadGameFromSlot(int slot) async {
    _changeState(GameState.loading);
    
    final save = _saveService.loadGame(slot);
    if (save != null) {
      _player = save.player;
      _playTimeSeconds = save.playTimeSeconds;
      
      await loadMap(save.currentMapId);
      
      _playtimeStopwatch.reset();
      _playtimeStopwatch.start();
      
      _changeState(GameState.playing);
    }
  }
  
  /// Save current game to a slot
  Future<void> saveGameToSlot({
    required int slot,
    required String saveName,
  }) async {
    if (_player == null || _currentMap == null) return;
    
    await _saveService.saveGame(
      slot: slot,
      name: saveName,
      player: _player!,
      currentMapId: _currentMap!.id,
      playTimeSeconds: _playTimeSeconds + _playtimeStopwatch.elapsedMilliseconds ~/ 1000,
    );
    
    _availableSaves = _saveService.getSaveSlots();
    notifyListeners();
  }
  
  /// Load a map by ID
  Future<void> loadMap(String mapId) async {
    // TODO: Load map from assets/game_data
    // For now, create a dummy map
    _currentMap = GameMap(
      id: mapId,
      name: 'Test Map',
      width: 50,
      height: 50,
      tiles: List.generate(
        50,
        (y) => List.generate(
          50,
          (x) => Tile(
            id: 0,
            name: 'Grass',
            isWalkable: true,
            spriteAsset: 'assets/sprites/tiles/grass.png',
          ),
        ),
      ),
      entities: [],
      musicTrackId: 0,
    );
    
    if (_player != null) {
      _currentMap!.entities.add(_player!);
    }
    
    notifyListeners();
  }
  
  /// Toggle pause
  void togglePause() {
    if (_currentState == GameState.playing) {
      _playtimeStopwatch.stop();
      _changeState(GameState.paused);
    } else if (_currentState == GameState.paused) {
      _playtimeStopwatch.start();
      _changeState(GameState.playing);
    }
  }
  
  /// Start a battle
  void startBattle(List<Entity> enemies) {
    if (_player == null) return;
    
    _currentBattle = Battle(
      id: 'battle_${DateTime.now().millisecondsSinceEpoch}',
      playerParty: [_player!],
      enemyParty: enemies,
      reward: 100,
    );
    
    _playtimeStopwatch.stop();
    _changeState(GameState.inBattle);
  }
  
  /// End current battle
  void endBattle() {
    if (_player != null && _currentBattle != null) {
      if (_currentBattle!.playerWon) {
        _player!.addGold(_currentBattle!.reward);
        _player!.addExperience(50);
      } else if (_currentBattle!.playerLost) {
        _changeState(GameState.gameOver);
        _playtimeStopwatch.stop();
        notifyListeners();
        return;
      }
    }
    
    _currentBattle = null;
    _playtimeStopwatch.start();
    _changeState(GameState.playing);
  }
  
  /// Move player in a direction
  void movePlayer(int dx, int dy) {
    if (_player == null || _currentMap == null) return;
    if (_currentState != GameState.playing) return;
    
    final newX = _player!.position.x + dx;
    final newY = _player!.position.y + dy;
    
    _currentMap!.moveEntity(_player!, newX, newY);
    notifyListeners();
  }
  
  /// Toggle settings
  void toggleSound() {
    _soundEnabled = !_soundEnabled;
    notifyListeners();
  }
  
  void toggleMusic() {
    _musicEnabled = !_musicEnabled;
    notifyListeners();
  }
  
  void setMasterVolume(double volume) {
    _masterVolume = volume.clamp(0.0, 1.0);
    notifyListeners();
  }
  
  /// Update playtime counter
  void updatePlaytime() {
    if (_playtimeStopwatch.isRunning) {
      _playTimeSeconds = _playtimeStopwatch.elapsedMilliseconds ~/ 1000;
    }
  }
  
  /// Change game state
  void _changeState(GameState newState) {
    if (_currentState != newState) {
      _currentState = newState;
      notifyListeners();
    }
  }
  
  /// Return to main menu
  void returnToMenu() {
    _playtimeStopwatch.stop();
    _player = null;
    _currentMap = null;
    _currentBattle = null;
    _changeState(GameState.menu);
  }
  
  /// Quit game
  void quitGame() {
    _playtimeStopwatch.stop();
    _changeState(GameState.initializing);
  }
}
