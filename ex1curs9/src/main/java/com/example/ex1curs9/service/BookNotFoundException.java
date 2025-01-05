package com.example.ex1curs9.service;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("The requested book was not found.");
    }
}
