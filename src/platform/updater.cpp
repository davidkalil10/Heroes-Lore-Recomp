// updater.cpp — Sistema de Verificação e Atualização OTA via GitHub Releases
#include "updater.h"
#include "platform.h"
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <cmath>
#include <algorithm>
#include <mutex>
#include <atomic>
#include <sstream>

#if defined(__has_include)
  #if __has_include(<SDL2/SDL.h>)
    #include <SDL2/SDL.h>
  #else
    #include <SDL.h>
  #endif
#else
  #include <SDL.h>
#endif

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
  return "heroes_lore.exe";
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
#if defined(__SWITCH__)
static bool s_socketInitialized = false;
#endif

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
#if defined(__SWITCH__)
  if (!s_socketInitialized) return "";
#endif
  CURL* curl = curl_easy_init();
  if (!curl) return "";
  std::string response;
  curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
  curl_easy_setopt(curl, CURLOPT_USERAGENT, "Heroes-Lore-Updater/1.0");
  curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1L);
  curl_easy_setopt(curl, CURLOPT_SSL_VERIFYPEER, 0L);
  curl_easy_setopt(curl, CURLOPT_SSL_VERIFYHOST, 0L);
  curl_easy_setopt(curl, CURLOPT_NOSIGNAL, 1L);
  curl_easy_setopt(curl, CURLOPT_TIMEOUT, 6L);
  curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, curlWriteCallback);
  curl_easy_setopt(curl, CURLOPT_WRITEDATA, &response);

  CURLcode res = curl_easy_perform(curl);
  curl_easy_cleanup(curl);
  return (res == CURLE_OK) ? response : "";
}

static bool curlDownloadFile(const std::string& url, const std::string& outPath) {
#if defined(__SWITCH__)
  if (!s_socketInitialized) return false;
#endif
  FILE* fp = fopen(outPath.c_str(), "wb");
  if (!fp) return false;

  CURL* curl = curl_easy_init();
  if (!curl) { fclose(fp); return false; }

  curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
  curl_easy_setopt(curl, CURLOPT_USERAGENT, "Heroes-Lore-Updater/1.0");
  curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1L);
  curl_easy_setopt(curl, CURLOPT_SSL_VERIFYPEER, 0L);
  curl_easy_setopt(curl, CURLOPT_SSL_VERIFYHOST, 0L);
  curl_easy_setopt(curl, CURLOPT_NOSIGNAL, 1L);
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
static jclass s_activityClass = nullptr;
static jmethodID s_midHttpGet = nullptr;
static jmethodID s_midDownloadFile = nullptr;
static jmethodID s_midInstallApk = nullptr;

static void androidInitJni() {
  if (s_activityClass) return;
  JNIEnv* env = (JNIEnv*)SDL_AndroidGetJNIEnv();
  if (!env) return;

  // Tenta obter a instância viva do SDLActivity
  jobject activityObj = (jobject)SDL_AndroidGetActivity();
  if (activityObj) {
    jclass localClass = env->GetObjectClass(activityObj);
    if (localClass) {
      s_activityClass = (jclass)env->NewGlobalRef(localClass);
      env->DeleteLocalRef(localClass);
    }
  }

  // Fallback para FindClass se GetActivity ainda não estiver pronto
  if (!s_activityClass) {
    jclass localClass = env->FindClass("org/libsdl/app/SDLActivity");
    if (localClass) {
      s_activityClass = (jclass)env->NewGlobalRef(localClass);
      env->DeleteLocalRef(localClass);
    }
  }

  if (s_activityClass) {
    s_midHttpGet = env->GetStaticMethodID(s_activityClass, "httpGet", "(Ljava/lang/String;)Ljava/lang/String;");
    s_midDownloadFile = env->GetStaticMethodID(s_activityClass, "downloadFile", "(Ljava/lang/String;Ljava/lang/String;)Z");
    s_midInstallApk = env->GetStaticMethodID(s_activityClass, "installApk", "(Ljava/lang/String;)V");
  }

  if (env->ExceptionCheck()) {
    env->ExceptionClear();
  }
}

