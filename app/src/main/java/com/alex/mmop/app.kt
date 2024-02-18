package com.alex.mmop

import android.app.Application
import android.content.Context
import com.fvbox.lib.FCore
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics

class app : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
         FCore.get().init(this,true)
    }

    override fun onCreate() {
        super.onCreate()
        if(FCore.isClient()) {
        FirebaseApp.initializeApp(this)
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
            return
        }
    }
}
