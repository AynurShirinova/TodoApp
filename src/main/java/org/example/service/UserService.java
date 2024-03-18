package org.example.service;

import org.example.Main;
import org.example.database.DatabaseManager;
import org.example.domain.User;
import org.example.repository.UserRepository;
import org.example.utils.CoreUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
@SuppressWarnings("ALL")
public class UserService {
    private  UserRepository userRepository;
    private final Scanner scanner = new Scanner(System.in);
    private static final Map<String, User> userCredentials = new HashMap<>();
    private static final String SELECT_USERNAME_BY_ID = "SELECT user_name FROM users WHERE id = ?";
    private static final String SELECT_STATUS_BY_ID = "SELECT status FROM users WHERE id = ?";
    private static final String SELECT_ALL_USER_IDS = "SELECT id FROM users";
    private static final String SELECT_QUERY_BY_USER_ID = "SELECT email FROM users WHERE id = ?\n";
   public void logUp(User user) {
       userRepository.logUp(user);
   }

    public void logIn(User user) {
        userRepository.logIn(user);
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
