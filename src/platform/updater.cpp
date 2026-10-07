// updater.cpp — Sistema de Verificação e Atualização OTA via GitHub Releases
#include "updater.h"
#include "platform.h"
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <cmath>
#include <algorithm>
#include <thread>
#include <mutex>
#include <atomic>
#include <sstream>

#if defined(_WIN32)
  #ifndef WIN32_LEAN_AND_MEAN
    #define WIN32_LEAN_AND_MEAN
  #endif
  #include <windows.h>
  #include <winhttp.h>
  #include <shellapi.h>
#elif defined(__SWITCH__)
  #include <switch.h>
  #include <curl/curl.h>
  #include <unistd.h>
#elif defined(__ANDROID__)
  #include <jni.h>
  #include <unistd.h>
  #if defined(__has_include)
    #if __has_include(<SDL2/SDL_system.h>)
      #include <SDL2/SDL_system.h>
    #else
      #include <SDL_system.h>
    #endif
  #else
    #include <SDL_system.h>
  #endif
#elif defined(__linux__)
  #include <curl/curl.h>
  #include <unistd.h>
  #include <sys/stat.h>
#endif

namespace hl {

static UpdateState s_state = UpdateState::IDLE;
static UpdateReleaseInfo s_releaseInfo;
static std::atomic<float> s_downloadProgress{0.0f};
static std::atomic<size_t> s_downloadedBytes{0};
static std::atomic<size_t> s_totalBytes{0};
static std::string s_statusMessage = "";
static std::mutex s_updaterMutex;
static bool s_promptActive = false;

extern SDL_Renderer* s_renderer;

// Helper para comparar tags semânticas (v1.0.3 vs v1.0.2)
static bool isNewerVersion(const std::string& remoteTag, const std::string& localTag) {
  int rMaj = 0, rMin = 0, rPatch = 0;
  int lMaj = 0, lMin = 0, lPatch = 0;
  const char* rStr = remoteTag.c_str();
  if (rStr[0] == 'v' || rStr[0] == 'V') rStr++;
  const char* lStr = localTag.c_str();
  if (lStr[0] == 'v' || lStr[0] == 'V') lStr++;
  if (sscanf(rStr, "%d.%d.%d", &rMaj, &rMin, &rPatch) < 1) return false;
  if (sscanf(lStr, "%d.%d.%d", &lMaj, &lMin, &lPatch) < 1) return false;
  if (rMaj != lMaj) return rMaj > lMaj;
  if (rMin != lMin) return rMin > lMin;
  return rPatch > lPatch;
}

// Extrator simples de valores em JSON sem dependências externas
static std::string extractJsonString(const std::string& json, const std::string& key) {
  size_t k = json.find("\"" + key + "\"");
  if (k == std::string::npos) return "";
  size_t colon = json.find(':', k);
  if (colon == std::string::npos) return "";
  size_t q1 = json.find('"', colon);
  if (q1 == std::string::npos) return "";
  size_t q2 = json.find('"', q1 + 1);
  if (q2 == std::string::npos) return "";
  return json.substr(q1 + 1, q2 - q1 - 1);
}

// Retorna o nome de arquivo esperado no release do GitHub para a plataforma atual
static std::string getExpectedAssetName() {
#if defined(__SWITCH__)
  return "heroes_lore.nro";
#elif defined(__ANDROID__)
  return "heroes_lore_android_universal.apk";
#elif defined(_WIN32)
  return "heroes_lore_windows_x64.zip";
#elif defined(__linux__)
  return "heroes_lore_linux_x86_64.AppImage";
#else
  return "heroes_lore";
#endif
}

// Parser da resposta do GitHub para extrair informações do release e do asset da plataforma
static bool parseReleaseJson(const std::string& json, UpdateReleaseInfo& out) {
  out.tagName = extractJsonString(json, "tag_name");
  out.releaseName = extractJsonString(json, "name");
  out.releaseNotes = extractJsonString(json, "body");
  if (out.tagName.empty()) return false;

  std::string targetAsset = getExpectedAssetName();

  // Varre a lista de assets procurando o correspondente
  size_t pos = 0;
  while ((pos = json.find("\"name\"", pos)) != std::string::npos) {
    size_t colon = json.find(':', pos);
    if (colon == std::string::npos) break;
    size_t q1 = json.find('"', colon);
    if (q1 == std::string::npos) break;
    size_t q2 = json.find('"', q1 + 1);
    if (q2 == std::string::npos) break;

    std::string aname = json.substr(q1 + 1, q2 - q1 - 1);
    if (aname == targetAsset || aname.find(targetAsset) != std::string::npos) {
      out.assetName = aname;
      // Procura URL de download no mesmo bloco
      size_t urlKey = json.find("\"browser_download_url\"", q2);
      if (urlKey != std::string::npos) {
        size_t uColon = json.find(':', urlKey);
        size_t uQ1 = json.find('"', uColon);
        size_t uQ2 = json.find('"', uQ1 + 1);
        if (uQ1 != std::string::npos && uQ2 != std::string::npos) {
          out.assetUrl = json.substr(uQ1 + 1, uQ2 - uQ1 - 1);
        }
      }
      // Procura tamanho do asset
      size_t sizeKey = json.find("\"size\"", q2);
      if (sizeKey != std::string::npos && sizeKey < urlKey) {
        size_t sColon = json.find(':', sizeKey);
        if (sColon != std::string::npos) {
          out.assetSize = std::strtoul(json.c_str() + sColon + 1, nullptr, 10);
        }
      }
      break;
    }
    pos = q2 + 1;
  }

  return !out.assetUrl.empty();
}

#if defined(_WIN32)
// Implementação nativa de requisição HTTP(S) no Windows via WinHTTP
static std::string winHttpGet(const std::wstring& host, const std::wstring& path, bool followRedirects = true) {
  std::string response;
  HINTERNET hSession = WinHttpOpen(L"Heroes-Lore-Updater/1.0",
                                   WINHTTP_ACCESS_TYPE_DEFAULT_PROXY,
                                   WINHTTP_NO_PROXY_NAME,
                                   WINHTTP_NO_PROXY_BYPASS, 0);
  if (!hSession) return "";

  HINTERNET hConnect = WinHttpConnect(hSession, host.c_str(), INTERNET_DEFAULT_HTTPS_PORT, 0);
  if (!hConnect) { WinHttpCloseHandle(hSession); return ""; }

  HINTERNET hRequest = WinHttpOpenRequest(hConnect, L"GET", path.c_str(),
                                         NULL, WINHTTP_NO_REFERER,
                                         WINHTTP_DEFAULT_ACCEPT_TYPES,
                                         WINHTTP_FLAG_SECURE);
  if (!hRequest) {
    WinHttpCloseHandle(hConnect);
    WinHttpCloseHandle(hSession);
    return "";
  }

  if (followRedirects) {
    DWORD redirectPolicy = WINHTTP_OPTION_REDIRECT_POLICY_ALWAYS;
    WinHttpSetOption(hRequest, WINHTTP_OPTION_REDIRECT_POLICY, &redirectPolicy, sizeof(redirectPolicy));
  }

  // Header do GitHub
  LPCWSTR headers = L"User-Agent: Heroes-Lore-Updater/1.0\r\nAccept: application/vnd.github.v3+json\r\n";
  WinHttpAddRequestHeaders(hRequest, headers, (ULONG)-1L, WINHTTP_ADDREQ_FLAG_ADD);

  // Timeouts curtos (3s conexão, 5s envio/recepção)
  WinHttpSetTimeouts(hRequest, 3000, 3000, 5000, 5000);

  if (WinHttpSendRequest(hRequest, WINHTTP_NO_ADDITIONAL_HEADERS, 0, WINHTTP_NO_REQUEST_DATA, 0, 0, 0) &&
      WinHttpReceiveResponse(hRequest, NULL)) {
    DWORD dwSize = 0;
    DWORD dwDownloaded = 0;
    do {
      dwSize = 0;
      if (!WinHttpQueryDataAvailable(hRequest, &dwSize)) break;
      if (dwSize == 0) break;
      std::vector<char> buffer(dwSize + 1);
      if (WinHttpReadData(hRequest, buffer.data(), dwSize, &dwDownloaded)) {
        response.append(buffer.data(), dwDownloaded);
      }
    } while (dwSize > 0);
  }

  WinHttpCloseHandle(hRequest);
  WinHttpCloseHandle(hConnect);
  WinHttpCloseHandle(hSession);
  return response;
}

// Download de arquivo no Windows com progresso
static bool winHttpDownloadFile(const std::string& url, const std::string& outPath) {
  // Converte URL completa em host e path
  std::wstring wurl(url.begin(), url.end());
  URL_COMPONENTS urlComp;
  memset(&urlComp, 0, sizeof(urlComp));
  urlComp.dwStructSize = sizeof(urlComp);
  wchar_t hostName[256] = {0};
  wchar_t urlPath[2048] = {0};
  urlComp.lpszHostName = hostName;
  urlComp.dwHostNameLength = 256;
  urlComp.lpszUrlPath = urlPath;
  urlComp.dwUrlPathLength = 2048;

  if (!WinHttpCrackUrl(wurl.c_str(), (DWORD)wurl.length(), 0, &urlComp)) return false;

  HINTERNET hSession = WinHttpOpen(L"Heroes-Lore-Updater/1.0",
                                   WINHTTP_ACCESS_TYPE_DEFAULT_PROXY,
                                   WINHTTP_NO_PROXY_NAME,
                                   WINHTTP_NO_PROXY_BYPASS, 0);
  if (!hSession) return false;

  HINTERNET hConnect = WinHttpConnect(hSession, hostName, urlComp.nPort, 0);
  if (!hConnect) { WinHttpCloseHandle(hSession); return false; }

  DWORD flags = (urlComp.nScheme == INTERNET_SCHEME_HTTPS) ? WINHTTP_FLAG_SECURE : 0;
  HINTERNET hRequest = WinHttpOpenRequest(hConnect, L"GET", urlPath,
                                         NULL, WINHTTP_NO_REFERER,
                                         WINHTTP_DEFAULT_ACCEPT_TYPES, flags);
  if (!hRequest) {
    WinHttpCloseHandle(hConnect);
    WinHttpCloseHandle(hSession);
    return false;
  }

  DWORD redirectPolicy = WINHTTP_OPTION_REDIRECT_POLICY_ALWAYS;
  WinHttpSetOption(hRequest, WINHTTP_OPTION_REDIRECT_POLICY, &redirectPolicy, sizeof(redirectPolicy));

  LPCWSTR headers = L"User-Agent: Heroes-Lore-Updater/1.0\r\n";
  WinHttpAddRequestHeaders(hRequest, headers, (ULONG)-1L, WINHTTP_ADDREQ_FLAG_ADD);

  bool success = false;
  if (WinHttpSendRequest(hRequest, WINHTTP_NO_ADDITIONAL_HEADERS, 0, WINHTTP_NO_REQUEST_DATA, 0, 0, 0) &&
      WinHttpReceiveResponse(hRequest, NULL)) {
    
    // Obtém Content-Length se disponível
    wchar_t szContentLength[32] = {0};
    DWORD cchContentLength = sizeof(szContentLength);
    if (WinHttpQueryHeaders(hRequest, WINHTTP_QUERY_CONTENT_LENGTH, NULL, szContentLength, &cchContentLength, NULL)) {
      size_t total = (size_t)_wtoi64(szContentLength);
      if (total > 0) s_totalBytes = total;
    }

    FILE* fp = fopen(outPath.c_str(), "wb");
    if (fp) {
      DWORD dwSize = 0;
      DWORD dwDownloaded = 0;
      size_t current = 0;
      do {
        dwSize = 0;
        if (!WinHttpQueryDataAvailable(hRequest, &dwSize)) break;
        if (dwSize == 0) break;
        std::vector<char> buffer(dwSize);
        if (WinHttpReadData(hRequest, buffer.data(), dwSize, &dwDownloaded)) {
          fwrite(buffer.data(), 1, dwDownloaded, fp);
          current += dwDownloaded;
          s_downloadedBytes = current;
          if (s_totalBytes > 0) {
            s_downloadProgress = (float)current / (float)s_totalBytes.load();
          }
        }
      } while (dwSize > 0);
      fclose(fp);
      success = (current > 0);
    }
  }

  WinHttpCloseHandle(hRequest);
  WinHttpCloseHandle(hConnect);
  WinHttpCloseHandle(hSession);
  return success;
}
#endif

#if defined(__SWITCH__) || (defined(__linux__) && !defined(__ANDROID__))
// Callback de escrita de dados com libcurl
static size_t curlWriteCallback(void* contents, size_t size, size_t nmemb, void* userp) {
  size_t total = size * nmemb;
  std::string* str = static_cast<std::string*>(userp);
  str->append(static_cast<char*>(contents), total);
  return total;
}

static size_t curlWriteFileCallback(void* contents, size_t size, size_t nmemb, void* userp) {
  FILE* fp = static_cast<FILE*>(userp);
  return fwrite(contents, size, nmemb, fp);
}

static int curlProgressCallback(void*, curl_off_t dltotal, curl_off_t dlnow, curl_off_t, curl_off_t) {
  if (dltotal > 0) {
    s_totalBytes = (size_t)dltotal;
    s_downloadedBytes = (size_t)dlnow;
    s_downloadProgress = (float)dlnow / (float)dltotal;
  }
  return 0;
}

static std::string curlHttpGet(const std::string& url) {
  CURL* curl = curl_easy_init();
  if (!curl) return "";
  std::string response;
  curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
  curl_easy_setopt(curl, CURLOPT_USERAGENT, "Heroes-Lore-Updater/1.0");
  curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1L);
  curl_easy_setopt(curl, CURLOPT_SSL_VERIFYPEER, 0L);
  curl_easy_setopt(curl, CURLOPT_SSL_VERIFYHOST, 0L);
  curl_easy_setopt(curl, CURLOPT_TIMEOUT, 6L);
  curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, curlWriteCallback);
  curl_easy_setopt(curl, CURLOPT_WRITEDATA, &response);

  CURLcode res = curl_easy_perform(curl);
  curl_easy_cleanup(curl);
  return (res == CURLE_OK) ? response : "";
}

