package com.alex.mmop.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.alex.mmop.R
import com.alex.mmop.api.alexapi
import com.alex.mmop.api.any
import com.alex.mmop.authapi.gamedata
import com.alex.mmop.authapi.getuserinfo
import com.alex.mmop.authapi.kuroapi
import com.alex.mmop.authapi.userinfo
import com.alex.mmop.composable.Selectmode
import com.alex.mmop.composable.generateuuid
import com.alex.mmop.ui.theme.selectgametheme
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Headers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

class selectgame : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            selectgametheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn(modifier = Modifier.fillMaxSize()
                    ){
                     items(mutablelistfgames()) {

                        Selectmode(
                            version = it.version,
                            packagename = it.packagename,
                            icon = it.icon,
                            apkname = it.apkname,
                            oninstall = {


                            }, onuninstall =
                            {


                            }
                        )
                         runBlocking {
                             val prefs = getSharedPreferences(any.prefskey, MODE_PRIVATE)
                             val userkey = prefs.getString(any.usersafe,"")
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
                             checkingagaun(kuroapi = kuroapi,
                                 context = this@selectgame
                             )
                         }
                      }
                    }
                }
            }
        }
    }

    fun checkingagaun(kuroapi: kuroapi, context: Context){
        // kuro login api made by alex5402 using kotlin and okhttp and google gson
        val scope = CoroutineScope(Dispatchers.Default)
        val clint = OkHttpClient()
        val gson = Gson()
        val requestbody = RequestBody.create(
            "application/x-www-form-urlencoded".toMediaTypeOrNull(),
            "game=PUBG&user_key=${kuroapi.userkey}&serial=${kuroapi.uuid}"
        )
        val headers = Headers.Builder()
            .add("Accept", "application/json")
            .add("Content-Type", "application/x-www-form-urlencoded")
            .add("User-Agent" ,"Dalvik Hajajndbhaiakwn")
            .add("Charset", "UTF-8").build()

        val makerequest = Request.Builder()
            .url(any.loginurl)
            .headers(headers)
            .post(requestbody)
            .build()
        scope.launch {
            try {
                clint .newCall(makerequest).enqueue(object : Callback {
                    override fun onResponse(call: Call, response: Response) {
                        if (response.isSuccessful){
                            val extramethoods = response.body?.string()
                            extramethoods.let {
                                try {
                                    //    Log.w("boom", it.toString())
                                    val getinfo = it?.let {
                                        gson.fromJson(it, getuserinfo::class.java)
                                    }
                                    if (getinfo?.status == true){
                                        val tocken = getinfo.data.token
                                        CoroutineScope(Dispatchers.Main).launch {
                                            val checktocken =
                                       alexapi.calculateMD5("PUBG-${kuroapi.userkey}-${kuroapi.uuid}-${any.apikey}")
                                            val verify = tocken == checktocken
                                            if (!verify){
                                                CoroutineScope(Dispatchers.Main)
                                                    .launch {
                                                        Toast.makeText(context, R.string.iscrack,
                                                            Toast.LENGTH_LONG).show()
                                                        delay(3000)
                                                        System.exit(0)
                                                    }
                                            }
                                        }
                                    }else{
                                        CoroutineScope(Dispatchers.Main)
                                            .launch {
                                                getinfo?.reason.let {
                                                    Toast.makeText(context ,"Login error : $it",
                                                        Toast.LENGTH_LONG).show()
                                                    Log.w("login", it.toString())
                                                }

                                            }
                                    }
                                }catch (err : Exception){
                                    CoroutineScope(Dispatchers.Main)
                                        .launch {
                                           Toast.makeText(context,R.string.somethingwrong,Toast.LENGTH_SHORT)
                                               .show()
                                        }
                                    err.printStackTrace()
                                }
                            }

                        }
                    }
                    override fun onFailure(call: Call, e: IOException) {
                        CoroutineScope(Dispatchers.Main)
                            .launch {
                                Toast.makeText(context,"${R.string.somethingwrong} $e",Toast.LENGTH_SHORT)
                                    .show()
                            }
                    }


                })
            }catch (err : Exception){
                err.printStackTrace()
                CoroutineScope(Dispatchers.Main)
                    .launch {

                    }
                Log.e("kuroapi",err.toString())
            }

        }
    }

    // this funtuion just set data to each veriable
    fun mutablelistfgames () : MutableList<gamedata>{
        val icons = listOf(
            R.drawable.bgmi_icon,
            R.drawable.globalpubg,
            R.drawable.icon_foreground
        )
        val versions = listOf(
            "3.0.0",
            "3.1.1",
            "3.1.0"
        )
        val apknames = listOf(
            "BGMI INDIA",
            "GLOBAL PUBG",
            "PUBG KOREA"
        )
        val packagenames = listOf(
            "com.pubg.imobile",
            "com.tencent.ig",
            "com.pubg.korea"
        )
        val list = mutableListOf<gamedata>()
       return runBlocking {
           for (i in icons.indices) {
               val data = gamedata(
                   packagename = packagenames[i],
                   icon = icons[i],
                   apkname = apknames[i],
                   version = versions[i]
                   )
               list.add(data)
           }
           list
       }
    }
}





