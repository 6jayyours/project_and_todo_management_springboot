package com.app.todos.controller;

import com.app.todos.entity.Todo;
import com.app.todos.request.CreateTodoRequest;
import com.app.todos.request.UpdateTodoRequest;
import com.app.todos.response.Response;
import com.app.todos.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Response> add(@PathVariable Long id, @RequestBody CreateTodoRequest request) {
        return todoService.addTodo(id,request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable Long id, @RequestBody UpdateTodoRequest request) {
        return todoService.updateTodo(id,request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        return todoService.deleteTodo(id);
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<Response> updateStatus(@PathVariable Long id, @RequestParam boolean status) {
        return todoService.updateTodoStatus(id,status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Todo>> getTodos(@PathVariable Long id) {
        return todoService.getAllTodos(id);
    }

    @GetMapping("/export")
    public ResponseEntity<Response> exportAsGist() {
        return todoService.exportTodoAsGist();
    }
}
