import '../models/game_models.dart';

/// Sistema de combate por turnos
class CombatSystem {
  final Battle battle;
  late List<Entity> turnOrder;
  int currentTurnIndex = 0;
  
  CombatSystem({required this.battle}) {
    _calculateTurnOrder();
  }
  
  /// Calcula a ordem de turno baseado em velocidade
  void _calculateTurnOrder() {
    turnOrder = [...battle.playerParty, ...battle.enemyParty];
    
    // Ordenar por Dexterity (higher = faster)
    turnOrder.sort((a, b) => b.dexterity.compareTo(a.dexterity));
    
    // Adicionar pequena variação aleatória
    for (int i = 0; i < turnOrder.length; i++) {
      if (i > 0 && turnOrder[i].dexterity == turnOrder[i - 1].dexterity) {
        // Aleatorizar ordem se têm mesma dex
      }
    }
  }
  
  /// Retorna a entidade cuja vez é
  Entity get currentActor => turnOrder[currentTurnIndex];
  
  /// Verifica se é a vez de um player
  bool get isPlayerTurn => battle.playerParty.contains(currentActor);
  
  /// Verifica se é a vez de um inimigo
  bool get isEnemyTurn => battle.enemyParty.contains(currentActor);
  
  /// Próxima entidade viva
  void nextValidTurn() {
    do {
      currentTurnIndex = (currentTurnIndex + 1) % turnOrder.length;
    } while (!currentActor.isAlive);
  }
  
  /// Processa um ataque
  AttackResult executeAttack({
    required Entity attacker,
    required Entity target,
    Ability? ability,
  }) {
    if (!currentActor.isAlive || !target.isAlive) {
      return AttackResult.invalid();
    }
    
    // Calcular damage base
    int baseDamage = ability?.effects['damage'] ?? attacker.strength;
    
    // Critério para acerto (Hit chance)
    int hitChance = 80 + (attacker.dexterity - target.dexterity);
    bool isHit = _rollDice(100) <= hitChance;
    
    if (!isHit) {
      return AttackResult.miss();
    }
    
    // Variação de damage
    int variance = (baseDamage * 0.1).toInt();
    int finalDamage = baseDamage + _rollDice(variance * 2) - variance;
    
    // Critério para crítico
    int critChance = (attacker.dexterity / 10).floor();
    bool isCrit = _rollDice(100) < critChance;
    
    if (isCrit) {
      finalDamage = (finalDamage * 1.5).toInt();
    }
    
    // Aplicar defesa do alvo
    if (target is PlayerCharacter || target is NonPlayerCharacter) {
      // Armadura reduz damage
      int defenseReduction = (target.constitution / 2).toInt();
      finalDamage = (finalDamage - defenseReduction).clamp(1, 9999);
    }
    
    // Aplicar damage
    target.takeDamage(finalDamage);
    
    return AttackResult.hit(
      damage: finalDamage,
      isCritical: isCrit,
      targetSurvived: target.isAlive,
    );
  }
  
  /// Processa uma ação defensiva
  void executeDefend({required Entity entity}) {
    // TODO: Implementar buff temporário de defesa
    // entity.addBuff(Buff(name: 'Defend', duration: 1, defenseBonus: 0.5));
  }
  
  /// Processa um item consumível
  void useItem({
    required Entity user,
    required Item item,
    required Entity target,
  }) {
    if (!user.inventory.contains(item)) return;
    
    final effects = item.effects;
    
    if (effects.containsKey('hp')) {
      target.heal(effects['hp']!);
    }
    
    // Remover item se quantidad = 1, senão decrementar
    if (item.quantity <= 1) {
      user.inventory.remove(item);
    } else {
      item.quantity--;
    }
  }
  
  /// Testa se a batalha terminou
  bool isBattleOver() {
    return battle.playerWon || battle.playerLost;
  }
  
  /// Simula um D20 (0-max)
  int _rollDice(int max) {
    return DateTime.now().millisecondsSinceEpoch % max;
  }
}

/// Resultado de um ataque
class AttackResult {
  final bool isValid;
  final bool isHit;
  final int damage;
  final bool isCritical;
  final bool targetSurvived;
  final String message;
  
  AttackResult._({
    required this.isValid,
    required this.isHit,
    required this.damage,
    required this.isCritical,
    required this.targetSurvived,
    required this.message,
  });
  
  factory AttackResult.invalid() {
    return AttackResult._(
      isValid: false,
      isHit: false,
      damage: 0,
      isCritical: false,
      targetSurvived: true,
      message: 'Ação inválida',
    );
  }
  
