package com.alex.mmop.api

import android.annotation.SuppressLint
import android.content.Context
import com.alex.mmop.viewmodels.modelmain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

object Filesapi {
    private fun copyFile(sourceFile: File, destDir: File,context: Context) {
        if (!sourceFile.exists()) {
            // Source file does not exist
            return
        }
        if (!destDir.exists()) {
            destDir.mkdirs()
        }
        val scope = CoroutineScope(Dispatchers.IO)
        val destFile = File(destDir, sourceFile.name)
        scope.launch {
            try {
                val input = FileInputStream(sourceFile)
                val output = FileOutputStream(destFile)
                val buffer = ByteArray(1024)
                var lenth : Int

                while (input.read(buffer).also { lenth = it } > 0){
                    output.write(buffer,0,lenth)
                }
                input.close()
                output.close()
            }catch (exeptiom : IOException){
                exeptiom.printStackTrace()
            }

        }
    }
    @SuppressLint("Range")

    fun isobb(): Boolean{
       val viewmodel = modelmain()
        viewmodel.isobb = true









    return false
    }

}