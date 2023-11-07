package com.example.officecafe

import com.google.gson.annotations.SerializedName

data class RequestModelOtp(
    @SerializedName("email") val email: String?,
    @SerializedName("otp") val otp:Int
)
