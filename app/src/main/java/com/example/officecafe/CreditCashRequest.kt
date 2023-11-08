package com.example.officecafe

import com.google.gson.annotations.SerializedName

data class CreditCashRequest(
    @SerializedName("email")
    val email: String
)
