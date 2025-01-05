package com.example.ex1curs9.exception;

public class OrderAlreadyExistsException extends RuntimeException {
    public OrderAlreadyExistsException() {
        super("User already has a pending order.");
    }
}