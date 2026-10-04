# Heroes Lore: Wind of Soltia - Modern Edition
## Visão Geral do Projeto

Um fan project que recria a clássica aventura J2ME em Flutter, preservando a jogabilidade original enquanto moderniza controles, UI e suporte multiplataforma.

---

## 📦 O que foi criado

### Estrutura do Projeto
```
heroes-lore-modern/
├── 📄 pubspec.yaml              # Dependências e configuração Flutter
├── 📘 README.md                 # Guia principal do projeto
├── 📋 PROJECT_OVERVIEW.md       # Este arquivo
└── 📚 ASSET_EXTRACTION_GUIDE.md # Como extrair assets do JAR original

lib/
├── 🎮 main.dart
│   └── Entry point da app + router
│
├── 🎯 core/
│   ├── input_handler.dart      # Mapeamento universal de inputs
│   │   - GameAction enum
│   │   - InputMapper (teclado, gamepad, touch)
│   │   - TouchInput & GamepadState
│   │
│   └── combat_system.dart      # Sistema de combate por turnos
│       - CombatSystem (lógica de turno)
│       - AttackResult (resultado de ataques)
│       - EnemyAI (IA simples)
│
├── 📊 models/
│   └── game_models.dart        # Estruturas de dados
│       - Entity, PlayerCharacter, NonPlayerCharacter
│       - Item, Ability, ItemType, AbilityType
│       - Tile, GameMap, Battle, GameSave
│
├── 🔧 providers/
│   └── game_state_provider.dart # State management (Provider)
│       - GameStateProvider (gamestate único)
│       - GameState enum
│       - Integração com SaveService
│
├── 💾 services/
│   └── save_service.dart       # Persistência com Hive
│       - Serialização/desserialização completa
│       - 10 slots de save
│       - Suporte a Player, Inventory, Abilities
│
├── 🖼️ screens/
│   ├── main_menu_screen.dart   # Menu principal responsivo
│   │   - Criação de personagem
│   │   - LoadGameScreen (load de saves)
│   │
│   └── game_screen.dart        # Tela principal do jogo
│       - GameWorldView (renderização de mapa)
│       - MapPainter (tile-based rendering)
│       - PauseMenuOverlay
│       - Keyboard + Touch input handling
│
├── 🎨 widgets/
│   ├── game_hud.dart           # HUD overlay
│   │   - Stats do jogador (HP, EXP, Gold)
│   │   - Playtime tracker
│   │   - Game state indicator
│   │
│   └── virtual_dpad.dart       # D-Pad virtual para mobile
│       - 8-way directional control
│       - Gesture-based input
│
└── ⚙️ utils/
    └── constants.dart          # Constantes globais
        - GameConstants
        - ResponsiveConstants
        - ThemeColors
        - IDs de mapas, NPCs, inimigos, items, abilities
        - Enums (GameLocale, GameDifficulty, HealthStatus)
```

---

## 🎯 O que foi implementado

### ✅ Completo
- [x] Arquitetura de projeto escalável (multi-plataforma)
- [x] Sistema de entrada universal (teclado, gamepad, touch)
- [x] Modelos de dados fundamentais (personagem, item, mapa, battaglia)
- [x] Sistema de salvamento/carregamento com Hive
- [x] State management com Provider
- [x] Menu principal responsivo
- [x] Tela de jogo com renderização de mapa
- [x] HUD com stats do jogador
- [x] D-Pad virtual para mobile
- [x] Pause menu
- [x] Combat system framework (com IA básica)
- [x] Constantes e configurações globais

### ⚠️ Em Progresso
- [ ] Extração de assets do JAR original
- [ ] Conversão de formatos binários para JSON
- [ ] Asset loader para sprites, áudio, dados
- [ ] Renderização de sprites animados
- [ ] Sistema de diálogo/NPC
- [ ] Lógica completa de batalha (UI + animações)
- [ ] Sistema de inventário/equipamento
- [ ] Sistema de quests
- [ ] Suporte a multiplayer local (co-op)

### 📋 Não Iniciado
- [ ] Audio (música, efeitos sonoros)
- [ ] Mapa editor/visualizador
- [ ] Cloud save (Firebase/iCloud)
- [ ] Achievements & Leaderboards
- [ ] Bug fixes e otimizações

---

## 🚀 Como Começar

### 1. Setup Inicial
```bash
cd heroes-lore-modern
flutter pub get
flutter pub run build_runner build
```

### 2. Rodar em desenvolvimento
```bash
# Android
flutter run -d android

# iOS
flutter run -d ios

# Web
flutter run -d chrome

# Windows/Mac/Linux
flutter run -d windows
```

### 3. Próximo passo recomendado
**Extrair assets do JAR original** → Ver `ASSET_EXTRACTION_GUIDE.md`

---

## 📂 Estrutura de Dados (JSON)

Exemplo de como os assets serão organizados:

