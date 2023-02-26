package com.example.udemyjwtauth.repository

import com.example.udemyjwtauth.entity.Address
import org.springframework.data.jpa.repository.JpaRepository

interface AddressRepository:JpaRepository<Address,Long> {
}