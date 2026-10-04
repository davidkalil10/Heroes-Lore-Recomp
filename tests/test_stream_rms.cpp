#include "vm/vm.h"
#include "midp/midp.h"
#include <cstdio>
#include <cassert>

using namespace hl;

int main() {
  printf("[Test] Iniciando teste de DataOutputStream / ByteArrayOutputStream / RMS...\n");

  VM vm;
  vm.init("reference/extracted");

  ThreadCtx mainCtx;
  mainCtx.id = vm.nextTid++;
  tctx = &mainCtx;
  vm.threads.push_back(&mainCtx);

  // 1. Testa criação de ByteArrayOutputStream e DataOutputStream
  ClassInfo* cBaos = vm.mustClass("java/io/ByteArrayOutputStream");
  ClassInfo* cDos = vm.mustClass("java/io/DataOutputStream");
  ClassInfo* cDis = vm.mustClass("java/io/DataInputStream");
  ClassInfo* cBais = vm.mustClass("java/io/ByteArrayInputStream");

  Object* baos = vm.newObject(cBaos);
  Value args[2];
  args[0].o = baos;
  Value ret[2];
  vm.invoke(vm.mustMethod(cBaos, "<init>:()V"), args, ret);

  Object* dos = vm.newObject(cDos);
  args[0].o = dos;
  args[1].o = baos;
  vm.invoke(vm.mustMethod(cDos, "<init>:(Ljava/io/OutputStream;)V"), args, ret);

  // 2. Escreve dados primitivos via DataOutputStream
  args[0].o = dos;
  args[1].i = 0x12345678;
  vm.invoke(vm.mustMethod(cDos, "writeInt:(I)V"), args, ret);

  args[0].o = dos;
  args[1].i = 0x55AA;
  vm.invoke(vm.mustMethod(cDos, "writeShort:(I)V"), args, ret);

  args[0].o = dos;
  args[1].i = 0x7F;
  vm.invoke(vm.mustMethod(cDos, "writeByte:(I)V"), args, ret);

  // 3. O CASO CRÍTICO QUE CAUSAVA O CRASH: invokevirtual OutputStream.write([B)V em um DataOutputStream!
  Array* dummyBuf = vm.newArray('B', 4);
  dummyBuf->data[0] = 0xDE;
  dummyBuf->data[1] = 0xAD;
  dummyBuf->data[2] = 0xBE;
  dummyBuf->data[3] = 0xEF;

  args[0].o = dos; // NOTA: args[0].o é o DataOutputStream (Dos*)!
  args[1].o = dummyBuf;
  ClassInfo* cOs = vm.mustClass("java/io/OutputStream");
  vm.invoke(vm.mustMethod(cOs, "write:([B)V"), args, ret);

  // 4. Converte para byte array
  args[0].o = baos;
  vm.invoke(vm.mustMethod(cBaos, "toByteArray:()[B"), args, ret);
  Array* outArr = static_cast<Array*>(ret[0].o);
  assert(outArr != nullptr);
  printf("[Test] Bytes gerados pelo stream: %d (esperado: 11)\n", outArr->len);
  assert(outArr->len == 11);

  // 5. Lê de volta usando DataInputStream
  Object* bais = vm.newObject(cBais);
  args[0].o = bais;
  args[1].o = outArr;
  vm.invoke(vm.mustMethod(cBais, "<init>:([B)V"), args, ret);

  Object* dis = vm.newObject(cDis);
  args[0].o = dis;
  args[1].o = bais;
  vm.invoke(vm.mustMethod(cDis, "<init>:(Ljava/io/InputStream;)V"), args, ret);

  args[0].o = dis;
  vm.invoke(vm.mustMethod(cDis, "readInt:()I"), args, ret);
  int vInt = ret[0].i;
  printf("[Test] ReadInt: 0x%08X (esperado: 0x12345678)\n", vInt);
  assert(vInt == 0x12345678);

  args[0].o = dis;
  vm.invoke(vm.mustMethod(cDis, "readShort:()S"), args, ret);
  int vShort = ret[0].i;
  printf("[Test] ReadShort: 0x%04X (esperado: 0x55AA)\n", (uint16_t)vShort);
  assert((uint16_t)vShort == 0x55AA);

  args[0].o = dis;
  vm.invoke(vm.mustMethod(cDis, "readByte:()B"), args, ret);
  int vByte = ret[0].i;
  printf("[Test] ReadByte: 0x%02X (esperado: 0x7F)\n", (uint8_t)vByte);
  assert((uint8_t)vByte == 0x7F);

  Array* readBuf = vm.newArray('B', 4);
  args[0].o = dis;
  args[1].o = readBuf;
  vm.invoke(vm.mustMethod(cDis, "readFully:([B)V"), args, ret);
  printf("[Test] ReadFully: %02X %02X %02X %02X (esperado: DE AD BE EF)\n",
         (uint8_t)readBuf->data[0], (uint8_t)readBuf->data[1], (uint8_t)readBuf->data[2], (uint8_t)readBuf->data[3]);
  assert((uint8_t)readBuf->data[0] == 0xDE);
  assert((uint8_t)readBuf->data[1] == 0xAD);
  assert((uint8_t)readBuf->data[2] == 0xBE);
  assert((uint8_t)readBuf->data[3] == 0xEF);

  // 6. Testa salvar no RecordStore
  ClassInfo* cRs = vm.mustClass("javax/microedition/rms/RecordStore");
  Value rsArgs[5];
  rsArgs[0].o = vm.newStrUtf8("test_save");
  rsArgs[1].i = 1; // createIfNecessary = true
  vm.invoke(vm.mustMethod(cRs, "openRecordStore:(Ljava/lang/String;Z)Ljavax/microedition/rms/RecordStore;"), rsArgs, ret);
  Object* rsObj = ret[0].o;
  assert(rsObj != nullptr);

  rsArgs[0].o = rsObj;
  rsArgs[1].o = outArr;
  rsArgs[2].i = 0;
  rsArgs[3].i = outArr->len;
  vm.invoke(vm.mustMethod(cRs, "addRecord:([BII)I"), rsArgs, ret);
  int recId = ret[0].i;
  printf("[Test] RecordStore addRecord ret=%d\n", recId);
  assert(recId == 1);

  rsArgs[0].o = rsObj;
  vm.invoke(vm.mustMethod(cRs, "closeRecordStore:()V"), rsArgs, ret);

  // 7. Reabre e confere dados do RecordStore
  rsArgs[0].o = vm.newStrUtf8("test_save");
  rsArgs[1].i = 0; // createIfNecessary = false
  vm.invoke(vm.mustMethod(cRs, "openRecordStore:(Ljava/lang/String;Z)Ljavax/microedition/rms/RecordStore;"), rsArgs, ret);
  rsObj = ret[0].o;
  assert(rsObj != nullptr);

  rsArgs[0].o = rsObj;
  rsArgs[1].i = 1;
  vm.invoke(vm.mustMethod(cRs, "getRecord:(I)[B"), rsArgs, ret);
  Array* loadedArr = static_cast<Array*>(ret[0].o);
  assert(loadedArr != nullptr && loadedArr->len == 11);
  assert(std::memcmp(loadedArr->data.data(), outArr->data.data(), 11) == 0);
  printf("[Test] Dados carregados do RMS conferem perfeitamente com os salvos!\n");

  rsArgs[0].o = rsObj;
  vm.invoke(vm.mustMethod(cRs, "closeRecordStore:()V"), rsArgs, ret);

  // Deleta o registro de teste
  rsArgs[0].o = vm.newStrUtf8("test_save");
  vm.invoke(vm.mustMethod(cRs, "deleteRecordStore:(Ljava/lang/String;)V"), rsArgs, ret);
  printf("[Test] RecordStore excluído com sucesso.\n");

  printf("\n>>> TODOS OS TESTES PASSARAM COM SUCESSO! <<<\n");
  return 0;
}
