package org.example.service;

import org.example.domain.Todo;
import org.example.dto.TodoDTO;
import org.example.repository.TodoRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void addTask(Todo todo) {
        todoRepository.addTodo(todo);
    }

    public void deleteTodo(Todo todo) {
        todoRepository.deleteTodo(todo);
    }

    public void updateTask(Todo todo) {
        todoRepository.updateTask(todo);
    }


    public List<TodoDTO> readTasks() {
        return todoRepository.readTasks();
    }

    public List<Todo> findByAssignedTo(UUID todo) {
        return todoRepository.findByAssignedTo(todo);
    }

    public List<Todo> getTodosBetweenDates(LocalDate start, LocalDate end) {
        return todoRepository.getTodosBetweenDates(start, end);
    }
    public Todo getTaskById(UUID id) {
        return todoRepository.getTodoById(id).orElse(null);
    }
    public List<Todo> listTasksByAssignedTo(UUID userId) {
        return todoRepository.findByAssignedTo(userId);
    }
    public Map<UUID, String> getAllTaskIds() {
        return todoRepository.getAllTaskIds();
    }
}
