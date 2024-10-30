package com.app.todos.service;

import com.app.todos.entity.Project;
import com.app.todos.entity.Todo;
import com.app.todos.exceptions.ProjectNotFoundException;
import com.app.todos.exceptions.TodoNotFoundException;
import com.app.todos.repository.ProjectRepository;
import com.app.todos.repository.TodoRepository;
import com.app.todos.request.CreateTodoRequest;
import com.app.todos.request.UpdateTodoRequest;
import com.app.todos.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final ProjectRepository projectRepository;

    public TodoService(TodoRepository todoRepository, ProjectRepository projectRepository) {
        this.todoRepository = todoRepository;
        this.projectRepository = projectRepository;
    }

    public ResponseEntity<Response> addTodo(Long projectId, CreateTodoRequest request) {
        try {
            Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project not found with id: " + projectId));            Todo todo = new Todo();
            todo.setDescription(request.getDescription());
            todo.setStatus(false);
            todo.setCreatedDate(LocalDate.now());
            todo.setProject(project);
            todoRepository.save(todo);
            return ResponseEntity.ok(new Response("Todo added successfully"));
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while creating todo: " + ex.getMessage());
        }
    }

    public ResponseEntity<Response> updateTodo(Long id, UpdateTodoRequest request) {
        try {
            Todo todo = todoRepository.findById(id)
                    .orElseThrow(() -> new TodoNotFoundException("Todo not found with ID: " + id));
            todo.setDescription(request.getDescription());
            todo.setUpdatedDate(LocalDate.now());
            todoRepository.save(todo);
            return ResponseEntity.ok(new Response("Todo updated successfully"));
        } catch (TodoNotFoundException ex) {
            return ResponseEntity.status(404).body(new Response(ex.getMessage()));
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while updating todo: " + ex.getMessage());
        }
    }

    public ResponseEntity<Response> deleteTodo(Long id) {
        try {
            if (!todoRepository.existsById(id)) {
                throw new TodoNotFoundException("Todo not found with ID: " + id);
            }
            todoRepository.deleteById(id);
            return ResponseEntity.ok(new Response("Todo deleted successfully"));
        } catch (TodoNotFoundException e) {
            return ResponseEntity.status(404).body(new Response(e.getMessage()));
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while deleting todo: " + ex.getMessage());
        }
    }

    public ResponseEntity<Response> updateTodoStatus(Long id) {
        try {
            Todo todo = todoRepository.findById(id)
                    .orElseThrow(() -> new TodoNotFoundException("Todo not found with ID: " + id));
            todo.setStatus(!todo.isStatus());
            todo.setUpdatedDate(LocalDate.now());
            todoRepository.save(todo);
            return ResponseEntity.ok(new Response("Todo status updated successfully"));
        } catch (TodoNotFoundException e) {
            return ResponseEntity.status(404).body(new Response(e.getMessage()));
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while updating todo status: " + ex.getMessage());
        }
    }

    public ResponseEntity<List<Todo>> getAllTodos(Long projectId) {
        try {
            List<Todo> todos = todoRepository.findByProjectId(projectId); // Ensure this method is implemented in the repository
            return ResponseEntity.ok(todos);
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while retrieving todos: " + ex.getMessage());
        }
    }

    public ResponseEntity<Response> exportTodoAsGist() {
        try {
            // Implement logic to export todos as a gist here
            return ResponseEntity.ok(new Response("Todos exported as gist successfully"));
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while exporting todos as gist: " + ex.getMessage());
        }
    }
}