static bool curlDownloadFile(const std::string& url, const std::string& outPath) {
  FILE* fp = fopen(outPath.c_str(), "wb");
  if (!fp) return false;

  CURL* curl = curl_easy_init();
  if (!curl) { fclose(fp); return false; }

  curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
  curl_easy_setopt(curl, CURLOPT_USERAGENT, "Heroes-Lore-Updater/1.0");
  curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1L);
  curl_easy_setopt(curl, CURLOPT_SSL_VERIFYPEER, 0L);
  curl_easy_setopt(curl, CURLOPT_SSL_VERIFYHOST, 0L);
  curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, curlWriteFileCallback);
  curl_easy_setopt(curl, CURLOPT_WRITEDATA, fp);
  curl_easy_setopt(curl, CURLOPT_XFERINFOFUNCTION, curlProgressCallback);
  curl_easy_setopt(curl, CURLOPT_NOPROGRESS, 0L);

  CURLcode res = curl_easy_perform(curl);
  fclose(fp);
  curl_easy_cleanup(curl);
  return (res == CURLE_OK);
}
#endif

#if defined(__ANDROID__)
static std::string androidHttpGet(const std::string& url) {
  JNIEnv* env = (JNIEnv*)SDL_AndroidGetJNIEnv();
  if (!env) return "";

  jclass activityClass = env->FindClass("org/libsdl/app/SDLActivity");
  if (!activityClass) return "";

  jmethodID mid = env->GetStaticMethodID(activityClass, "httpGet", "(Ljava/lang/String;)Ljava/lang/String;");
  if (!mid) {
    env->DeleteLocalRef(activityClass);
    return "";
  }

  jstring jUrl = env->NewStringUTF(url.c_str());
  jstring jRes = (jstring)env->CallStaticObjectMethod(activityClass, mid, jUrl);
  env->DeleteLocalRef(jUrl);
  env->DeleteLocalRef(activityClass);

  if (!jRes) return "";

  const char* utf = env->GetStringUTFChars(jRes, nullptr);
  std::string result = utf ? utf : "";
  if (utf) env->ReleaseStringUTFChars(jRes, utf);
  env->DeleteLocalRef(jRes);

  return result;
}

