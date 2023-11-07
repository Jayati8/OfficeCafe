package com.example.officecafe

data class OTPResponseData(
    val name: String,
    val email: String,
    val balance: Int,
    val token: String,
    val message: String,
    val isotpverified: Int
)
