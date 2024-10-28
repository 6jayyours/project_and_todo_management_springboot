package com.app.todos.request;

import com.app.todos.entity.Project;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateTodoRequest {
    private String description;
    private LocalDate createdDate;
}
