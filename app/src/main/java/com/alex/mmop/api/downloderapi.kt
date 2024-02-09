package com.alex.mmop.api

import android.content.Context
import android.util.Log
import com.alex.mmop.viewmodels.modelmain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.lingala.zip4j.ZipFile
import net.lingala.zip4j.exception.ZipException
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL

object downloderapi {

    fun downloadlib(ondownloadsucess:()->Unit,
                    ondownloadfailed:(reason:String) ->Unit,
                    zippassword:String,
                    context: Context){
        val viewModel = modelmain()

        val ioscope = CoroutineScope(Dispatchers.IO)


        runBlocking {
            val tempdir = System.getProperty("java.io.tmpdir")
            val currtime = System.currentTimeMillis()
            val outputfile = File(tempdir,"$currtime.zip")
            ioscope.launch {
               try {
                   val url= URL(viewModel.libdownloadurl)
                   BufferedInputStream(url.openStream()).use { input->
                       FileOutputStream(outputfile).use {output->
                           val dataBuffer = ByteArray(1024)
                           var bytesRead: Int
                           while (input.read(dataBuffer, 0, 1024).also { bytesRead = it } != -1) {
                               output.write(dataBuffer, 0, bytesRead)
                           }
                       }

                       outputfile.setExecutable(true)
                       outputfile.setReadable(true)
                       outputfile.setWritable(true)
                   }
               }catch(exeption :IOException){
                   exeption.printStackTrace()
                   ondownloadfailed(exeption.toString())
               }catch (err:Exception){
                   err.printStackTrace()
                   ondownloadfailed(err.toString())
               }
                val appllicationinfo = context.packageManager.getApplicationInfo(context.packageName,0)
                val nativelibdir = appllicationinfo.nativeLibraryDir
                val nativelibs = File(nativelibdir)
                val filelist = nativelibs.listFiles()
                for (file in filelist){
                    if (file != null){
                        file.delete()

                        Log.w("FILE", "DELETED $file")
                    }
                }
                outputfile.let { thezip ->
                    val zip = ZipFile(thezip)
                    try {
                        if (zip.isEncrypted){
                            zip.setPassword(zippassword.toCharArray())
                        }
                        zip.extractAll(nativelibdir)
                        val filelist2 = nativelibs.listFiles()
                        for (file in filelist2){
                            if (file != null){
                                file.setExecutable(true)
                                file.setReadable(true)
                                file.setWritable(true)
                                Log.w("FILE", "PERMISSON DONE $file")
                            }
                        }
                        delay(200) // set a delay to make permisson apply please don't remove
                        ondownloadsucess()
                    }catch (err : ZipException){
                        err.printStackTrace()
                        ondownloadfailed(err.toString())
                    }catch (err:IOException){
                        err.printStackTrace()
                        ondownloadfailed(err.toString())
                    }catch (err:Exception){
                        err.printStackTrace()
                        ondownloadfailed(err.toString())
                    }
                }
            }
        }
    }



}