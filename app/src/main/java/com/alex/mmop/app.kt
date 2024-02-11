package com.alex.mmop

import android.app.Application
import android.content.Context
import com.fvbox.lib.FCore
import com.google.firebase.FirebaseApp

class app : Application() {


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
         FCore.get().init(this)
    }

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        if(FCore.isClient()) {
            return
        }
    }
}