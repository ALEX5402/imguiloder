package com.alex.mmop.authapi

import com.google.errorprone.annotations.Keep


@Keep
data class kuroapi(
    val userkey: String,
    val uuid : String,
    val androidid : String,
    val devicemodel : String,
    val devicebrand : String
)


