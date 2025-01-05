package com.example.ex1curs9.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException() {
        super("The requested category was not found.");
    }
}