static bool androidDownloadFile(const std::string& url, const std::string& outPath) {
  JNIEnv* env = (JNIEnv*)SDL_AndroidGetJNIEnv();
  if (!env) return false;

  jclass activityClass = env->FindClass("org/libsdl/app/SDLActivity");
  if (!activityClass) return false;

  jmethodID mid = env->GetStaticMethodID(activityClass, "downloadFile", "(Ljava/lang/String;Ljava/lang/String;)Z");
  if (!mid) {
    env->DeleteLocalRef(activityClass);
    return false;
  }

  jstring jUrl = env->NewStringUTF(url.c_str());
  jstring jPath = env->NewStringUTF(outPath.c_str());
  jboolean res = env->CallStaticBooleanMethod(activityClass, mid, jUrl, jPath);
  env->DeleteLocalRef(jUrl);
  env->DeleteLocalRef(jPath);
  env->DeleteLocalRef(activityClass);

  return res == JNI_TRUE;
}

static void androidInstallApk(const std::string& apkPath) {
  JNIEnv* env = (JNIEnv*)SDL_AndroidGetJNIEnv();
  if (!env) return;

  jclass activityClass = env->FindClass("org/libsdl/app/SDLActivity");
  if (!activityClass) return;

  jmethodID mid = env->GetStaticMethodID(activityClass, "installApk", "(Ljava/lang/String;)V");
  if (mid) {
    jstring jPath = env->NewStringUTF(apkPath.c_str());
    env->CallStaticVoidMethod(activityClass, mid, jPath);
    env->DeleteLocalRef(jPath);
  }
  env->DeleteLocalRef(activityClass);
}
#endif

