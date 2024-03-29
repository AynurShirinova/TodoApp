package org.example.service;
//
//import lombok.Builder;
//import lombok.NoArgsConstructor;
//import org.example.database.DatabaseManager;
//import org.example.domain.User;
//import org.example.repository.UserRepository;
//import org.springframework.stereotype.Service;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.*;
//@SuppressWarnings("ALL")
//@Builder
//@NoArgsConstructor
//@Service
//public class UserService {
//    private  UserRepository userRepository;
////    public UserService(){};
//public UserService(UserRepository userRepository) {
//    this.userRepository = userRepository;
//}
//
//    private final Scanner scanner = new Scanner(System.in);
//    private static final Map<String, User> userCredentials = new HashMap<>();
//    private static final String SELECT_USERNAME_BY_ID = "SELECT user_name FROM users WHERE id = ?";
//    private static final String SELECT_STATUS_BY_ID = "SELECT status FROM users WHERE id = ?";
//    private static final String SELECT_ALL_USER_IDS = "SELECT id FROM users";
//   public void logUp(User user) {
//       userRepository.logUp(user);
//   }
//
//    public void logIn(User user) {
//        userRepository.logIn(user);
//    }
//    public String getEmailByUserId(UUID userId) {
//        userRepository.getEmailByUserId(userId );
//        return "yanlcn";
//    }
//
//    public String getUsernameById(UUID uuid) {
//        try (Connection connection = DatabaseManager.connect();
//             PreparedStatement statement = connection.prepareStatement(SELECT_USERNAME_BY_ID)) {
//            statement.setObject(1, uuid);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                return resultSet.getString("user_name");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return "Input boş və ya id yanlışdır";
//    }
//
//    public String getStatusById(UUID uuid) {
//        try (Connection connection = DatabaseManager.connect();
//             PreparedStatement statement = connection.prepareStatement(SELECT_STATUS_BY_ID)) {
//            statement.setString(1, uuid.toString());
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                return resultSet.getString("status");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return "Input boş və ya id yanlışdır";
//    }
//
//    public List<UUID> getAllUserIds() {
//        List<UUID> userIds = new ArrayList<>();
//        try (Connection connection = DatabaseManager.connect();
//             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USER_IDS)) {
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                userIds.add(UUID.fromString(resultSet.getString("id")));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return userIds;
//
//    }
//}
// UserService.java

import org.springframework.stereotype.Service;
import org.example.repository.UserRepository;
import org.example.domain.User;

import java.sql.SQLException;
import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void logUp(User user) {
        userRepository.logUp(user);
    }

    public void logIn(User user) {
        userRepository.logIn(user);
    }

    public String getEmailByUserId(UUID userId) {
        return userRepository.getEmailByUserId(userId);
    }

    public String getUsernameById(UUID uuid) {
        return userRepository.getUsernameById(uuid);
    }

    public String getStatusById(UUID uuid) {
        return userRepository.getStatusById(uuid);
    }

    public List<UUID> getAllUserIds() {
        return userRepository.getAllUserIds();
    }
}

