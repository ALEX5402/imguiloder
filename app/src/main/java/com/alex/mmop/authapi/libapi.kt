package com.alex.mmop.authapi

import com.google.gson.annotations.SerializedName

data class libapi(
    @SerializedName("libs") val libs: String,
    @SerializedName("notice_body") val notice_body: String,
    @SerializedName("notice_title") val notice_title: String,
    @SerializedName("noticemode") val noticemode: Boolean,
    @SerializedName("opentime") val opentime: String,
    @SerializedName("servermeassage") val servermeassage: String,
    @SerializedName("serverstatus") val serverstatus: Boolean,
    @SerializedName("zippassmode") val zippassmode: Boolean
)
