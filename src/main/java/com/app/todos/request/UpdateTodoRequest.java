package com.app.todos.request;

import lombok.Data;


@Data
public class UpdateTodoRequest {
    private String description;
    private boolean status;
}
