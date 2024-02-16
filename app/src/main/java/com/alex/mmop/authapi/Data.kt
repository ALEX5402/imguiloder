package com.alex.mmop.authapi

import com.google.errorprone.annotations.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Data(
    @SerializedName("Enc") val Enc: String,
    @SerializedName("credit") val credit: String,
    @SerializedName("device") val device: String,
    @SerializedName("mod_status") val mod_status: String,
    @SerializedName("modname") val modname: String,
    @SerializedName("EXP") val EXP : String,
    @SerializedName("token") val token: String,
    @SerializedName("rng") val rng: Int,

    // libapi extra remove if panel does't support it
    @SerializedName("libs") val libs: String,
    @SerializedName("liburlgl") val liburlgl: String,
    @SerializedName("liburlvng") val liburlvng: String,
    @SerializedName("liburltiwan") val liburltiwan: String,
    @SerializedName("liburlcnina") val liburlcnina: String,
    @SerializedName("linkorea") val linkorea: String,

    @SerializedName("noticemode") val noticemode: String, //fortemp
    @SerializedName("version") val version: String,
    @SerializedName("updatedapk") val updatedapk: String,
    @SerializedName("serverstatus") val serverstatus: String,
    @SerializedName("zippassmode") val zippassmode: String,

    @SerializedName("bgmistatus") val bgmistatus: String,
    @SerializedName("globalstatus") val globalstatus: String,
    @SerializedName("koreastatus") val koreastatus: String,
    @SerializedName("chinastatus") val chinastatus: String,
    @SerializedName("tiwanstatus") val tiwanstatus: String,
    @SerializedName("vngstatus") val vngstatus: String,


    /*
 bgmistatus
 globalstatus
 koreastatus
 chinastatus
 tiwanstatus
 vngstatus
     */
)