static std::string androidHttpGet(const std::string& url) {
  JNIEnv* env = (JNIEnv*)SDL_AndroidGetJNIEnv();
  if (!env) return "";

  if (!s_activityClass || !s_midHttpGet) {
    androidInitJni();
  }
  if (!s_activityClass || !s_midHttpGet) return "";

  jstring jUrl = env->NewStringUTF(url.c_str());
  if (!jUrl) return "";

  jstring jRes = (jstring)env->CallStaticObjectMethod(s_activityClass, s_midHttpGet, jUrl);
  env->DeleteLocalRef(jUrl);

  if (env->ExceptionCheck()) {
    env->ExceptionClear();
    return "";
  }
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

  if (!s_activityClass || !s_midDownloadFile) {
    androidInitJni();
  }
  if (!s_activityClass || !s_midDownloadFile) return false;

  jstring jUrl = env->NewStringUTF(url.c_str());
  jstring jPath = env->NewStringUTF(outPath.c_str());
  if (!jUrl || !jPath) {
    if (jUrl) env->DeleteLocalRef(jUrl);
    if (jPath) env->DeleteLocalRef(jPath);
    return false;
  }

  jboolean res = env->CallStaticBooleanMethod(s_activityClass, s_midDownloadFile, jUrl, jPath);
  env->DeleteLocalRef(jUrl);
  env->DeleteLocalRef(jPath);

  if (env->ExceptionCheck()) {
    env->ExceptionClear();
    return false;
  }

  return res == JNI_TRUE;
}

static void androidInstallApk(const std::string& apkPath) {
  JNIEnv* env = (JNIEnv*)SDL_AndroidGetJNIEnv();
  if (!env) return;

  if (!s_activityClass || !s_midInstallApk) {
    androidInitJni();
  }
  if (!s_activityClass || !s_midInstallApk) return;

  jstring jPath = env->NewStringUTF(apkPath.c_str());
  if (jPath) {
    env->CallStaticVoidMethod(s_activityClass, s_midInstallApk, jPath);
    env->DeleteLocalRef(jPath);
  }

  if (env->ExceptionCheck()) {
    env->ExceptionClear();
  }
}
#endif

void Updater::init() {
#if defined(__SWITCH__)
  Result rc = socketInitializeDefault();
  if (R_SUCCEEDED(rc)) {
    s_socketInitialized = true;
    curl_global_init(CURL_GLOBAL_ALL);
  }
#elif defined(__linux__) && !defined(__ANDROID__)
  curl_global_init(CURL_GLOBAL_ALL);
#elif defined(__ANDROID__)
  androidInitJni();
#endif
  s_state = UpdateState::IDLE;
  s_promptActive = false;
}

void Updater::shutdown() {
#if defined(__SWITCH__)
  if (s_socketInitialized) {
    curl_global_cleanup();
    socketExit();
    s_socketInitialized = false;
  }
#elif defined(__linux__) && !defined(__ANDROID__)
  curl_global_cleanup();
#elif defined(__ANDROID__)
  if (s_activityClass) {
    JNIEnv* env = (JNIEnv*)SDL_AndroidGetJNIEnv();
    if (env) {
      env->DeleteGlobalRef(s_activityClass);
    }
    s_activityClass = nullptr;
    s_midHttpGet = nullptr;
    s_midDownloadFile = nullptr;
    s_midInstallApk = nullptr;
  }
#endif
}

std::string Updater::getLocalVersion() {
  return HL_VERSION_TAG;
}

struct CheckUpdateThreadArgs {
  bool notifyIfNoUpdate;
};

