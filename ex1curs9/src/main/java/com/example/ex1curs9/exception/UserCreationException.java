package com.example.ex1curs9.exception;

public class UserCreationException extends RuntimeException {
    public UserCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}

