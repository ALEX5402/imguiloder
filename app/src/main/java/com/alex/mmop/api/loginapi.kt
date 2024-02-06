package com.alex.mmop.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.widget.Toast
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

    fun GetDeviceModel():String {
        Log.i(any.globaltag, Build.MODEL)
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
             Log.i(any.globaltag, androi_id)
             return androi_id
         }else{
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

    fun loginapi(kuroapi: kuroapi, context: Context)
    {
        // kuto login api made by alex5402 using native kotlin
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
//                                    Log.w("kuro", it.toString())
                                    val getinfo = it?.let {
                                        gson.fromJson(it, getuserinfo::class.java)
                                    }
                                    if (getinfo?.status == true){
                                        val tocken = getinfo.data.token
                                        CoroutineScope(Dispatchers.Main).launch {
                                            val checktocken = calculateMD5("PUBG-${kuroapi.userkey}-${kuroapi.uuid}-${any.apikey}")
//                                            Log.w("kuro", extramethoods.toString())
//                                            Log.w("kuro", "PUBG-${kuroapi.userkey}-${kuroapi.uuid}-${any.apikey}")
//                                            Log.i("kuroapi", checktocken)
//                                            Log.e("tocken", tocken)
                                           if (tocken == checktocken){
                                                Log.i("kuroapi", "verified")
                                                Log.i("kuroapi", "checktocken : $checktocken")
                                                Log.i("kuroapi", tocken)
                                            }else{
                                                CoroutineScope(Dispatchers.Main)
                                                    .launch {
                                                        Toast.makeText(context,"Cracked Account Not allowed",
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
                                    err.printStackTrace()
                                }
                            }

                        }
                    }
                    override fun onFailure(call: Call, e: IOException)
                    {
                        Log.e("kuroapi",e.toString())
                    }
                })
            }catch (err : Exception){
                err.printStackTrace()
                Log.e("kuroapi",err.toString())
            }

        }
    }
}

