package com.example.udemyjwtauth.repository

import com.example.udemyjwtauth.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository:JpaRepository<Customer,Long> {
}