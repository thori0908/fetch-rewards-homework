package com.receiptProcessor.receiptProcessor.controllers

import com.receiptProcessor.receiptProcessor.exceptions.InvalidArgumentException
import com.receiptProcessor.receiptProcessor.exceptions.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
object GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleNotFound(exception: ResourceNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(ErrorResponse.of(exception))
    }

    @ExceptionHandler(InvalidArgumentException::class)
    fun handle(exception: InvalidArgumentException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(ErrorResponse.of(exception))
    }
}

data class ErrorResponse(val message: String?) {
    companion object {
        fun of(notFoundException: ResourceNotFoundException): ErrorResponse =
            ErrorResponse(notFoundException.message)

        fun of(invalidArgumentException: InvalidArgumentException): ErrorResponse =
            ErrorResponse(invalidArgumentException.message)
    }
}
