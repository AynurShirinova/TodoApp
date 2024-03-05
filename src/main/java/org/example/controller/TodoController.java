package org.example.controller;
import org.example.service.TodoService;
import java.util.Scanner;

@SuppressWarnings("ALL")

public class TodoController {
    private final TodoService todoService;
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
                System.out.println("5. Get Tasks By UserId ");
                System.out.println("6. task between 2 date ");
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
                    case "5":
                        todoService.listTasksByAssignedTo();
                        break;
                    case "6":
                        todoService.askUserForDateRangeAndPrintTodos();
                        break;
                    case "0":
                        System.out.println("Exiting program. Goodbye!h");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                System.out.println("-----------------------");
            } while (!choice.equals("0"));
        }
    }
