package com.example.ex1curs9.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler({DuplicateDestinationException.class})
    public ResponseEntity handle(DuplicateDestinationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({DestinationNotFoundException.class})
    public ResponseEntity handle(DestinationNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }


    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorized(UnauthorizedException e) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(e.getMessage());
    }
    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<String> handleOutOfStock(OutOfStockException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
