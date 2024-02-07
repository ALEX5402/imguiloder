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
    @SerializedName("rng") val rng: Int,

    // libapi extra remove if panel does't support it
    @SerializedName("libs") val libs: String,
    @SerializedName("notice_body") val notice_body: String,
    @SerializedName("notice_title") val notice_title: String,
    @SerializedName("noticemode") val noticemode: Boolean,
    @SerializedName("opentime") val opentime: String,
    @SerializedName("servermeassage") val servermeassage: String,
    @SerializedName("serverstatus") val serverstatus: Boolean,
    @SerializedName("zippassmode") val zippassmode: Boolean
)