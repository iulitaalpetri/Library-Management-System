package com.example.ex1curs9.exception;


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("The requested user was not found.");
    }
}