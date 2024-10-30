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
@CrossOrigin(origins = "http://localhost:5173")
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

    @PutMapping("/status/{id}")
    public ResponseEntity<Response> updateStatus(@PathVariable Long id) {
        return todoService.updateTodoStatus(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Todo>> getTodos(@PathVariable Long id) {
        return todoService.getAllTodos(id);
    }

}
