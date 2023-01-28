package com.example.udemyjwtauth.service

import com.example.udemyjwtauth.entity.Student
import com.example.udemyjwtauth.entity.User
import com.example.udemyjwtauth.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository
) {

    fun getAllUsers(): MutableList<User> {
        return userRepository.findAll()
    }
}