void Updater::init() {
#if defined(__SWITCH__)
  socketInitializeDefault();
  curl_global_init(CURL_GLOBAL_ALL);
#elif defined(__linux__) && !defined(__ANDROID__)
  curl_global_init(CURL_GLOBAL_ALL);
#endif
  s_state = UpdateState::IDLE;
  s_promptActive = false;
}

void Updater::shutdown() {
#if defined(__SWITCH__)
  curl_global_cleanup();
  socketExit();
#elif defined(__linux__) && !defined(__ANDROID__)
  curl_global_cleanup();
#endif
}

std::string Updater::getLocalVersion() {
  return HL_VERSION_TAG;
}

void Updater::checkAsync(bool notifyIfNoUpdate) {
  if (s_state == UpdateState::CHECKING || s_state == UpdateState::DOWNLOADING) {
    if (notifyIfNoUpdate) {
      Platform::showOsdMessage("Verificacao de atualizacao ja em andamento...");
    }
    return;
  }
  s_state = UpdateState::CHECKING;
  s_statusMessage = "Verificando atualizacoes no GitHub...";
  if (notifyIfNoUpdate) {
    Platform::showOsdMessage("Verificando atualizacoes no GitHub...");
  }

  std::thread([notifyIfNoUpdate]() {
    std::string json;
#if defined(_WIN32)
    json = winHttpGet(L"api.github.com", L"/repos/davidkalil10/Heroes-Lore-Recomp/releases/latest");
#elif defined(__SWITCH__) || (defined(__linux__) && !defined(__ANDROID__))
    json = curlHttpGet("https://api.github.com/repos/davidkalil10/Heroes-Lore-Recomp/releases/latest");
#elif defined(__ANDROID__)
    json = androidHttpGet("https://api.github.com/repos/davidkalil10/Heroes-Lore-Recomp/releases/latest");
#endif

    std::lock_guard<std::mutex> lock(s_updaterMutex);
    if (json.empty()) {
      s_state = UpdateState::CHECK_FAILED;
      s_statusMessage = "Nao foi possivel conectar ao GitHub.";
      if (notifyIfNoUpdate) {
        Platform::showOsdMessage("Atualizacao: Sem conexao com a internet.");
      }
      return;
    }

    // Se o repositório for privado ou o endpoint não tiver release público (404 Not Found)
    if (json.find("\"Not Found\"") != std::string::npos || json.find("\"status\":\"404\"") != std::string::npos) {
      s_state = UpdateState::NO_UPDATE;
      s_statusMessage = "Jogo atualizado (" + std::string(HL_VERSION_TAG) + ").";
      if (notifyIfNoUpdate) {
        Platform::showOsdMessage("Jogo atualizado (" + std::string(HL_VERSION_TAG) + ").\nNenhuma versao nova encontrada.");
      }
      return;
    }

    UpdateReleaseInfo info;
    if (parseReleaseJson(json, info)) {
      s_releaseInfo = info;
      if (isNewerVersion(info.tagName, HL_VERSION_TAG)) {
        s_state = UpdateState::UPDATE_AVAILABLE;
        s_statusMessage = "Nova versao disponivel: " + info.tagName;
        s_promptActive = true; // Abre o diálogo nobre com o aviso crucial de salvar o jogo
        Platform::showOsdMessage("Nova versao " + info.tagName + " disponivel!");
      } else {
        s_state = UpdateState::NO_UPDATE;
        s_statusMessage = "Voce ja possui a versao mais recente (" + std::string(HL_VERSION_TAG) + ").";
        if (notifyIfNoUpdate) {
          Platform::showOsdMessage("Jogo atualizado (" + std::string(HL_VERSION_TAG) + ").\nNenhuma versao nova encontrada.");
        }
      }
    } else {
      s_state = UpdateState::CHECK_FAILED;
      s_statusMessage = "Falha ao processar dados de release.";
      if (notifyIfNoUpdate) {
        Platform::showOsdMessage("Jogo atualizado (" + std::string(HL_VERSION_TAG) + ").\nNenhuma versao nova encontrada.");
      }
    }
  }).detach();
}

