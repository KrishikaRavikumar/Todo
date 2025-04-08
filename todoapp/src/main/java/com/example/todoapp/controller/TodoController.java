package com.example.todoapp.controller;

// ✅ Don't practice until you get it right. Practice until you can't get it wrong.

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.example.todoapp.model.Todo;
import com.example.todoapp.repository.TodoRepository;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*") // allow all origins for testing
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    // Get all todos
    @GetMapping
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    // Get a todo by ID
    @GetMapping("/{id}")
    public Optional<Todo> getTodoById(@PathVariable Long id) {
        return todoRepository.findById(id);
    }

    // Create a new todo
    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

    // Update full todo (both title and completed)
    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo updatedTodo) {
        return todoRepository.findById(id).map(todo -> {
            if (updatedTodo.getTitle() != null) {
                todo.setTitle(updatedTodo.getTitle());
            }
            todo.setCompleted(updatedTodo.isCompleted());
            return todoRepository.save(todo);
        }).orElseGet(() -> {
            updatedTodo.setId(id);
            return todoRepository.save(updatedTodo);
        });
    }

    // ✅ Safely update only the completed status
    @PutMapping("/{id}/complete")
    public Todo updateCompletedStatus(@PathVariable Long id, @RequestBody Map<String, Boolean> payload) {
        return todoRepository.findById(id).map(todo -> {
            Boolean completed = payload.get("completed");
            if (completed != null) {
                todo.setCompleted(completed);
            }
            return todoRepository.save(todo);
        }).orElseThrow(() -> new RuntimeException("Todo not found"));
    }

    // Delete a todo
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoRepository.deleteById(id);
    }
}
