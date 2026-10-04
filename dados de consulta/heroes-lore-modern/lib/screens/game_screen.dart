import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:provider/provider.dart';
import 'package:responsive_builder/responsive_builder.dart';
import '../providers/game_state_provider.dart';
import '../core/input_handler.dart';
import '../models/game_models.dart';
import '../widgets/game_hud.dart';
import '../widgets/virtual_dpad.dart';

class GameScreen extends StatefulWidget {
  const GameScreen({Key? key}) : super(key: key);

  @override
  State<GameScreen> createState() => _GameScreenState();
}

class _GameScreenState extends State<GameScreen> {
  late FocusNode _focusNode;
  final List<GameAction> _activeActions = [];
  
  @override
  void initState() {
    super.initState();
    _focusNode = FocusNode();
    WidgetsBinding.instance.addPostFrameCallback((_) {
      _focusNode.requestFocus();
    });
  }
  
  @override
  void dispose() {
    _focusNode.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: () async {
        // Handle back button
        context.read<GameStateProvider>().togglePause();
        return false;
      },
      child: Scaffold(
        body: ResponsiveBuilder(
          builder: (context, sizingInformation) {
            return RawKeyboardListener(
              focusNode: _focusNode,
              onKey: _handleKeyboardInput,
              child: Consumer<GameStateProvider>(
                builder: (context, gameState, _) {
                  if (gameState.currentMap == null) {
                    return const Center(child: CircularProgressIndicator());
                  }
                  
                  return Stack(
                    children: [
                      // Game world/map rendering
                      GameWorldView(
                        map: gameState.currentMap!,
                        player: gameState.player!,
                      ),
                      
                      // HUD overlay
                      GameHUD(gameState: gameState),
                      
                      // Virtual D-Pad (mobile only)
                      if (sizingInformation.deviceScreenType == DeviceScreenType.mobile)
                        Positioned(
                          bottom: 20,
                          left: 20,
                          child: VirtualDPad(
                            onDirectionChange: (dx, dy) {
                              context.read<GameStateProvider>().movePlayer(dx, dy);
                            },
                          ),
                        ),
                      
                      // Action buttons (mobile)
                      if (sizingInformation.deviceScreenType == DeviceScreenType.mobile)
                        Positioned(
                          bottom: 20,
                          right: 20,
                          child: Column(
                            mainAxisSize: MainAxisSize.min,
                            spacing: 8,
                            children: [
                              FloatingActionButton(
                                heroTag: 'attack_btn',
                                mini: true,
                                onPressed: () {
                                  // TODO: Implement attack
                                },
                                child: const Icon(Icons.flash_on),
                              ),
                              FloatingActionButton(
                                heroTag: 'interact_btn',
                                mini: true,
                                onPressed: () {
                                  // TODO: Implement interact
                                },
                                child: const Icon(Icons.touch_app),
                              ),
                              FloatingActionButton(
                                heroTag: 'menu_btn',
                                onPressed: () {
                                  context.read<GameStateProvider>().togglePause();
                                },
                                child: const Icon(Icons.menu),
                              ),
                            ],
                          ),
                        ),
                      
                      // Pause menu overlay
                      if (gameState.currentState == GameState.paused)
                        PauseMenuOverlay(gameState: gameState),
                    ],
                  );
                },
              ),
            );
          },
        ),
      ),
    );
  }
  
  void _handleKeyboardInput(RawKeyEvent event) {
    if (event is RawKeyDownEvent) {
      final action = InputMapper.keyboardMap[event.logicalKey];
      if (action != null && action != GameAction.none) {
        _activeActions.add(action);
        _processInput();
      }
    } else if (event is RawKeyUpEvent) {
      final action = InputMapper.keyboardMap[event.logicalKey];
      if (action != null) {
        _activeActions.remove(action);
      }
    }
  }
  
  void _processInput() {
    final gameState = context.read<GameStateProvider>();
    
    if (_activeActions.contains(GameAction.moveUp)) {
      gameState.movePlayer(0, -1);
    }
    if (_activeActions.contains(GameAction.moveDown)) {
      gameState.movePlayer(0, 1);
    }
    if (_activeActions.contains(GameAction.moveLeft)) {
      gameState.movePlayer(-1, 0);
    }
    if (_activeActions.contains(GameAction.moveRight)) {
      gameState.movePlayer(1, 0);
    }
    
    if (_activeActions.contains(GameAction.pause)) {
      gameState.togglePause();
      _activeActions.remove(GameAction.pause);
    }
  }
}

/// Renders the game world/map
class GameWorldView extends StatelessWidget {
  final GameMap map;
  final PlayerCharacter player;
  
  const GameWorldView({
    required this.map,
    required this.player,
  });

