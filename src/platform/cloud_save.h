#pragma once
#include <string>
#include <vector>
#include <atomic>
#include <mutex>
#include <cstdint>

#if defined(__has_include)
  #if __has_include(<SDL2/SDL.h>)
    #include <SDL2/SDL.h>
  #else
    #include <SDL.h>
  #endif
#else
  #include <SDL.h>
#endif

namespace hl {

class VM;

// Credenciais OAuth 2.0 (Google Cloud - TVs & Limited Input Devices)
std::string getGoogleClientId();
std::string getGoogleClientSecret();

enum class CloudSaveState {
  NOT_LOGGED_IN,
  REQUESTING_CODE,
  WAITING_USER_AUTH,
  LOGGED_IN,
  QUERYING_BACKUP,
  UPLOADING,
  DOWNLOADING,
  RESTORE_CONFIRM,
  SUCCESS_NOTIFICATION,
  ERROR_NOTIFICATION
};

struct DeviceCodeInfo {
  std::string userCode;         // Ex: "ABCD-EFGH"
  std::string deviceCode;       // Token interno para polling
  std::string verificationUrl;  // "https://www.google.com/device"
  int expiresIn = 1800;
  int interval = 5;
};

struct CloudBackupInfo {
  bool exists = false;
  std::string fileId;
  std::string modifiedTime;     // Data/hora da última sincronização
  std::string summary;          // Resumo dos heróis (ex: "Ronin Nv 24, Reah Nv 15")
  std::string platformName;     // Plataforma de origem (ex: "Windows", "Switch", "Android")
  size_t fileSize = 0;
};

class CloudSave {
public:
  static void init();
  static void shutdown();
  static void update(VM* vm = nullptr);

  // Estado e Informações
  static bool isLoggedIn();
  static CloudSaveState getState();
  static std::string getStatusMessage();
  static const DeviceCodeInfo& getDeviceCodeInfo();
  static const CloudBackupInfo& getCloudBackupInfo();

  // Fluxo de Autenticação OAuth 2.0 Device Flow
  static void startLogin();
  static void cancelLogin();
  static void logout();

  // Operações no Google Drive (appDataFolder)
  static void queryCloudBackupAsync();
  static void uploadBackupAsync();
  static void requestRestore();
  static void confirmRestore(VM* vm);
  static void cancelRestore();

  // Interface Modal e Controle de Navegação
  static bool isModalActive();
  static void openModal(VM* vm);
  static void closeModal();

  // Entrada de usuário no diálogo modal
  static bool handleInput(int key, VM* vm);
  static void handleClick(int x, int y, VM* vm);

  // Renderização da Janela Modal sobre o Renderer SDL
  static void drawModal(SDL_Renderer* renderer, int winW, int winH);
};

} // namespace hl
