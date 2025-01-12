package com.example.ex1curs9.exception;

public class InvalidUsernameException extends RuntimeException {
    public InvalidUsernameException() {
        super("The username is invalid!");
    }
}
