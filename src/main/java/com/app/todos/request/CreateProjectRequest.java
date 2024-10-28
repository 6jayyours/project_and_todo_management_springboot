package com.app.todos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateProjectRequest {
    @NotNull
    private String title;

    @NotNull
    private LocalDate createdDate;
}
