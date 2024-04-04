package org.example.repository;

import org.example.database.DatabaseManager;
import org.example.domain.Priority;
import org.example.domain.Status;
import org.example.domain.Todo;
import org.example.dto.TodoDTO;
import org.example.utils.CoreUtils;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@SuppressWarnings("ALL")
public class TodoRepository   {
        public void addTodo(Todo todo) {
            String sql = "INSERT INTO todos (id, title, description, status, assigned_to,created, created_by, priority) VALUES (?, ?, ?, ?, ?,?, ?,?)";
            try (Connection connection = DatabaseManager.connect();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setObject(1, CoreUtils.getRandomId());
                statement.setString(2, todo.getTitle());
                statement.setString(3, todo.getDescription());
                statement.setString(4, todo.getStatus().toString());
                statement.setObject(5, todo.getAssignedTo().toString());
                statement.setObject(6, todo.getCreated());
                statement.setString(7, todo.getCreatedBy());
                statement.setString(8, todo.getPriority().toString());


                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void deleteTodo(Todo todo) {
            String sql = "DELETE FROM todos WHERE id = ?";
            try (Connection connection = DatabaseManager.connect();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setObject(1, todo.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void updateTask(Todo todo) {
            String sql = "UPDATE todos SET title = ?, description = ?, status = ?, priority = ? WHERE id = ?";
            try (Connection connection = DatabaseManager.connect();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, todo.getTitle());
                statement.setString(2, todo.getDescription());
                statement.setString(3, todo.getStatus().toString());
                statement.setString(4, todo.getPriority().toString());
                statement.setObject(5, todo.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public List<TodoDTO> readTasks() {
            List<TodoDTO> todos = new ArrayList<>();
            String sql = "SELECT * FROM todos";
            try (Connection connection = DatabaseManager.connect();
                 PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    TodoDTO todo = TodoDTO.builder()
                            .id(UUID.fromString(resultSet.getString("id")))
                            .createdBy(resultSet.getString("created_by"))
                            .title(resultSet.getString("title"))
                            .description(resultSet.getString("description"))
                            .status(Status.valueOf(resultSet.getString("status")))
                            // .priority(Priority.valueOf(resultSet.getString("priority")))
                            .priority(getPriority(resultSet.getString("priority")))
                            .created(resultSet.getString("created"))
                            .assignedTo(UUID.fromString(resultSet.getString("assigned_to")))
                            .build();
                    todos.add(todo);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return todos;
        }
    private Priority getPriority(String priorityString) {
        Priority priority = null;
        if (priorityString != null) {
            try {
                priority = Priority.valueOf(priorityString);
            } catch (IllegalArgumentException e) {
                // Enum sabiti oluşturulamazsa yapılacak işlemler
                e.printStackTrace();
            }
        }
        return priority;
    }


        public Optional<Todo> getTodoById(UUID id) {
            String sql = "SELECT * FROM todos WHERE id = ?";
            try (Connection connection = DatabaseManager.connect();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, id.toString());
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(Todo.builder()
                                .id(UUID.fromString(resultSet.getString("id")))
                                .title(resultSet.getString("title"))
                                .description(resultSet.getString("description"))
                                .status(Status.valueOf(resultSet.getString("status")))
                                .assignedTo(UUID.fromString(resultSet.getString("assigned_to")))
                                .build());
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Optional.empty();
        }

        public List<Todo> findByAssignedTo(UUID todo) {
            List<Todo> todos = new ArrayList<>();
            String sql = "SELECT * FROM todos WHERE assigned_to = ?";
            try (Connection connection = DatabaseManager.connect();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, todo.toString());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Todo todoss = Todo.builder()
                                .id(UUID.fromString(resultSet.getString("id")))
                                .title(resultSet.getString("title"))
                                .description(resultSet.getString("description"))
                                .status(Status.valueOf(resultSet.getString("status")))
                                .assignedTo(UUID.fromString(resultSet.getString("assigned_to")))
                                .build();
                        todos.add(todoss);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return todos;
        }
    public List<Todo> getTodosBetweenDates(LocalDate start, LocalDate end) {
        List<Todo> todos = new ArrayList<>();
        String sql = "SELECT * FROM todos WHERE created BETWEEN ? AND ?";
        try (Connection connection = DatabaseManager.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, Date.valueOf(start));
            statement.setDate(2, Date.valueOf(end));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    UUID id = UUID.fromString(resultSet.getString("id"));
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    LocalDate created = resultSet.getDate("created").toLocalDate();
                    Status status = Status.valueOf(resultSet.getString("status"));
                    UUID assignedTo = UUID.fromString(resultSet.getString("assigned_to"));

                    Todo todo = Todo.builder()
                            .id(id)
                            .title(title)
                            .description(description)
                            .created(created)
                            .status(status)
                            .assignedTo(assignedTo)
                            .build();

                    todos.add(todo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }

public Map<UUID, String> getAllTaskIds() {
    Map<UUID, String> taskNamesById = new HashMap<>();
        try (Connection connection = DatabaseManager.connect()) {
            String SELECT_ALL_TASK_IDS = "SELECT id, title FROM todos";
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_TASK_IDS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UUID taskId=UUID.fromString(resultSet.getString("id"));
                String title = resultSet.getString("title");
                taskNamesById.put(taskId, title);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskNamesById;
    }
}

