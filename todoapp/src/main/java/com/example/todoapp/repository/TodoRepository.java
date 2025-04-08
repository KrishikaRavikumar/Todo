package com.example.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todoapp.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    // No need to implement anything! Spring does it for you.
}
