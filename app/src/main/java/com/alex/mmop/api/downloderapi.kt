package com.alex.mmop.api

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
                    librl:String,
                    context: Context) {
        val ioscope = CoroutineScope(Dispatchers.IO)
        runBlocking {
            val tempdir = System.getProperty("java.io.tmpdir")
            val currtime = System.currentTimeMillis()
            val outputfile = File(tempdir, "$currtime.zip")

            ioscope.launch {
                librl.let {
                    Log.e("LinUrl", it)
                    try {
                        val url = URL(it)
                        BufferedInputStream(url.openStream()).use { input ->
                            FileOutputStream(outputfile).use { output ->
                                val dataBuffer = ByteArray(1024)
                                var bytesRead: Int
                                while (input.read(dataBuffer, 0, 1024)
                                        .also { bytesRead = it } != -1
                                ) {
                                    output.write(dataBuffer, 0, bytesRead)
                                }
                            }
                            outputfile.setExecutable(true)
                            outputfile.setReadable(true)
                            outputfile.setWritable(true)
                        }
                    } catch (exeption: IOException) {
                        exeption.printStackTrace()
                        ondownloadfailed(exeption.toString())
                    } catch (err: Exception) {
                        err.printStackTrace()
                        ondownloadfailed(err.toString())
                    }
                    val datadir = context.filesDir
                    val filelist = datadir.listFiles()
                    for (file in filelist) {
                        if (file != null) {
                            file.delete()
                            LOGS.warn("CLEARED $file")
                        }
                    }
                    outputfile.let { thezip ->
                        val zip = ZipFile(thezip)
                        try {
                            if (zip.isEncrypted) {
                                zip.setPassword(zippassword.toCharArray())
                            }
                            zip.extractAll(datadir.absolutePath)
                            val filelist2 = datadir.listFiles()
                            filelist2?.let { files->
                                for (file in files) {
                                    if (file != null) {
                                        val mainlib = file.name.equals(any.actuallibname)
                                        if (mainlib){
                                            file.setExecutable(true)
                                            file.setReadable(true)
                                            file.setWritable(true)
                                            LOGS.warn( "PERMISSON DONE $file")
                                            ondownloadsucess()
                                        }
                                    }
                                }
                            }
                        } catch (err: ZipException) {
                            err.printStackTrace()
                            ondownloadfailed(err.toString())
                        } catch (err: IOException) {
                            err.printStackTrace()
                            ondownloadfailed(err.toString())
                        } catch (err: Exception) {
                            err.printStackTrace()
                            ondownloadfailed(err.toString())
                        }
                    }
                }
            }
        }
    }
}