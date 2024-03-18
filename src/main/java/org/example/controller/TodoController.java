package org.example.controller;
import org.example.domain.Todo;
import org.example.service.TodoService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@SuppressWarnings("ALL")

public class TodoController {
    private final TodoService todoService;
    private final Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
                        deleteTask();
                        break;
                    case "3":
                        updateTask();
                        break;
                    case "4":
                        todoService.readTasks();
                        break;
                    case "5":
                        listTasksByAssignedTo();
                        break;
                    case "6":
                        askUserForDateRangeAndPrintTodos();
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

    private void askUserForDateRangeAndPrintTodos() {
        System.out.println("Enter start date (dd/MM/yyyy):");
        String startDateInput = scanner.nextLine();
        LocalDate startDate = LocalDate.parse(startDateInput, dateFormatter);

        System.out.println("Enter end date (dd/MM/yyyy):");
        String endDateInput = scanner.nextLine();
        LocalDate endDate = LocalDate.parse(endDateInput, dateFormatter);
      //  List<Todo> filteredTodos = todoService.askUserForDateRangeAndPrintTodos(startDate, endDate);
    //    filteredTodos.forEach(task -> System.out.println(task));
        Todo todo = Todo.builder()
                .endDateInput(endDateInput)
                .startDateInput(startDateInput)
                .build();
        todoService.askUserForDateRangeAndPrintTodos(todo);
    }

    private void listTasksByAssignedTo() {
        System.out.println("Enter the ID of the user to list tasks for:");
        String userIdString = scanner.nextLine();
        UUID userId = UUID.fromString(userIdString);
        Todo todo = Todo.builder()
                .id(userId)
                .build();
        List<Todo> assignedTasks = todoService.printTodos(todo);
        if (assignedTasks.isEmpty()) {
            System.out.println("No tasks assigned to user with ID " + userId);
        } else {
            System.out.println("Tasks assigned to user with ID " + userId + ":");
            assignedTasks.forEach(task -> System.out.println(task));
        }
    }


    private void updateTask() {
        System.out.println("Enter the ID of the task you want to update:");
        String idString = scanner.nextLine();
        UUID id = UUID.fromString(idString);
        Todo todo= Todo.builder()
                .id(id)
                .build();
        todoService.updateTask(todo);

    }

    private void deleteTask() {
            System.out.println("Enter the ID of the task you want to delete:");
            String idString = scanner.nextLine();
            UUID id = UUID.fromString(idString);

            Todo todo= Todo.builder()
                    .id(id)
                    .build();
        todoService.deleteTask(todo);

    }
}
