package com.alex.mmop.activity

import android.Manifest
import android.Manifest.permission.REQUEST_INSTALL_PACKAGES
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.alex.mmop.R
import com.alex.mmop.api.alexapi
import com.alex.mmop.api.any
import com.alex.mmop.authapi.gamedata
import com.alex.mmop.authapi.getuserinfo
import com.alex.mmop.authapi.kuroapi
import com.alex.mmop.authapi.userinfo
import com.alex.mmop.composable.Selectmode
import com.alex.mmop.composable.Settingsmenu
import com.alex.mmop.composable.generateuuid
import com.alex.mmop.ui.theme.selectgametheme
import com.alex.mmop.viewmodels.modelmain
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
                val constraintsss = ConstraintSet {
                    val flot =  createRefFor("floatingbutton")
                    constrain(flot){
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }

                }
                val viewmodel = modelmain()
                checkAndRequestFilePermission(this)

                val prefs = getSharedPreferences(any.packageinstallpermisson, MODE_PRIVATE)
                val boollll = prefs.getBoolean("installpermisson",false)

                if (!boollll){
                    isUnknownSourcesPermissionAllowed(this)
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConstraintLayout(constraintSet = constraintsss,
                        modifier = Modifier.fillMaxSize()
                    )
                    {
                        var isshowing by remember {
                            mutableStateOf(false)
                        } 

                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        items(mutablelistfgames()) {
                            Selectmode(
                                version = it.version,
                                icon = it.icon,
                                apkname = it.apkname,
                                pkgstatus = it.gamestatus,
                                packagename = it.packagename,
                                oninstall = {
                                 startActivity(Intent(this@selectgame,injectinto::class.java))
                                }, onuninstall =
                                {

                                },
                                liburl = viewmodel.libdownloadurl,
                            )

                            runBlocking {
                                val prefs = getSharedPreferences(any.prefskey, MODE_PRIVATE)
                                val userkey = prefs.getString(any.usersafe, "")
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
                                checkingagaun(
                                    kuroapi = kuroapi,
                                    context = this@selectgame
                                )
                            }

                            if (isshowing){
                                Dialog(onDismissRequest = {
                                    isshowing = false
                                }) {
                                    Card(colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                                    )
                                        , modifier = Modifier
                                            .fillMaxWidth()
                                            .height(400.dp)

                                    ) {
                                    Settingsmenu(this@selectgame, permissonpopup = {
                                        isUnknownSourcesPermissionAllowed(this@selectgame)
                                    } )
                                    }
                                }

                            }
                        }
                    }
                        FloatingActionButton(onClick = {
                            isshowing = true
                        }, modifier = Modifier
                            .layoutId("floatingbutton")
                            .padding(30.dp)
                        ) {
                            Icon(Icons.Filled.Settings, "Settings button")
                        }

                    }
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()
        runBlocking {
            val prefs = getSharedPreferences(any.prefskey, MODE_PRIVATE)
            val userkey = prefs.getString(any.usersafe, "")
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
            checkingagaun(
                kuroapi = kuroapi,
                context = this@selectgame
            )
        }
    }

    fun checkAndRequestFilePermission(activity: Activity): Boolean {
        val filePermission = Manifest.permission.READ_EXTERNAL_STORAGE
        if (ContextCompat.checkSelfPermission(activity, filePermission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(filePermission), 1001)
            return false
        }
        return true
    }

    companion object {
        const val FILE_PICKER_REQUEST_CODE = 1
    }

   @Deprecated("Deprecated in Java")
   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                val filePath = getFilePathFromUri(uri,this)
                if (filePath != null) {
                    Toast.makeText(this, "File selected: $filePath", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed to get file path", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @SuppressLint("Range")
    private fun getFilePathFromUri(uri: Uri, context: Context): String? {
        return if (DocumentsContract.isDocumentUri(context, uri)) {
            applicationContext.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    cursor.getString(cursor.getColumnIndex(DocumentsContract.Document.COLUMN_DOCUMENT_ID))
                } else {
                    null
                }
            }
        } else {
            uri.path
        }
    }


    @SuppressLint("CommitPrefEdits")
    fun isUnknownSourcesPermissionAllowed(context: Context){
        val prefs = getSharedPreferences(any.packageinstallpermisson, MODE_PRIVATE)
        val editor = prefs.edit()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val permissonquery = ContextCompat.checkSelfPermission(context , REQUEST_INSTALL_PACKAGES)
            if(permissonquery.equals(PackageManager.PERMISSION_GRANTED)){
                Log.i("Permisson", "done")
                return
            }else{
                val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES)
                startActivity(intent)
                editor.putBoolean("installpermisson",true)
                editor.apply()
            }
        }else{
            val intent = Intent(Settings.ACTION_SECURITY_SETTINGS)
            startActivity(intent)
            editor.putBoolean("installpermisson",true)
            editor.apply()
        }
    }

    fun checkingagaun(kuroapi: kuroapi, context: Context){
        Log.i("check","done")
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
                               //       Log.w("boom", it.toString())
                                    val getinfo = it?.let {
                                        gson.fromJson(it, getuserinfo::class.java)
                                    }
                                    if (getinfo?.status == true){
                                        val viewmodel = modelmain()
                                        viewmodel.libdownloadurl = getinfo.data.libs
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
                                                delay(5000)
                                                System.exit(1)
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
            R.drawable.icon_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground
        )
        val viewnodel = modelmain()

        val gamestatus = listOf(
            viewnodel.bgmistatus,
            viewnodel.globalstatus,
            viewnodel.koreastatus,
            viewnodel.chinastatus,
            viewnodel.tiwanstatus,
            viewnodel.vngstatus,
        )

        val versions = listOf(
            "3.0.0",
            "3.1.1",
            "3.1.0",
            "3.1.0",
            "3.1.0",
            "3.1.0"
        )
        val apknames = listOf(
            "BGMI INDIA",
            "GLOBAL PUBG",
            "PUBG KOREA",
            "CHINA PUBG",
            "PUBG TIWAN",
            "PUBG VNG"
        )
        val packagenames = listOf(
            "com.pubg.imobile",
            "com.tencent.ig",
            "com.pubg.korea",
            "com.pubg.china",
            "com.pubg.tiwan",
            "com.pubg.Vng"
        )
        val list = mutableListOf<gamedata>()
       return runBlocking {
           for (i in icons.indices) {
               val data = gamedata(
                   packagename = packagenames[i],
                   icon = icons[i],
                   apkname = apknames[i],
                   version = versions[i],
                   gamestatus = gamestatus[i]
                   )
               list.add(data)
           }
           list
       }
    }
}





