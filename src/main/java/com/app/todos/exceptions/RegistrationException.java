package com.app.todos.exceptions;


/**
 * Custom exception class that is thrown when an error occurs during user registration.
 */
public class RegistrationException extends RuntimeException{
    public RegistrationException(String message) {
        super(message);
    }
}
