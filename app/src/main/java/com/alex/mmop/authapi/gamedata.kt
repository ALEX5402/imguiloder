package com.alex.mmop.authapi

import com.google.errorprone.annotations.Keep


@Keep
data class gamedata (
    val packagename : String,
    val icon : Int,
    val apkname : String,
    val version: String,
    val gamestatus : Boolean
)