```
assets/
├── game_data/
│   ├── metadata.json           # Info geral do jogo
│   ├── maps/
│   │   ├── map_00.json         # Mapa com tiles e entidades
│   │   ├── map_01.json
│   │   └── ...
│   ├── npcs/
│   │   ├── npc_inn_001.json
│   │   └── ...
│   ├── enemies/
│   │   ├── goblin.json
│   │   └── ...
│   ├── items/
│   │   ├── iron_sword.json
│   │   └── ...
│   └── abilities/
│       ├── slash.json
│       └── ...
├── sprites/                    # PNG extraídas do JAR
│   ├── tiles/
│   ├── characters/
│   ├── npcs/
│   ├── enemies/
│   └── ui/
├── audio/
│   ├── music/
│   │   ├── 00.mid
│   │   └── ...
│   └── sfx/
│       ├── 00.wav
│       └── ...
└── fonts/
    └── game_font.ttf
```

---

## 🛠️ Tecnologias Usadas

| Tecnologia | Propósito |
|-----------|----------|
| **Flutter** | Framework multiplataforma |
| **Dart** | Linguagem de programação |
| **Flame** | Game engine & rendering |
| **Provider** | State management |
| **Hive** | Persistência local (save/load) |
| **Responsive Builder** | UI responsiva |
| **GamePads** | Input de gamepad |

---

## 💡 Conceitos Principais

### Input Handling
Sistema universal que mapeia inputs físicos (teclado, gamepad, touch) para `GameAction`s abstratas:
```
Teclado: W → GameAction.moveUp
Gamepad: DPad Up → GameAction.moveUp
Touch: Swipe Up → GameAction.moveUp
↓
GameStateProvider.movePlayer(0, -1)
```

### State Management
Tudo passa por `GameStateProvider` (Provider):
- Mudanças de estado notificam listeners
- UI reconstrói automaticamente
- SaveService integrado

### Combat System
Framework preparado para batalhas por turnos:
1. Cálculo de ordem de turno (baseado em Dexterity)
2. Processamento de ações (ataque, defesa, item)
3. Cálculo de dano (com variância, critério, defesa)
4. Checagem de vitória/derrota

---

## 🎮 Exemplos de Uso

### Mover personagem
```dart
context.read<GameStateProvider>().movePlayer(1, 0); // Move direita
```

### Iniciar batalha
```dart
final enemies = [goblin1, goblin2, boss];
context.read<GameStateProvider>().startBattle(enemies);
```

### Salvar jogo
```dart
await context.read<GameStateProvider>().saveGameToSlot(
  slot: 0,
  saveName: 'Main Quest Progress',
);
```

### Processar ataque em batalha
```dart
final result = combatSystem.executeAttack(
  attacker: player,
  target: enemy,
);
print(result.message); // "GOLPE CRÍTICO! 45 de damage!"
```

---

## 📚 Próximas Tarefas (Prioridade)

### Fase 1: Assets
1. **Extrair imagens** do JAR (PNG já estão em formato padrão)
2. **Extrair áudio** (MIDI e WAV)
3. **Descompilar** classes principais para entender formatos
4. **Parsear .map files** (mapa binário)
5. **Converter para JSON** e testar no jogo

### Fase 2: Gameplay
1. **Asset loader** - carregar sprites, áudio, dados em runtime
2. **Sprite rendering** - mostrar personagens/inimigos em vez de blocos
3. **Battle screen** - UI completa de combate
4. **Combat loops** - animações e feedback visual
5. **Inventory system** - gerenciar itens

### Fase 3: Polish
1. **Efeitos visuais** - partículas, transições
2. **Animações** - movimento, ataque, magia
3. **Áudio** - música, efeitos sonoros
4. **Localization** - suporte a múltiplos idiomas
5. **Otimizações** - performance, uso de memória

---

## 🔗 Recursos Úteis

- **Flutter Docs**: https://flutter.dev/docs
- **Flame Docs**: https://flame.blue/
- **Provider Docs**: https://pub.dev/packages/provider
- **Hive Docs**: https://docs.hivedb.dev/
- **Asset Extraction**: Ver `ASSET_EXTRACTION_GUIDE.md`

---

## 📝 Notas

- Código comentado e estruturado para fácil expansão
- TODO markers indicam onde implementar features futuras
- Sistema modular: cada componente é independente
- Testável: cada service/provider pode ser testado isoladamente
- Responsivo: funciona de 320px (mobile) até 4K (desktop)

---

## 🤝 Contribuindo

Se você quer expandir este projeto:

1. Crie um branch: `git checkout -b feature/meu-feature`
2. Implemente mantendo a arquitetura
3. Adicione comentários em código complexo
4. Teste em múltiplas plataformas
5. Abra um PR com descrição clara

---

**Última atualização**: Julho 2026
**Status**: Alpha - Arquitetura completa, assets pendentes
