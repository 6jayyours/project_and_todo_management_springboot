package com.app.todos.repository;

import com.app.todos.entity.Project;
import com.app.todos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {


    /**
     * Finds all projects associated with a specific user.
     */
    List<Project> findByUser(User user);
}
