package com.example.ex1curs9.exception;

public class DuplicateDestinationException extends RuntimeException {
    public DuplicateDestinationException() {
        super("A destination with the same name already exists.");
    }
}
