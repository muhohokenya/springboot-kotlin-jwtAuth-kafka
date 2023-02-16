package com.example.udemyjwtauth.dto

import com.example.udemyjwtauth.entity.Guardian

data class StudentDto(
    val id: Long?,
    val admNo: String,
    val age: String,
    val name: String,
    var guardian: Guardian
)
