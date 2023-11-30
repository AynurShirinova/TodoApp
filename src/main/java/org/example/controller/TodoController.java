package org.example.controller;

import org.example.service.TodoService;
import org.example.service.UserService;

import java.util.Scanner;
public class TodoController {
    private final TodoService todoService;
    UserService userService;
    private final Scanner scanner = new Scanner(System.in);
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    public void run() {
        String choice;

        do {
            System.out.println("1. Add Task");
            System.out.println("2. Delete Task");
            System.out.println("3. Update Task");
            System.out.println("4. Read Tasks");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    todoService.addTask();
                    break;
                case "2":
                    todoService.deleteTask(scanner);
                    break;
                case "3":
                    todoService.updateTask(scanner);
                    break;
                case "4":
                    todoService.readTasks();
                    break;
                case "0":
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println("-----------------------");
        } while (!choice.equals("0"));
    }
}