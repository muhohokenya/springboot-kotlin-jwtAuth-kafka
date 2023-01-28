package com.example.udemyjwtauth.dto

import org.springframework.beans.factory.annotation.Value

data class JwtAuthResponse(
    val accessKey:String,
    val accessType: String = "Bearer",
    val expirationDate: String = "72000"
    )
