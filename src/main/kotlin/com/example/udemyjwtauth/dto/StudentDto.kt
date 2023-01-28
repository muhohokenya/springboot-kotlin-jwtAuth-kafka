package com.example.udemyjwtauth.dto

import com.example.udemyjwtauth.entity.Guardian
import com.example.udemyjwtauth.entity.Role
import jakarta.persistence.Id

data class StudentDto(
    val id: Long?,
    val admNo: String,
    val age: String,
    val name: String,
    val guardian: Guardian
)
