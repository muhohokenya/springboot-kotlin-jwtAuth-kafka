package com.example.udemyjwtauth.dto

import com.example.udemyjwtauth.entity.Address

data class EmployeeDto(
    val id:Long,
    val name:String,
    val age:Int,
    val address: Address
)