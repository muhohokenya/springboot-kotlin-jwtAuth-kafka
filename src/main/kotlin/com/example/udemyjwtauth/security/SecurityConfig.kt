package com.example.udemyjwtauth.security

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InjectionPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.logging.Logger


@Configuration
@EnableMethodSecurity
class SecurityConfig(
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    val userDetailsService: UserDetailsService
) {


    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Scope("prototype")
    fun produceLogger(injectionPoint: InjectionPoint): org.slf4j.Logger? {
        val classOnWired = injectionPoint.member.declaringClass
        return LoggerFactory.getLogger(classOnWired)
    }

    @Bean
    @Override
    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager {
        return configuration.authenticationManager
    }

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .cors()
            .and()
            .csrf()
            .disable()
            .authorizeHttpRequests {
                it.requestMatchers("/api/auth/**","/api/v1/kafka/**","/error/**")
                    .permitAll()
                    .anyRequest().authenticated()
            }.exceptionHandling { exception ->
                exception.authenticationEntryPoint(jwtAuthenticationEntryPoint)
            }.sessionManagement { session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }.cors(Customizer.withDefaults())

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

    /*@Bean
    fun userDetailsService(): UserDetailsService {
        val user = User.builder()
            .username("jeremy")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build()

        val admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("password"))
            .roles("ADMIN")
            .build()

        return InMemoryUserDetailsManager(user, admin)
    }*/
}