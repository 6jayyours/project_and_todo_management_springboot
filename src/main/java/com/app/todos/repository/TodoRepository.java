package com.app.todos.repository;

import com.app.todos.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByProjectId(Long projectId);
}
