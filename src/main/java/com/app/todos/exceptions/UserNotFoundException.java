package com.app.todos.exceptions;


/**
 * Custom exception class that is thrown when a requested user is not found
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}