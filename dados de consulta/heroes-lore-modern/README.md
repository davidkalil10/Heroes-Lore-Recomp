# Heroes Lore: Wind of Soltia - Modern Edition

Uma recriação moderna do clássico jogo J2ME **Heroes Lore: Wind of Soltia** em Flutter, com suporte para **Android**, **iOS**, **Web** e **Desktop (Windows/Linux/macOS)**.

Este é um **fan project** que visa preservar e modernizar uma peça clássica da história dos jogos mobile, com controles adaptáveis (touch, gamepad, teclado/mouse) e interface responsiva.

## 🎮 Features

- ✅ **Multiplataforma**: Android, iOS, Web, Windows, macOS, Linux
- ✅ **Controles Responsivos**: Touch (D-Pad virtual), Gamepad Bluetooth/USB, Teclado (WASD), Mouse
- ✅ **UI Adaptável**: Responsiva de smartphones (320px) até 4K desktop
- ✅ **Sistema de Save/Load**: Até 10 slots de salvamento com persistência local
- ✅ **HUD Completo**: Stats do personagem, playtime, estado do jogo
- ✅ **State Management**: Provider para gerenciamento de estado global
- ✅ **Renderização Pixel-Perfect**: Sprites mantêm proporção 1:1 (32x32 tiles)

## 📁 Estrutura do Projeto

```
heroes-lore-modern/
├── lib/
│   ├── main.dart                    # Entry point & router
│   ├── core/
│   │   └── input_handler.dart       # Mapeamento de inputs (teclado, gamepad, touch)
│   ├── models/
│   │   └── game_models.dart         # Entidades, items, mapas, etc
│   ├── providers/
│   │   └── game_state_provider.dart # State management (Provider)
│   ├── services/
│   │   └── save_service.dart        # Save/Load com Hive
│   ├── screens/
│   │   ├── main_menu_screen.dart    # Menu principal
│   │   └── game_screen.dart         # Tela principal do jogo
│   ├── widgets/
│   │   ├── game_hud.dart            # HUD overlay
│   │   └── virtual_dpad.dart        # D-Pad virtual para mobile
│   └── utils/                       # Utilitários (constantes, extensions, etc)
├── assets/
│   ├── game_data/                   # Dados do jogo em JSON
│   ├── sprites/                     # Sprites (PNG)
│   ├── audio/                       # Música e SFX
│   └── fonts/                       # Fontes customizadas
├── pubspec.yaml                     # Dependências
└── README.md                        # Este arquivo
```

## 🚀 Setup & Execução

### Pré-requisitos
- **Flutter 3.2.0+**
- **Dart 3.2.0+**
- Android SDK (para Android) ou Xcode (para iOS/macOS)

### Instalação

```bash
# Clone ou descompacte o projeto
cd heroes-lore-modern

# Instale as dependências
flutter pub get

# Gere os arquivos de build (necessário para Hive)
flutter pub run build_runner build
```

### Rodar em diferentes plataformas

**Android (Device ou Emulator)**
```bash
flutter run -d android
```

**iOS (Simulator ou Device)**
```bash
flutter run -d ios
```

**Web (Chrome, Firefox, Safari)**
```bash
flutter run -d chrome
```

**Windows (Desktop)**
```bash
flutter run -d windows
```

**macOS (Desktop)**
```bash
flutter run -d macos
```

**Linux (Desktop)**
```bash
flutter run -d linux
```

## 🎮 Controles

### Teclado (Desktop/Web)
- **WASD** ou **Setas**: Movimento
- **Space**: Atacar
- **E**: Interagir
- **I**: Abrir menu/inventário
- **ESC**: Fechar menu
- **Enter**: Confirmar
- **Backspace**: Cancelar
- **P**: Pausar
- **F**: Fast-forward (durante cinemáticas)

