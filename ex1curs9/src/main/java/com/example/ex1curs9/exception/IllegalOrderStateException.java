package com.example.ex1curs9.exception;

public class IllegalOrderStateException extends RuntimeException {
    public IllegalOrderStateException() {
        super("Order cannot be modified in its current state.");
    }
}