package org.example.repository;

import org.example.database.DatabaseManager;
import org.example.domain.Project;
import org.example.utils.CoreUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectRepository {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String username = "postgres";
    private final String password = "aynur123";

    public List<Project> getProjectList() {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM projects";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("id"));
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String createdAt = resultSet.getString("createdAt");

                Project project = new Project(id, title, description, createdAt);
                projects.add(project);
            }
        } catch (SQLException e) {
            System.err.println("Veritabanı işlemi sırasında bir hata oluştu: " + e.getMessage());
        }

        return projects;
    }

    public void addProject(Project project) {
        String sql = "INSERT INTO projects (id, title, description, createdAt) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseManager.connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, CoreUtils.getRandomId());
            statement.setString(2, project.getTitle());
            statement.setString(3, project.getDescription());
            statement.setString(4, project.getCreatedAt());
            statement.executeUpdate();
            System.out.println("Proje başarıyla eklendi.");
        } catch (SQLException e) {
            System.err.println("Proje eklenirken bir hata oluştu: " + e.getMessage());
        }
    }

    public void updateProject(UUID id, Project updatedProject) {
        String sql = "UPDATE projects SET title = ?, description = ?, createdAt = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, updatedProject.getTitle());
            statement.setString(2, updatedProject.getDescription());
            statement.setString(3, updatedProject.getCreatedAt());
            statement.setString(4, id.toString());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Proje başarıyla güncellendi.");
            } else {
                System.out.println("Güncellenen proje bulunamadı.");
            }
        } catch (SQLException e) {
            System.err.println("Proje güncellenirken bir hata oluştu: " + e.getMessage());
        }
    }

    public void deleteProject(UUID id) {
        String sql = "DELETE FROM projects WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, id.toString());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Proje başarıyla silindi.");
            } else {
                System.out.println("Silinecek proje bulunamadı.");
            }
        } catch (SQLException e) {
            System.err.println("Proje silinirken bir hata oluştu: " + e.getMessage());
        }
    }
    public Project getProjectById(UUID id) {
        String sql = "SELECT * FROM projects WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setObject(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    String createdAt = resultSet.getString("createdAt");

                    return new Project(id, title, description, createdAt);
                }
            }
        } catch (SQLException e) {
            System.err.println("Veritabanı işlemi sırasında bir hata oluştu: " + e.getMessage());
        }

        return null;
    }
}
