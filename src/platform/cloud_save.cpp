// cloud_save.cpp — Sistema de Backup e Sincronização Cruzada na Nuvem via Google Drive (appDataFolder)
#include "cloud_save.h"
#include "platform.h"
#include "vm/vm.h"

#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <ctime>
#include <chrono>
#include <fstream>
#include <sstream>
#include <thread>
#include <algorithm>
#include <filesystem>

#if defined(_WIN32)
  #ifndef WIN32_LEAN_AND_MEAN
    #define WIN32_LEAN_AND_MEAN
  #endif
  #include <windows.h>
  #include <winhttp.h>
#elif defined(__SWITCH__)
  #include <switch.h>
  #include <curl/curl.h>
#elif defined(__ANDROID__)
  #include <jni.h>
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
#endif

namespace hl {

// -----------------------------------------------------------------------------
// Variáveis de Estado Internas
// -----------------------------------------------------------------------------
static CloudSaveState s_state = CloudSaveState::NOT_LOGGED_IN;
static DeviceCodeInfo s_deviceCode;
static CloudBackupInfo s_cloudBackup;
static std::string s_statusMessage = "";
static std::mutex s_cloudMutex;
static bool s_modalActive = false;
static std::atomic<bool> s_loginActive{false};
static std::string s_accessToken = "";
static std::string s_refreshToken = "";
static uint64_t s_tokenExpiryEpoch = 0;
static VM* s_activeVm = nullptr;

static SDL_Rect s_modalRect = {0, 0, 0, 0};
static SDL_Rect s_btnAction1 = {0, 0, 0, 0};
static SDL_Rect s_btnAction2 = {0, 0, 0, 0};
static SDL_Rect s_btnAction3 = {0, 0, 0, 0};
static SDL_Rect s_btnCancel  = {0, 0, 0, 0};

// Forward declarations
static void saveTokensToFile();
static void loadTokensFromFile();
static bool ensureValidAccessToken();

std::string getGoogleClientId() {
  static const uint8_t enc[] = {
    108, 104, 108, 99, 104, 98, 111, 110, 98, 107, 111, 107, 119, 107, 104, 98, 59, 42, 50, 54, 59, 52, 46, 110, 104, 111, 54, 61, 109, 47, 57, 108, 55, 106, 43, 53, 44, 48, 47, 108, 52, 63, 60, 41, 61, 116, 59, 42, 42, 41, 116, 61, 53, 53, 61, 54, 63, 47, 41, 63, 40, 57, 53, 52, 46, 63, 52, 46, 116, 57, 53, 55
  };
  std::string s;
  s.reserve(sizeof(enc));
  for (uint8_t b : enc) s.push_back((char)(b ^ 0x5A));
  return s;
}

std::string getGoogleClientSecret() {
  static const uint8_t enc[] = {
    29, 21, 25, 9, 10, 2, 119, 45, 107, 28, 106, 56, 21, 48, 106, 99, 109, 107, 44, 11, 98, 119, 119, 61, 23, 24, 48, 43, 106, 8, 25, 109, 104, 45, 10
  };
  std::string s;
  s.reserve(sizeof(enc));
  for (uint8_t b : enc) s.push_back((char)(b ^ 0x5A));
  return s;
}

// -----------------------------------------------------------------------------
// Utilitários de String e JSON
// -----------------------------------------------------------------------------
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

static int extractJsonInt(const std::string& json, const std::string& key, int defaultVal = 0) {
  size_t k = json.find("\"" + key + "\"");
  if (k == std::string::npos) return defaultVal;
  size_t colon = json.find(':', k);
  if (colon == std::string::npos) return defaultVal;
  size_t start = json.find_first_of("0123456789-", colon);
  if (start == std::string::npos) return defaultVal;
  return std::atoi(json.c_str() + start);
}

static std::string jsonEscape(const std::string& s) {
  std::ostringstream o;
  for (char c : s) {
    if (c == '"') o << "\\\"";
    else if (c == '\\') o << "\\\\";
    else if (c == '\b') o << "\\b";
    else if (c == '\f') o << "\\f";
    else if (c == '\n') o << "\\n";
    else if (c == '\r') o << "\\r";
    else if (c == '\t') o << "\\t";
    else if ((unsigned char)c < 32) {
      char buf[8];
      std::snprintf(buf, sizeof(buf), "\\u%04x", (unsigned char)c);
      o << buf;
    } else {
      o << c;
    }
  }
  return o.str();
}

static std::string urlEncode(const std::string& s) {
  std::ostringstream escaped;
  escaped.fill('0');
  escaped << std::hex;
  for (char c : s) {
    if (isalnum((unsigned char)c) || c == '-' || c == '_' || c == '.' || c == '~') {
      escaped << c;
    } else {
      escaped << '%' << std::uppercase << std::setw(2) << int((unsigned char)c) << std::nouppercase;
    }
  }
  return escaped.str();
}

// -----------------------------------------------------------------------------
// Base64 Encode / Decode
// -----------------------------------------------------------------------------
static const char B64_CHARS[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

static std::string base64Encode(const std::vector<uint8_t>& data) {
  std::string out;
  int val = 0, valb = -6;
  for (uint8_t c : data) {
    val = (val << 8) + c;
    valb += 8;
    while (valb >= 0) {
      out.push_back(B64_CHARS[(val >> valb) & 0x3F]);
      valb -= 6;
    }
  }
  if (valb > -6) out.push_back(B64_CHARS[((val << 8) >> (valb + 8)) & 0x3F]);
  while (out.size() % 4) out.push_back('=');
  return out;
}

static std::vector<uint8_t> base64Decode(const std::string& in) {
  std::vector<uint8_t> out;
  std::vector<int> T(256, -1);
  for (int i = 0; i < 64; i++) T[(unsigned char)B64_CHARS[i]] = i;

  int val = 0, valb = -8;
  for (char c : in) {
    if (c == '=' || (unsigned char)c > 255 || T[(unsigned char)c] == -1) continue;
    val = (val << 6) + T[(unsigned char)c];
    valb += 6;
    if (valb >= 0) {
      out.push_back(uint8_t((val >> valb) & 0xFF));
      valb -= 8;
    }
  }
  return out;
}

// -----------------------------------------------------------------------------
// Cliente HTTP Multiplataforma (GET, POST, PATCH)
// -----------------------------------------------------------------------------
struct HttpResponse {
  int statusCode = 0;
  std::string body;
};

#if defined(_WIN32)
static HttpResponse winHttpRequest(const std::string& method, const std::string& url,
                                  const std::vector<std::string>& headers, const std::string& bodyData) {
  HttpResponse resp;
  std::wstring wurl(url.begin(), url.end());
  URL_COMPONENTS urlComp;
  ZeroMemory(&urlComp, sizeof(urlComp));
  urlComp.dwStructSize = sizeof(urlComp);
  urlComp.dwSchemeLength = (DWORD)-1;
  urlComp.dwHostNameLength = (DWORD)-1;
  urlComp.dwUrlPathLength = (DWORD)-1;
  urlComp.dwExtraInfoLength = (DWORD)-1;

  if (!WinHttpCrackUrl(wurl.c_str(), (DWORD)wurl.length(), 0, &urlComp)) return resp;

  HINTERNET hSession = WinHttpOpen(L"Heroes-Lore-Cloud/1.0",
                                   WINHTTP_ACCESS_TYPE_DEFAULT_PROXY,
                                   WINHTTP_NO_PROXY_NAME,
                                   WINHTTP_NO_PROXY_BYPASS, 0);
  if (!hSession) return resp;

  std::wstring host(urlComp.lpszHostName, urlComp.dwHostNameLength);
  HINTERNET hConnect = WinHttpConnect(hSession, host.c_str(), urlComp.nPort, 0);
  if (!hConnect) { WinHttpCloseHandle(hSession); return resp; }

  std::wstring path(urlComp.lpszUrlPath, urlComp.dwUrlPathLength);
  if (urlComp.dwExtraInfoLength > 0) {
    path.append(urlComp.lpszExtraInfo, urlComp.dwExtraInfoLength);
  }

  std::wstring wMethod(method.begin(), method.end());
  DWORD flags = (urlComp.nScheme == INTERNET_SCHEME_HTTPS) ? WINHTTP_FLAG_SECURE : 0;
  HINTERNET hRequest = WinHttpOpenRequest(hConnect, wMethod.c_str(), path.c_str(),
                                         NULL, WINHTTP_NO_REFERER,
                                         WINHTTP_DEFAULT_ACCEPT_TYPES, flags);
  if (!hRequest) {
    WinHttpCloseHandle(hConnect);
    WinHttpCloseHandle(hSession);
    return resp;
  }

  // Adiciona cabeçalhos
  for (const auto& h : headers) {
    std::wstring wh(h.begin(), h.end());
    WinHttpAddRequestHeaders(hRequest, wh.c_str(), (ULONG)-1L, WINHTTP_ADDREQ_FLAG_ADD);
  }

  // Timeouts: 10s connect, 15s send, 20s receive
  WinHttpSetTimeouts(hRequest, 10000, 10000, 15000, 20000);

  DWORD bodyLen = (DWORD)bodyData.size();
  BOOL sent = WinHttpSendRequest(hRequest, WINHTTP_NO_ADDITIONAL_HEADERS, 0,
                                bodyLen > 0 ? (LPVOID)bodyData.data() : WINHTTP_NO_REQUEST_DATA,
                                bodyLen, bodyLen, 0);

  if (sent && WinHttpReceiveResponse(hRequest, NULL)) {
    DWORD dwStatusCode = 0;
    DWORD dwSize = sizeof(dwStatusCode);
    if (WinHttpQueryHeaders(hRequest, WINHTTP_QUERY_STATUS_CODE | WINHTTP_QUERY_FLAG_NUMBER,
                            WINHTTP_HEADER_NAME_BY_INDEX, &dwStatusCode, &dwSize, WINHTTP_NO_HEADER_INDEX)) {
      resp.statusCode = (int)dwStatusCode;
    }

    std::vector<char> buffer(4096);
    DWORD dwDownloaded = 0;
    while (WinHttpQueryDataAvailable(hRequest, &dwSize) && dwSize > 0) {
      if (dwSize > buffer.size()) buffer.resize(dwSize);
      if (WinHttpReadData(hRequest, buffer.data(), dwSize, &dwDownloaded) && dwDownloaded > 0) {
        resp.body.append(buffer.data(), dwDownloaded);
      } else {
        break;
      }
    }
  }

  WinHttpCloseHandle(hRequest);
  WinHttpCloseHandle(hConnect);
  WinHttpCloseHandle(hSession);
  return resp;
}

#elif defined(__SWITCH__) || (defined(__linux__) && !defined(__ANDROID__))

static size_t curlWriteCallback(void* contents, size_t size, size_t nmemb, void* userp) {
  size_t total = size * nmemb;
  std::string* str = static_cast<std::string*>(userp);
  str->append(static_cast<char*>(contents), total);
  return total;
}

static HttpResponse curlHttpRequest(const std::string& method, const std::string& url,
                                   const std::vector<std::string>& headers, const std::string& bodyData) {
  HttpResponse resp;
  CURL* curl = curl_easy_init();
  if (!curl) return resp;

  curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
  curl_easy_setopt(curl, CURLOPT_CUSTOMREQUEST, method.c_str());
  curl_easy_setopt(curl, CURLOPT_USERAGENT, "Heroes-Lore-Cloud/1.0");
  curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1L);
  curl_easy_setopt(curl, CURLOPT_SSL_VERIFYPEER, 0L);
  curl_easy_setopt(curl, CURLOPT_SSL_VERIFYHOST, 0L);
  curl_easy_setopt(curl, CURLOPT_NOSIGNAL, 1L);
  curl_easy_setopt(curl, CURLOPT_TIMEOUT, 20L);
  curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, curlWriteCallback);
  curl_easy_setopt(curl, CURLOPT_WRITEDATA, &resp.body);

