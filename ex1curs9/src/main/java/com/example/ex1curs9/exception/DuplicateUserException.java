package com.example.ex1curs9.exception;

public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException() {
        super("A user with the same username already exists.");
    }
}
