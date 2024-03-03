package org.example.repository;
import org.example.domain.Todo;
import org.example.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.example.service.UserService.userCredentials;

@SuppressWarnings("ALL")

public class TodoRepository   {
    private final static List<Todo> todoList = new ArrayList<>();

    public static List<Todo> getTodoList() {
        return todoList;
    }
    private final List<Todo> subTodoList = new ArrayList<>();

    public List<Todo> getSubTodoLis() {
        return subTodoList;
    }


    public void addTodo(Todo todo) {
       getTodoList().add(todo);
       getSubTodoLis().add(todo);
    }
    public void deleteTodo(UUID id) {
        getTodoList().removeIf(todo -> todo.getId().equals(id));
       }
    public void updateTodo(UUID id, Todo updatedTodo) {
        getTodoById(id).ifPresentOrElse(
                todo -> {

                    todo.setDescription(updatedTodo.getDescription());
                    todo.setStatus(updatedTodo.getStatus());
                    todo.setStatus(updatedTodo.getStatus());
                },
                () -> {
                    System.out.println("Todo with ID " + id + " not found.");
                }
        );
    }
    public List<Todo> readTasks() {
        return getTodoList();
    }
    public Optional<Todo> getTodoById(UUID id) {
        return getTodoList().stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst();
    }

    public List<Todo> getTaskByFkUserId(UUID id) {
        return getTodoList().stream()
                .filter(todo -> todo.getFkUserId().equals(id))
                .collect(Collectors.toList());
    }



}