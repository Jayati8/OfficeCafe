package com.example.officecafe

import com.google.gson.annotations.SerializedName

data class GetBalanceResponseData(
    @SerializedName("balance")
    val balance: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String
)
