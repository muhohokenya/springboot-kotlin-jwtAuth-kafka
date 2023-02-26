package com.example.udemyjwtauth.repository

import com.example.udemyjwtauth.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository:JpaRepository<Order,Long> {
}