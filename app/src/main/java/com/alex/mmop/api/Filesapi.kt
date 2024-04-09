package com.alex.mmop.api

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.telephony.SubscriptionManager.OnSubscriptionsChangedListener
import android.widget.Toast
import com.fvbox.lib.FCore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.lang.reflect.Field
import java.nio.file.Files
import java.nio.file.StandardCopyOption

object Filesapi {
    fun isinternalobb(context: Context, packagename: String): Boolean {

        val internaldatadir = context.dataDir.absolutePath
        val obbpath = File("${internaldatadir}/storage/emulated/0/Android/obb/$packagename")
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
    fun removefiles(packagename: String,
                    context: Context,
                    copydone : (reasult : Boolean)-> Unit,
                    copyfailed : (reasult : Boolean,
                                  reason:String)-> Unit){
        val internaldatadir = context.dataDir.absolutePath
        val obbdir = File("${internaldatadir}/storage/emulated/0/Android/obb/$packagename")
        runBlocking {
            val getobb = obbdir.listFiles { file ->
                file.isFile && file.name.endsWith(".obb", ignoreCase = true)
            }
            withContext(Dispatchers.IO, block = {
                try {
                    getobb?.forEach {
                        it.delete()
                        copydone(false)
                    }
                }catch (err :IOException){
                    LOGS.error(err.toString())
                    err.printStackTrace()
                    copyfailed(false,err.toString())
                }

            })
        }

    }
    fun removegame(packagename: String){
        if (FCore.get().isInstalled(packagename,0)){
            FCore.get().uninstallPackageAsUser(packagename,0)
        }
    }


     @SuppressLint("Range")
     fun importFile(
        receivedFileUri: Uri?,
        context: Context,
        onCopyDone: () -> Unit,
        onFailed: (reason: String) -> Unit,
        onstarted: () -> Unit
    ) {
        try {
            receivedFileUri ?: throw IllegalArgumentException("Received file URI is null")

            // Open input stream from received file URI
            val inputStream = context.contentResolver.openInputStream(receivedFileUri)
                ?: throw IllegalArgumentException("Failed to open input stream for URI: $receivedFileUri")

            val time = System.currentTimeMillis()
            val dataDir = File("${context.dataDir}/storage/emulated/0/Imported-files")

            // Extract original file name and extension
            val originalFileName = context.contentResolver.query(receivedFileUri, null, null, null, null)
                ?.use { cursor ->
                    if (cursor.moveToFirst()) {
                        cursor.getString(cursor.getColumnIndex("_display_name"))
                    } else {
                        throw IllegalArgumentException("Failed to retrieve original file name")
                    }
                } ?: throw IllegalArgumentException("Failed to retrieve original file name")

            val originalFileExtension = originalFileName.substringAfterLast('.', "")

            val destination = File(dataDir, "imported_file_$time.$originalFileExtension")

            // Open output stream to destination file

            if (!dataDir.isDirectory){
                dataDir.mkdirs()
            }
            val outputStream = FileOutputStream(destination)
            onstarted()
            // Copy file contents from input stream to output stream
            inputStream.use { input ->
                outputStream.use { output ->
                    val buffer = ByteArray(1024)
                    var length: Int
                    var byteread = 0
                    val totalBytes = inputStream.available()
                    while (input.read(buffer).also { length = it } > 0) {
                        output.write(buffer, 0, length)
                        byteread += length
                        val newProgress = (byteread * 1000) / totalBytes

                    }

                }
            }

            // Notify onCopyDone on the main thread
            CoroutineScope(Dispatchers.Main).launch {
                onCopyDone()
            }
        } catch (err: Exception) {
            // Notify onFailed with error message on the main thread
            CoroutineScope(Dispatchers.Main).launch {
                onFailed(err.message ?: "Unknown error")
            }
        }
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
                    val internaldatadir = context.dataDir.absolutePath
                    val destinationpath = File("${internaldatadir}/storage/emulated/0/Android/obb/$packagename")
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
                    if (!destinationpath.exists()) {
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
                            LOGS.error(err.toString())
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
                    Toast.makeText(context,"Obb Not Found on Obb folder make Shure your Game is installed Perfectly", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }





}