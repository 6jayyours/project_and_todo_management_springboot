package com.app.todos.exceptions;

/**
 * Custom exception class that is thrown when a requested todo_item is not found
 */
public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(String message) {
        super(message);
    }
}