void Updater::startDownload() {
  if (s_releaseInfo.assetUrl.empty()) return;
  s_state = UpdateState::DOWNLOADING;
  s_downloadProgress = 0.0f;
  s_downloadedBytes = 0;
  s_totalBytes = s_releaseInfo.assetSize;
  s_statusMessage = "Baixando atualizacao: " + s_releaseInfo.assetName;

  std::string downloadUrl = s_releaseInfo.assetUrl;
  std::string assetName = s_releaseInfo.assetName;

  std::thread([downloadUrl, assetName]() {
    std::string destPath = Platform::getStorageDir() + "/" + assetName + ".download";
    bool ok = false;
#if defined(_WIN32)
    ok = winHttpDownloadFile(downloadUrl, destPath);
#elif defined(__SWITCH__) || (defined(__linux__) && !defined(__ANDROID__))
    ok = curlDownloadFile(downloadUrl, destPath);
#elif defined(__ANDROID__)
    ok = androidDownloadFile(downloadUrl, destPath);
#endif

    std::lock_guard<std::mutex> lock(s_updaterMutex);
    if (ok) {
      s_state = UpdateState::DOWNLOAD_COMPLETE;
      s_downloadProgress = 1.0f;
      s_statusMessage = "Download concluido! Aplicando...";
      applyUpdate();
    } else {
      s_state = UpdateState::DOWNLOAD_FAILED;
      s_statusMessage = "Falha no download da atualizacao.";
      Platform::showOsdMessage("Falha no download da atualizacao.");
    }
  }).detach();
}

