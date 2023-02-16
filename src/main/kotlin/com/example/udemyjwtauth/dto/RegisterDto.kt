package com.example.udemyjwtauth.dto

import jakarta.validation.constraints.NotEmpty

data class RegisterDto(
    /*
    {
       "name":"jeremy",
       "email":"jeremy@gmail.com",
       "password":"password",
       "username":"jeremy",
    }
    * */
    @field:NotEmpty(message = "The Name cannot be empty")
    val name:String,

    @field:NotEmpty(message = "The Email cannot be empty")
    val email:String,

    @field:NotEmpty(message = "The Password cannot be empty")
    val password:String,

    @field:NotEmpty(message = "The Username cannot be empty")
    val username:String,
)