  factory AttackResult.miss() {
    return AttackResult._(
      isValid: true,
      isHit: false,
      damage: 0,
      isCritical: false,
      targetSurvived: true,
      message: 'Errou!',
    );
  }
  
  factory AttackResult.hit({
    required int damage,
    required bool isCritical,
    required bool targetSurvived,
  }) {
    final msg = isCritical
        ? 'GOLPE CRÍTICO! $damage de damage!'
        : '$damage de damage!';
    
    return AttackResult._(
      isValid: true,
      isHit: true,
      damage: damage,
      isCritical: isCritical,
      targetSurvived: targetSurvived,
      message: msg,
    );
  }
}

/// IA simples para inimigos
class EnemyAI {
  final Entity enemy;
  final List<Entity> playerParty;
  
  EnemyAI({
    required this.enemy,
    required this.playerParty,
  });
  
  /// Decide a próxima ação do inimigo
  EnemyAction decideAction() {
    if (playerParty.isEmpty) {
      return EnemyAction.pass();
    }
    
    // Encontrar alvo com menor HP
    Entity target = playerParty.first;
    for (final player in playerParty) {
      if (player.isAlive && player.currentHp < target.currentHp) {
        target = player;
      }
    }
    
    // 70% atacar, 30% defender (quando HP baixo)
    if (enemy.currentHp < enemy.maxHp * 0.3) {
      return EnemyAction.defend();
    } else if ((DateTime.now().millisecondsSinceEpoch % 100) < 70) {
      return EnemyAction.attack(target: target);
    } else {
      return EnemyAction.defend();
    }
  }
}

/// Ação que um inimigo pode tomar
abstract class EnemyAction {
  factory EnemyAction.attack({required Entity target}) {
    return _AttackAction(target);
  }
  
  factory EnemyAction.defend() {
    return _DefendAction();
  }
  
  factory EnemyAction.pass() {
    return _PassAction();
  }
}

class _AttackAction extends EnemyAction {
  final Entity target;
  _AttackAction(this.target);
}

class _DefendAction extends EnemyAction {}

class _PassAction extends EnemyAction {}

// ============================================================================
// EXEMPLO DE USO
// ============================================================================
/*

// No GameStateProvider.startBattle():
void startBattle(List<Entity> enemies) {
  if (_player == null) return;
  
  _currentBattle = Battle(
    id: 'battle_${DateTime.now().millisecondsSinceEpoch}',
    playerParty: [_player!],
    enemyParty: enemies,
    reward: 100,
  );
  
  _combatSystem = CombatSystem(battle: _currentBattle!);
  _changeState(GameState.inBattle);
}

// No BattleScreen - processar um turno:
void processBattleTurn(GameAction action) {
  if (_combatSystem == null || _combatSystem!.isBattleOver()) return;
  
  final actor = _combatSystem!.currentActor;
  
  if (_combatSystem!.isPlayerTurn) {
    // Player escolheu uma ação
    Entity? target;
    
    switch (action) {
      case GameAction.attack:
        target = _selectTarget(_combatSystem!.battle.enemyParty);
        if (target != null) {
          final result = _combatSystem!.executeAttack(
            attacker: actor as PlayerCharacter,
            target: target,
          );
          _addBattleLog(result.message);
        }
        break;
      
      case GameAction.defend:
        _combatSystem!.executeDefend(entity: actor);
        _addBattleLog('${actor.name} assume posição defensiva!');
        break;
      
      case GameAction.useItem:
        final item = (actor as PlayerCharacter).inventory.firstOrNull;
        if (item != null && target != null) {
          _combatSystem!.useItem(user: actor, item: item, target: target);
          _addBattleLog('${actor.name} usou ${item.name}!');
        }
        break;
      
      default:
        break;
    }
  } else if (_combatSystem!.isEnemyTurn) {
    // IA do inimigo
    final ai = EnemyAI(
      enemy: actor,
      playerParty: _combatSystem!.battle.playerParty,
    );
    
    final action = ai.decideAction();
    
    if (action is _AttackAction) {
      final result = _combatSystem!.executeAttack(
        attacker: actor,
        target: action.target,
      );
      _addBattleLog('${actor.name} atacou ${action.target.name}! ${result.message}');
    } else if (action is _DefendAction) {
      _combatSystem!.executeDefend(entity: actor);
      _addBattleLog('${actor.name} se defendeu!');
    }
  }
  
  // Próximo turno
  _combatSystem!.nextValidTurn();
  
  // Checar se batalha acabou
  if (_combatSystem!.isBattleOver()) {
    endBattle();
  }
  
  notifyListeners();
}

*/
