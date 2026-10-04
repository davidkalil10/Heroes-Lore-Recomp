import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:hive_flutter/hive_flutter.dart';
import 'providers/game_state_provider.dart';
import 'services/save_service.dart';
import 'screens/main_menu_screen.dart';
import 'screens/game_screen.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  
  // Initialize Hive for local storage
  await Hive.initFlutter();
  
  // Initialize services
  final saveService = SaveService();
  await saveService.initialize();
  
  runApp(MyApp(saveService: saveService));
}

class MyApp extends StatelessWidget {
  final SaveService saveService;
  
  const MyApp({Key? key, required this.saveService}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider(
          create: (context) {
            final provider = GameStateProvider();
            provider.initialize(saveService);
            return provider;
          },
        ),
        Provider<SaveService>(create: (_) => saveService),
      ],
      child: MaterialApp(
        title: 'Heroes Lore: Wind of Soltia',
        theme: ThemeData(
          useMaterial3: true,
          colorScheme: ColorScheme.fromSeed(
            seedColor: Colors.deepOrange,
            brightness: Brightness.dark,
          ),
          fontFamily: 'GameFont',
        ),
        home: const GameRouter(),
        debugShowCheckedModeBanner: false,
      ),
    );
  }
}

/// Routes between different game screens based on game state
class GameRouter extends StatelessWidget {
  const GameRouter({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Consumer<GameStateProvider>(
      builder: (context, gameState, _) {
        switch (gameState.currentState) {
          case GameState.initializing:
            return const Scaffold(
              body: Center(
                child: CircularProgressIndicator(),
              ),
            );
          case GameState.menu:
            return const MainMenuScreen();
          case GameState.playing:
          case GameState.paused:
          case GameState.inBattle:
          case GameState.talking:
            return const GameScreen();
          case GameState.gameOver:
            return const GameOverScreen();
          case GameState.loading:
            return const Scaffold(
              body: Center(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    CircularProgressIndicator(),
                    SizedBox(height: 16),
                    Text('Loading...'),
                  ],
                ),
              ),
            );
        }
      },
    );
  }
}

/// Game over screen
class GameOverScreen extends StatelessWidget {
  const GameOverScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Text(
              'GAME OVER',
              style: TextStyle(fontSize: 48, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 32),
            ElevatedButton(
              onPressed: () {
                context.read<GameStateProvider>().returnToMenu();
              },
              child: const Text('Return to Menu'),
            ),
          ],
        ),
      ),
    );
  }
}
