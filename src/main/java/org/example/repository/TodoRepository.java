package org.example.repository;

import org.example.database.DatabaseManager;
import org.example.domain.Status;
import org.example.domain.Todo;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("ALL")
public class TodoRepository   {
        public void addTodo(Todo todo) {
            String sql = "INSERT INTO todos (id, title, description, status, assigned_to) VALUES (?, ?, ?, ?, ?)";

            try (Connection connection = DatabaseManager.connect();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, todo.getId().toString());
                statement.setString(2, todo.getTitle());
                statement.setString(3, todo.getDescription());
                statement.setString(4, todo.getStatus().toString());
                statement.setString(5, todo.getAssignedTo().toString());

                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void deleteTodo(UUID id) {
            String sql = "DELETE FROM todos WHERE id = ?";

            try (Connection connection = DatabaseManager.connect();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, id.toString());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void updateTodo(UUID id, Todo updatedTodo) {
            String sql = "UPDATE todos SET title = ?, description = ?, status = ? WHERE id = ?";

            try (Connection connection = DatabaseManager.connect();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, updatedTodo.getTitle());
                statement.setString(2, updatedTodo.getDescription());
                statement.setString(3, updatedTodo.getStatus().toString());
                statement.setString(4, id.toString());

                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public List<Todo> readTasks() {
            List<Todo> todos = new ArrayList<>();
            String sql = "SELECT * FROM todos";

            try (Connection connection = DatabaseManager.connect();
                 PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Todo todo = Todo.builder()
                            .id(UUID.fromString(resultSet.getString("id")))
                            .title(resultSet.getString("title"))
                            .description(resultSet.getString("description"))
                            .status(Status.valueOf(resultSet.getString("status")))
                            .assignedTo(UUID.fromString(resultSet.getString("assigned_to")))
                            .build();
                    todos.add(todo);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return todos;
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

        public List<Todo> findByAssignedTo(UUID assignedToId) {
            List<Todo> todos = new ArrayList<>();
            String sql = "SELECT * FROM todos WHERE assigned_to = ?";

            try (Connection connection = DatabaseManager.connect();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, assignedToId.toString());

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Todo todo = Todo.builder()
                                .id(UUID.fromString(resultSet.getString("id")))
                                .title(resultSet.getString("title"))
                                .description(resultSet.getString("description"))
                                .status(Status.valueOf(resultSet.getString("status")))
                                .assignedTo(UUID.fromString(resultSet.getString("assigned_to")))
                                .build();
                        todos.add(todo);
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
    }