  struct curl_slist* hList = nullptr;
  for (const auto& h : headers) {
    hList = curl_slist_append(hList, h.c_str());
  }
  if (hList) curl_easy_setopt(curl, CURLOPT_HTTPHEADER, hList);

  if (!bodyData.empty()) {
    curl_easy_setopt(curl, CURLOPT_POSTFIELDS, bodyData.c_str());
    curl_easy_setopt(curl, CURLOPT_POSTFIELDSIZE, (long)bodyData.size());
  }

  CURLcode res = curl_easy_perform(curl);
  if (res == CURLE_OK) {
    long code = 0;
    curl_easy_getinfo(curl, CURLINFO_RESPONSE_CODE, &code);
    resp.statusCode = (int)code;
  }

  if (hList) curl_slist_free_all(hList);
  curl_easy_cleanup(curl);
  return resp;
}

#elif defined(__ANDROID__)

static HttpResponse androidHttpRequest(const std::string& method, const std::string& url,
                                      const std::vector<std::string>& headers, const std::string& bodyData) {
  HttpResponse resp;
  JNIEnv* env = (JNIEnv*)SDL_AndroidGetJNIEnv();
  if (!env) return resp;

  jclass activityClass = env->FindClass("org/libsdl/app/SDLActivity");
  if (!activityClass) return resp;

  jmethodID mid = env->GetStaticMethodID(activityClass, "httpExecute",
                                        "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;");
  if (!mid) {
    env->DeleteLocalRef(activityClass);
    return resp;
  }

  std::string headersConcat;
  for (const auto& h : headers) {
    headersConcat += h + "\n";
  }

  jstring jUrl = env->NewStringUTF(url.c_str());
  jstring jMethod = env->NewStringUTF(method.c_str());
  jstring jHeaders = env->NewStringUTF(headersConcat.c_str());
  jstring jBody = env->NewStringUTF(bodyData.c_str());

  jstring jResult = (jstring)env->CallStaticObjectMethod(activityClass, mid, jUrl, jMethod, jHeaders, jBody);

  env->DeleteLocalRef(jUrl);
  env->DeleteLocalRef(jMethod);
  env->DeleteLocalRef(jHeaders);
  env->DeleteLocalRef(jBody);
  env->DeleteLocalRef(activityClass);

  if (jResult) {
    const char* utf = env->GetStringUTFChars(jResult, nullptr);
    if (utf) {
      std::string fullResult(utf);
      env->ReleaseStringUTFChars(jResult, utf);
      env->DeleteLocalRef(jResult);

      size_t nl = fullResult.find('\n');
      if (nl != std::string::npos) {
        resp.statusCode = std::atoi(fullResult.substr(0, nl).c_str());
        resp.body = fullResult.substr(nl + 1);
      } else {
        resp.statusCode = std::atoi(fullResult.c_str());
      }
    }
  }

  return resp;
}
#endif

static HttpResponse httpExecute(const std::string& method, const std::string& url,
                               const std::vector<std::string>& headers, const std::string& bodyData) {
#if defined(_WIN32)
  return winHttpRequest(method, url, headers, bodyData);
#elif defined(__SWITCH__) || (defined(__linux__) && !defined(__ANDROID__))
  return curlHttpRequest(method, url, headers, bodyData);
#elif defined(__ANDROID__)
  return androidHttpRequest(method, url, headers, bodyData);
#else
  return HttpResponse{};
#endif
}

// -----------------------------------------------------------------------------
// Persistência de Tokens Locais (cloud_auth.json)
// -----------------------------------------------------------------------------
static std::string getAuthFilePath() {
  return Platform::getStorageDir() + "/cloud_auth.json";
}

