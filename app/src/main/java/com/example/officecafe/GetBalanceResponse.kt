package com.example.officecafe

import com.google.gson.annotations.SerializedName

data class GetBalanceResponse(
    @SerializedName("data")
    val `data`: GetBalanceResponseData,
    @SerializedName("status")
    val status: Int
)