bool Updater::applyUpdate() {
  std::string storage = Platform::getStorageDir();
  std::string tmpFile = storage + "/" + s_releaseInfo.assetName + ".download";

#if defined(__SWITCH__)
  std::string targetNro = "sdmc:/switch/heroes_lore/heroes_lore.nro";
  remove(targetNro.c_str());
  if (rename(tmpFile.c_str(), targetNro.c_str()) == 0) {
    s_state = UpdateState::RESTART_READY;
    s_statusMessage = "Atualizacao concluida! Reiniciando...";
    envSetNextLoad(targetNro.c_str(), targetNro.c_str());
    Platform::showOsdMessage("Atualizacao concluida! Reiniciando...");
    return true;
  }
#elif defined(_WIN32)
  std::string targetExe = "heroes_lore.exe";
  std::string oldExe = "heroes_lore.exe.old";
  remove(oldExe.c_str());
  MoveFileA(targetExe.c_str(), oldExe.c_str());
  if (MoveFileA(tmpFile.c_str(), targetExe.c_str())) {
    s_state = UpdateState::RESTART_READY;
    s_statusMessage = "Atualizacao concluida! Reiniciando...";
    Platform::showOsdMessage("Atualizado! Reinicie o executavel.");
    return true;
  }
#elif defined(__linux__) && !defined(__ANDROID__)
  chmod(tmpFile.c_str(), 0755);
  std::string targetAppImage = "heroes_lore.AppImage";
  remove(targetAppImage.c_str());
  if (rename(tmpFile.c_str(), targetAppImage.c_str()) == 0) {
    s_state = UpdateState::RESTART_READY;
    s_statusMessage = "Atualizacao concluida! Reiniciando...";
    Platform::showOsdMessage("Atualizado! Reinicie o aplicativo.");
    return true;
  }
#elif defined(__ANDROID__)
  std::string targetApk = storage + "/" + s_releaseInfo.assetName;
  remove(targetApk.c_str());
  rename(tmpFile.c_str(), targetApk.c_str());
  androidInstallApk(targetApk);
  s_state = UpdateState::RESTART_READY;
  s_statusMessage = "Instalando atualizacao...";
  Platform::showOsdMessage("Instalador aberto! Conclua a atualizacao.");
  return true;
#endif
  return false;
}

UpdateState Updater::getState() { return s_state; }
const UpdateReleaseInfo& Updater::getReleaseInfo() { return s_releaseInfo; }
float Updater::getDownloadProgress() { return s_downloadProgress.load(); }
std::string Updater::getStatusMessage() { return s_statusMessage; }

bool Updater::isPromptActive() { return s_promptActive; }
void Updater::showPrompt() { s_promptActive = true; }
void Updater::dismissPrompt() {
  s_promptActive = false;
  if (s_state == UpdateState::UPDATE_AVAILABLE || s_state == UpdateState::CHECK_FAILED) {
    s_state = UpdateState::IDLE;
  }
}

void Updater::confirmUpdate() {
  if (s_state == UpdateState::UPDATE_AVAILABLE) {
    startDownload();
  } else if (s_state == UpdateState::RESTART_READY) {
    exit(0);
  }
}

bool Updater::handleInput(int key) {
  if (!s_promptActive) return false;

  // Tecla '5' / Enter / A -> Confirmação / Download / Reinício
  if (key == 53 || key == 13 || key == 5) {
    confirmUpdate();
    return true;
  }
  // Tecla '7' / ESC / B -> Cancelar / Salvar Primeiro / Fechar
  if (key == 55 || key == 27 || key == 7) {
    dismissPrompt();
    return true;
  }
  return true; // Bloqueia outros inputs de passarem para o jogo enquanto o modal estiver aberto
}

