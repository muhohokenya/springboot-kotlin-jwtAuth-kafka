package com.example.udemyjwtauth.security

import com.sun.nio.sctp.IllegalReceiveException
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Header
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.interceptor.CacheOperationInvoker.ThrowableWrapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ExceptionHandler
import java.security.Key
import java.util.Base64.Decoder
import java.util.Date
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.FlexibleTypeDeserializer.ThrowException

@Component
class JwtTokenProvider {
    @Value("\${app.jwt-secret}")
    val jwtSecret = ""

    @Value("\${app.jwt-expiration-milliseconds}")
    val jwtExpirationDate:Long? = null



    fun generateToken(authentication:Authentication):String{
        val username = authentication.name
        val currentDate = Date()
        val expirationDate = Date(currentDate.time + jwtExpirationDate!!)
        return Jwts.builder()
            .setSubject(username)
            .setExpiration(expirationDate)
            .setIssuedAt(currentDate)
            .signWith(key())
            .compact()
    }

    fun getUserName(token:String):String{
        val claims =  Jwts.parserBuilder()
            .setSigningKey(key())
            .build()
            .parseClaimsJws(token)
            .body
        return claims.subject
    }

//    @Throws(SignatureException::class)
    fun validateToken(token:String): Boolean {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parse(token)
                .body
            return true
        } catch (e: MalformedJwtException) {
            handleMalformedJwtException()
            return false
        }catch (expired:ExpiredJwtException){
         return false
        }catch (unsupported:UnsupportedJwtException){
            handleUnsupportedJwtException()
            return false
        }catch (illegal:IllegalReceiveException){
            throw IllegalReceiveException("Error with the token")
        }catch (e:JwtException){
            println(e)
            return false
        }
    }

    @ExceptionHandler(UnsupportedJwtException::class)
    fun handleUnsupportedJwtException():ResponseEntity<Any>{
        return ResponseEntity(HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(MalformedJwtException::class)
    fun handleMalformedJwtException():ResponseEntity<Any>{
        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    private fun key(): Key {
        return Keys.hmacShaKeyFor(
            Decoders.BASE64URL.decode(jwtSecret)
        )
    }
}