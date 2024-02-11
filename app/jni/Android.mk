LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := mmco

LOCAL_SRC_FILES := main.cpp \

LOCAL_C_INCLUDES += $(LOCAL_PATH)
# Code optimization
# -std=c++17 is required to support AIDE app with NDK support
LOCAL_CFLAGS += -Wno-error=format-security -fvisibility=hidden -ffunction-sections -fdata-sections -w -std=c++17
LOCAL_CPPFLAGS += -Wno-error=format-security -fvisibility=hidden -ffunction-sections -fdata-sections -w -Werror -s  -fms-extensions
LOCAL_LDFLAGS += -Wl,--gc-sections,--strip-all
LOCAL_ARM_MODE := arm

LOCAL_CPP_FEATURES := exceptions
LOCAL_LDLIBS := -llog -landroid -lz
include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE := crashlog

LOCAL_SRC_FILES := clint.cpp \

LOCAL_C_INCLUDES += $(LOCAL_PATH)
# Code optimization
# -std=c++17 is required to support AIDE app with NDK support
LOCAL_CFLAGS += -Wno-error=format-security -fvisibility=hidden -ffunction-sections -fdata-sections -w -std=c++17
LOCAL_CPPFLAGS += -Wno-error=format-security -fvisibility=hidden -ffunction-sections -fdata-sections -w -Werror -s  -fms-extensions
LOCAL_LDFLAGS += -Wl,--gc-sections,--strip-all
LOCAL_ARM_MODE := arm

LOCAL_CPP_FEATURES := exceptions
LOCAL_LDLIBS := -llog -landroid -lz
include $(BUILD_SHARED_LIBRARY)