static int runCheckUpdateThread(void* data) {
  CheckUpdateThreadArgs* args = static_cast<CheckUpdateThreadArgs*>(data);
  bool notifyIfNoUpdate = args ? args->notifyIfNoUpdate : false;
  delete args;

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
    return 0;
  }

  // Se o repositório for privado ou o endpoint não tiver release público (404 Not Found)
  if (json.find("\"Not Found\"") != std::string::npos || json.find("\"status\":\"404\"") != std::string::npos) {
    s_state = UpdateState::NO_UPDATE;
    s_statusMessage = "Jogo atualizado (" + std::string(HL_VERSION_TAG) + ").";
    if (notifyIfNoUpdate) {
      Platform::showOsdMessage("Jogo atualizado (" + std::string(HL_VERSION_TAG) + ").\nNenhuma versao nova encontrada.");
    }
    return 0;
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
  return 0;
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

  CheckUpdateThreadArgs* args = new CheckUpdateThreadArgs{notifyIfNoUpdate};
  SDL_Thread* th = SDL_CreateThreadWithStackSize(runCheckUpdateThread, "HL_CheckUpdate", 1024 * 1024, args);
  if (!th) {
    th = SDL_CreateThread(runCheckUpdateThread, "HL_CheckUpdate", args);
  }
  if (th) {
    SDL_DetachThread(th);
  } else {
    delete args;
    s_state = UpdateState::CHECK_FAILED;
  }
}

struct DownloadThreadArgs {
  std::string downloadUrl;
  std::string assetName;
};

static int runDownloadThread(void* data) {
  DownloadThreadArgs* args = static_cast<DownloadThreadArgs*>(data);
  std::string downloadUrl = args->downloadUrl;
  std::string assetName = args->assetName;
  delete args;

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
    Updater::applyUpdate();
  } else {
    s_state = UpdateState::DOWNLOAD_FAILED;
    s_statusMessage = "Falha no download da atualizacao.";
    Platform::showOsdMessage("Falha no download da atualizacao.");
  }
  return 0;
}

void Updater::startDownload() {
  if (s_releaseInfo.assetUrl.empty()) return;
  s_state = UpdateState::DOWNLOADING;
  s_downloadProgress = 0.0f;
  s_downloadedBytes = 0;
  s_totalBytes = s_releaseInfo.assetSize;
  s_statusMessage = "Baixando atualizacao: " + s_releaseInfo.assetName;

  DownloadThreadArgs* args = new DownloadThreadArgs{s_releaseInfo.assetUrl, s_releaseInfo.assetName};
  SDL_Thread* th = SDL_CreateThreadWithStackSize(runDownloadThread, "HL_Download", 1024 * 1024, args);
  if (!th) {
    th = SDL_CreateThread(runDownloadThread, "HL_Download", args);
  }
  if (th) {
    SDL_DetachThread(th);
  } else {
    delete args;
    s_state = UpdateState::DOWNLOAD_FAILED;
  }
}

static bool copyFile(const std::string& src, const std::string& dst) {
  FILE* in = fopen(src.c_str(), "rb");
  if (!in) return false;
  FILE* out = fopen(dst.c_str(), "wb");
  if (!out) {
    fclose(in);
    return false;
  }
  char buf[65536];
  size_t n = 0;
  while ((n = fread(buf, 1, sizeof(buf), in)) > 0) {
    if (fwrite(buf, 1, n, out) != n) {
      fclose(in);
      fclose(out);
      return false;
    }
  }
  fclose(in);
  fclose(out);
  return true;
}

static SDL_Rect s_modalRect = { 0, 0, 0, 0 };
static SDL_Rect s_btnConfirmRect = { 0, 0, 0, 0 };
static SDL_Rect s_btnCancelRect = { 0, 0, 0, 0 };

