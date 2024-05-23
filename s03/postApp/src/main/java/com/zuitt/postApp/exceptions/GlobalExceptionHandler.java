package com.zuitt.postApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// @ControllerAdvice annotation signals that this class will handle exceptions globally for all controllers
@ControllerAdvice
public class GlobalExceptionHandler {

    // Specifies that this method handles UserException
    @ExceptionHandler(UserException.class)

    public ResponseEntity<Object> handleUserException(UserException ex) {
        // Create a ResponseEntity with the exception's message and HTTP status 409 (CONFLICT)
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
