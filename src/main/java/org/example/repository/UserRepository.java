package org.example.repository;

import org.example.Main;
import org.example.database.DatabaseManager;
import org.example.domain.User;
import org.example.utils.CoreUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserRepository {
    public void logUp(User user) {
        try (Connection connection = DatabaseManager.connect()) {
            // SQL əmrini hazırlamaq
            String sql = "INSERT INTO users (id, user_name, mail, password) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, CoreUtils.getRandomId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getMail());
            statement.setString(4, user.getPassword());
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
        public void logIn(User user){
            try (Connection connection = DatabaseManager.connect()) {
                // SQL əmrini hazırlamaq
                String sql = "SELECT id FROM users WHERE mail = ? AND password = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, user.getMail());
                statement.setString(2, user.getPassword());
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

    }
