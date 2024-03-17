package org.example.service;

import org.example.Main;
import org.example.database.DatabaseManager;
import org.example.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
@SuppressWarnings("ALL")
public class UserService {
    private final Scanner scanner = new Scanner(System.in);
    private static final Map<String, User> userCredentials = new HashMap<>();
    private static final String SELECT_USERNAME_BY_ID = "SELECT user_name FROM users WHERE id = ?";
    private static final String SELECT_STATUS_BY_ID = "SELECT status FROM users WHERE id = ?";
    private static final String SELECT_ALL_USER_IDS = "SELECT id FROM users";
    private static final String SELECT_QUERY_BY_USER_ID = "SELECT email FROM users WHERE id = ?\n";
    public void logUp() {
        try (Connection connection = DatabaseManager.connect()) {
            System.out.print("E-poçt daxil edin: ");
            String email = scanner.nextLine();
            System.out.print("İstifadəçi adı daxil edin: ");
            String userName = scanner.nextLine();
            System.out.print("Şifrə daxil edin: ");
            String password = scanner.nextLine();

            // UUID yaratmaq
            UUID id = UUID.randomUUID();

            // SQL əmrini hazırlamaq
            String sql = "INSERT INTO users (id, user_name, mail, password) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
            statement.setString(2, userName);
            statement.setString(3, email);
            statement.setString(4, password);

            // SQL əmri icra etmək
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Qeydiyyat uğurla tamamlandı.");
            } else {
                System.out.println("Qeydiyyat zamanı problem yaşandı.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void logIn() {
        try (Connection connection = DatabaseManager.connect()) {
            System.out.print("E-poçt daxil edin: ");
            String mail = scanner.nextLine();
            System.out.print("Şifrə daxil edin: ");
            String password = scanner.nextLine();

            // SQL əmrini hazırlamaq
            String sql = "SELECT id FROM users WHERE mail = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, mail);
            statement.setString(2, password);

            // SQL əmri icra etmək və nəticəni əldə etmək
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                UUID userId = UUID.fromString(resultSet.getString("id"));
                System.out.println("Giriş uğurlu. İstifadəçi ID: " + userId);
                new Main().projectController.manageProjects();

            } else {
                System.out.println("Giriş uğursuz. İstifadəçi məlumatları yanlışdır.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getEmailByUserId(UUID userId) {
        try (Connection connection = DatabaseManager.connect();
             PreparedStatement statement = connection.prepareStatement(SELECT_QUERY_BY_USER_ID)) {

            statement.setObject(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("mail");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUsernameById(UUID uuid) {
        try (Connection connection = DatabaseManager.connect();
             PreparedStatement statement = connection.prepareStatement(SELECT_USERNAME_BY_ID)) {

            statement.setString(1, uuid.toString());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Input boş və ya id yanlışdır";
    }

    public String getStatusById(UUID uuid) {
        try (Connection connection = DatabaseManager.connect();
             PreparedStatement statement = connection.prepareStatement(SELECT_STATUS_BY_ID)) {

            statement.setString(1, uuid.toString());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Input boş və ya id yanlışdır";
    }

    public List<UUID> getAllUserIds() {
        List<UUID> userIds = new ArrayList<>();
        try (Connection connection = DatabaseManager.connect();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USER_IDS)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                userIds.add(UUID.fromString(resultSet.getString("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userIds;

    }
}