bool Updater::applyUpdate() {
  std::string storage = Platform::getStorageDir();
  std::string tmpFile = storage + "/" + s_releaseInfo.assetName + ".download";

#if defined(__SWITCH__)
  std::string targetNro = "";
  std::string execPath = Platform::getExecutablePath();
  if (!execPath.empty() && (execPath.rfind(".nro") != std::string::npos || execPath.rfind(".NRO") != std::string::npos)) {
    targetNro = execPath;
  } else {
    // Se execPath não tiver a extensão .nro, verifica onde o arquivo já existe no SD
    FILE* test1 = fopen("sdmc:/switch/heroes_lore.nro", "rb");
    if (test1) {
      fclose(test1);
      targetNro = "sdmc:/switch/heroes_lore.nro";
    } else {
      FILE* test2 = fopen("sdmc:/switch/heroes_lore/heroes_lore.nro", "rb");
      if (test2) {
        fclose(test2);
        targetNro = "sdmc:/switch/heroes_lore/heroes_lore.nro";
      } else {
        targetNro = "sdmc:/switch/heroes_lore.nro";
      }
    }
  }

  // Copia APENAS para a pasta onde o NRO de fato reside
  bool ok = copyFile(tmpFile, targetNro);
  remove(tmpFile.c_str());

  if (ok) {
    s_state = UpdateState::RESTART_READY;
    s_statusMessage = "Atualizacao concluida! Reiniciando...";
    envSetNextLoad(targetNro.c_str(), targetNro.c_str());
    Platform::showOsdMessage("Atualizacao concluida! Reinicie o aplicativo.");
    return true;
  }
#elif defined(_WIN32)
  std::string targetExe = "heroes_lore.exe";
  std::string execPath = Platform::getExecutablePath();
  if (!execPath.empty() && (execPath.rfind(".exe") != std::string::npos || execPath.rfind(".EXE") != std::string::npos)) {
    targetExe = execPath;
  }
  std::string oldExe = targetExe + ".old";
  remove(oldExe.c_str());
  MoveFileA(targetExe.c_str(), oldExe.c_str());
  if (MoveFileA(tmpFile.c_str(), targetExe.c_str()) || copyFile(tmpFile, targetExe)) {
    remove(tmpFile.c_str());
    s_state = UpdateState::RESTART_READY;
    s_statusMessage = "Atualizacao concluida! Reinicie o jogo.";
    Platform::showOsdMessage("Atualizacao concluida! Reinicie o jogo.");
    return true;
  }
#elif defined(__linux__) && !defined(__ANDROID__)
  chmod(tmpFile.c_str(), 0755);
  std::string targetAppImage = "heroes_lore.AppImage";
  const char* appimageEnv = getenv("APPIMAGE");
  if (appimageEnv && appimageEnv[0] != '\0') {
    targetAppImage = appimageEnv;
  } else {
    std::string execPath = Platform::getExecutablePath();
    if (!execPath.empty() && execPath.find(".AppImage") != std::string::npos) {
      targetAppImage = execPath;
    }
  }
  remove(targetAppImage.c_str());
  if (rename(tmpFile.c_str(), targetAppImage.c_str()) == 0 || copyFile(tmpFile, targetAppImage)) {
    remove(tmpFile.c_str());
    chmod(targetAppImage.c_str(), 0755);
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
#if defined(_WIN32)
    std::string targetExe = "heroes_lore.exe";
    std::string execPath = Platform::getExecutablePath();
    if (!execPath.empty() && execPath.rfind(".exe") != std::string::npos) {
      targetExe = execPath;
    }
    ShellExecuteA(NULL, "open", targetExe.c_str(), NULL, NULL, SW_SHOWNORMAL);
    Platform::requestQuit();
#elif defined(__SWITCH__)
    Platform::requestQuit();
#elif defined(__linux__) && !defined(__ANDROID__)
    const char* appimage = getenv("APPIMAGE");
    if (appimage && appimage[0] != '\0') {
      execl(appimage, appimage, NULL);
    }
    Platform::requestQuit();
#elif defined(__ANDROID__)
    dismissPrompt();
#endif
  }
}

