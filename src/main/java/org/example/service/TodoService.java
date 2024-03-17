package org.example.service;

import org.example.domain.Todo;
import org.example.repository.TodoRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
@SuppressWarnings("ALL")
@Service
public class TodoService {
        private final TodoRepository todoRepository;
        private final UserService userService;
        private final Scanner scanner = new Scanner(System.in);
        private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        public TodoService(TodoRepository todoRepository, UserService userService) {
            this.todoRepository = todoRepository;
            this.userService = userService;
        }

    // TodoService.java
    public void addTask() {
        try {
            List<UUID> userIds = new ArrayList<>();
            Map<UUID, String> userIdsByEmail = new HashMap<>();

            // Bütün istifadəçi id-lərini və email-ləri yığılacaq list və map yaradırıq
            for (UUID userId : userService.getAllUserIds()) {
                String email = userService.getEmailByUserId(userId);
                userIds.add(userId);
                userIdsByEmail.put(userId, email);
            }

            System.out.println("task or subtask?");
            String taskType = scanner.nextLine();

            UUID assignedTo = null;
            if (taskType.equals("task")) {
                // Task yaratmaq üçün lazımi məlumatları əldə edirik
                for (UUID userId : userIds) {
                    String email = userIdsByEmail.get(userId);
                    System.out.printf("User ID: %s, Email: %s\n", userId, email);
                }

                System.out.println("Enter the ID of the user to assign the task:");
                String userIdString = scanner.nextLine();
                assignedTo = UUID.fromString(userIdString);

                if (!userIds.contains(assignedTo)) {
                    System.out.println("User does not exist.");
                    return;
                }

                String username = userService.getUsernameById(assignedTo);
                System.out.println("Enter title:");
                String title = scanner.nextLine();

                System.out.println("Enter description:");
                String description = scanner.nextLine();

                System.out.println("Enter start date (dd/MM/yyyy):");
                String startDateInput = scanner.nextLine();
                LocalDate startDate = LocalDate.parse(startDateInput, dateFormatter);

                // Yeni taskı yaradır və məlumat bazasına əlavə edir
                Todo newTodo = Todo.builder()
                        .assignedTo(assignedTo)
                        .title(title)
                        .description(description)
                        .created(startDate)
                        .createdBy(username)
                        .build();

                todoRepository.addTodo(newTodo);
            } else if (taskType.equals("subtask")) {
                // Alttaskı yaratmaq üçün məntiqi əlavə edin
            } else {
                System.out.println("Invalid task type.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format. Please enter date in dd/MM/yyyy format.");
        }
    }


    public void deleteTask() {
            System.out.println("Enter the ID of the task you want to delete:");
            String idString = scanner.nextLine();
            UUID id = UUID.fromString(idString);
            todoRepository.deleteTodo(id);
            System.out.println("Task deleted successfully.");
        }

        public void updateTask() {
            System.out.println("Enter the ID of the task you want to update:");
            String idString = scanner.nextLine();
            UUID id = UUID.fromString(idString);
            // Add update task logic
        }

        public void readTasks() {
            List<Todo> tasks = todoRepository.readTasks();
            tasks.forEach(task -> System.out.println(task));
        }

        public void listTasksByAssignedTo() {
            System.out.println("Enter the ID of the user to list tasks for:");
            String userIdString = scanner.nextLine();
            UUID userId = UUID.fromString(userIdString);
            List<Todo> assignedTasks = todoRepository.findByAssignedTo(userId);
            if (assignedTasks.isEmpty()) {
                System.out.println("No tasks assigned to user with ID " + userId);
            } else {
                System.out.println("Tasks assigned to user with ID " + userId + ":");
                assignedTasks.forEach(task -> System.out.println(task));
            }
        }

        public void askUserForDateRangeAndPrintTodos() {
            System.out.println("Enter start date (dd/MM/yyyy):");
            String startDateInput = scanner.nextLine();
            LocalDate startDate = LocalDate.parse(startDateInput, dateFormatter);

            System.out.println("Enter end date (dd/MM/yyyy):");
            String endDateInput = scanner.nextLine();
            LocalDate endDate = LocalDate.parse(endDateInput, dateFormatter);

            List<Todo> filteredTodos = todoRepository.getTodosBetweenDates(startDate, endDate);
            filteredTodos.forEach(task -> System.out.println(task));
        }
    private void printTodos(List<Todo> todos) {
        if (todos.isEmpty()) {
            System.out.println("Belirtilmiş tarix aralığında todo tapılmadı.");
        } else {
            System.out.println("Belirtilmiş tarix aralığındaki todoslar:");
            for (Todo todo : todos) {
                System.out.println(todo);
            }
        }
        }

    }


