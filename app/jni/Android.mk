LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := libcurl
LOCAL_SRC_FILES := backend/external/curl-android-$(TARGET_ARCH_ABI)/lib/libcurl.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libssl
LOCAL_SRC_FILES := backend/external/openssl-android-$(TARGET_ARCH_ABI)/lib/libssl.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libcrypto
LOCAL_SRC_FILES := backend/external/openssl-android-$(TARGET_ARCH_ABI)/lib/libcrypto.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE := mmco

LOCAL_SRC_FILES := main.cpp \
backend/Tools.cpp \

LOCAL_C_INCLUDES += $(LOCAL_PATH)

LOCAL_C_INCLUDES := backend/external/curl-android-$(TARGET_ARCH_ABI)/include
LOCAL_C_INCLUDES += backend/external/openssl-android-$(TARGET_ARCH_ABI)/include
LOCAL_C_INCLUDES += libzip

# Code optimization
# -std=c++17 is required to support AIDE app with NDK support
LOCAL_CFLAGS += -Wno-error=format-security -fvisibility=hidden -ffunction-sections -fdata-sections -w -std=c++17
LOCAL_CPPFLAGS += -Wno-error=format-security -fvisibility=hidden -ffunction-sections -fdata-sections -w -Werror -s  -fms-extensions
LOCAL_LDFLAGS += -Wl,--gc-sections,--strip-all
LOCAL_ARM_MODE := arm

LOCAL_CPP_FEATURES := exceptions
LOCAL_LDLIBS := -llog -landroid -lz

LOCAL_STATIC_LIBRARIES					:= libcurl \
                                           libssl \
                                           libcrypto \

include $(BUILD_SHARED_LIBRARY)
