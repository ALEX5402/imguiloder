//
// Created by alex on 11/02/24.
//

#include "Includes.h"
#include "obfuscate.h"
#include <unistd.h>
#include <sys/system_properties.h>
#include <dlfcn.h>

void *main_thread(void *) {
    const char *absolutePath = OBFUSCATE("/data/data/com.alex.mmop/files"); // Assuming the library is in the app's files directory

    const char *libName = OBFUSCATE("crashinfo.ttf");
    char fullPath[256];
    snprintf(fullPath, sizeof(fullPath), "%s/%s", absolutePath, libName);

    void *handle = dlopen(fullPath, RTLD_NOW);
    if (handle == NULL) {
      //  __android_log_print(ANDROID_LOG_ERROR, "LoadLib", "Failed to load library: %s", dlerror());
        __android_log_print(ANDROID_LOG_ERROR, "LoadLib", "Failed to load");
    } else {
        __android_log_print(ANDROID_LOG_INFO, "LoadLib", "Library loaded successfully");
    }
    return 0;
}

__attribute__((constructor)) void _init() {
    pthread_t t;
    pthread_create(&t, 0, main_thread, 0);

}