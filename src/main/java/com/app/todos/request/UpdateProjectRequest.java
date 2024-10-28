package com.app.todos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateProjectRequest {
    @NotNull
    private String title;
}
