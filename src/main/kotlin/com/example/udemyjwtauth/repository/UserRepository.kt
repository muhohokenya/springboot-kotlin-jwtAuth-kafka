package com.example.udemyjwtauth.repository

import com.example.udemyjwtauth.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository:JpaRepository<User,Long> {

    fun findUserByEmail(email:String):Optional<User>
    fun findByUsername(email:String):Optional<User>

    fun findUserByUsernameOrEmail(username: String, email: String):Optional<User>

    fun findUserByUsername(email:String):Optional<User>

    fun existsUserByUsername(username:String):Boolean

    fun existsUserByEmail(email:String):Boolean
}