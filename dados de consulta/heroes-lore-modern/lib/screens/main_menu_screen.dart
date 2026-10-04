import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:responsive_builder/responsive_builder.dart';
import '../providers/game_state_provider.dart';
import '../models/game_models.dart';

class MainMenuScreen extends StatefulWidget {
  const MainMenuScreen({Key? key}) : super(key: key);

  @override
  State<MainMenuScreen> createState() => _MainMenuScreenState();
}

class _MainMenuScreenState extends State<MainMenuScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ResponsiveBuilder(
        builder: (context, sizingInformation) {
          // Responsiveness: Mobile, Tablet, Desktop
          return Container(
            decoration: BoxDecoration(
              gradient: LinearGradient(
                begin: Alignment.topLeft,
                end: Alignment.bottomRight,
                colors: [
                  Colors.deepOrange.shade900,
                  Colors.orange.shade800,
                ],
              ),
            ),
            child: Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  // Title
                  Text(
                    'HEROES LORE',
                    style: Theme.of(context).textTheme.displayLarge?.copyWith(
                      fontWeight: FontWeight.bold,
                      color: Colors.white,
                    ),
                  ),
                  Text(
                    'Wind of Soltia - Modern Edition',
                    style: Theme.of(context).textTheme.titleLarge?.copyWith(
                      color: Colors.white70,
                    ),
                  ),
                  const SizedBox(height: 64),
                  
                  // Menu buttons
                  SizedBox(
                    width: 300,
                    child: Column(
                      mainAxisSize: MainAxisSize.min,
                      children: [
                        _MenuButton(
                          label: 'New Game',
                          onPressed: () {
                            _showNewGameDialog(context);
                          },
                        ),
                        const SizedBox(height: 16),
                        _MenuButton(
                          label: 'Load Game',
                          onPressed: () {
                            Navigator.of(context).push(
                              MaterialPageRoute(
                                builder: (_) => const LoadGameScreen(),
                              ),
                            );
                          },
                        ),
                        const SizedBox(height: 16),
                        _MenuButton(
                          label: 'Settings',
                          onPressed: () {
                            // TODO: Open settings screen
                          },
                        ),
                        const SizedBox(height: 16),
                        _MenuButton(
                          label: 'Credits',
                          onPressed: () {
                            // TODO: Open credits screen
                          },
                        ),
                        const SizedBox(height: 16),
                        _MenuButton(
                          label: 'Exit',
                          onPressed: () {
                            // In a real app, properly exit
                            context.read<GameStateProvider>().quitGame();
                          },
                        ),
                      ],
                    ),
                  ),
                  
                  const SizedBox(height: 64),
                  
                  // Footer
                  Text(
                    'Fan remake - Original by Hands-On Mobile',
                    style: Theme.of(context).textTheme.bodySmall?.copyWith(
                      color: Colors.white54,
                    ),
                  ),
                ],
              ),
            ),
          );
        },
      ),
    );
  }
  
  void _showNewGameDialog(BuildContext context) {
    String playerName = '';
    CharacterClass selectedClass = CharacterClass.warrior;
    
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('Create Character'),
        content: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            TextField(
              decoration: const InputDecoration(
                labelText: 'Character Name',
                border: OutlineInputBorder(),
              ),
              onChanged: (value) => playerName = value,
            ),
            const SizedBox(height: 16),
            DropdownButton<CharacterClass>(
              value: selectedClass,
              isExpanded: true,
              items: CharacterClass.values.map((cls) {
                return DropdownMenuItem(
                  value: cls,
                  child: Text(cls.toString().split('.').last.toUpperCase()),
                );
              }).toList(),
              onChanged: (value) {
                if (value != null) {
                  selectedClass = value;
                }
              },
            ),
          ],
        ),
        actions: [
          TextButton(
            onPressed: () => Navigator.pop(context),
            child: const Text('Cancel'),
          ),
          ElevatedButton(
            onPressed: () {
              if (playerName.isEmpty) {
                ScaffoldMessenger.of(context).showSnackBar(
                  const SnackBar(content: Text('Please enter a name')),
                );
                return;
              }
              
              Navigator.pop(context);
              context.read<GameStateProvider>().startNewGame(
                playerName,
                selectedClass,
              );
            },
            child: const Text('Start'),
          ),
        ],
      ),
    );
  }
}

class _MenuButton extends StatelessWidget {
  final String label;
  final VoidCallback onPressed;
  
  const _MenuButton({
    required this.label,
    required this.onPressed,
  });

  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
      onPressed: onPressed,
      style: ElevatedButton.styleFrom(
        padding: const EdgeInsets.symmetric(vertical: 16),
        backgroundColor: Colors.deepOrange.shade700,
        foregroundColor: Colors.white,
      ),
      child: Text(
        label,
        style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
      ),
    );
  }
}

/// Load game screen showing all save slots
class LoadGameScreen extends StatelessWidget {
  const LoadGameScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Load Game')),
      body: Consumer<GameStateProvider>(
        builder: (context, gameState, _) {
          final saves = gameState.availableSaves;
          
          return ListView.builder(
            itemCount: saves.length,
            itemBuilder: (context, index) {
              final save = saves[index];
              
              return Card(
                margin: const EdgeInsets.all(8),
                child: ListTile(
                  title: save != null
                      ? Text('Slot $index - ${save.name}')
                      : Text('Slot $index - Empty'),
                  subtitle: save != null
                      ? Text(
                        'Level ${save.player.level} ${save.player.characterClass.toString().split('.').last} • '
                        'Playtime: ${_formatPlaytime(save.playTimeSeconds)}',
                      )
                      : null,
                  enabled: save != null,
                  onTap: save != null
                      ? () {
                        context.read<GameStateProvider>().loadGameFromSlot(index);
                      }
                      : null,
                ),
              );
            },
          );
        },
      ),
    );
  }
  
  String _formatPlaytime(int seconds) {
    final hours = seconds ~/ 3600;
    final minutes = (seconds % 3600) ~/ 60;
    return '${hours}h ${minutes}m';
  }
}