### Gamepad (Bluetooth/USB)
- **Analog Stick Esquerdo**: Movimento
- **A (Cross)**: Atacar
- **B (Circle)**: Defender
- **X (Square)**: Usar item
- **Y (Triangle)**: Interagir
- **LB (L1)**: Abrir menu
- **RB (R1)**: Fechar menu
- **Back (Select)**: Pausar

### Touch (Mobile/Tablet)
- **D-Pad Virtual**: Movimento (canto inferior esquerdo)
- **Botão ⚡**: Atacar (canto inferior direito)
- **Botão 👆**: Interagir (canto inferior direito)
- **Botão ≡**: Menu/Pausa (canto inferior direito)

## 📊 Arquitetura

### Game State Management (Provider)
```dart
GameStateProvider
├── currentState: GameState (menu, playing, paused, inBattle, gameOver)
├── player: PlayerCharacter
├── currentMap: GameMap
├── currentBattle: Battle?
└── playTimeSeconds: int
```

### Input Handling
- **InputMapper**: Mapeia inputs físicos para GameAction
- **GameAction**: Enum com todas as ações possíveis
- **InputMethod**: Detecta qual método está sendo usado (keyboard, gamepad, touch)

### Save System
- **SaveService**: Interface com Hive para persistência
- Até **10 slots** de save
- Serializa/deserializa: Player, Inventory, Abilities, Game State
- Timestamp automático e playtime tracking

### Responsive UI
- **ResponsiveBuilder**: Adapta layout baseado no tamanho
- **DeviceScreenType**: Mobile, Tablet, Desktop
- Suporta portrait/landscape em mobile

## 🛠️ Como Expandir

### Adicionar um novo NPC
```dart
// lib/models/game_models.dart
final npc = NonPlayerCharacter(
  id: 'npc_001',
  name: 'Merchant',
  x: 20,
  y: 15,
  maxHp: 50,
  level: 5,
  // ... outros stats
  dialogue: 'Welcome, adventurer!',
  isHostile: false,
  lootTable: [],
);
```

### Implementar batalla
```dart
// lib/providers/game_state_provider.dart
void startBattle(List<Entity> enemies) {
  _currentBattle = Battle(
    id: 'battle_001',
    playerParty: [_player!],
    enemyParty: enemies,
    reward: 100,
  );
  _changeState(GameState.inBattle);
  // TODO: Implementar lógica de turno
}
```

### Carregar dados do JAR original
1. Extrair `.jar` (é um ZIP)
2. Converter formatos proprietários para JSON
3. Colocar em `assets/game_data/`
4. Criar loader em `services/asset_loader.dart`

### Adicionar animações
- Usar **Flame** (já em pubspec.yaml) para animar sprites
- Implementar frame-based animation para movimento de personagens

### Suporte a multiplayer local
- Estender **GameStateProvider** para múltiplos players
- Adaptar **GameScreen** para split-screen em tablets/desktop

## 📦 Dependências Principais

| Package | Uso |
|---------|-----|
| **flame** | Game engine & rendering |
| **provider** | State management |
| **hive_flutter** | Persistência local (saves) |
| **responsive_builder** | UI adaptável |
| **gamepads** | Input de gamepad |
| **keyboard_event** | Input de teclado |

## 🎯 Roadmap

- [ ] Extrair assets do JAR original (sprites, mapas, dados)
- [ ] Implementar combat system completo (turnos, abilities)
- [ ] Sistema de diálogo/NPC
- [ ] Inventário e equipamento
- [ ] Sistema de quests
- [ ] Efeitos visuais e animações
- [ ] Áudio (música e SFX)
- [ ] Suporte a multiplayer local (co-op)
- [ ] Cloud save (Firebase/iCloud)
- [ ] Achievements & Stats

## 📜 Licença & Créditos

- **Original Game**: Heroes Lore: Wind of Soltia - Desenvolvido por Hands-On Mobile (2007)
- **Modern Edition**: Fan project para preservação histórica
- **Engine**: Flutter + Flame
- **Code**: Open source para fins educacionais e preservação

---

Dúvidas? Quer contribuir? Abra uma issue ou pull request! 🚀
