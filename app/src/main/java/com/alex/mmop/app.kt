package com.alex.mmop

import android.app.Application
import android.content.Context
import com.fvbox.lib.FCore
import com.google.firebase.FirebaseApp

class app : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        base?.let {
            FCore.get().init(it,true)
        }
        FCore.get().setAllowSystemInteraction(true)
        FCore.get().setAutoPreloadApplication(true)
        if(FCore.isClient()) {
            return
        }
    }

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        if(FCore.isClient()) {
            return
        }
    }
}