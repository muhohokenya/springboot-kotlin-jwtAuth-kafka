package com.example.udemyjwtauth.security

import com.sun.nio.sctp.IllegalReceiveException
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.interceptor.CacheOperationInvoker.ThrowableWrapper
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
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

    @Throws(SignatureException::class)
    fun validateToken(token:String): Boolean {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parse(token)
                .body
            return true
        } catch (e: MalformedJwtException) {
//            TODO("Not yet implemented")
            return false
        }catch (expired:ExpiredJwtException){
            return false
            // TODO: To be implemented 
        }catch (unsupported:UnsupportedJwtException){
            TODO("Not yet implemented")
        }catch (illegal:IllegalReceiveException){
            TODO("Not yet implemented")
        }
    }

    private fun key(): Key {
        return Keys.hmacShaKeyFor(
            Decoders.BASE64URL.decode(jwtSecret)
        )
    }
}