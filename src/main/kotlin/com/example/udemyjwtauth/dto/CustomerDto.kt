package com.example.udemyjwtauth.dto

import com.example.udemyjwtauth.entity.Order

data class CustomerDto(
    val id:Long,
    val name:String,
    val orders:MutableList<Order>,
)