static void saveTokensToFile() {
  std::ofstream f(getAuthFilePath(), std::ios::trunc);
  if (!f) return;
  f << "{\n";
  f << "  \"refresh_token\": \"" << jsonEscape(s_refreshToken) << "\",\n";
  f << "  \"access_token\": \"" << jsonEscape(s_accessToken) << "\",\n";
  f << "  \"expires_at\": " << s_tokenExpiryEpoch << "\n";
  f << "}\n";
}

static void loadTokensFromFile() {
  std::ifstream f(getAuthFilePath());
  if (!f) return;
  std::stringstream ss;
  ss << f.rdbuf();
  std::string json = ss.str();
  s_refreshToken = extractJsonString(json, "refresh_token");
  s_accessToken = extractJsonString(json, "access_token");
  s_tokenExpiryEpoch = (uint64_t)extractJsonInt(json, "expires_at", 0);
}

static bool ensureValidAccessToken() {
  if (s_refreshToken.empty()) return false;

  auto now = (uint64_t)std::time(nullptr);
  if (!s_accessToken.empty() && s_tokenExpiryEpoch > now + 60) {
    return true; // Token ainda é válido por mais de 1 minuto
  }

  // Renova o access_token usando o refresh_token
  std::string url = "https://oauth2.googleapis.com/token";
  std::vector<std::string> headers = {
    "Content-Type: application/x-www-form-urlencoded"
  };
  std::string body = "client_id=" + urlEncode(getGoogleClientId()) +
                     "&client_secret=" + urlEncode(getGoogleClientSecret()) +
                     "&refresh_token=" + urlEncode(s_refreshToken) +
                     "&grant_type=refresh_token";

  HttpResponse resp = httpExecute("POST", url, headers, body);
  if (resp.statusCode == 200) {
    std::string newTok = extractJsonString(resp.body, "access_token");
    int expIn = extractJsonInt(resp.body, "expires_in", 3600);
    if (!newTok.empty()) {
      s_accessToken = newTok;
      s_tokenExpiryEpoch = (uint64_t)std::time(nullptr) + expIn;
      saveTokensToFile();
      return true;
    }
  }
  return false;
}

// -----------------------------------------------------------------------------
// Resumo dos Saves Locais (Slot Karis, Shion, Luiel)
// -----------------------------------------------------------------------------
static std::string getLocalSavesSummary() {
  std::string rmsBase = Platform::getRmsDir(s_activeVm);
  std::string summary = "";

  struct SlotCheck {
    const char* filename;
    const char* defaultHero;
  };
  SlotCheck slots[] = {
    {"_k.rms", "Karis"},
    {"_s.rms", "Shion"},
    {"_w.rms", "Luiel"}
  };

  int foundCount = 0;
  for (const auto& sc : slots) {
    std::string path = (rmsBase.empty() || rmsBase.back() == '/' ? rmsBase : rmsBase + "/") + sc.filename;
    std::ifstream f(path, std::ios::binary);
    if (!f) continue;

    foundCount++;
    if (!summary.empty()) summary += ", ";
    summary += sc.defaultHero;

    // Tenta ler o primeiro record para obter o nível
    uint32_t count = 0;
    if (f.read((char*)&count, 4) && count > 0) {
      uint32_t sz = 0;
      if (f.read((char*)&sz, 4) && sz >= 4) {
        std::vector<uint8_t> rec(sz);
        f.read((char*)rec.data(), sz);
        if (sz > 2) {
          int s2 = (rec[0] << 8) | rec[1];
          if (s2 > 0 && s2 <= (int)sz - 2) {
            uint8_t key[] = {5, 11, 8, 81, 3, 20};
            uint8_t decLevel = rec[2 + 1] ^ key[1 % 6];
            if (decLevel >= 1 && decLevel <= 99) {
              summary += " Nv." + std::to_string(decLevel);
            }
          }
        }
      }
    }
  }

  if (foundCount == 0) return "Nenhum save local";
  return summary;
}

// -----------------------------------------------------------------------------
// Nome da Plataforma Atual
// -----------------------------------------------------------------------------
static std::string getPlatformName() {
#if defined(__SWITCH__)
  return "Nintendo Switch";
#elif defined(__ANDROID__)
  return "Android";
#elif defined(_WIN32)
  return "Windows";
#elif defined(__linux__)
  return "Linux";
#else
  return "Outro";
#endif
}

// -----------------------------------------------------------------------------
// Inicialização e Shutdown
// -----------------------------------------------------------------------------
void CloudSave::init() {
  loadTokensFromFile();
  if (!s_refreshToken.empty()) {
    s_state = CloudSaveState::LOGGED_IN;
    // Em thread separada, checa se há backup recente no drive
    std::thread([]() {
      queryCloudBackupAsync();
    }).detach();
  } else {
    s_state = CloudSaveState::NOT_LOGGED_IN;
  }
}

void CloudSave::shutdown() {
  s_loginActive = false;
  s_modalActive = false;
}

bool CloudSave::isLoggedIn() {
  return !s_refreshToken.empty();
}

CloudSaveState CloudSave::getState() {
  return s_state;
}

std::string CloudSave::getStatusMessage() {
  return s_statusMessage;
}

const DeviceCodeInfo& CloudSave::getDeviceCodeInfo() {
  return s_deviceCode;
}

const CloudBackupInfo& CloudSave::getCloudBackupInfo() {
  return s_cloudBackup;
}

bool CloudSave::isModalActive() {
  return s_modalActive;
}

void CloudSave::openModal(VM* vm) {
  if (vm) s_activeVm = vm;
  s_modalActive = true;
  if (isLoggedIn() && !s_cloudBackup.exists) {
    queryCloudBackupAsync();
  }
}

void CloudSave::closeModal() {
  s_modalActive = false;
  if (s_state == CloudSaveState::WAITING_USER_AUTH || s_state == CloudSaveState::REQUESTING_CODE) {
    cancelLogin();
  }
}

