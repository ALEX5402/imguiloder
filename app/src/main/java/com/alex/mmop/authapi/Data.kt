package com.alex.mmop.authapi

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("Enc") val Enc: String,
    @SerializedName("credit") val credit: String,
    @SerializedName("device") val device: String,
    @SerializedName("mod_status") val mod_status: String,
    @SerializedName("modname") val modname: String,
    @SerializedName("EXP") val EXP : String,
    @SerializedName("token") val token: String,
    @SerializedName("rng") val rng: Int
)