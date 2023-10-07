package com.example.UserAuthenticationProject.Controllers;

import com.example.UserAuthenticationProject.Exceptions.OTPException;
import com.example.UserAuthenticationProject.Exceptions.UserProfileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ OTPException.class, UserProfileNotFoundException.class })
    public ResponseEntity<String> handleCustomException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}