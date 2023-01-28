package com.example.udemyjwtauth.security

import com.example.udemyjwtauth.entity.Role
import com.example.udemyjwtauth.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
            .orElseThrow { UsernameNotFoundException("User Not Found with $username") }

        val authorities = user
            .roles
            .stream()
            .map { role: Role ->
                SimpleGrantedAuthority(
                    role.name
                )
            }.collect(Collectors.toSet())

        user.roles
            .stream()
            .map { role -> SimpleGrantedAuthority(role.name) }
            .collect(Collectors.toSet())


        return User(user.username, user.password,authorities)
    }
}