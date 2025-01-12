package com.example.ex1curs9.exception;

public class AdminLimitExceededException extends RuntimeException {
    public AdminLimitExceededException(String message) {
        super(message);
    }
}