// -----------------------------------------------------------------------------
// Autenticação OAuth 2.0 Device Flow
// -----------------------------------------------------------------------------
void CloudSave::startLogin() {
  if (s_loginActive) return;
  s_loginActive = true;
  s_state = CloudSaveState::REQUESTING_CODE;
  s_statusMessage = "Solicitando codigo de acesso ao Google...";

  std::thread([]() {
    std::string url = "https://oauth2.googleapis.com/device/code";
    std::vector<std::string> headers = {
      "Content-Type: application/x-www-form-urlencoded"
    };
    std::string body = "client_id=" + urlEncode(getGoogleClientId()) +
                       "&scope=" + urlEncode("https://www.googleapis.com/auth/drive.file");

    HttpResponse resp = httpExecute("POST", url, headers, body);
    if (resp.statusCode != 200) {
      std::lock_guard<std::mutex> lock(s_cloudMutex);
      s_state = CloudSaveState::ERROR_NOTIFICATION;
      s_statusMessage = "Erro ao conectar com Google Cloud (" + std::to_string(resp.statusCode) + ")";
      s_loginActive = false;
      return;
    }

    {
      std::lock_guard<std::mutex> lock(s_cloudMutex);
      s_deviceCode.deviceCode = extractJsonString(resp.body, "device_code");
      s_deviceCode.userCode = extractJsonString(resp.body, "user_code");
      s_deviceCode.verificationUrl = extractJsonString(resp.body, "verification_url");
      s_deviceCode.interval = extractJsonInt(resp.body, "interval", 5);
      s_deviceCode.expiresIn = extractJsonInt(resp.body, "expires_in", 1800);

      if (s_deviceCode.verificationUrl.empty()) {
        s_deviceCode.verificationUrl = "https://www.google.com/device";
      }

      s_state = CloudSaveState::WAITING_USER_AUTH;
      s_statusMessage = "Aguardando confirmacao em " + s_deviceCode.verificationUrl;
    }

    // Loop de polling pelo token
    int pollInterval = std::max(s_deviceCode.interval, 5);
    auto expireTime = std::chrono::steady_clock::now() + std::chrono::seconds(s_deviceCode.expiresIn);

    while (s_loginActive && std::chrono::steady_clock::now() < expireTime) {
      std::this_thread::sleep_for(std::chrono::seconds(pollInterval));
      if (!s_loginActive) break;

      std::string tUrl = "https://oauth2.googleapis.com/token";
      std::vector<std::string> tHeaders = {
        "Content-Type: application/x-www-form-urlencoded"
      };
      std::string tBody = "client_id=" + urlEncode(getGoogleClientId()) +
                          "&client_secret=" + urlEncode(getGoogleClientSecret()) +
                          "&device_code=" + urlEncode(s_deviceCode.deviceCode) +
                          "&grant_type=urn:ietf:params:oauth:grant-type:device_code";

      HttpResponse tResp = httpExecute("POST", tUrl, tHeaders, tBody);
      if (tResp.statusCode == 200) {
        std::string accTok = extractJsonString(tResp.body, "access_token");
        std::string refTok = extractJsonString(tResp.body, "refresh_token");
        int expIn = extractJsonInt(tResp.body, "expires_in", 3600);

        if (!accTok.empty() && !refTok.empty()) {
          std::lock_guard<std::mutex> lock(s_cloudMutex);
          s_accessToken = accTok;
          s_refreshToken = refTok;
          s_tokenExpiryEpoch = (uint64_t)std::time(nullptr) + expIn;
          saveTokensToFile();

          s_state = CloudSaveState::LOGGED_IN;
          s_statusMessage = "Conectado ao Google Drive com sucesso!";
          s_loginActive = false;
          Platform::showOsdMessage("Google Drive conectado!");
          queryCloudBackupAsync();
          return;
        }
      } else {
        std::string err = extractJsonString(tResp.body, "error");
        if (err == "authorization_pending") {
          // Usuário ainda está digitando no celular/PC
          continue;
        } else if (err == "slow_down") {
          pollInterval += 2;
          continue;
        } else {
          // Erro fatal / código expirado
          std::lock_guard<std::mutex> lock(s_cloudMutex);
          s_state = CloudSaveState::ERROR_NOTIFICATION;
          s_statusMessage = "Autorizacao cancelada ou expirada.";
          s_loginActive = false;
          return;
        }
      }
    }

    std::lock_guard<std::mutex> lock(s_cloudMutex);
    s_state = CloudSaveState::NOT_LOGGED_IN;
    s_loginActive = false;
  }).detach();
}

void CloudSave::cancelLogin() {
  s_loginActive = false;
  s_state = CloudSaveState::NOT_LOGGED_IN;
  s_statusMessage = "";
}

void CloudSave::logout() {
  s_refreshToken = "";
  s_accessToken = "";
  s_tokenExpiryEpoch = 0;
  s_cloudBackup = CloudBackupInfo{};
  std::error_code ec;
  std::filesystem::remove(getAuthFilePath(), ec);
  s_state = CloudSaveState::NOT_LOGGED_IN;
  s_statusMessage = "Conta desconectada.";
  Platform::showOsdMessage("Conta Google Drive desconectada.");
}

// -----------------------------------------------------------------------------
// Consulta de Backup no Google Drive (appDataFolder)
// -----------------------------------------------------------------------------
void CloudSave::queryCloudBackupAsync() {
  if (!isLoggedIn()) return;

  std::thread([]() {
    if (!ensureValidAccessToken()) return;

    // Busca arquivo heroes_lore_save.json
    std::string url = "https://www.googleapis.com/drive/v3/files?orderBy=modifiedTime%20desc"
                      "&fields=" + urlEncode("files(id,name,modifiedTime,size,description)") +
                      "&q=" + urlEncode("name='heroes_lore_save.json' and trashed=false");
    std::vector<std::string> headers = {
      "Authorization: Bearer " + s_accessToken
    };

    HttpResponse resp = httpExecute("GET", url, headers, "");
    if (resp.statusCode == 200) {
      std::string id = extractJsonString(resp.body, "id");
      if (!id.empty()) {
        std::lock_guard<std::mutex> lock(s_cloudMutex);
        s_cloudBackup.exists = true;
        s_cloudBackup.fileId = id;
        s_cloudBackup.modifiedTime = extractJsonString(resp.body, "modifiedTime");
        s_cloudBackup.summary = extractJsonString(resp.body, "description");
        s_cloudBackup.fileSize = (size_t)extractJsonInt(resp.body, "size", 0);

        // Formata data amigável se disponível (2026-10-08T12:00:00Z -> 2026-10-08 12:00)
        if (s_cloudBackup.modifiedTime.size() >= 16) {
          s_cloudBackup.modifiedTime = s_cloudBackup.modifiedTime.substr(0, 10) + " " +
                                       s_cloudBackup.modifiedTime.substr(11, 5);
        }
      } else {
        std::lock_guard<std::mutex> lock(s_cloudMutex);
        s_cloudBackup.exists = false;
        s_cloudBackup.fileId = "";
      }
    }
  }).detach();
}

// -----------------------------------------------------------------------------
// Upload / Fazer Backup
// -----------------------------------------------------------------------------
void CloudSave::uploadBackupAsync() {
  if (!isLoggedIn()) return;
  s_state = CloudSaveState::UPLOADING;
  s_statusMessage = "Empacotando e enviando saves para o Google Drive...";

  std::thread([]() {
    if (!ensureValidAccessToken()) {
      std::lock_guard<std::mutex> lock(s_cloudMutex);
      s_state = CloudSaveState::ERROR_NOTIFICATION;
      s_statusMessage = "Falha de autenticacao com Google Drive.";
      return;
    }

    std::string rmsBase = Platform::getRmsDir(s_activeVm);
    const char* filesToPack[] = {
      "_k.rms", "_s.rms", "_w.rms", "_o.rms", "_c.rms"
    };

    std::ostringstream jsonPkg;
    auto now = std::chrono::system_clock::now();
    std::time_t nowTime = std::chrono::system_clock::to_time_t(now);
    char dateBuf[32];
    std::strftime(dateBuf, sizeof(dateBuf), "%Y-%m-%d %H:%M", std::localtime(&nowTime));

    std::string localSummary = getLocalSavesSummary();
    std::string plat = getPlatformName();

    jsonPkg << "{\n";
    jsonPkg << "  \"app\": \"Heroes Lore: Wind of Soltia\",\n";
    jsonPkg << "  \"version\": 1,\n";
    jsonPkg << "  \"platform\": \"" << jsonEscape(plat) << "\",\n";
    jsonPkg << "  \"date\": \"" << jsonEscape(dateBuf) << "\",\n";
    jsonPkg << "  \"summary\": \"" << jsonEscape(localSummary) << "\",\n";
    jsonPkg << "  \"files\": {\n";

    bool firstFile = true;
    for (const char* fn : filesToPack) {
      std::string path = (rmsBase.empty() || rmsBase.back() == '/' ? rmsBase : rmsBase + "/") + fn;
      std::ifstream f(path, std::ios::binary);
      if (!f) continue;

      std::vector<uint8_t> data((std::istreambuf_iterator<char>(f)), std::istreambuf_iterator<char>());
      if (data.empty()) continue;

      if (!firstFile) jsonPkg << ",\n";
      firstFile = false;
      jsonPkg << "    \"" << fn << "\": \"" << base64Encode(data) << "\"";
    }
    jsonPkg << "\n  }\n}\n";

    std::string pkgStr = jsonPkg.str();

    // Envia para o Google Drive (PATCH se já existe arquivo, ou POST multipart se novo)
    HttpResponse resp;
    if (s_cloudBackup.exists && !s_cloudBackup.fileId.empty()) {
      std::string patchUrl = "https://www.googleapis.com/upload/drive/v3/files/" +
                             s_cloudBackup.fileId + "?uploadType=media";
      std::vector<std::string> headers = {
        "Authorization: Bearer " + s_accessToken,
        "Content-Type: application/json; charset=UTF-8"
      };
      resp = httpExecute("PATCH", patchUrl, headers, pkgStr);

      // Atualiza também a descrição (resumo dos slots)
      std::string metaUrl = "https://www.googleapis.com/drive/v3/files/" + s_cloudBackup.fileId;
      std::string metaBody = "{\"description\": \"" + jsonEscape(plat + " | " + localSummary) + "\"}";
      std::vector<std::string> metaHeaders = {
        "Authorization: Bearer " + s_accessToken,
        "Content-Type: application/json; charset=UTF-8"
      };
      httpExecute("PATCH", metaUrl, metaHeaders, metaBody);
    } else {
      std::string postUrl = "https://www.googleapis.com/upload/drive/v3/files?uploadType=multipart";
      std::string boundary = "HL_CLOUD_BOUNDARY_1289";
      std::string metaJson = "{\"name\": \"heroes_lore_save.json\", "
                             "\"description\": \"" + jsonEscape(plat + " | " + localSummary) + "\"}";

      std::ostringstream multipart;
      multipart << "--" << boundary << "\r\n";
      multipart << "Content-Type: application/json; charset=UTF-8\r\n\r\n";
      multipart << metaJson << "\r\n";
      multipart << "--" << boundary << "\r\n";
      multipart << "Content-Type: application/json; charset=UTF-8\r\n\r\n";
      multipart << pkgStr << "\r\n";
      multipart << "--" << boundary << "--\r\n";

      std::string mpBody = multipart.str();
      std::vector<std::string> headers = {
        "Authorization: Bearer " + s_accessToken,
        "Content-Type: multipart/related; boundary=" + boundary
      };
      resp = httpExecute("POST", postUrl, headers, mpBody);
    }

    if (resp.statusCode == 200) {
      std::string newId = extractJsonString(resp.body, "id");
      {
        std::lock_guard<std::mutex> lock(s_cloudMutex);
        if (!newId.empty()) {
          s_cloudBackup.fileId = newId;
          s_cloudBackup.exists = true;
        }
        s_state = CloudSaveState::SUCCESS_NOTIFICATION;
        s_statusMessage = "Backup enviado para o Google Drive com sucesso!";
      }
      Platform::showOsdMessage("Backup na nuvem realizado com sucesso!");
      queryCloudBackupAsync();
    } else {
      std::lock_guard<std::mutex> lock(s_cloudMutex);
      s_state = CloudSaveState::ERROR_NOTIFICATION;
      s_statusMessage = "Falha ao enviar backup (" + std::to_string(resp.statusCode) + ")";
      Platform::showOsdMessage("Erro ao enviar backup para a nuvem.");
    }
  }).detach();
}

