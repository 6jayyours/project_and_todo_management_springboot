package com.app.todos.controller;

import com.app.todos.entity.Todo;
import com.app.todos.request.CreateTodoRequest;
import com.app.todos.request.UpdateTodoRequest;
import com.app.todos.response.Response;
import com.app.todos.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



/**
 * Controller for managing to-do tasks, including adding, updating, deleting, and retrieving todos.
 */
@RestController
@RequestMapping("api/v1/todo")
@CrossOrigin(origins = "http://localhost:5173")
public class TodoController {
    private final TodoService todoService;


    /**
     * Constructor for dependency injection of TodoService.
     */
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    /**
     * Endpoint for adding a new to-do item for a specific user.
     */
    @PostMapping("/{id}")
    public ResponseEntity<Response> add(@PathVariable Long id, @RequestBody CreateTodoRequest request) {
        return todoService.addTodo(id,request);
    }


    /**
     * Endpoint for updating an existing to-do item.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable Long id, @RequestBody UpdateTodoRequest request) {
        return todoService.updateTodo(id,request);
    }

    /**
     * Endpoint for deleting a to-do item by ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        return todoService.deleteTodo(id);
    }


    /**
     * Endpoint for updating the status of a to-do item (e.g., marking it as completed).
     */
    @PutMapping("/status/{id}")
    public ResponseEntity<Response> updateStatus(@PathVariable Long id) {
        return todoService.updateTodoStatus(id);
    }


    /**
     * Endpoint for retrieving all to-do items associated with a specific user.
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<Todo>> getTodos(@PathVariable Long id) {
        return todoService.getAllTodos(id);
    }

}