bool Updater::handleInput(int key) {
  if (!s_promptActive) return false;

  // Confirmar: 53 ('5'), 13 (Enter), 5 (Joypad A / Key 5), 32 (Space)
  if (key == 53 || key == 13 || key == 5 || key == 32) {
    confirmUpdate();
    return true;
  }
  // Cancelar: -7 (Joypad B / RSK), 7, 55 ('7'), 27 (ESC), 8 (Backspace)
  if (key == -7 || key == 7 || key == 55 || key == 27 || key == 8) {
    dismissPrompt();
    return true;
  }
  return true; // Bloqueia outros inputs de passarem para o jogo enquanto o modal estiver aberto
}

void Updater::handleClick(int x, int y) {
  if (!s_promptActive) return;

  SDL_Point pt = { x, y };
  if (s_btnConfirmRect.w > 0 && SDL_PointInRect(&pt, &s_btnConfirmRect)) {
    confirmUpdate();
    return;
  }
  if (s_btnCancelRect.w > 0 && SDL_PointInRect(&pt, &s_btnCancelRect)) {
    dismissPrompt();
    return;
  }
  // Se clicou fora da janela do modal: fecha o aviso com segurança
  if (s_modalRect.w > 0 && !SDL_PointInRect(&pt, &s_modalRect)) {
    if (s_state == UpdateState::UPDATE_AVAILABLE || s_state == UpdateState::CHECK_FAILED || s_state == UpdateState::NO_UPDATE) {
      dismissPrompt();
      return;
    }
  }
}

