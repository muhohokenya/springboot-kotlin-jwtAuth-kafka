package com.example.udemyjwtauth.dto

data class FedexTokenRequest(
    val client_id:String,
    val client_secret:String,
    val grant_type:String,
)