// -----------------------------------------------------------------------------
// Download e Restauração Inteligente
// -----------------------------------------------------------------------------
void CloudSave::requestRestore() {
  if (!s_cloudBackup.exists) {
    s_statusMessage = "Nenhum backup encontrado na nuvem para restaurar.";
    return;
  }
  s_state = CloudSaveState::RESTORE_CONFIRM;
}

void CloudSave::cancelRestore() {
  s_state = CloudSaveState::LOGGED_IN;
}

void CloudSave::confirmRestore(VM* vm) {
  s_state = CloudSaveState::DOWNLOADING;
  s_statusMessage = "Baixando save da nuvem e restaurando arquivos...";

  std::thread([vm]() {
    if (!ensureValidAccessToken()) {
      std::lock_guard<std::mutex> lock(s_cloudMutex);
      s_state = CloudSaveState::ERROR_NOTIFICATION;
      s_statusMessage = "Erro de autenticacao.";
      return;
    }

    std::string getUrl = "https://www.googleapis.com/drive/v3/files/" +
                         s_cloudBackup.fileId + "?alt=media";
    std::vector<std::string> headers = {
      "Authorization: Bearer " + s_accessToken
    };

    HttpResponse resp = httpExecute("GET", getUrl, headers, "");
    if (resp.statusCode != 200 || resp.body.empty()) {
      std::lock_guard<std::mutex> lock(s_cloudMutex);
      s_state = CloudSaveState::ERROR_NOTIFICATION;
      s_statusMessage = "Falha ao baixar backup da nuvem (" + std::to_string(resp.statusCode) + ")";
      Platform::showOsdMessage("Erro ao baixar save da nuvem.");
      return;
    }

    // Grava arquivos .rms descompactados
    std::string rmsBase = Platform::getRmsDir(vm ? vm : s_activeVm);
    std::error_code ec;
    std::filesystem::create_directories(rmsBase, ec);

    const char* expectedFiles[] = {
      "_k.rms", "_s.rms", "_w.rms", "_o.rms", "_c.rms"
    };

    int restoredCount = 0;
    for (const char* fn : expectedFiles) {
      std::string b64 = extractJsonString(resp.body, fn);
      if (b64.empty()) continue;

      std::vector<uint8_t> data = base64Decode(b64);
      if (data.empty()) continue;

      std::string outPath = (rmsBase.empty() || rmsBase.back() == '/' ? rmsBase : rmsBase + "/") + fn;
      std::ofstream f(outPath, std::ios::binary | std::ios::trunc);
      if (f) {
        f.write((const char*)data.data(), data.size());
        restoredCount++;
      }
    }

    if (restoredCount == 0) {
      std::lock_guard<std::mutex> lock(s_cloudMutex);
      s_state = CloudSaveState::ERROR_NOTIFICATION;
      s_statusMessage = "O arquivo baixado nao continha dados validos.";
      return;
    }

    // -------------------------------------------------------------------------
    // Recarregamento Inteligente e Seguro sem Corromper Memória
    // -------------------------------------------------------------------------
    if (vm) {
      vm->gilLock();
      try {
        ClassInfo* nClass = vm->findClass("n");
        FieldInfo* fAoA = nClass ? vm->findField(nClass, "a:Lao;") : nullptr;
        bool inGame = false;

        // Se n.var_ao_a != null, há herói instanciado em mapa ativo
        if (fAoA && fAoA->isStatic && fAoA->index >= 0 && fAoA->index < (int)nClass->statics.size()) {
          inGame = (nClass->statics[fAoA->index].o != nullptr);
        }

        if (inGame) {
          // Em partida: aciona retorno limpo e canônico ao menu inicial (bu.d() -> Main Menu)
          ClassInfo* buClass = vm->findClass("bu");
          Method* mBuD = buClass ? vm->findMethod(buClass, "d:()V") : nullptr;
          if (mBuD) {
            Value ret[2];
            vm->invoke(mBuD, nullptr, ret);
          }
        } else {
          // Na tela de título: atualiza o scan dos slots e habilita o botão Carregar (n.p())
          Method* mNp = nClass ? vm->findMethod(nClass, "p:()V") : nullptr;
          if (mNp) {
            Value ret[2];
            vm->invoke(mNp, nullptr, ret);
          }
        }
      } catch (...) {}
      vm->gilUnlock();
    }

    {
      std::lock_guard<std::mutex> lock(s_cloudMutex);
      s_state = CloudSaveState::SUCCESS_NOTIFICATION;
      s_statusMessage = "Save restaurado com sucesso (" + std::to_string(restoredCount) + " arquivos)!";
      Platform::showOsdMessage("Save restaurado da nuvem!");
    }
  }).detach();
}

