#include <pthread.h>
#include <jni.h>
#include <string>
#include "backend/Tools.h"
#include "backend/openssl/md5.h"


std::string CalcMD5(std::string s) {
    std::string result;

    unsigned char hash[MD5_DIGEST_LENGTH];
    char tmp[4];
    MD5_CTX md5;
    MD5_Init(&md5);
    MD5_Update(&md5, s.c_str(), s.length());
    MD5_Final(hash, &md5);
    for (unsigned char i : hash) {
        sprintf(tmp, "%02x", i);
        result += tmp;
    }
    return result;
}



extern "C"
JNIEXPORT jstring JNICALL
Java_com_alex_mmop_api_alexapi_getmd5fromc(JNIEnv *env, jobject thiz, jstring input) {
    const char *inputStr = env->GetStringUTFChars(input, nullptr);
    std::string md5Result = CalcMD5(inputStr);
    env->ReleaseStringUTFChars(input, inputStr);
    LOGE(inputStr);
    LOGE(md5Result.c_str());
    return env->NewStringUTF(md5Result.c_str());
}

/*
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *globalEnv;
    vm->GetEnv((void **) &globalEnv, JNI_VERSION_1_6);
  */
/*  pthread_t t;
    pthread_create(&t, 0, hook, 0);*//*


    //  return funtion_name(arguments); // impliment the funtion here which one you want to call after hooks are loaded

}
JNIEXPORT void JNICALL JNI_OnUnload(JavaVM *vm, void *reserved) {}


*/

