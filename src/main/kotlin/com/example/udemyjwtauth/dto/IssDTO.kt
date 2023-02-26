package com.example.udemyjwtauth.dto

import jdk.jfr.Timestamp
import java.time.LocalDate

data class IssDTO(
    val timestamp: String,
    val latitude:String,
    val longitude:String,
)