  @override
  Widget build(BuildContext context) {
    final screenSize = MediaQuery.of(context).size;
    const tileSize = 32.0; // Pixel-perfect tile size
    
    // Calculate viewport based on screen size
    final visibleTilesX = (screenSize.width / tileSize).ceil();
    final visibleTilesY = (screenSize.height / tileSize).ceil();
    
    // Calculate offset based on player position (center player on screen)
    final offsetX = (player.position.x - visibleTilesX ~/ 2) * tileSize;
    final offsetY = (player.position.y - visibleTilesY ~/ 2) * tileSize;
    
    return Container(
      color: Colors.black,
      child: Stack(
        children: [
          // Tile map
          CustomPaint(
            painter: MapPainter(
              map: map,
              tileSize: tileSize,
              offsetX: offsetX,
              offsetY: offsetY,
              screenWidth: screenSize.width,
              screenHeight: screenSize.height,
            ),
            size: screenSize,
          ),
          
          // Player sprite
          Positioned(
            left: (player.position.x * tileSize) - offsetX,
            top: (player.position.y * tileSize) - offsetY,
            child: Container(
              width: tileSize,
              height: tileSize,
              decoration: BoxDecoration(
                color: Colors.blue,
                border: Border.all(color: Colors.cyan),
              ),
              child: const Center(
                child: Text('P', style: TextStyle(color: Colors.white)),
              ),
            ),
          ),
          
          // Other entities
          ...map.entities
              .where((e) => e.id != player.id)
              .map((entity) {
                return Positioned(
                  left: (entity.position.x * tileSize) - offsetX,
                  top: (entity.position.y * tileSize) - offsetY,
                  child: Container(
                    width: tileSize,
                    height: tileSize,
                    decoration: BoxDecoration(
                      color: entity is NonPlayerCharacter && entity.isHostile
                          ? Colors.red
                          : Colors.green,
                      border: Border.all(color: Colors.white),
                    ),
                    child: const Center(
                      child: Text('E', style: TextStyle(color: Colors.white)),
                    ),
                  ),
                );
              })
              .toList(),
        ],
      ),
    );
  }
}

/// Custom painter for rendering the tile map
class MapPainter extends CustomPainter {
  final GameMap map;
  final double tileSize;
  final double offsetX;
  final double offsetY;
  final double screenWidth;
  final double screenHeight;
  
  MapPainter({
    required this.map,
    required this.tileSize,
    required this.offsetX,
    required this.offsetY,
    required this.screenWidth,
    required this.screenHeight,
  });

  @override
  void paint(Canvas canvas, Size size) {
    final paint = Paint()..color = Colors.grey.shade800;
    
    final startX = (offsetX / tileSize).floor();
    final startY = (offsetY / tileSize).floor();
    final endX = startX + (screenWidth / tileSize).ceil() + 1;
    final endY = startY + (screenHeight / tileSize).ceil() + 1;
    
    for (int y = startX.clamp(0, map.height - 1).toInt();
        y <= endX.clamp(0, map.height - 1).toInt();
        y++) {
      for (int x = startY.clamp(0, map.width - 1).toInt();
          x <= endX.clamp(0, map.width - 1).toInt();
          x++) {
        final tile = map.tiles[y][x];
        
        final rectX = (x * tileSize) - offsetX;
        final rectY = (y * tileSize) - offsetY;
        
        // Draw tile background
        canvas.drawRect(
          Rect.fromLTWH(rectX, rectY, tileSize, tileSize),
          paint,
        );
        
        // Draw grid
        canvas.drawRect(
          Rect.fromLTWH(rectX, rectY, tileSize, tileSize),
          Paint()
            ..color = Colors.grey.shade600
            ..style = PaintingStyle.stroke
            ..strokeWidth = 0.5,
        );
      }
    }
  }

  @override
  bool shouldRepaint(MapPainter oldDelegate) {
    return oldDelegate.offsetX != offsetX || oldDelegate.offsetY != offsetY;
  }
}

/// Pause menu overlay
class PauseMenuOverlay extends StatelessWidget {
  final GameStateProvider gameState;
  
  const PauseMenuOverlay({required this.gameState});

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.black54,
      child: Center(
        child: Card(
          child: Padding(
            padding: const EdgeInsets.all(24),
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                const Text(
                  'PAUSED',
                  style: TextStyle(fontSize: 32, fontWeight: FontWeight.bold),
                ),
                const SizedBox(height: 32),
                SizedBox(
                  width: 200,
                  child: Column(
                    mainAxisSize: MainAxisSize.min,
                    spacing: 8,
                    children: [
                      ElevatedButton(
                        onPressed: () {
                          context.read<GameStateProvider>().togglePause();
                        },
                        child: const Text('Resume'),
                      ),
                      ElevatedButton(
                        onPressed: () {
                          // TODO: Open settings
                        },
                        child: const Text('Settings'),
                      ),
                      ElevatedButton(
                        onPressed: () {
                          // TODO: Save current game
                        },
                        child: const Text('Save Game'),
                      ),
                      ElevatedButton(
                        onPressed: () {
                          context.read<GameStateProvider>().returnToMenu();
                        },
                        child: const Text('Main Menu'),
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
