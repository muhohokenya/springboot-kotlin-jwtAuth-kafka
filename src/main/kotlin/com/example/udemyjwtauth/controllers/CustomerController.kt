package com.example.udemyjwtauth.controllers

import com.example.udemyjwtauth.dto.CustomerDto
import com.example.udemyjwtauth.entity.Customer
import com.example.udemyjwtauth.services.CustomerService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/customer")
class CustomerController(
    val customerService: CustomerService
) {

    @PostMapping("save")
    fun saveCustomer(@RequestBody customerDto: CustomerDto): Customer {
        return customerService.saveCustomer(customerDto)
    }

    @PostMapping("order")
    fun saveOrder(){

    }

}