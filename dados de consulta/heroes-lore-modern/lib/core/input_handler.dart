import 'package:flutter/services.dart';

/// Enum for all possible input actions in the game
enum GameAction {
  // Movement
  moveUp,
  moveDown,
  moveLeft,
  moveRight,
  
  // Combat & Interaction
  attack,
  defend,
  useItem,
  interact,
  
  // UI Navigation
  menuOpen,
  menuClose,
  confirm,
  cancel,
  
  // Game Control
  pause,
  fastForward,
  
  // Special
  none,
}

/// Maps physical inputs to game actions
class InputMapper {
  // Keyboard mappings (WASD + Arrows)
  static final Map<LogicalKeyboardKey, GameAction> keyboardMap = {
    // Movement
    LogicalKeyboardKey.keyW: GameAction.moveUp,
    LogicalKeyboardKey.arrowUp: GameAction.moveUp,
    LogicalKeyboardKey.keyS: GameAction.moveDown,
    LogicalKeyboardKey.arrowDown: GameAction.moveDown,
    LogicalKeyboardKey.keyA: GameAction.moveLeft,
    LogicalKeyboardKey.arrowLeft: GameAction.moveLeft,
    LogicalKeyboardKey.keyD: GameAction.moveRight,
    LogicalKeyboardKey.arrowRight: GameAction.moveRight,
    
    // Actions
    LogicalKeyboardKey.space: GameAction.attack,
    LogicalKeyboardKey.keyE: GameAction.interact,
    LogicalKeyboardKey.keyI: GameAction.menuOpen,
    LogicalKeyboardKey.escape: GameAction.menuClose,
    LogicalKeyboardKey.enter: GameAction.confirm,
    LogicalKeyboardKey.backspace: GameAction.cancel,
    LogicalKeyboardKey.keyP: GameAction.pause,
    LogicalKeyboardKey.keyF: GameAction.fastForward,
  };
  
  // Gamepad button mappings
  static GameAction mapGamepadButton(int button) {
    switch (button) {
      case 0: return GameAction.attack;      // A / Cross
      case 1: return GameAction.defend;      // B / Circle
      case 2: return GameAction.useItem;     // X / Square
      case 3: return GameAction.interact;    // Y / Triangle
      case 4: return GameAction.menuOpen;    // LB / L1
      case 5: return GameAction.menuClose;   // RB / R1
      case 6: return GameAction.pause;       // Back / Select
      default: return GameAction.none;
    }
  }
  
  // Gamepad stick dead zone (0-1)
  static const double stickDeadZone = 0.2;
}

/// Represents a touch input with position and state
class TouchInput {
  final double x;
  final double y;
  final TouchPhase phase;
  
  TouchInput({
    required this.x,
    required this.y,
    required this.phase,
  });
}

enum TouchPhase { down, move, up }

/// Represents a gamepad input state
class GamepadState {
  final double leftStickX;
  final double leftStickY;
  final double rightStickX;
  final double rightStickY;
  final bool buttonA;
  final bool buttonB;
  final bool buttonX;
  final bool buttonY;
  final bool buttonLB;
  final bool buttonRB;
  final bool buttonStart;
  final bool buttonBack;
  
  GamepadState({
    this.leftStickX = 0.0,
    this.leftStickY = 0.0,
    this.rightStickX = 0.0,
    this.rightStickY = 0.0,
    this.buttonA = false,
    this.buttonB = false,
    this.buttonX = false,
    this.buttonY = false,
    this.buttonLB = false,
    this.buttonRB = false,
    this.buttonStart = false,
    this.buttonBack = false,
  });
  
  GamepadState copyWith({
    double? leftStickX,
    double? leftStickY,
    double? rightStickX,
    double? rightStickY,
    bool? buttonA,
    bool? buttonB,
    bool? buttonX,
    bool? buttonY,
    bool? buttonLB,
    bool? buttonRB,
    bool? buttonStart,
    bool? buttonBack,
  }) {
    return GamepadState(
      leftStickX: leftStickX ?? this.leftStickX,
      leftStickY: leftStickY ?? this.leftStickY,
      rightStickX: rightStickX ?? this.rightStickX,
      rightStickY: rightStickY ?? this.rightStickY,
      buttonA: buttonA ?? this.buttonA,
      buttonB: buttonB ?? this.buttonB,
      buttonX: buttonX ?? this.buttonX,
      buttonY: buttonY ?? this.buttonY,
      buttonLB: buttonLB ?? this.buttonLB,
      buttonRB: buttonRB ?? this.buttonRB,
      buttonStart: buttonStart ?? this.buttonStart,
      buttonBack: buttonBack ?? this.buttonBack,
    );
  }
}

/// Determines which input method is currently active
enum InputMethod { keyboard, gamepad, touch, mouse }
