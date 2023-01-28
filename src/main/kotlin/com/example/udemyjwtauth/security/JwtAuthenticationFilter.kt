package com.example.udemyjwtauth.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider,
    private val userDetailsService: UserDetailsService
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = getUserNameFromToken(request);

        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
            val username = jwtTokenProvider.getUserName(token)
            val userDetails = userDetailsService.loadUserByUsername(username)
            val authenticationToken = UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.authorities
            )

            authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authenticationToken
        }

        filterChain.doFilter(request,response)
    }


    private fun getUserNameFromToken(request: HttpServletRequest): String {
        val bearer = request.getHeader("Authorization")

        if(StringUtils.hasText(bearer) &&  bearer.startsWith("Bearer ")) {
            return bearer.substring(7, bearer.length)
        }
        return ""
    }
}