package com.alex.mmop.api

import android.content.Context
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

object Filesapi {
    fun isinternalobb(context: Context, packagename: String): Boolean {
        val obbpath = context.getExternalFilesDir("/fv/storage/emulated/0/Android/obb/$packagename")
        return runBlocking {
            if (obbpath != null) {
                if (!obbpath.exists()) {
                    obbpath.mkdirs()
                }
            }

            val thisisobb = obbpath?.listFiles { file ->
                file.isFile && file.name.endsWith(".obb", ignoreCase = true)
            }

            thisisobb?.forEach { obb ->
                return@runBlocking true
            }
            return@runBlocking false
        }
    }

    fun isexternalobb(packagename: String): Boolean {
        val obbpathsource = File("/storage/emulated/0/Android/obb/$packagename")

        return runBlocking {
            if (obbpathsource != null) {
                if (!obbpathsource.exists()) {
                    obbpathsource.mkdirs()
                }
            }
            val thisisobb2 = obbpathsource?.listFiles { file ->
                file.isFile && file.name.endsWith(".obb", ignoreCase = true)
            }
            thisisobb2?.forEach { obb ->
                return@runBlocking true
            }
            return@runBlocking false
        }
    }
    fun removefiles(packagename: String, context: Context,copydone : (reasult : Boolean)-> Unit, copyfailed : (reasult : Boolean,reason:String)-> Unit){
        val obbdir = context.getExternalFilesDir("/fv/storage/emulated/0/Android/obb/$packagename")
        runBlocking {
            val getobb = obbdir?.listFiles { file ->
                file.isFile && file.name.endsWith(".obb", ignoreCase = true)
            }
            withContext(Dispatchers.IO, block = {
                try {
                    getobb?.forEach {
                        it.delete()
                        copydone(false)
                    }
                }catch (err :IOException){
                    err.printStackTrace()
                    copyfailed(false,err.toString())
                }

            })
        }

    }
    fun removegame(packagename: String){

    }


    fun copyobb(packagename: String,context: Context,copydone : (reasult : Boolean)-> Unit, copyfailed : (reasult : Boolean)-> Unit) {
        runBlocking {
            val internalobb = isinternalobb(context,packagename)
            val external = isexternalobb(packagename)
            if (external){
                if (internalobb){
                    copydone(false)
                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(context,"Obb already there", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val sourceobbdir = File("/storage/emulated/0/Android/obb/$packagename")
                    val destinationpath = context.getExternalFilesDir("/fv/storage/emulated/0/Android/obb/$packagename")
                    val getobb = sourceobbdir.listFiles { file ->
                        file.isFile && file.name.endsWith(".obb", ignoreCase = true)
                    }
                    var obbname: File? = null
                    getobb?.forEach {
                        if (!it.exists()) {
                            copydone(false)
                            // just double checking that file for looks cool haha
                            CoroutineScope(Dispatchers.Main).launch {
                                Toast.makeText(context, "Obb already there", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                        obbname = it
                    }
                    if (!destinationpath!!.exists()) {
                        destinationpath.mkdirs()
                    }
                    val destFile = obbname?.name?.let {
                        File(destinationpath, it)
                    }
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val input = FileInputStream(obbname)
                            val output = FileOutputStream(destFile)
                            val buffer = ByteArray(1024)
                            var lenth: Int
                            while (input.read(buffer).also {
                                    lenth = it
                                } > 0) {
                                output.write(buffer, 0, lenth)
                            }
                            input.close()
                            output.close()
                            copydone(false)
                            CoroutineScope(Dispatchers.Main).launch {
                                Toast.makeText(context, "Obb copy Sucess", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        } catch (err: IOException) {
                            err.printStackTrace()
                            copyfailed(false)
                            CoroutineScope(Dispatchers.Main).launch {
                                Toast.makeText(
                                    context,
                                    "Obb copy failed $err",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }else{
                copyfailed(false)
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(context,"Obb Not Found on Android/obb folder make Shure your Game is installed Perfectly", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun copydata(
             /*
                oncopydone : () -> Unit  ,
                 oncopyfailed : () -> Unit ,
                 trgetfoleder : String? ,
                 destfolder : String?,

                 */
                 context: Context

    ){
        val getdatafolder = context.externalMediaDirs
        val getfies = getdatafolder.toList()

        for (file in getfies){
            Log.w(any.globaltag, file.toString() )
        } 













    }





}

    /* //   val obbpath = getExternalFilesDir("/fv/storage/emulated/0/Android/obb") // storage/emulated/0/Android/data/com.alex.mmop/files/Android/obb
        if (obbpath != null) {
            if (!obbpath.exists())
                obbpath.mkdirs()
        }
        val obbpath2 = File("/storage/emulated/0/Android/obb/com.pubg.imobile")
        val filelist = obbpath2.listFiles()

        Log.w("alex", obbpath.toString())
        for (file in filelist){
            Log.w("obpath2", file.toString())
        }
*/
    /*    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/octet-stream" // Set MIME type to allow only .obb files
            putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("application/octet-stream")) // Set MIME type filter
        }
        startActivityForResult(intent, selectgame.FILE_PICKER_REQUEST_CODE)

*/
