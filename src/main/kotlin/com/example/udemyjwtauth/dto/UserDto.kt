package com.example.udemyjwtauth.dto

import com.example.udemyjwtauth.entity.Role

data class UserDto(
    val id:Long,
    val name:String,
    val email:String,
    val username:String
)
