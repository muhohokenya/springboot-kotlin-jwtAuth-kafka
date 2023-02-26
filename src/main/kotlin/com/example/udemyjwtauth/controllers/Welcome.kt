package com.example.udemyjwtauth.controllers

import com.example.udemyjwtauth.dto.UserDto
import com.example.udemyjwtauth.entity.User
import com.example.udemyjwtauth.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@RequestMapping("api/")
class Welcome(
    val userService: UserService,
) {


    private fun mapToDto(user: User): UserDto {
        return UserDto(
            user.id,
            user.name,
            user.email,
            user.username,
        )
    }


    @CrossOrigin(origins = ["http://localhost:3000/"])
    @GetMapping("users")
    fun getUsers(): ResponseEntity<MutableList<UserDto>> {
        val users = userService.getAllUsers()
            .stream()
            .map { user ->
                mapToDto(user)
            }
            .collect(Collectors.toList())
        return ResponseEntity.ok(users)
    }

    @GetMapping("users/{id}")
    fun getUserById(@PathVariable id: Long): User {
        return userService.getUserById(id)
    }
}