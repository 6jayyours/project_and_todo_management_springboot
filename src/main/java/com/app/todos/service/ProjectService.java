package com.app.todos.service;

import com.app.todos.entity.Project;
import com.app.todos.entity.User;
import com.app.todos.exceptions.ProjectNotFoundException;
import com.app.todos.exceptions.UserNotFoundException;
import com.app.todos.repository.ProjectRepository;
import com.app.todos.repository.UserRepository;
import com.app.todos.request.CreateProjectRequest;
import com.app.todos.response.AuthenticationResponse;
import com.app.todos.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Response> createProject(Long id, CreateProjectRequest request) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

            Project project = new Project();
            project.setTitle(request.getTitle());
            project.setCreatedDate(request.getCreatedDate());
            project.setUser(user);
            projectRepository.save(project);

            return ResponseEntity.ok(new Response("Project Created Successfully"));
        } catch (UserNotFoundException ex) {
            throw ex; 
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while creating the project: " + ex.getMessage());
        }
    }


    public ResponseEntity<Response> updateProject(Long id, String title) {
        try {
            Project project = projectRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("Project not found with id: " + id));

            project.setTitle(title);
            projectRepository.save(project); // Save the updated project

            return ResponseEntity.ok(new Response("Project updated successfully"));
        } catch (ProjectNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while updating the project: " + ex.getMessage());
        }
    }

    public ResponseEntity<List<Project>> getAllProjects(Long userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

            List<Project> projects = projectRepository.findByUser(user);
            return ResponseEntity.ok(projects);
        } catch (UserNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while fetching projects: " + ex.getMessage());
        }
    }

    public ResponseEntity<Project> getProject(Long id) {
        try {
            Project project = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("Project not found with i d: " + id));
            return ResponseEntity.ok(project);
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while fetching projects: " + ex.getMessage());
        }
    }
}
