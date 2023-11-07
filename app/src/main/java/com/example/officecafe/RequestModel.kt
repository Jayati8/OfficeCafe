package com.example.officecafe

import com.google.gson.annotations.SerializedName

data class RequestModel(
    @SerializedName("email") val email: String
)
