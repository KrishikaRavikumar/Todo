package com.example.todoapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoapp.repository.TodoRepository;

@RestController
public class TestController {

    private final TodoRepository todoRepository;

    public TestController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/test")
    public String testRepo() {
        return "Repo is working: " + todoRepository.count();
    }
}