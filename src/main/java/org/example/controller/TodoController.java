package org.example.controller;
import org.example.domain.Status;
import org.example.domain.Todo;
import org.example.service.TodoService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
                        addTask();
                        break;
                    case "2":
                        deleteTask();
                        break;
                    case "3":
                        updateTask();
                        break;
                    case "4":
                        readTasks();
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

    private void listTasksByAssignedTo() {
        try {
            System.out.println("Enter the ID of the user to list tasks for:");
            String userIdString = scanner.nextLine();
            UUID userId = UUID.fromString(userIdString);
            List<Todo> assignedTasks = todoService.listTasksByAssignedTo(userId);
            if (assignedTasks.isEmpty()) {
                System.out.println("No tasks assigned to user with ID " + userId);
            } else {
                System.out.println("Tasks assigned to user with ID " + userId + ":");
                printTodos(assignedTasks);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid user ID format.");
        }
    }


    private void readTasks() {
            List<Todo> tasks = todoService.readTasks();
            if (tasks.isEmpty()) {
                System.out.println("No tasks found.");
            } else {
                System.out.println("Tasks:");
                for (Todo task : tasks) {
                    System.out.println(task);
                }
            }
        }



    private void addTask() {
        try {
            System.out.println("Enter the ID of the user to assign the task:");
            String userIdString = scanner.nextLine();
            UUID assignedTo = UUID.fromString(userIdString);

            System.out.println("Enter title:");
            String title = scanner.nextLine();

            System.out.println("Enter description:");
            String description = scanner.nextLine();

            System.out.println("Enter start date (dd/MM/yyyy):");
            String startDateInput = scanner.nextLine();
            LocalDate startDate = LocalDate.parse(startDateInput, dateFormatter);

            Todo newTodo = Todo.builder()
                    .assignedTo(assignedTo)
                    .title(title)
                    .description(description)
                    .status(Status.PROGRESS)
                    .created(startDate)
                    .build();

            todoService.addTask(newTodo);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("Invalid date format. Please enter date in dd/MM/yyyy format.");
        }
    }

    private void askUserForDateRangeAndPrintTodos() {
        try {
            System.out.println("Enter start date (dd/MM/yyyy):");

            String startDateInput = scanner.nextLine();
            LocalDate startDate = LocalDate.parse(startDateInput, dateFormatter);

            System.out.println("Enter end date (dd/MM/yyyy):");
            String endDateInput = scanner.nextLine();
            LocalDate endDate = LocalDate.parse(endDateInput, dateFormatter);
            List<Todo> filteredTodos = todoService.getTodosBetweenDates(startDate, endDate);
            printTodos(filteredTodos);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter date in dd/MM/yyyy format.");
        }

    }

      private void printTodos(List<Todo> todos) {
            if (todos.isEmpty()) {
                System.out.println("No tasks found.");
            } else {
                System.out.println("Tasks:");
                for (Todo todo : todos) {
                    System.out.println(todo);
                }
            }
      }



        private void updateTask() {
            try {
                System.out.println("Enter the ID of the task you want to update:");
                String idString = scanner.nextLine();
                UUID id = UUID.fromString(idString);

                // Gerekli bilgileri kullanıcıdan alabilirsiniz
                System.out.println("Enter new title:");
                String newTitle = scanner.nextLine();
                System.out.println("Enter new description:");
                String newDescription = scanner.nextLine();
                System.out.println("Enter new status:");
                String newStatus = scanner.nextLine();

                Todo todo = Todo.builder()
                        .id(id)
                        .title(newTitle)
                        .description(newDescription)
                        .status(Status.valueOf(newStatus))
                        .build();

                todoService.updateTask(todo);
                System.out.println("Task updated successfully.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }




        private void deleteTask() {
            try {
                System.out.println("Enter the ID of the task you want to delete:");
                String idString = scanner.nextLine();
                UUID id = UUID.fromString(idString);

                Todo todoToDelete = Todo.builder()
                        .id(id)
                        .build();

                todoService.deleteTodo(todoToDelete);
                System.out.println("Task deleted successfully.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }



    }

