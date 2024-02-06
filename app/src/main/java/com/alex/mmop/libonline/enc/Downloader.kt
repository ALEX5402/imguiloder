package com.alex.mmop.libonline.enc


/*
class Downloader(
    responce:Responce,
    private val downloadlin: String,
    private val ahahaha: String,
    private val dwaawdawfd : String,
    private val zipmode : Boolean
) : AsyncTask<Void,Void,String>() {
   private val responce = WeakReference(responce)
    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg voids: Void?): String? {
        try {

            val downloadlink = downloadlin.let {
                URL(it)
            }
            val tempDir = System.getProperty("java.io.tmpdir")
            val timestamp = System.currentTimeMillis()
            val outputfile = File(tempDir, "$timestamp.zip")
            BufferedInputStream(URL(downloadlink.toString()).openStream()).use { inputStream ->
                FileOutputStream(outputfile).use { outputStream ->
                    val dataBuffer = ByteArray(1024)
                    var bytesRead: Int
                    while (inputStream.read(dataBuffer, 0, 1024).also { bytesRead = it } != -1) {
                        outputStream.write(dataBuffer, 0, bytesRead)
                    }
                }
                outputfile.setExecutable(true)
                outputfile.setReadable(true)
                outputfile.setWritable(true)
                return outputfile.absoluteFile.toString()
            }

        } catch (err : Exception)
        {
            err.printStackTrace()
        }
        return "Background task err"
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: String?) {
        val file = result?.let { File(it) }
        val destDirectory = File(System.getProperty("java.io.tmpdir") as String)
        try {
            if (file != null) {
                if (file.exists()) {
                    val Zip = ZipFile(file)
                    try {
                        if (Zip.isEncrypted) {
                            if (zipmode)
                            {
                                Zip.setPassword(dwaawdawfd.toCharArray())
                            }else{
                                Zip.setPassword(ahahaha.toCharArray())
                            }
                            Zip.extractAll(destDirectory.toString())
                        }else{
                            Zip.extractAll(destDirectory.toString())
                        }
                       responce.get()?.loadlib()
                    } catch (Err: ZipException) {
                        responce.get()?.downloadagain()
                        Err.printStackTrace()
                   //     Log.e("alex","FAILEd")
                    }
                } else {
                    responce.get()?.downloadagain()
               //     Log.e("alex","NOT EXIST")
                }
            }

        } catch (err: Exception) {
            err.printStackTrace()
            responce.get()?.downloadagain()
          //  Log.e("alex","failes2")
        }

    }
}*/
