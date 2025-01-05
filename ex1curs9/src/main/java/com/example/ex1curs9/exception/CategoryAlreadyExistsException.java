package com.example.ex1curs9.exception;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException() {
        super("A category with this name already exists.");
    }
}
