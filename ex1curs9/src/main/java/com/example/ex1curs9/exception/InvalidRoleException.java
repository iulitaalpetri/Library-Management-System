package com.example.ex1curs9.exception;

public class InvalidRoleException extends RuntimeException {
    public InvalidRoleException() {
        super("The role is invalid!");
    }
}
