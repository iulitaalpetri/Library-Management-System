package com.example.ex1curs9.exception;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException() {
        super("The requested cart was not found.");
    }
}