package com.app.todos.controller;

import com.app.todos.entity.Project;
import com.app.todos.request.CreateProjectRequest;
import com.app.todos.request.UpdateProjectRequest;
import com.app.todos.response.Response;
import com.app.todos.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Response> create(@PathVariable Long id, @RequestBody CreateProjectRequest request) {
        return projectService.createProject(id,request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> edit(@PathVariable Long id, @RequestBody UpdateProjectRequest request) {
        return projectService.updateProject(id,request.getTitle());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Project>> getAll(@PathVariable Long id) {
        return projectService.getAllProjects(id);
    }
}
