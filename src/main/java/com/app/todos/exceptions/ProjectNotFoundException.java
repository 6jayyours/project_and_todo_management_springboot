package com.app.todos.exceptions;


/**
 * Custom exception class that is thrown when a requested project is not found
 */
public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(String message) {
        super(message);
    }
}