// Renderização gráfica nobre da janela modal de atualização (Soltia Theme)
void Updater::drawModal(SDL_Renderer* renderer, int winW, int winH) {
  if (!s_promptActive || !renderer) return;

  SDL_SetRenderDrawBlendMode(renderer, SDL_BLENDMODE_BLEND);

  // 1. Escurecimento translúcido do fundo (Backdrop)
  SDL_Rect fullScreen = { 0, 0, winW, winH };
  SDL_SetRenderDrawColor(renderer, 0, 0, 0, 210);
  SDL_RenderFillRect(renderer, &fullScreen);

  // 2. Caixa Modal Proporcional (não estica no widescreen)
  int modalW = std::min(winW - 24, std::max(310, std::min(450, (int)(winH * 0.95f))));
  int modalH = std::min(winH - 24, std::max(270, (int)(modalW * 0.70f)));
  int modalX = (winW - modalW) / 2;
  int modalY = (winH - modalH) / 2;
  s_modalRect = { modalX, modalY, modalW, modalH };

  // Sombra suave da caixa
  SDL_Rect shadow = { modalX + 6, modalY + 6, modalW, modalH };
  SDL_SetRenderDrawColor(renderer, 0, 0, 0, 160);
  SDL_RenderFillRect(renderer, &shadow);

  // Fundo ardósia nobre de Soltia
  SDL_Rect box = { modalX, modalY, modalW, modalH };
  SDL_SetRenderDrawColor(renderer, 14, 18, 26, 252);
  SDL_RenderFillRect(renderer, &box);

  // Borda externa de aço
  SDL_SetRenderDrawColor(renderer, 55, 75, 100, 255);
  SDL_RenderDrawRect(renderer, &box);

  // Filete interno em ouro nobre
  SDL_Rect goldBorder = { modalX + 4, modalY + 4, modalW - 8, modalH - 8 };
  SDL_SetRenderDrawColor(renderer, 195, 155, 60, 255);
  SDL_RenderDrawRect(renderer, &goldBorder);

  // Rebites de bronze nos 4 cantos
  SDL_SetRenderDrawColor(renderer, 225, 185, 80, 255);
  SDL_Rect r1 = { modalX + 7, modalY + 7, 4, 4 };
  SDL_Rect r2 = { modalX + modalW - 11, modalY + 7, 4, 4 };
  SDL_Rect r3 = { modalX + 7, modalY + modalH - 11, 4, 4 };
  SDL_Rect r4 = { modalX + modalW - 11, modalY + modalH - 11, 4, 4 };
  SDL_RenderFillRect(renderer, &r1);
  SDL_RenderFillRect(renderer, &r2);
  SDL_RenderFillRect(renderer, &r3);
  SDL_RenderFillRect(renderer, &r4);

  // Faixa de cabeçalho
  SDL_Rect headerBox = { modalX + 6, modalY + 6, modalW - 12, 32 };
  SDL_SetRenderDrawColor(renderer, 22, 28, 40, 255);
  SDL_RenderFillRect(renderer, &headerBox);
  SDL_SetRenderDrawColor(renderer, 195, 155, 60, 200);
  SDL_RenderDrawLine(renderer, modalX + 6, modalY + 38, modalX + modalW - 6, modalY + 38);

  int charH = 18;
  int charW = 12;
  int stepX = 8; // Espaçamento proporcional natural entre caracteres

  if (s_state == UpdateState::UPDATE_AVAILABLE || s_state == UpdateState::CONFIRM_PROMPT) {
    // Título do Cabeçalho
    std::string title = "ATUALIZACAO DISPONIVEL";
    int tw = Platform::getTextWidth(title, charW, stepX);
    Platform::drawText(renderer, title, modalX + (modalW - tw) / 2, modalY + 13, charW, charH, 255, stepX);

    // Faixa/Badge de Versão (Estilo Pill)
    int badgeW = std::min(modalW - 40, 230);
    int badgeH = 24;
    int badgeX = modalX + (modalW - badgeW) / 2;
    int badgeY = modalY + 48;
    SDL_Rect verBox = { badgeX, badgeY, badgeW, badgeH };
    SDL_SetRenderDrawColor(renderer, 18, 28, 42, 255);
    SDL_RenderFillRect(renderer, &verBox);
    SDL_SetRenderDrawColor(renderer, 70, 130, 190, 255);
    SDL_RenderDrawRect(renderer, &verBox);

    std::string vLine = std::string(HL_VERSION_TAG) + " -> " + s_releaseInfo.tagName;
    int vw = Platform::getTextWidth(vLine, charW, stepX);
    Platform::drawText(renderer, vLine, badgeX + (badgeW - vw) / 2, badgeY + 3, charW, charH, 255, stepX);

    // Caixa de Alerta Âmbar de Salvamento
    int noteX = modalX + 16;
    int noteY = modalY + 80;
    int noteW = modalW - 32;
    int noteH = 68;
    SDL_Rect noteBox = { noteX, noteY, noteW, noteH };
    SDL_SetRenderDrawColor(renderer, 28, 22, 12, 235);
    SDL_RenderFillRect(renderer, &noteBox);
    SDL_SetRenderDrawColor(renderer, 175, 125, 40, 255);
    SDL_RenderDrawRect(renderer, &noteBox);

    std::string a1 = "AVISO IMPORTANTE:";
    std::string a2 = "Salve o jogo antes de prosseguir.";
    std::string a3 = "O aplicativo sera reiniciado.";
    Platform::drawText(renderer, a1, noteX + (noteW - Platform::getTextWidth(a1, charW, stepX)) / 2, noteY + 7, charW, charH, 255, stepX);
    Platform::drawText(renderer, a2, noteX + (noteW - Platform::getTextWidth(a2, charW, stepX)) / 2, noteY + 27, charW, charH, 240, stepX);
    Platform::drawText(renderer, a3, noteX + (noteW - Platform::getTextWidth(a3, charW, stepX)) / 2, noteY + 46, charW, charH, 240, stepX);

    // Botões Interativos (Lado a Lado)
    int btnMargin = 16;
    int btnGap = 12;
    int btnW = (modalW - (btnMargin * 2) - btnGap) / 2;
    int btnH = 42;
    int btnY = modalY + modalH - btnH - 16;

    s_btnConfirmRect = { modalX + btnMargin, btnY, btnW, btnH };
    s_btnCancelRect = { modalX + btnMargin + btnW + btnGap, btnY, btnW, btnH };

    // 1. Botão Confirmar (Ciano / Ouro Real)
    SDL_SetRenderDrawColor(renderer, 18, 55, 80, 255);
    SDL_RenderFillRect(renderer, &s_btnConfirmRect);
    SDL_SetRenderDrawColor(renderer, 45, 190, 240, 255);
    SDL_RenderDrawRect(renderer, &s_btnConfirmRect);
    SDL_Rect cBorder = { s_btnConfirmRect.x + 2, s_btnConfirmRect.y + 2, s_btnConfirmRect.w - 4, s_btnConfirmRect.h - 4 };
    SDL_SetRenderDrawColor(renderer, 195, 155, 60, 180);
    SDL_RenderDrawRect(renderer, &cBorder);

    std::string bt1 = "ATUALIZAR";
    std::string sc1 = "[ A / 5 ]";
    Platform::drawText(renderer, bt1, s_btnConfirmRect.x + (btnW - Platform::getTextWidth(bt1, charW, stepX)) / 2, btnY + 4, charW, charH, 255, stepX);
    Platform::drawText(renderer, sc1, s_btnConfirmRect.x + (btnW - Platform::getTextWidth(sc1, charW, stepX)) / 2, btnY + 22, charW, charH, 200, stepX);

    // 2. Botão Cancelar (Ardósia Carmesim)
    SDL_SetRenderDrawColor(renderer, 48, 20, 26, 255);
    SDL_RenderFillRect(renderer, &s_btnCancelRect);
    SDL_SetRenderDrawColor(renderer, 175, 65, 75, 255);
    SDL_RenderDrawRect(renderer, &s_btnCancelRect);
    SDL_Rect rBorder = { s_btnCancelRect.x + 2, s_btnCancelRect.y + 2, s_btnCancelRect.w - 4, s_btnCancelRect.h - 4 };
    SDL_SetRenderDrawColor(renderer, 95, 55, 65, 180);
    SDL_RenderDrawRect(renderer, &rBorder);

    std::string bt2 = "CANCELAR";
    std::string sc2 = "[ B / 7 ]";
    Platform::drawText(renderer, bt2, s_btnCancelRect.x + (btnW - Platform::getTextWidth(bt2, charW, stepX)) / 2, btnY + 4, charW, charH, 255, stepX);
    Platform::drawText(renderer, sc2, s_btnCancelRect.x + (btnW - Platform::getTextWidth(sc2, charW, stepX)) / 2, btnY + 22, charW, charH, 200, stepX);

  } else if (s_state == UpdateState::DOWNLOADING) {
    std::string title = "BAIXANDO ATUALIZACAO";
    int tw = Platform::getTextWidth(title, charW, stepX);
    Platform::drawText(renderer, title, modalX + (modalW - tw) / 2, modalY + 13, charW, charH, 255, stepX);

    std::string fLine = s_releaseInfo.assetName;
    int fw = Platform::getTextWidth(fLine, charW, stepX);
    Platform::drawText(renderer, fLine, modalX + (modalW - fw) / 2, modalY + 54, charW, charH, 240, stepX);

    // Barra de progresso gráfica
    int barW = modalW - 48;
    int barH = 20;
    int barX = modalX + 24;
    int barY = modalY + 88;

    SDL_Rect barBg = { barX, barY, barW, barH };
    SDL_SetRenderDrawColor(renderer, 10, 14, 20, 255);
    SDL_RenderFillRect(renderer, &barBg);
    SDL_SetRenderDrawColor(renderer, 70, 100, 135, 255);
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
    int pw = Platform::getTextWidth(pStr, charW, stepX);
    Platform::drawText(renderer, pStr, modalX + (modalW - pw) / 2, barY + barH + 12, charW, charH, 255, stepX);

    std::string wLine = "Aguarde... Nao feche o jogo.";
    int ww = Platform::getTextWidth(wLine, charW, stepX);
    Platform::drawText(renderer, wLine, modalX + (modalW - ww) / 2, modalY + modalH - 32, charW, charH, 200, stepX);

    s_btnConfirmRect = { 0, 0, 0, 0 };
    s_btnCancelRect = { 0, 0, 0, 0 };

  } else if (s_state == UpdateState::RESTART_READY || s_state == UpdateState::DOWNLOAD_COMPLETE) {
    std::string title = "ATUALIZACAO CONCLUIDA";
    int tw = Platform::getTextWidth(title, charW, stepX);
    Platform::drawText(renderer, title, modalX + (modalW - tw) / 2, modalY + 13, charW, charH, 255, stepX);

    std::string s1 = "Arquivo instalado com sucesso!";
    int sw1 = Platform::getTextWidth(s1, charW, stepX);
    Platform::drawText(renderer, s1, modalX + (modalW - sw1) / 2, modalY + 68, charW, charH, 255, stepX);

    std::string s2 = "Reinicie para aplicar a nova versao.";
    int sw2 = Platform::getTextWidth(s2, charW, stepX);
    Platform::drawText(renderer, s2, modalX + (modalW - sw2) / 2, modalY + 92, charW, charH, 220, stepX);

    // Botão de Reinício (Verde Esmeralda)
    int btnW = std::min(modalW - 48, 220);
    int btnH = 42;
    int btnX = modalX + (modalW - btnW) / 2;
    int btnY = modalY + modalH - btnH - 18;
    s_btnConfirmRect = { btnX, btnY, btnW, btnH };
    s_btnCancelRect = { 0, 0, 0, 0 };

    SDL_SetRenderDrawColor(renderer, 18, 68, 38, 255);
    SDL_RenderFillRect(renderer, &s_btnConfirmRect);
    SDL_SetRenderDrawColor(renderer, 45, 210, 110, 255);
    SDL_RenderDrawRect(renderer, &s_btnConfirmRect);

    std::string rText = "REINICIAR";
    std::string rSub = "[ A / 5 ]";
    Platform::drawText(renderer, rText, btnX + (btnW - Platform::getTextWidth(rText, charW, stepX)) / 2, btnY + 4, charW, charH, 255, stepX);
    Platform::drawText(renderer, rSub, btnX + (btnW - Platform::getTextWidth(rSub, charW, stepX)) / 2, btnY + 22, charW, charH, 200, stepX);

  } else if (s_state == UpdateState::CHECK_FAILED || s_state == UpdateState::DOWNLOAD_FAILED) {
    std::string title = "AVISO DE ATUALIZACAO";
    int tw = Platform::getTextWidth(title, charW, stepX);
    Platform::drawText(renderer, title, modalX + (modalW - tw) / 2, modalY + 13, charW, charH, 255, stepX);

    std::string s1 = s_statusMessage.empty() ? "Nao foi possivel concluir a atualizacao." : s_statusMessage;
    int sw1 = Platform::getTextWidth(s1, charW, stepX);
    Platform::drawText(renderer, s1, modalX + (modalW - sw1) / 2, modalY + 75, charW, charH, 255, stepX);

    int btnW = 160;
    int btnH = 40;
    int btnX = modalX + (modalW - btnW) / 2;
    int btnY = modalY + modalH - btnH - 18;
    s_btnCancelRect = { btnX, btnY, btnW, btnH };
    s_btnConfirmRect = { 0, 0, 0, 0 };

    SDL_SetRenderDrawColor(renderer, 48, 20, 26, 255);
    SDL_RenderFillRect(renderer, &s_btnCancelRect);
    SDL_SetRenderDrawColor(renderer, 175, 65, 75, 255);
    SDL_RenderDrawRect(renderer, &s_btnCancelRect);

    std::string cText = "FECHAR [ B / 7 ]";
    Platform::drawText(renderer, cText, btnX + (btnW - Platform::getTextWidth(cText, charW, stepX)) / 2, btnY + 11, charW, charH, 255, stepX);
  }
}

} // namespace hl

