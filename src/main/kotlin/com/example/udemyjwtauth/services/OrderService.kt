package com.example.udemyjwtauth.services

import com.example.udemyjwtauth.dto.OrderDto
import com.example.udemyjwtauth.entity.Order
import com.example.udemyjwtauth.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(
    val orderRepository: OrderRepository
) {

    fun createOrder(orderDto: OrderDto): Order {
        val order = Order(
            orderDto.id,
            orderDto.name,
        )
        return orderRepository.save(order)
    }
}