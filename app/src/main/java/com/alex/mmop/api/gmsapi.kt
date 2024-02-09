package com.alex.mmop.api

import com.fvbox.lib.FCore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object gmsapi {
    fun removegms ( onsucess:()->Unit,onfail:(reason:String)->Unit){
        runBlocking {
            val packages = listOf(
                "com.google.android.play.games",
                "com.google.android.wearable.app",
                "com.google.android.wearable.app.cn",
                "com.google.android.gsf.login",
                "com.google.android.backuptransport",
                "com.google.android.backup",
                "com.google.android.configupdater",
                "com.google.android.syncadapters.contacts",
                "com.google.android.feedback",
                "com.google.android.onetimeinitializer",
                "com.google.android.partnersetup",
                "com.google.android.setupwizard",
                "com.google.android.syncadapters.calendar"
            )
            CoroutineScope(Dispatchers.Default).launch {
                try {

                    for (apk in packages){
                        val check = FCore.get().isInstalled(apk,0)
                        if (check){
                            FCore.get().uninstallPackageAsUser(apk,0)
                            onsucess()
                        }else{
                            onsucess()
                        }
                    }
                }catch (err :Exception){
                    err.printStackTrace()
                    onfail(err.toString())
                }
            }
        }
    }
    fun installgms ( onsucess:()->Unit,onfail:(reason:String)->Unit){
        runBlocking {
            val packages = listOf(
                "com.google.android.play.games",
                "com.google.android.wearable.app",
                "com.google.android.wearable.app.cn",
                "com.google.android.gsf.login",
                "com.google.android.backuptransport",
                "com.google.android.backup",
                "com.google.android.configupdater",
                "com.google.android.syncadapters.contacts",
                "com.google.android.feedback",
                "com.google.android.onetimeinitializer",
                "com.google.android.partnersetup",
                "com.google.android.setupwizard",
                "com.google.android.syncadapters.calendar"
            )
            CoroutineScope(Dispatchers.Default).launch {
                try {
                    for (apk in packages){
                        val check = FCore.get().isInstalled(apk,0)
                        if (!check){
                            FCore.get().installPackageAsUser(apk,0)
                            onsucess()
                        }else{
                            onsucess()
                        }
                    }
                }catch (err :Exception){
                    err.printStackTrace()
                    onfail(err.toString())
                }
            }
        }
    }
}