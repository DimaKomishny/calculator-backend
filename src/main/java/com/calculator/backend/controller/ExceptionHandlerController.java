package com.calculator.backend.controller;


import com.calculator.backend.exception.EquationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@ControllerAdvice
@RestController
public class ExceptionHandlerController {

    private static final String MESSAGE_NAME = "message";

    @ExceptionHandler(EquationException.class)
    public ResponseEntity<Map<String, String>> handleException(Exception e) {
        return new ResponseEntity<>(Map.of(MESSAGE_NAME, e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
