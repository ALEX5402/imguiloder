#include "Includes.h"
#include "obfuscate.h"


extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_alex_mmop_api_any_valueof(JNIEnv *env, jobject thiz) {
    const char *strings[] = {
            OBFUSCATE("https://raw.githubusercontent.com/ALEX5402/test-host/main/alex.json"),
            OBFUSCATE("mk23hdoie79kmaju"), // licence please don't change it 16 / 24 byte
            OBFUSCATE("DESede/ECB/PKCS5Padding"),
            OBFUSCATE("DESede"),
            OBFUSCATE("dw234"), //server status
            OBFUSCATE("awfd3wr"), // notice mode
            OBFUSCATE("awd435"), // logclearmode
            OBFUSCATE("sefsgtr"), // zip passwordmode
            OBFUSCATE("dwaesrt43"), // notice_title
            OBFUSCATE("adfret43"), // lib_url
            OBFUSCATE("alex_example_rebrander"), // rebrander name
            OBFUSCATE("1234"), //zip pass offline
            OBFUSCATE("waff33aw"), // zip code
            OBFUSCATE(""),
            OBFUSCATE(""),
            OBFUSCATE("")
    };

    int numStrings = sizeof(strings) / sizeof(strings[0]);
    jclass stringClass = env->FindClass("java/lang/String");
    jobjectArray stringArray = env->NewObjectArray(numStrings, stringClass, nullptr);
    for (int i = 0; i < numStrings; i++) {
        jstring string = env->NewStringUTF(strings[i]);
        env->SetObjectArrayElement(stringArray, i, string);
        env->DeleteLocalRef(string);
    }
    return stringArray;
}

