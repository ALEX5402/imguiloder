package com.alex.mmop

import android.app.Application
import android.content.Context
import com.alex.mmop.api.LOGS
import com.fvbox.lib.FCore
import com.fvbox.lib.rule.common.PackageRule

class app : Application() {
    val packagenames = listOf(
        "com.pubg.imobile",
        "com.tencent.ig",
        "com.pubg.korea",
        "com.tencent.tmgp.pubgmhd",
        "com.rekoo.pubgm",
        "com.vng.pubgmobile"
    )

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

         FCore.get().init(this,true)

    }

    override fun onCreate() {
        super.onCreate()
     //   FirebaseApp.initializeApp(this)  // firebase
     //   FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
        if(FCore.isClient()) {
            return
        }
        loadrule(packagenames)
    }

    fun loadrule ( packagenaems : List<String>){
        try {
            for (packagename in packagenaems ){
                val isinstalled =   FCore.get().isInstalled(packagename, 0)
                if ( isinstalled){
                    val fapplicationinfo = FCore.get().getApplicationInfo(packagename,0,0) ?: continue

                    val builder = PackageRule.Builder(fapplicationinfo.packageName).addUserId(0)
                    builder.setAllowSystemInteraction(true)
                    builder.isHideRoot(true)
                    builder.isDisableKill(true)
                    FCore.get().addRule(builder.build())
                    LOGS.info("Load Rule: " + fapplicationinfo.packageName)
                }
            }

        }catch ( err : Exception ){
            err.printStackTrace()
        }
    }
}
