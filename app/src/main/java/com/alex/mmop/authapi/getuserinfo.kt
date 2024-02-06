package com.alex.mmop.authapi

import com.google.gson.annotations.SerializedName

data class getuserinfo(
    @SerializedName("status") val status : Boolean,
    @SerializedName("data") val data : Data,
    @SerializedName("reason") val reason : String
)