-keep class com.alex.mmop.api.any
-keep class com.alex.mmop.api.alexapi
-keep class com.alex.mmop.api.downloderapi
-keep class com.alex.mmop.api.Filesapi
-keep class com.alex.mmop.api.LOGS
-keep class com.alex.mmop.api.updateapi
-keep class com.alex.mmop.api.gmsapi

-keep class com.alex.mmop.activity.injectinto
-keep class com.alex.mmop.activity.Login
-keep class com.alex.mmop.activity.selectgame
-keep class com.alex.mmop.activity.splash

-keep class com.alex.mmop.authapi.Data
-keep class com.alex.mmop.authapi.gamedata
-keep class com.alex.mmop.authapi.getuserinfo
-keep class com.alex.mmop.authapi.userinfo
-keep class com.alex.mmop.authapi.kuroapi

-keep class com.alex.mmop.composable.SelectgameKt
-keep class com.alex.mmop.composable.InjectintoKt
-keep class com.alex.mmop.composable.SplashuiKt

-keep class com.alex.mmop.libonline.enc.enc

-verbose
-keepattributes Signature
-keepattributes *Annotation*
-keepattributes SourceFile, LineNumberTable
-ignorewarnings

# 使用自定义字典
#-obfuscationdictionary dict-t.txt
#-classobfuscationdictionary dict.txt
#-packageobfuscationdictionary dict.txt
-overloadaggressively
-repackageclasses
-renamesourcefileattribute
-adaptresourcefilenames
-adaptresourcefilecontents
-allowaccessmodification

-keep class androidx.annotation.Keep
-keep @androidx.annotation.Keep class * {*;}
-keepclasseswithmembernames class * {
    @androidx.annotation.Keep <methods>;
}

# flib
-keep class com.fvbox.lib.system.proxy.hook.ProxyMethod**
-keep @com.fvbox.lib.system.proxy.hook.ProxyMethod** class * {*;}

-keep @androidx.annotation.Keep class * {*;}
-keep class com.fvbox.lib.client.proxy.**  { *; }
-keep class com.fvbox.lib.common.**  { *; }
-keep class com.fvbox.lib.abs.**  { *; }
-keep class com.fvbox.lib.FCore** { *; }
#-keep class com.fvbox.lib.data.FSystemConfig { *; }
#-keep class com.fvbox.lib.data.FUserConfig { *; }
#-keep public class **$Stub*  { *; }

# mirror
-keep public class android.**  { *; }
-keep public class com.android.**  { *; }

# BlackReflection
-keep class top.niunaijun.blackreflection.** {*; }
-keep @top.niunaijun.blackreflection.annotation.** class * {*;}
-keepclasseswithmembernames class * {
    @top.niunaijun.blackreflection.annotation.** <methods>;
}

# pine
-keep class top.canyie.** {*; }

# mmkv
-keep class com.tencent.mmkv.** {*; }

# HiddenApiBypass
-keep class org.lsposed.** {*; }

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
    public static ** valueOf(int);
}



-keep class com.fvbox.lib.rule.common.**  { *; }


-keep class com.bytedance.shadowhook.ShadowHook {
    native <methods>;
}


-keep class xcrash.NativeHandler {
    native <methods>;
    void crashCallback(...);
    void traceCallback(...);
}


-dontwarn dalvik.system.VMRuntime
-keepclassmembers class org.lsposed.hiddenapibypass.Helper$* { *; }

-keep class com.fvbox.lib.rule.common.**  { *; }


-keep class com.bytedance.shadowhook.ShadowHook {
    native <methods>;
}


-keep class xcrash.NativeHandler {
    native <methods>;
    void crashCallback(...);
    void traceCallback(...);
}

-dontwarn dalvik.system.VMRuntime
-keepclassmembers class org.lsposed.hiddenapibypass.Helper$* { *; }

