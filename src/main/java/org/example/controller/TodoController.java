package org.example.controller;
import org.example.domain.Todo;
import org.example.service.TodoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TodoController {
    private final TodoService todoService;
    private Scanner scanner = new Scanner(System.in);
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    public void run() {
        int choice;

        do {
            System.out.println("1. Add Task");
            System.out.println("2. Delete Task");
            System.out.println("3. Update Task");
            System.out.println("4. Read Tasks");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("enter description");
                    String description = scanner.nextLine();
                   Todo todo = new Todo();
                    todo.setDescription(description);
                    todoService.addTask(todo);
                    break;
                case 2:
                    todoService.deleteTask(scanner);
                    break;
                case 3:
                    todoService.updateTask(scanner);
                    break;
                case 4:
                    todoService.readTasks();
                    break;
                case 0:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }
}