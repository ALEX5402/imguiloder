#include "Includes.h"
#include "obfuscate.h"
#include <unistd.h>
#include <sys/system_properties.h>
#include <dlfcn.h>

extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_alex_mmop_api_any_valueof(JNIEnv *env, jobject thiz) {
    const char *strings[] = {
            OBFUSCATE("nothing"),
            OBFUSCATE("mk23hdoie79kmaju"), // zippassword
            OBFUSCATE("DESede/ECB/PKCS5Padding"),
            OBFUSCATE("DESede"),
            OBFUSCATE("https://anogs.tech/xvip/connect"), // loginurl
            OBFUSCATE("Vm8Lk7Uj2JmsjCPVPVjrLa7zgfx3uz9E"), // lisence
            OBFUSCATE("User-Agent"),
            OBFUSCATE("Dalvik Hajajndbhaiakwn"),
            OBFUSCATE("awd435"),
            OBFUSCATE("awd435"),
            OBFUSCATE("awd435"),
            OBFUSCATE("awd435"),
            OBFUSCATE("awd435"),
            OBFUSCATE("awd435"),
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
