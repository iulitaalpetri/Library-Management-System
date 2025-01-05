package com.example.ex1curs9.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super("The requested order was not found.");
    }
}