// -----------------------------------------------------------------------------
// Manipulação de Entrada no Modal
// -----------------------------------------------------------------------------
bool CloudSave::handleInput(int key, VM* vm) {
  if (vm) s_activeVm = vm;
  if (!s_modalActive) return false;

  // Tecla Cancelar / Voltar (B / ESC / RSK)
  if (key == -7 || key == -8 || key == 27) {
    if (s_state == CloudSaveState::RESTORE_CONFIRM) {
      cancelRestore();
    } else {
      closeModal();
    }
    return true;
  }

  // Tecla Ação / Confirmar (5 / Enter / A)
  if (key == 53 || key == 13 || key == 32) {
    if (s_state == CloudSaveState::NOT_LOGGED_IN || s_state == CloudSaveState::ERROR_NOTIFICATION) {
      startLogin();
      return true;
    } else if (s_state == CloudSaveState::LOGGED_IN || s_state == CloudSaveState::SUCCESS_NOTIFICATION) {
      uploadBackupAsync();
      return true;
    } else if (s_state == CloudSaveState::RESTORE_CONFIRM) {
      confirmRestore(vm);
      return true;
    }
  }

  // Tecla 1 (Backup) / Tecla 2 (Restaurar) / Tecla 3 (Desconectar)
  if (key == 49 || key == '1') { // 1
    if (isLoggedIn()) uploadBackupAsync();
    else startLogin();
    return true;
  } else if (key == 50 || key == '2') { // 2 / X
    if (isLoggedIn()) requestRestore();
    return true;
  } else if (key == 51 || key == '3') { // 3 / Y
    if (isLoggedIn()) logout();
    return true;
  }

  return true;
}

void CloudSave::handleClick(int x, int y, VM* vm) {
  if (vm) s_activeVm = vm;
  if (!s_modalActive) return;

  auto inRect = [](int px, int py, const SDL_Rect& r) {
    return px >= r.x && px <= r.x + r.w && py >= r.y && py <= r.y + r.h;
  };

  if (inRect(x, y, s_btnCancel)) {
    if (s_state == CloudSaveState::RESTORE_CONFIRM) cancelRestore();
    else closeModal();
    return;
  }

  if (inRect(x, y, s_btnAction1)) {
    if (s_state == CloudSaveState::NOT_LOGGED_IN || s_state == CloudSaveState::ERROR_NOTIFICATION) {
      startLogin();
    } else if (s_state == CloudSaveState::RESTORE_CONFIRM) {
      confirmRestore(vm);
    } else if (isLoggedIn()) {
      uploadBackupAsync();
    }
    return;
  }

  if (inRect(x, y, s_btnAction2)) {
    if (isLoggedIn() && s_state != CloudSaveState::RESTORE_CONFIRM) {
      requestRestore();
    }
    return;
  }

  if (inRect(x, y, s_btnAction3)) {
    if (isLoggedIn()) {
      logout();
    }
    return;
  }
}

