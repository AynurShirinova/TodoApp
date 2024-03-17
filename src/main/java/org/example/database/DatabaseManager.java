package org.example.database;

import java.sql.*;

public class DatabaseManager {
    // JDBC URL, istifadəçi adı və şifrənin təyin edilməsi
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "aynur123";

    // Məlumat bazası ilə bağlantı qurmaq üçün metod
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    // Bağlantını yoxlamaq üçün metod
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Daha çox əməliyyatlar əlavə etmək üçün digər metodlar...
}

