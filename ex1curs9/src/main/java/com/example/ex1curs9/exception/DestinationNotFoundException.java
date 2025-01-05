package com.example.ex1curs9.exception;

public class DestinationNotFoundException extends RuntimeException {
    public DestinationNotFoundException(long id) {
        super("The destination with id " + id + " doesn't exist.");
    }
}
