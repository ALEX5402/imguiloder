package com.alex.mmop.api

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import com.alex.mmop.R
import com.alex.mmop.authapi.getuserinfo
import com.alex.mmop.authapi.kuroapi
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
import okio.IOException
import java.security.MessageDigest
import java.util.UUID

object alexapi {

    fun calculateMD5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return runBlocking {
            val digest = md.digest(input.toByteArray())
            digest.fold(StringBuilder()) { acc, byte ->
                acc.append(String.format("%02x", byte))
            }.toString()
        }
    }
    fun openLink(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
    fun GetDeviceModel():String {
        LOGS.info(Build.MODEL)
        return Build.MODEL
    }
    fun GetDeviceBrand() :String{
        Log.i(any.globaltag, Build.BRAND)
        return Build.BRAND
    }
    fun GetAndroidID():String{
        val androi_id = Settings.Secure.ANDROID_ID
         if(androi_id !== null)
         {
             LOGS.info(androi_id)
             return androi_id
         }else {
             return "androidid err"
         }
    }
    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true

    }

    fun getDeviceUniqueIdentifier(uuid: String): String {
        return runBlocking {
            val byteUuid = uuid.toByteArray(Charsets.UTF_8)
            val uuidObject = UUID.nameUUIDFromBytes(byteUuid)
            uuidObject.toString()
        }
    }
    fun loginnnn(kuroapi: kuroapi,
                 context: Context ,
                 onsucess: () -> Unit,
                 onfailed: ( reason : String ) -> Unit,
    )
    {
        // kuro login api made by alex5402 using kotlin and okhttp and google gson
        val scope = CoroutineScope(Dispatchers.Default)
        val clint = OkHttpClient()
        val gson = Gson()
        val requestbody = RequestBody.create(
            "application/x-www-form-urlencoded".toMediaTypeOrNull(),
            "game=PUBG&user_key=${kuroapi.userkey}&serial=${kuroapi.uuid}"
        )
        val agent = any.source[6]
        val header4111 = any.source[7]
        val headers = Headers.Builder()
            .add("Accept", "application/json")
            .add("Content-Type", "application/x-www-form-urlencoded")
            .add(agent,header4111)
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
                                    LOGS.warn(it.toString())
                                    val getinfo = it?.let {
                                        gson.fromJson(it, getuserinfo::class.java)
                                    }
                                    if (getinfo?.status == true){
                                        val tocken = getinfo.data.token
                                        CoroutineScope(Dispatchers.Main).launch {
                                            val checktocken = calculateMD5("PUBG-${kuroapi.userkey}-${kuroapi.uuid}-${any.apikey}")
                                         LOGS.warn( extramethoods.toString())
                                          LOGS.warn("PUBG-${kuroapi.userkey}-${kuroapi.uuid}-${any.apikey}")
                                          LOGS.warn(checktocken)
                                          LOGS.warn(tocken)
                                            if (tocken == checktocken){

                                                val logs =


                                                LOGS.warn(getinfo.data.liburlgl+
                                                        getinfo.data.libs+
                                                        getinfo.data.liburlcnin+
                                                        getinfo.data.liburlvng+
                                                        getinfo.data.liburltiwan+
                                                        getinfo.data.linkorea   )


                                                any.bgmistatus =   getinfo.data.bgmistatus
                                                any.globalstatus = getinfo.data.globalstatus
                                                any.chinastatus =  getinfo.data.chinastatus
                                                any.koreastatus =  getinfo.data.koreastatus
                                                any.vngstatus =    getinfo.data.vngstatus
                                                any.tiwanstatus =  getinfo.data.tiwanstatus
                                                any.modownername = getinfo.data.modname
                                                any.liburlgl =     getinfo.data.liburlgl
                                                any.libbgmiurl =   getinfo.data.libs
                                                any.liburlchin =   getinfo.data.liburlcnin
                                                any.liburlvng =    getinfo.data.liburlvng
                                                any.liburltiwan =  getinfo.data.liburltiwan
                                                any.linkorea =     getinfo.data.linkorea

                                                LOGS.warn("verified")
                                                LOGS.warn("checktocken : $checktocken")
                                                LOGS.warn( tocken)
                                                CoroutineScope(Dispatchers.Main)
                                                    .launch {
                                                        onsucess()
                                                    }
                                            }else{

                                                CoroutineScope(Dispatchers.Main)
                                                    .launch {
                                                        onfailed(R.string.iscrack.toString())
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
                                                    onfailed(it!!)
                                                    Toast.makeText(context ,"Login error : $it",
                                                        Toast.LENGTH_LONG).show()
                                                    LOGS.warn(it.toString())
                                                }

                                            }
                                    }
                                }catch (err : Exception){
                                    CoroutineScope(Dispatchers.Main)
                                        .launch {
                                            onfailed(err.toString())
                                        }
                                    err.printStackTrace()
                                }
                            }

                        }
                    }
                    override fun onFailure(call: Call, e: IOException)
                    {
                        CoroutineScope(Dispatchers.Main)
                            .launch {
                                onfailed(e.toString())
                            }
                        LOGS.error(e.toString())
                    }
                })
            }catch (err : Exception){
                err.printStackTrace()
                CoroutineScope(Dispatchers.Main)
                    .launch {
                        onfailed(err.toString())
                    }
                LOGS.error(err.toString())
            }

        }
    }

    fun loginapi(kuroapi: kuroapi,
                 context: Context ,
                 onsucess: () -> Unit,
                 onfailed: ( reason : String ) -> Unit , )
    {
        // kuro login api made by alex5402 using kotlin and okhttp and google gson
        val scope = CoroutineScope(Dispatchers.Default)
        val clint = OkHttpClient()
        val gson = Gson()
        val requestbody = RequestBody.create(
            "application/x-www-form-urlencoded".toMediaTypeOrNull(),
            "game=PUBG&user_key=${kuroapi.userkey}&serial=${kuroapi.uuid}"
        )
        val agent = any.source[6]
        val header4111 = any.source[7]
        val headers = Headers.Builder()
            .add("Accept", "application/json")
            .add("Content-Type", "application/x-www-form-urlencoded")
            .add(agent,header4111)
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
                            extramethoods.let { it ->
                                try {
                              LOGS.warn( it.toString())
                                    val getinfo = it?.let {
                                        gson.fromJson(it, getuserinfo::class.java)
                                    }
                                    if (getinfo?.status == true){
                                        val tocken = getinfo.data.token
                                        CoroutineScope(Dispatchers.Main).launch {
                                            val checktocken = calculateMD5("PUBG-${kuroapi.userkey}-${kuroapi.uuid}-${any.apikey}")
                                            LOGS.warn( extramethoods.toString())
                                            LOGS.warn("PUBG-${kuroapi.userkey}-${kuroapi.uuid}-${any.apikey}")
                                            LOGS.warn( checktocken)
                                            LOGS.warn( tocken)

                                           if (tocken == checktocken){

                                               LOGS.warn(getinfo.data.liburlgl+
                                                       getinfo.data.libs+
                                                       getinfo.data.liburlcnin+
                                                       getinfo.data.liburlvng+
                                                       getinfo.data.liburltiwan+
                                                       getinfo.data.linkorea   )

                                              any.bgmistatus = getinfo.data.bgmistatus
                                                any.globalstatus = getinfo.data.globalstatus
                                               any.chinastatus = getinfo.data.chinastatus
                                               any.koreastatus = getinfo.data.koreastatus
                                               any.vngstatus = getinfo.data.vngstatus
                                               any.tiwanstatus = getinfo.data.tiwanstatus
                                               any.modownername = getinfo.data.modname

                                               any.liburlgl = getinfo.data.liburlgl
                                               any.libbgmiurl = getinfo.data.libs
                                               any.liburlchin = getinfo.data.liburlcnin
                                               any.liburlvng = getinfo.data.liburlvng
                                               any.liburltiwan = getinfo.data.liburltiwan
                                               any.linkorea = getinfo.data.linkorea




                                               LOGS.warn(any.libbgmiurl)
                                               LOGS.warn( "verified")
                                               LOGS.warn("checktocken : $checktocken")
                                               LOGS.warn(tocken)
                                               CoroutineScope(Dispatchers.Main)
                                                   .launch {
                                                       onsucess()
                                                   }
                                            }else{

                                                CoroutineScope(Dispatchers.Main)
                                                    .launch {
                                                        onfailed(R.string.iscrack.toString())
                                                        Toast.makeText(context, R.string.iscrack,
                                                            Toast.LENGTH_LONG).show()
                                                        delay(3000)
                                                        System.exit(1)
                                                    }
                                            }
                                        }
                                    }else{
                                        CoroutineScope(Dispatchers.Main)
                                            .launch {
                                                getinfo?.reason.let {
                                                    onfailed(it!!)
                                                    Toast.makeText(context ,"Login error : $it",
                                                        Toast.LENGTH_LONG).show()
                                                    LOGS.warn( it.toString())
                                                }

                                            }
                                    }
                                }catch (err : Exception){
                                    CoroutineScope(Dispatchers.Main)
                                        .launch {
                                            onfailed(err.toString())
                                        }
                                    err.printStackTrace()
                                }
                            }

                        }
                    }
                    override fun onFailure(call: Call, e: IOException)
                    {
                        CoroutineScope(Dispatchers.Main)
                            .launch {
                                onfailed(e.toString())
                            }
                        LOGS.error(e.toString())
                    }
                })
            }catch (err : Exception){
                err.printStackTrace()
                CoroutineScope(Dispatchers.Main)
                    .launch {
                        onfailed(err.toString())
                    }
                LOGS.error(err.toString())
            }

        }
    }
}

