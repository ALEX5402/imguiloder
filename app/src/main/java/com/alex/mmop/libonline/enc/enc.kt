package com.alex.mmop.libonline.enc

import android.util.Base64
import com.alex.mmop.api.any

import java.nio.charset.StandardCharsets
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object enc {
    val codes = any.valueof()
   private var SECRET_KEY = codes[1]
        fun decrypt(encryptedData: String): String {
            val secretKeySpec = SecretKeySpec(SECRET_KEY.toByteArray(), codes[3])
            val cipher = Cipher.getInstance(codes[2])
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec)
            val decryptedData = cipher.doFinal(Base64.decode(encryptedData, DEFAULT_BUFFER_SIZE))
            return String(decryptedData, StandardCharsets.UTF_8)
        }

}