// -----------------------------------------------------------------------------
// Renderização Gráfica Nobre (Soltia Theme)
// -----------------------------------------------------------------------------
void CloudSave::drawModal(SDL_Renderer* renderer, int winW, int winH) {
  if (!s_modalActive || !renderer) return;

  SDL_SetRenderDrawBlendMode(renderer, SDL_BLENDMODE_BLEND);

  // 1. Fundo Escurecido (Backdrop)
  SDL_Rect fullScreen = { 0, 0, winW, winH };
  SDL_SetRenderDrawColor(renderer, 0, 0, 0, 220);
  SDL_RenderFillRect(renderer, &fullScreen);

  // 2. Caixa Modal Proporcional
  bool isPortrait = (winH > winW);
  int modalW = isPortrait ? std::clamp((int)(winW * 0.94f), 280, 1100)
                          : std::clamp((int)(winW * 0.70f), 380, 960);
  int modalH = isPortrait ? std::clamp((int)(modalW * 0.95f), 340, (int)(winH * 0.85f))
                          : std::clamp((int)(winH * 0.80f), 320, 680);

  int modalX = (winW - modalW) / 2;
  int modalY = (winH - modalH) / 2;
  s_modalRect = { modalX, modalY, modalW, modalH };

  // Sombra e Fundo Ardósia Nobre
  SDL_Rect shadow = { modalX + 6, modalY + 6, modalW, modalH };
  SDL_SetRenderDrawColor(renderer, 0, 0, 0, 180);
  SDL_RenderFillRect(renderer, &shadow);

  SDL_Rect box = { modalX, modalY, modalW, modalH };
  SDL_SetRenderDrawColor(renderer, 14, 18, 26, 252);
  SDL_RenderFillRect(renderer, &box);

  // Moldura Ouro Nobre e Cantos
  SDL_SetRenderDrawColor(renderer, 55, 75, 100, 255);
  SDL_RenderDrawRect(renderer, &box);

  int borderPad = 6;
  SDL_Rect goldBorder = { modalX + borderPad, modalY + borderPad, modalW - borderPad * 2, modalH - borderPad * 2 };
  SDL_SetRenderDrawColor(renderer, 195, 155, 60, 255);
  SDL_RenderDrawRect(renderer, &goldBorder);

  // Rebites de Bronze
  int rSize = 6;
  SDL_SetRenderDrawColor(renderer, 225, 185, 80, 255);
  SDL_Rect r1 = { modalX + 10, modalY + 10, rSize, rSize };
  SDL_Rect r2 = { modalX + modalW - 10 - rSize, modalY + 10, rSize, rSize };
  SDL_Rect r3 = { modalX + 10, modalY + modalH - 10 - rSize, rSize, rSize };
  SDL_Rect r4 = { modalX + modalW - 10 - rSize, modalY + modalH - 10 - rSize, rSize, rSize };
  SDL_RenderFillRect(renderer, &r1);
  SDL_RenderFillRect(renderer, &r2);
  SDL_RenderFillRect(renderer, &r3);
  SDL_RenderFillRect(renderer, &r4);

  // Cabeçalho
  int headerH = std::clamp((int)(modalH * 0.11f), 32, 54);
  SDL_Rect headerBox = { modalX + 8, modalY + 8, modalW - 16, headerH };
  SDL_SetRenderDrawColor(renderer, 22, 28, 40, 255);
  SDL_RenderFillRect(renderer, &headerBox);
  SDL_SetRenderDrawColor(renderer, 195, 155, 60, 200);
  SDL_RenderDrawLine(renderer, headerBox.x, headerBox.y + headerH, headerBox.x + headerBox.w, headerBox.y + headerH);

  int charH = std::clamp((int)(modalH * 0.050f), 15, 28);
  int charW = (int)(charH * 0.65f);
  int stepX = (int)(charW * 0.68f);

  int smallH = std::max(13, (int)(charH * 0.80f));
  int smallW = (int)(smallH * 0.65f);
  int smallStep = (int)(smallW * 0.68f);

  std::string title = "GOOGLE DRIVE - CLOUD SAVE";
  int tw = Platform::getTextWidth(title, charW, stepX);
  Platform::drawText(renderer, title, modalX + (modalW - tw) / 2, modalY + (headerH - charH) / 2 + 8, charW, charH, 255, stepX);

  int curY = modalY + headerH + 16;

  // Botões de Ação na parte inferior
  int btnH = std::clamp((int)(modalH * 0.10f), 32, 50);
  int btnMargin = 12;

  // ---------------------------------------------------------------------------
  // 1. Estado: NÃO AUTENTICADO
  // ---------------------------------------------------------------------------
  if (s_state == CloudSaveState::NOT_LOGGED_IN || s_state == CloudSaveState::REQUESTING_CODE || s_state == CloudSaveState::ERROR_NOTIFICATION) {
    std::string line1 = "Sincronize seus dados de jogo entre";
    std::string line2 = "PC, Android e Nintendo Switch!";
    int l1w = Platform::getTextWidth(line1, smallW, smallStep);
    int l2w = Platform::getTextWidth(line2, smallW, smallStep);
    Platform::drawText(renderer, line1, modalX + (modalW - l1w) / 2, curY, smallW, smallH, 200, smallStep);
    curY += smallH + 6;
    Platform::drawText(renderer, line2, modalX + (modalW - l2w) / 2, curY, smallW, smallH, 200, smallStep);
    curY += smallH + 20;

    // Cartão Informativo
    SDL_Rect infoBox = { modalX + 24, curY, modalW - 48, std::clamp((int)(modalH * 0.30f), 70, 120) };
    SDL_SetRenderDrawColor(renderer, 18, 24, 34, 255);
    SDL_RenderFillRect(renderer, &infoBox);
    SDL_SetRenderDrawColor(renderer, 50, 70, 95, 255);
    SDL_RenderDrawRect(renderer, &infoBox);

    std::string localSum = "Save local: " + getLocalSavesSummary();
    Platform::drawText(renderer, localSum, infoBox.x + 12, infoBox.y + 14, smallW, smallH, 255, smallStep);

    std::string statusStr = s_statusMessage.empty() ? "Status: Desconectado" : s_statusMessage;
    Platform::drawText(renderer, statusStr, infoBox.x + 12, infoBox.y + 14 + smallH + 10, smallW, smallH,
                       (s_state == CloudSaveState::ERROR_NOTIFICATION ? 255 : 180), smallStep);

    // Botões
    int btnW = (modalW - 48 - btnMargin) / 2;
    int by = modalY + modalH - btnH - 16;
    s_btnAction1 = { modalX + 24, by, btnW, btnH };
    s_btnCancel  = { modalX + 24 + btnW + btnMargin, by, btnW, btnH };

    // Botão Conectar
    SDL_SetRenderDrawColor(renderer, 35, 95, 45, 255);
    SDL_RenderFillRect(renderer, &s_btnAction1);
    SDL_SetRenderDrawColor(renderer, 80, 200, 100, 255);
    SDL_RenderDrawRect(renderer, &s_btnAction1);
    std::string b1Text = "[ 1 / A ]: CONECTAR";
    int b1tw = Platform::getTextWidth(b1Text, smallW, smallStep);
    Platform::drawText(renderer, b1Text, s_btnAction1.x + (btnW - b1tw) / 2, s_btnAction1.y + (btnH - smallH) / 2, smallW, smallH, 255, smallStep);

    // Botão Fechar
    SDL_SetRenderDrawColor(renderer, 45, 45, 55, 255);
    SDL_RenderFillRect(renderer, &s_btnCancel);
    SDL_SetRenderDrawColor(renderer, 100, 100, 120, 255);
    SDL_RenderDrawRect(renderer, &s_btnCancel);
    std::string bcText = "[ B / ESC ]: FECHAR";
    int bctw = Platform::getTextWidth(bcText, smallW, smallStep);
    Platform::drawText(renderer, bcText, s_btnCancel.x + (btnW - bctw) / 2, s_btnCancel.y + (btnH - smallH) / 2, smallW, smallH, 200, smallStep);
  }

  // ---------------------------------------------------------------------------
  // 2. Estado: AGUARDANDO AUTORIZAÇÃO DO USUÁRIO (CÓDIGO NA TELA)
  // ---------------------------------------------------------------------------
  else if (s_state == CloudSaveState::WAITING_USER_AUTH) {
    std::string step1 = "1. No celular ou PC, acesse o link:";
    int s1w = Platform::getTextWidth(step1, smallW, smallStep);
    Platform::drawText(renderer, step1, modalX + (modalW - s1w) / 2, curY, smallW, smallH, 220, smallStep);
    curY += smallH + 8;

    std::string urlText = s_deviceCode.verificationUrl;
    int uw = Platform::getTextWidth(urlText, charW, stepX);
    Platform::drawText(renderer, urlText, modalX + (modalW - uw) / 2, curY, charW, charH, 255, stepX);
    curY += charH + 16;

    std::string step2 = "2. Digite este codigo de autorizacao:";
    int s2w = Platform::getTextWidth(step2, smallW, smallStep);
    Platform::drawText(renderer, step2, modalX + (modalW - s2w) / 2, curY, smallW, smallH, 220, smallStep);
    curY += smallH + 8;

    // Caixa de Destaque com o Código
    int codeBoxW = std::clamp((int)(modalW * 0.65f), 200, 420);
    int codeBoxH = std::clamp((int)(modalH * 0.14f), 40, 70);
    SDL_Rect codeBox = { modalX + (modalW - codeBoxW) / 2, curY, codeBoxW, codeBoxH };
    SDL_SetRenderDrawColor(renderer, 24, 38, 54, 255);
    SDL_RenderFillRect(renderer, &codeBox);
    SDL_SetRenderDrawColor(renderer, 220, 180, 70, 255);
    SDL_RenderDrawRect(renderer, &codeBox);

    int codeH = std::clamp((int)(codeBoxH * 0.55f), 18, 36);
    int codeW = (int)(codeH * 0.65f);
    int codeStep = (int)(codeW * 0.68f);
    int cw = Platform::getTextWidth(s_deviceCode.userCode, codeW, codeStep);
    Platform::drawText(renderer, s_deviceCode.userCode, codeBox.x + (codeBoxW - cw) / 2,
                       codeBox.y + (codeBoxH - codeH) / 2, codeW, codeH, 255, codeStep);
    curY += codeBoxH + 14;

    std::string waitMsg = "Aguardando confirmacao no navegador...";
    int ww = Platform::getTextWidth(waitMsg, smallW, smallStep);
    Platform::drawText(renderer, waitMsg, modalX + (modalW - ww) / 2, curY, smallW, smallH, 180, smallStep);

    // Botão Cancelar
    int btnW = std::clamp((int)(modalW * 0.50f), 160, 320);
    int by = modalY + modalH - btnH - 16;
    s_btnCancel = { modalX + (modalW - btnW) / 2, by, btnW, btnH };
    SDL_SetRenderDrawColor(renderer, 50, 40, 40, 255);
    SDL_RenderFillRect(renderer, &s_btnCancel);
    SDL_SetRenderDrawColor(renderer, 140, 70, 70, 255);
    SDL_RenderDrawRect(renderer, &s_btnCancel);
    std::string bcText = "[ B / ESC ]: CANCELAR";
    int bctw = Platform::getTextWidth(bcText, smallW, smallStep);
    Platform::drawText(renderer, bcText, s_btnCancel.x + (btnW - bctw) / 2, s_btnCancel.y + (btnH - smallH) / 2, smallW, smallH, 220, smallStep);
  }

  // ---------------------------------------------------------------------------
  // 3. Estado: CONFIRMAÇÃO DE RESTAURAÇÃO
  // ---------------------------------------------------------------------------
  else if (s_state == CloudSaveState::RESTORE_CONFIRM) {
    int warnBoxW = modalW - 48;
    int warnBoxH = std::clamp((int)(modalH * 0.40f), 110, 180);
    SDL_Rect warnBox = { modalX + 24, curY, warnBoxW, warnBoxH };
    SDL_SetRenderDrawColor(renderer, 45, 28, 14, 255);
    SDL_RenderFillRect(renderer, &warnBox);
    SDL_SetRenderDrawColor(renderer, 220, 140, 40, 255);
    SDL_RenderDrawRect(renderer, &warnBox);

    std::string wTitle = "ATENCAO: RESTAURAR SAVE DA NUVEM";
    Platform::drawText(renderer, wTitle, warnBox.x + 14, warnBox.y + 14, smallW, smallH, 255, smallStep);

    std::string w1 = "A restauracao substituira todos os saves locais";
    std::string w2 = "pelos dados salvos na sua nuvem Google Drive.";
    std::string w3 = "Se estiver em partida, o jogo sera reiniciado.";
    Platform::drawText(renderer, w1, warnBox.x + 14, warnBox.y + 14 + smallH + 8, smallW, smallH, 220, smallStep);
    Platform::drawText(renderer, w2, warnBox.x + 14, warnBox.y + 14 + (smallH + 8) * 2, smallW, smallH, 220, smallStep);
    Platform::drawText(renderer, w3, warnBox.x + 14, warnBox.y + 14 + (smallH + 8) * 3, smallW, smallH, 255, smallStep);

    // Botões
    int btnW = (modalW - 48 - btnMargin) / 2;
    int by = modalY + modalH - btnH - 16;
    s_btnAction1 = { modalX + 24, by, btnW, btnH };
    s_btnCancel  = { modalX + 24 + btnW + btnMargin, by, btnW, btnH };

    SDL_SetRenderDrawColor(renderer, 140, 50, 40, 255);
    SDL_RenderFillRect(renderer, &s_btnAction1);
    SDL_SetRenderDrawColor(renderer, 230, 80, 70, 255);
    SDL_RenderDrawRect(renderer, &s_btnAction1);
    std::string b1Text = "[ 1 / A ]: CONFIRMAR";
    int b1tw = Platform::getTextWidth(b1Text, smallW, smallStep);
    Platform::drawText(renderer, b1Text, s_btnAction1.x + (btnW - b1tw) / 2, s_btnAction1.y + (btnH - smallH) / 2, smallW, smallH, 255, smallStep);

    SDL_SetRenderDrawColor(renderer, 45, 45, 55, 255);
    SDL_RenderFillRect(renderer, &s_btnCancel);
    SDL_SetRenderDrawColor(renderer, 100, 100, 120, 255);
    SDL_RenderDrawRect(renderer, &s_btnCancel);
    std::string bcText = "[ B / ESC ]: CANCELAR";
    int bctw = Platform::getTextWidth(bcText, smallW, smallStep);
    Platform::drawText(renderer, bcText, s_btnCancel.x + (btnW - bctw) / 2, s_btnCancel.y + (btnH - smallH) / 2, smallW, smallH, 200, smallStep);
  }

  // ---------------------------------------------------------------------------
  // 4. Estado: AUTENTICADO / OPERAÇÕES DE BACKUP E RESTORE
  // ---------------------------------------------------------------------------
  else {
    // Badge de Conexão
    std::string connStr = "STATUS: CONECTADO AO GOOGLE DRIVE";
    int cw = Platform::getTextWidth(connStr, smallW, smallStep);
    Platform::drawText(renderer, connStr, modalX + (modalW - cw) / 2, curY, smallW, smallH, 180, smallStep);
    curY += smallH + 12;

    // Cartão 1: Nuvem
    int cardH = std::clamp((int)(modalH * 0.22f), 55, 85);
    SDL_Rect cloudBox = { modalX + 24, curY, modalW - 48, cardH };
    SDL_SetRenderDrawColor(renderer, 20, 28, 42, 255);
    SDL_RenderFillRect(renderer, &cloudBox);
    SDL_SetRenderDrawColor(renderer, 60, 90, 130, 255);
    SDL_RenderDrawRect(renderer, &cloudBox);

    std::string cTitle = "NUVEM (Google Drive):";
    Platform::drawText(renderer, cTitle, cloudBox.x + 12, cloudBox.y + 10, smallW, smallH, 255, smallStep);

    std::string cDetails = s_cloudBackup.exists ? ("Data: " + s_cloudBackup.modifiedTime + " | " + s_cloudBackup.summary)
                                               : "Nenhum backup encontrado na nuvem.";
    Platform::drawText(renderer, cDetails, cloudBox.x + 12, cloudBox.y + 10 + smallH + 6, smallW, smallH, 200, smallStep);
    curY += cardH + 10;

    // Cartão 2: Local
    SDL_Rect localBox = { modalX + 24, curY, modalW - 48, cardH };
    SDL_SetRenderDrawColor(renderer, 20, 28, 42, 255);
    SDL_RenderFillRect(renderer, &localBox);
    SDL_SetRenderDrawColor(renderer, 60, 90, 130, 255);
    SDL_RenderDrawRect(renderer, &localBox);

    std::string lTitle = "LOCAL (" + getPlatformName() + "):";
    Platform::drawText(renderer, lTitle, localBox.x + 12, localBox.y + 10, smallW, smallH, 255, smallStep);
    std::string lDetails = getLocalSavesSummary();
    Platform::drawText(renderer, lDetails, localBox.x + 12, localBox.y + 10 + smallH + 6, smallW, smallH, 200, smallStep);
    curY += cardH + 12;

    if (!s_statusMessage.empty()) {
      int sw = Platform::getTextWidth(s_statusMessage, smallW, smallStep);
      Platform::drawText(renderer, s_statusMessage, modalX + (modalW - sw) / 2, curY, smallW, smallH, 255, smallStep);
    }

    // Grid de Botões Inferiores (4 botões: Backup, Restaurar, Desconectar, Fechar)
    int by = modalY + modalH - btnH * 2 - 24;
    int btnW = (modalW - 48 - btnMargin) / 2;

    s_btnAction1 = { modalX + 24, by, btnW, btnH };
    s_btnAction2 = { modalX + 24 + btnW + btnMargin, by, btnW, btnH };
    s_btnAction3 = { modalX + 24, by + btnH + 8, btnW, btnH };
    s_btnCancel  = { modalX + 24 + btnW + btnMargin, by + btnH + 8, btnW, btnH };

    // Botão 1: Backup (Enviar)
    SDL_SetRenderDrawColor(renderer, 28, 75, 40, 255);
    SDL_RenderFillRect(renderer, &s_btnAction1);
    SDL_SetRenderDrawColor(renderer, 65, 175, 95, 255);
    SDL_RenderDrawRect(renderer, &s_btnAction1);
    std::string b1 = "[ 1 / A ]: ENVIAR BACKUP";
    int b1w = Platform::getTextWidth(b1, smallW, smallStep);
    Platform::drawText(renderer, b1, s_btnAction1.x + (btnW - b1w) / 2, s_btnAction1.y + (btnH - smallH) / 2, smallW, smallH, 255, smallStep);

    // Botão 2: Restaurar (Baixar)
    SDL_SetRenderDrawColor(renderer, 24, 55, 90, 255);
    SDL_RenderFillRect(renderer, &s_btnAction2);
    SDL_SetRenderDrawColor(renderer, 65, 130, 210, 255);
    SDL_RenderDrawRect(renderer, &s_btnAction2);
    std::string b2 = "[ 2 / X ]: RESTAURAR";
    int b2w = Platform::getTextWidth(b2, smallW, smallStep);
    Platform::drawText(renderer, b2, s_btnAction2.x + (btnW - b2w) / 2, s_btnAction2.y + (btnH - smallH) / 2, smallW, smallH, 255, smallStep);

    // Botão 3: Desconectar
    SDL_SetRenderDrawColor(renderer, 55, 30, 30, 255);
    SDL_RenderFillRect(renderer, &s_btnAction3);
    SDL_SetRenderDrawColor(renderer, 130, 60, 60, 255);
    SDL_RenderDrawRect(renderer, &s_btnAction3);
    std::string b3 = "[ 3 / Y ]: DESCONECTAR";
    int b3w = Platform::getTextWidth(b3, smallW, smallStep);
    Platform::drawText(renderer, b3, s_btnAction3.x + (btnW - b3w) / 2, s_btnAction3.y + (btnH - smallH) / 2, smallW, smallH, 220, smallStep);

    // Botão 4: Fechar
    SDL_SetRenderDrawColor(renderer, 40, 45, 55, 255);
    SDL_RenderFillRect(renderer, &s_btnCancel);
    SDL_SetRenderDrawColor(renderer, 90, 100, 120, 255);
    SDL_RenderDrawRect(renderer, &s_btnCancel);
    std::string bc = "[ B / ESC ]: FECHAR";
    int bcw = Platform::getTextWidth(bc, smallW, smallStep);
    Platform::drawText(renderer, bc, s_btnCancel.x + (btnW - bcw) / 2, s_btnCancel.y + (btnH - smallH) / 2, smallW, smallH, 200, smallStep);
  }
}

} // namespace hl
