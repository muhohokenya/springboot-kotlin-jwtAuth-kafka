package com.example.udemyjwtauth.controllers

import com.example.udemyjwtauth.dto.JwtAuthResponse
import com.example.udemyjwtauth.dto.LoginDto
import com.example.udemyjwtauth.dto.RegisterDto
import com.example.udemyjwtauth.entity.User
import com.example.udemyjwtauth.repository.UserRepository
import com.example.udemyjwtauth.security.JwtTokenProvider
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import java.util.function.Consumer


@RestController

@RequestMapping("api/auth/")
class AuthController(
    val authenticationManager: AuthenticationManager,
    val jwtTokenProvider: JwtTokenProvider,
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder,
) {


    @CrossOrigin(origins = ["http://localhost:3000/"])
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
    fun register(@Valid @RequestBody registerDto: RegisterDto): ResponseEntity<Any> {
        return try {
            val user = userRepository.save(
                User(
                    1,
                    registerDto.name,
                    registerDto.email,
                    passwordEncoder.encode(registerDto.password),
                    registerDto.username,
                )
            )
            ResponseEntity.ok(user)
        }catch (e: HttpMessageNotReadableException){
            ResponseEntity.ok("Error")
        }
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(
        ex: MethodArgumentNotValidException
    ): Map<String, String?>? {
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.forEach(Consumer { error: ObjectError ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.getDefaultMessage()
            errors[fieldName] = errorMessage
        })
        return errors
    }
}