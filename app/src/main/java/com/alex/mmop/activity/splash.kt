package com.alex.mmop.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.alex.mmop.R
import com.alex.mmop.api.LOGS
import com.alex.mmop.api.alexapi
import com.alex.mmop.api.any
import com.alex.mmop.authapi.kuroapi
import com.alex.mmop.authapi.userinfo
import com.alex.mmop.composable.generateuuid
import com.alex.mmop.composable.splashscreen
import com.alex.mmop.ui.theme.ImguiloderTheme
import com.fvbox.lib.FCore
import com.fvbox.lib.system.proxy.FIStorageManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import java.nio.file.Files


class splash : ComponentActivity() {
    init {
        System.loadLibrary("mmco")
    }
    private var getuserkey : String = ""

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clean()
        
        setContent {
            val prefffs = getSharedPreferences(any.prefskey, MODE_PRIVATE)

                ImguiloderTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorScheme.background
                ) {
                    splashscreen()
                    runBlocking {
                        getuserkey = prefffs.getString(any.usersafe,"").toString()
                    }
                    startnect(this,getuserkey)
                }
            }
        }
    }
    fun startnect( context : Context , userkey : String? ){
        if (getuserkey == "")
        {
            CoroutineScope(Dispatchers.Default).launch {
                donext(context)
            }
        }else{
            if (!alexapi.isInternetAvailable(context))
                return Toast.makeText(context, "Unable to connect to internet", Toast.LENGTH_LONG).show()

            CoroutineScope(Dispatchers.Default).launch{
                val userinfoclass = userinfo(
                    userkey!!,
                    alexapi.GetAndroidID(),
                    alexapi.GetDeviceModel(),
                    alexapi.GetDeviceBrand()
                )
                val uuid = generateuuid(userinfoclass)
                val kuroapi = kuroapi(
                    userkey = userkey,
                    uuid = uuid,
                    androidid = userinfoclass.androidid,
                    devicemodel = userinfoclass.devicemodel,
                    devicebrand = userinfoclass.devicemodel
                )
                alexapi.loginapi(kuroapi,context, onsucess = {
                    CoroutineScope(Dispatchers.Main).launch{
                        Toast.makeText(context,R.string.splashtost,Toast.LENGTH_LONG)
                            .show()
                        startactivity(context)
                    }
                }, onfailed = {
                    Toast.makeText(context , "${R.string.login_failed} $it",Toast.LENGTH_LONG)
                        .show()
                    alexapi.openLink(context,any.telegram_link)
                    CoroutineScope(Dispatchers.Default).launch{
                        donext(context)
                    }
                })
            }
        }
    }
    fun clean ( ){
        CoroutineScope(Dispatchers.Default).launch {
            val getfiles = filesDir.listFiles()
            delay(3000)
            getfiles?.let {
                for (files in getfiles) {
                    val getfile = files.name.endsWith(
                        ".ttf",
                        true
                    )
                    if (getfile){
                        files.delete()
                        Log.w("FILE DELETED", files.toString())
                    }
                }
            }
        }
    }

    fun startactivity(context : Context){
        context.startActivity(Intent(this,selectgame::class.java))
        finish()
    }
    suspend fun donext(context: Context){
      
        delay(3000)
        context.startActivity(Intent(this,Login::class.java))
        finish()
    }
}

