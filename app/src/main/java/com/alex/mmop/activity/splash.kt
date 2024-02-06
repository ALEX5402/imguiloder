package com.alex.mmop.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.alex.mmop.R
import com.alex.mmop.api.alexapi
import com.alex.mmop.api.any
import com.alex.mmop.authapi.kuroapi
import com.alex.mmop.authapi.userinfo
import com.alex.mmop.composable.generateuuid
import com.alex.mmop.composable.splashscreen
import com.alex.mmop.ui.theme.ImguiloderTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class splash : ComponentActivity() {
    private var getuserkey : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            CoroutineScope(Dispatchers.Main).launch {
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
                    runBlocking {
                        donext(context)
                    }
                } )
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

