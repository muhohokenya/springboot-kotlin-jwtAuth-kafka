package com.example.udemyjwtauth.services

import com.example.udemyjwtauth.dto.CustomerDto
import com.example.udemyjwtauth.entity.Customer
import com.example.udemyjwtauth.entity.Order
import com.example.udemyjwtauth.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository
) {

    fun saveCustomer(customerDto: CustomerDto): Customer {
        val customer = Customer(
            customerDto.id,
            customerDto.name,
            customerDto.orders
        )
        return customerRepository.save(customer)
    }
}