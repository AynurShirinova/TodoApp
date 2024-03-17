package org.example.service;

import org.example.Main;
import org.example.database.DatabaseManager;
import org.example.domain.Constants;
import org.example.domain.Session;
import org.example.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class UserService {
//    private final Scanner scanner = new Scanner(System.in);
//    private static Session session;
//    public  UserService(){};
//
//    public static Map<String, User> userCredentials = new HashMap<>();
//
//    public boolean isValidEmail(String email) {
//        final Pattern pattern = Pattern.compile(Constants.EMAIL_REGEX);
//
//        if (email == null) {
//            return false;
//        }
//        Matcher matcher = pattern.matcher(email);
//        return matcher.matches();
//    }
//    public boolean register(String email, String password,String userName,UUID id) {
//        if (!isValidEmail(email)) {
//            return false;
//        }
//        if (userCredentials.containsKey(email)) {
//            return false; // E-posta zaten kayıtlı
//        }
//
//        userCredentials.put(email, User.builder()
//                        .mail(email)
//                        .id(id)
//                        .userName(userName)
//                .build());
//        return true;
//    }
//
//    public UUID authenticateAndGetId(String email, String password) {
//        if (!isValidEmail(email)) {
//            return null; // Invalid email format
//        }
//        User user = userCredentials.get(email);
//        if (user != null && user.getPassword().equals(password)) {
//            return user.getId(); // Return the user's ID on successful authentication
//        }
//        return null; //
//    }
//    public static Optional<UUID> getUserIdByEmail(String email) {
//        if (email == null || !userCredentials.containsKey(email)) {
//            return Optional.empty(); // User not found or email is null
//        }
//        return Optional.ofNullable(userCredentials.get(email).getId());
//    }
//    public static String getUsernameById(UUID uuid) {
//        if (uuid == null || !userCredentials.containsKey(uuid)) {
//            return "Input boş və ya id yanlışdır"; // User not found or email is null
//        }
//        return userCredentials.get(uuid).getUserName();
//    }
//
//    public static String StatusById(UUID uuid) {
//        if (uuid == null || !userCredentials.containsKey(uuid)) {
//            return "Input boş və ya id yanlışdır"; // User not found or email is null
//        }
//        return userCredentials.get(uuid).getUserName();
//    }
//    public static List<UUID> getAllUserIds() {
//        // Check if the userCredentials map is not null or empty
//        if (userCredentials == null || userCredentials.isEmpty()) {
//            return Collections.emptyList(); // Return an empty list if no users are found
//        }
//        // Use a stream to collect all user IDs from the userCredentials map
//        return userCredentials.values().stream()
//                .map(user -> user.getId()) // Convert each user to their UUID
//                .filter(Objects::nonNull) // Filter out any null IDs
//                .collect(Collectors.toList()); // Collect the IDs into a List
//    }
//
//    public List<String> getEmailsByUserIds(List<UUID> uuids) {
//        List<String> emails = new ArrayList<>();
//
//        if (uuids == null || uuids.isEmpty()) {
//            return emails;
//        }
//
//        for (Map.Entry<String, User> entry : userCredentials.entrySet()) {
//            String email = entry.getKey();
//            User user = entry.getValue();
//
//            if (uuids.contains(user.getId())) {
//                emails.add(email);
//            }
//        }
//        return emails;
//    }
//
//
//    public void logUp(UUID id) {
//        System.out.print("Kayıt için e-posta giriniz: ");
//        String email = scanner.nextLine();
//        System.out.print("Kayıt için username giriniz: ");
//        String userName = scanner.nextLine();
//        System.out.print("Şifre giriniz: ");
//        String password = scanner.nextLine();
//
//        if (register(email, password,userName,id)) {
//            System.out.println("Kayıt başarılı. Giriş yapabilirsiniz.");
//        } else {
//            System.out.println("Kayıt başarısız. E-posta zaten kullanımda veya geçersiz.");
//        }
//    }
//
//    public void logIn(UUID id) {
//        System.out.print("E-posta giriniz: ");
//        String email = scanner.nextLine();
//        System.out.print("Şifre giriniz: ");
//        String password = scanner.nextLine();
//
//        UUID userId = authenticateAndGetId(email, password);
//        if (userId != null) {
//            System.out.println("Giriş başarılı.");
//            System.out.println("User ID: " + userId);
//            new Main().projectController.manageProjects();
//        } else {
//            System.out.println("Yanlış kullanıcı adı veya şifre.");
//        }
//    }
    private final Scanner scanner = new Scanner(System.in);
    private static final Map<String, User> userCredentials = new HashMap<>();
    private static final String INSERT_QUERY = "INSERT INTO users (id, username, email, password) VALUES (?, ?, ?, ?)";
    private static final String SELECT_QUERY_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String SELECT_USERNAME_BY_ID = "SELECT username FROM users WHERE id = ?";
    private static final String SELECT_STATUS_BY_ID = "SELECT status FROM users WHERE id = ?";
    private static final String SELECT_ALL_USER_IDS = "SELECT id FROM users";
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
            String sql = "INSERT INTO users (id, username, email, password) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id.toString());
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
            String email = scanner.nextLine();
            System.out.print("Şifrə daxil edin: ");
            String password = scanner.nextLine();

            // SQL əmrini hazırlamaq
            String sql = "SELECT id FROM users WHERE email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
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
    public Optional<UUID> getUserIdByEmail(String email) {
        try (Connection connection = DatabaseManager.connect();
             PreparedStatement statement = connection.prepareStatement(SELECT_QUERY_BY_EMAIL)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(UUID.fromString(resultSet.getString("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
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
