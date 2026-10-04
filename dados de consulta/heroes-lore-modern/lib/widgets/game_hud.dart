import 'package:flutter/material.dart';
import '../providers/game_state_provider.dart';

class GameHUD extends StatelessWidget {
  final GameStateProvider gameState;
  
  const GameHUD({required this.gameState});

  @override
  Widget build(BuildContext context) {
    final player = gameState.player;
    if (player == null) return const SizedBox.shrink();
    
    final hpPercent = (player.currentHp / player.maxHp).clamp(0.0, 1.0);
    
    return Padding(
      padding: const EdgeInsets.all(16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          // Player info card
          Container(
            padding: const EdgeInsets.all(12),
            decoration: BoxDecoration(
              color: Colors.black54,
              border: Border.all(color: Colors.orange),
              borderRadius: BorderRadius.circular(4),
            ),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              mainAxisSize: MainAxisSize.min,
              children: [
                Row(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    Text(
                      player.name,
                      style: const TextStyle(
                        fontSize: 18,
                        fontWeight: FontWeight.bold,
                        color: Colors.white,
                      ),
                    ),
                    const SizedBox(width: 8),
                    Chip(
                      label: Text('Lv ${player.level}'),
                      backgroundColor: Colors.deepOrange,
                      labelStyle: const TextStyle(color: Colors.white),
                      padding: EdgeInsets.zero,
                    ),
                  ],
                ),
                const SizedBox(height: 8),
                // HP Bar
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        const Text(
                          'HP',
                          style: TextStyle(color: Colors.white70),
                        ),
                        Text(
                          '${player.currentHp}/${player.maxHp}',
                          style: const TextStyle(color: Colors.white70),
                        ),
                      ],
                    ),
                    const SizedBox(height: 4),
                    ClipRRect(
                      borderRadius: BorderRadius.circular(2),
                      child: LinearProgressIndicator(
                        value: hpPercent,
                        minHeight: 8,
                        backgroundColor: Colors.grey.shade700,
                        valueColor: AlwaysStoppedAnimation<Color>(
                          hpPercent > 0.5
                              ? Colors.green
                              : hpPercent > 0.25
                                  ? Colors.orange
                                  : Colors.red,
                        ),
                      ),
                    ),
                  ],
                ),
                const SizedBox(height: 8),
                // Stats row
                Row(
                  mainAxisSize: MainAxisSize.min,
                  spacing: 16,
                  children: [
                    _StatPill('EXP', '${player.experience}'),
                    _StatPill('Gold', '${player.gold}'),
                    _StatPill('STR', '${player.strength}'),
                  ],
                ),
              ],
            ),
          ),
          
          const SizedBox(height: 16),
          
          // Game time
          Container(
            padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 4),
            decoration: BoxDecoration(
              color: Colors.black54,
              border: Border.all(color: Colors.cyan),
              borderRadius: BorderRadius.circular(4),
            ),
            child: Text(
              'Time: ${_formatPlaytime(gameState.playTimeSeconds)}',
              style: const TextStyle(
                fontSize: 12,
                color: Colors.white70,
              ),
            ),
          ),
          
          // Game state indicator
          const Spacer(),
          Align(
            alignment: Alignment.bottomRight,
            child: Container(
              padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 6),
              decoration: BoxDecoration(
                color: Colors.black54,
                border: Border.all(
                  color: _getStateColor(gameState.currentState),
                ),
                borderRadius: BorderRadius.circular(4),
              ),
              child: Text(
                _getStateLabel(gameState.currentState),
                style: TextStyle(
                  fontSize: 12,
                  color: _getStateColor(gameState.currentState),
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
  
  String _formatPlaytime(int seconds) {
    final hours = seconds ~/ 3600;
    final minutes = (seconds % 3600) ~/ 60;
    final secs = seconds % 60;
    return '${hours.toString().padLeft(2, '0')}:${minutes.toString().padLeft(2, '0')}:${secs.toString().padLeft(2, '0')}';
  }
  
  String _getStateLabel(GameState state) {
    switch (state) {
      case GameState.playing:
        return '● Playing';
      case GameState.paused:
        return '⏸ Paused';
      case GameState.inBattle:
        return '⚔ Battle';
      case GameState.talking:
        return '💬 Talking';
      default:
        return '';
    }
  }
  
  Color _getStateColor(GameState state) {
    switch (state) {
      case GameState.playing:
        return Colors.green;
      case GameState.paused:
        return Colors.yellow;
      case GameState.inBattle:
        return Colors.red;
      case GameState.talking:
        return Colors.blue;
      default:
        return Colors.grey;
    }
  }
}

class _StatPill extends StatelessWidget {
  final String label;
  final String value;
  
  const _StatPill(this.label, this.value);

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisSize: MainAxisSize.min,
      children: [
        Text(
          label,
          style: const TextStyle(
            fontSize: 10,
            color: Colors.white54,
          ),
        ),
        Text(
          value,
          style: const TextStyle(
            fontSize: 14,
            fontWeight: FontWeight.bold,
            color: Colors.white,
          ),
        ),
      ],
    );
  }
}
