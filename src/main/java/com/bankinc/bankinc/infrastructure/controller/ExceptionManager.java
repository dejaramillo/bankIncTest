package com.bankinc.bankinc.infrastructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> businessException(RuntimeException e){
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
