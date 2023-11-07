package com.example.officecafe

import com.google.gson.annotations.SerializedName

data class GetBalanceRequest(
    @SerializedName("email") val email: String
)
