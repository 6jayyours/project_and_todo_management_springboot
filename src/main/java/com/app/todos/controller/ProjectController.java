package com.app.todos.controller;

import com.app.todos.entity.Project;
import com.app.todos.request.CreateProjectRequest;
import com.app.todos.request.UpdateProjectRequest;
import com.app.todos.response.Response;
import com.app.todos.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controller for managing project-related operations, including creating, updating, and retrieving projects.
 */
@RestController
@RequestMapping("api/v1/project")
@CrossOrigin(origins = "http://localhost:5173")
public class ProjectController {
    private final ProjectService projectService;


    /**
     * Constructor for dependency injection of ProjectService.
     */
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    /**
     * Endpoint for creating a new project for a specific user.
     */
    @PostMapping("/{id}")
    public ResponseEntity<Response> create(@PathVariable Long id, @RequestBody CreateProjectRequest request) {
        return projectService.createProject(id,request);
    }

    /**
     * Endpoint for updating an existing project.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Response> edit(@PathVariable Long id, @RequestBody UpdateProjectRequest request) {
        return projectService.updateProject(id,request.getTitle());
    }


    /**
     * Endpoint for retrieving all projects associated with a specific user.
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<Project>> getAll(@PathVariable Long id) {
        return projectService.getAllProjects(id);
    }


    /**
     * Endpoint for retrieving a specific project by its ID.
     */
    @GetMapping("project/{id}")
    public ResponseEntity<Project> getProject(@PathVariable Long id) {
        return projectService.getProject(id);
    }
}
