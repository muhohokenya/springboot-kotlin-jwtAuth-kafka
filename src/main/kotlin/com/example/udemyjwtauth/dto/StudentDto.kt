package com.example.udemyjwtauth.dto

import com.example.udemyjwtauth.entity.Course


data class StudentDto(
    val id: Long?,
    val admNo: String,
    val age: String,
    val name: String,
    val courses: MutableList<Course>,
)