// Renderização gráfica da caixa modal de atualização com estilo nobre de Soltia
void Updater::drawModal(SDL_Renderer* renderer, int winW, int winH) {
  if (!s_promptActive || !renderer) return;

  SDL_SetRenderDrawBlendMode(renderer, SDL_BLENDMODE_BLEND);

  // 1. Escurecimento translúcido do fundo (Backdrop Blur)
  SDL_Rect fullScreen = { 0, 0, winW, winH };
  SDL_SetRenderDrawColor(renderer, 0, 0, 0, 205);
  SDL_RenderFillRect(renderer, &fullScreen);

  // 2. Caixa Modal Centralizada
  int modalW = std::min(winW - 32, std::max(480, (int)(winW * 0.65f)));
  int modalH = std::min(winH - 32, (int)(modalW * 0.58f));
  int modalX = (winW - modalW) / 2;
  int modalY = (winH - modalH) / 2;

  // Sombra suave da caixa
  SDL_Rect shadow = { modalX + 6, modalY + 6, modalW, modalH };
  SDL_SetRenderDrawColor(renderer, 0, 0, 0, 180);
  SDL_RenderFillRect(renderer, &shadow);

  // Fundo ardósia nobre de Soltia
  SDL_Rect box = { modalX, modalY, modalW, modalH };
  SDL_SetRenderDrawColor(renderer, 15, 19, 27, 250);
  SDL_RenderFillRect(renderer, &box);

  // Borda chanfrada externa em aço polido
  SDL_SetRenderDrawColor(renderer, 75, 95, 125, 255);
  SDL_RenderDrawRect(renderer, &box);

  // Filete interno em ouro nobre
  SDL_Rect goldBorder = { modalX + 4, modalY + 4, modalW - 8, modalH - 8 };
  SDL_SetRenderDrawColor(renderer, 190, 150, 60, 255);
  SDL_RenderDrawRect(renderer, &goldBorder);

  // Rebites de bronze nos cantos
  SDL_SetRenderDrawColor(renderer, 225, 185, 80, 255);
  SDL_Rect r1 = { modalX + 8, modalY + 8, 4, 4 };
  SDL_Rect r2 = { modalX + modalW - 12, modalY + 8, 4, 4 };
  SDL_Rect r3 = { modalX + 8, modalY + modalH - 12, 4, 4 };
  SDL_Rect r4 = { modalX + modalW - 12, modalY + modalH - 12, 4, 4 };
  SDL_RenderFillRect(renderer, &r1);
  SDL_RenderFillRect(renderer, &r2);
  SDL_RenderFillRect(renderer, &r3);
  SDL_RenderFillRect(renderer, &r4);

  // Calcula tamanho dos caracteres baseado na resolução do modal
  int charH = std::max(18, (int)(modalH * 0.08f));
  int charW = (int)(charH * 0.61f);

  if (s_state == UpdateState::UPDATE_AVAILABLE || s_state == UpdateState::CONFIRM_PROMPT) {
    std::string title = "— ATUALIZACAO DISPONIVEL —";
    int tx = modalX + (modalW - (int)title.length() * charW) / 2;
    Platform::drawText(renderer, title, tx, modalY + 16, charW, charH, 255);

    // Divisor dourado
    SDL_SetRenderDrawColor(renderer, 190, 150, 60, 200);
    SDL_RenderDrawLine(renderer, modalX + 24, modalY + 16 + charH + 8, modalX + modalW - 24, modalY + 16 + charH + 8);

    std::string vLine = "Versao: " + std::string(HL_VERSION_TAG) + " -> " + s_releaseInfo.tagName;
    int vx = modalX + (modalW - (int)vLine.length() * charW) / 2;
    Platform::drawText(renderer, vLine, vx, modalY + 16 + charH + 16, charW, charH, 255);

    std::string a1 = "ATENCAO: E altamente recomendado";
    std::string a2 = "SALVAR O JOGO antes de prosseguir!";
    std::string a3 = "O aplicativo sera reiniciado.";
    int ax1 = modalX + (modalW - (int)a1.length() * charW) / 2;
    int ax2 = modalX + (modalW - (int)a2.length() * charW) / 2;
    int ax3 = modalX + (modalW - (int)a3.length() * charW) / 2;
    int lineY = modalY + 16 + charH * 3;
    Platform::drawText(renderer, a1, ax1, lineY, charW, charH, 255);
    Platform::drawText(renderer, a2, ax2, lineY + charH + 4, charW, charH, 255);
    Platform::drawText(renderer, a3, ax3, lineY + (charH + 4) * 2, charW, charH, 255);

    // Botões de confirmação
    std::string b1 = "[ 5 / A : ATUALIZAR AGORA ]";
    std::string b2 = "[ 7 / B : SALVAR PRIMEIRO ]";
    int bx1 = modalX + (modalW - (int)b1.length() * charW) / 2;
    int bx2 = modalX + (modalW - (int)b2.length() * charW) / 2;
    int by1 = modalY + modalH - (charH + 8) * 2 - 12;
    int by2 = modalY + modalH - (charH + 8) - 12;
    Platform::drawText(renderer, b1, bx1, by1, charW, charH, 255);
    Platform::drawText(renderer, b2, bx2, by2, charW, charH, 255);
  } else if (s_state == UpdateState::DOWNLOADING) {
    std::string title = "— BAIXANDO ATUALIZACAO —";
    int tx = modalX + (modalW - (int)title.length() * charW) / 2;
    Platform::drawText(renderer, title, tx, modalY + 16, charW, charH, 255);

    std::string fLine = "Arquivo: " + s_releaseInfo.assetName;
    int fx = modalX + (modalW - (int)fLine.length() * charW) / 2;
    Platform::drawText(renderer, fLine, fx, modalY + 16 + charH + 16, charW, charH, 255);

    // Barra de progresso gráfica
    int barW = modalW - 64;
    int barH = std::max(18, (int)(modalH * 0.12f));
    int barX = modalX + 32;
    int barY = modalY + modalH / 2 - barH / 2;

    SDL_Rect barBg = { barX, barY, barW, barH };
    SDL_SetRenderDrawColor(renderer, 10, 12, 16, 255);
    SDL_RenderFillRect(renderer, &barBg);
    SDL_SetRenderDrawColor(renderer, 90, 110, 140, 255);
    SDL_RenderDrawRect(renderer, &barBg);

    float prog = std::min(1.0f, std::max(0.0f, s_downloadProgress.load()));
    int fillW = (int)((float)(barW - 4) * prog);
    if (fillW > 0) {
      SDL_Rect barFill = { barX + 2, barY + 2, fillW, barH - 4 };
      SDL_SetRenderDrawColor(renderer, 0, 215, 255, 255);
      SDL_RenderFillRect(renderer, &barFill);
    }

    char pBuf[64];
    float mbDown = (float)s_downloadedBytes.load() / (1024.0f * 1024.0f);
    float mbTotal = (float)s_totalBytes.load() / (1024.0f * 1024.0f);
    snprintf(pBuf, sizeof(pBuf), "%.1f MB / %.1f MB (%d%%)", mbDown, mbTotal, (int)(prog * 100.0f));
    std::string pStr = pBuf;
    int px = modalX + (modalW - (int)pStr.length() * charW) / 2;
    Platform::drawText(renderer, pStr, px, barY + barH + 12, charW, charH, 255);

    std::string wLine = "Aguarde... Nao feche o jogo.";
    int wx = modalX + (modalW - (int)wLine.length() * charW) / 2;
    Platform::drawText(renderer, wLine, wx, modalY + modalH - charH - 16, charW, charH, 255);
  } else if (s_state == UpdateState::RESTART_READY || s_state == UpdateState::DOWNLOAD_COMPLETE) {
    std::string title = "— ATUALIZACAO CONCLUIDA —";
    int tx = modalX + (modalW - (int)title.length() * charW) / 2;
    Platform::drawText(renderer, title, tx, modalY + 16, charW, charH, 255);

    std::string s1 = "Arquivo instalado com sucesso!";
    std::string s2 = "[ 5 / A : REINICIAR ]";
    int sx1 = modalX + (modalW - (int)s1.length() * charW) / 2;
    int sx2 = modalX + (modalW - (int)s2.length() * charW) / 2;
    Platform::drawText(renderer, s1, sx1, modalY + modalH / 2 - charH, charW, charH, 255);
    Platform::drawText(renderer, s2, sx2, modalY + modalH - charH - 20, charW, charH, 255);
  } else if (s_state == UpdateState::CHECK_FAILED || s_state == UpdateState::DOWNLOAD_FAILED) {
    std::string title = "— ATUALIZACAO —";
    int tx = modalX + (modalW - (int)title.length() * charW) / 2;
    Platform::drawText(renderer, title, tx, modalY + 16, charW, charH, 255);

    std::string s1 = s_statusMessage;
    std::string s2 = "[ 7 / B : FECHAR ]";
    int sx1 = modalX + (modalW - (int)s1.length() * charW) / 2;
    int sx2 = modalX + (modalW - (int)s2.length() * charW) / 2;
    Platform::drawText(renderer, s1, sx1, modalY + modalH / 2 - charH, charW, charH, 255);
    Platform::drawText(renderer, s2, sx2, modalY + modalH - charH - 20, charW, charH, 255);
  }
}

} // namespace hl

