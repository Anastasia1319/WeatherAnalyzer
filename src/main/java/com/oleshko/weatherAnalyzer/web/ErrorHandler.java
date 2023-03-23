package com.oleshko.weatherAnalyzer.web;

import com.oleshko.weatherAnalyzer.exception.NotConnectionException;
import com.oleshko.weatherAnalyzer.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity notFoundHandler(NotFoundException e) {
        log.error("Object can't be found. Cause: ", e);
        return new ResponseEntity("Sorry, we couldn't find the information you need.",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity notConnectionHandler(NotConnectionException e) {
        log.error("Failed to create connection. Cause: ", e);
        return new ResponseEntity("Unfortunately, work is underway on the server at the moment",
                HttpStatus.GATEWAY_TIMEOUT);
    }

    @ExceptionHandler
    public ResponseEntity exceptionHandler(Exception e) {
        log.error("Unexpected error. Cause: ", e);
        return new ResponseEntity("Sorry! Unexpected error. We are workig on...",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
