package com.example.udemyjwtauth.exception

import io.jsonwebtoken.MalformedJwtException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(
        ex: HttpMessageNotReadableException,
        uri:WebRequest
    ):ResponseEntity<Any>{
        return ResponseEntity.badRequest().body(
            ErrorResponse(
            ex.localizedMessage,
        ))
    }


    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolationException(
        exception: DataIntegrityViolationException):ResponseEntity<Any>{
        return ResponseEntity.badRequest().body(
            ErrorResponse(
                exception.message,
            )
        )
    }

    @ExceptionHandler(MalformedJwtException::class)
    fun handleMalformedJwtException(): ResponseEntity<Any> {
        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }
}

data class ErrorResponse(
    val message:String?,
)