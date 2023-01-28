package com.example.udemyjwtauth.controllers

import com.example.udemyjwtauth.dto.JwtAuthResponse
import com.example.udemyjwtauth.dto.LoginDto
import com.example.udemyjwtauth.dto.RegisterDto
import com.example.udemyjwtauth.entity.User
import com.example.udemyjwtauth.repository.UserRepository
import com.example.udemyjwtauth.security.JwtTokenProvider
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController

@RequestMapping("api/auth/")
class AuthController(
    val authenticationManager: AuthenticationManager,
    val jwtTokenProvider: JwtTokenProvider,
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder,
) {


    @PostMapping("login")
    fun login(@RequestBody loginDto: LoginDto):ResponseEntity<JwtAuthResponse> {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(loginDto.username, loginDto.password)
        )

        // Set the security context
        SecurityContextHolder.getContext().authentication = authentication

        val jwt =  jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(JwtAuthResponse(jwt))
    }


    @PostMapping("register")
    fun register(@RequestBody registerDto: RegisterDto): User {
       return userRepository.save(
            User(1,
                registerDto.name,
                registerDto.email,
                passwordEncoder.encode(registerDto.password),
                registerDto.username,
            )
        )
    }
}