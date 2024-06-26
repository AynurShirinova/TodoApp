package org.example.repository;

import org.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User>, PagingAndSortingRepository<User, UUID> {

    User findByMail(String mail);

    Boolean existsByMailAndPassword(String mail,String password);

    Optional<User> findById(UUID id);


//public class UserRepository {

//    public void logUp(User user) {
//        try (Connection connection = DatabaseManager.connect()) {
//            String sql = "INSERT INTO users (id, user_name, mail, password) VALUES (?, ?, ?, ?)";
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setObject(1, CoreUtils.getRandomId());
//            statement.setString(2, user.getUserName());
//            statement.setString(3, user.getMail());
//            statement.setString(4, user.getPassword());
//            int rowsInserted = statement.executeUpdate();
//            if (rowsInserted > 0) {
//                System.out.println("Qeydiyyat uğurla tamamlandı.");
//            } else {
//                System.out.println("Qeydiyyat zamanı problem yaşandı.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void logIn(User user) {
//        try (Connection connection = DatabaseManager.connect()) {
//            String sql = "SELECT id FROM users WHERE mail = ? AND password = ?";
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, user.getMail());
//            statement.setString(2, user.getPassword());
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                UUID userId = UUID.fromString(resultSet.getString("id"));
//                System.out.println("Giriş uğurlu. İstifadəçi ID: " + userId);
//                // Yeni projeleri yönetmek için kontrolcüye yönlendirme
//                new Main().projectController.manageProjects();
//            } else {
//                System.out.println("Giriş uğursuz. İstifadəçi məlumatları yanlışdır.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String getEmailByUserId(UUID userId) {
//        try (Connection connection = DatabaseManager.connect()) {
//            String SELECT_QUERY_BY_USER_ID = "SELECT mail FROM users WHERE id = ?";
//            PreparedStatement statement = connection.prepareStatement(SELECT_QUERY_BY_USER_ID);
//            statement.setObject(1, userId);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                return resultSet.getString("mail");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public List<UsersDTO> getUsernameAndID(UUID userid) {
//        List<UsersDTO> users = new ArrayList<>();
//
//        String sql = "SELECT * FROM users WHERE id = ?";
//        try (Connection connection = DatabaseManager.connect();
//             PreparedStatement statement = connection.prepareStatement(sql);
//             //statement.setObject(1,userid);
//             ResultSet resultSet = statement.executeQuery()) {
//            while (resultSet.next()) {
//                UsersDTO user = UsersDTO.builder()
//                        .id(UUID.fromString(resultSet.getString("id")))
//                        .userName(resultSet.getString("user_name"))
//                        .build();
//                users.add(user);//dunyanin en asan sheyidi ograshdi bashimin en xarab vaxtinda hell elioyemmirem normal vaxtda baxmaq lazimdi
//                //ya paydu paceluyu jopunnan
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return users;
//    }
//
//    public String getUsernameById(UUID uuid) {
//        try (Connection connection = DatabaseManager.connect()) {
//            String SELECT_USERNAME_BY_ID = "SELECT user_name FROM users WHERE id = ?";
//            PreparedStatement statement = connection.prepareStatement(SELECT_USERNAME_BY_ID);
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
//        try (Connection connection = DatabaseManager.connect()) {
//            String SELECT_STATUS_BY_ID = "SELECT status FROM users WHERE id = ?";
//            PreparedStatement statement = connection.prepareStatement(SELECT_STATUS_BY_ID);
//            statement.setObject(1, uuid);
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
//
//public Map<UUID, String> getAllUserNamesById() {
//    Map<UUID, String> userNamesById = new HashMap<>();
//        try (Connection connection = DatabaseManager.connect()) {
//            String SELECT_ALL_USER_IDS = "SELECT id, user_name FROM users";
//            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USER_IDS);
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                UUID userId = UUID.fromString(resultSet.getString("id"));
//                String userName = resultSet.getString("user_name");
//                userNamesById.put(userId, userName);
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return userNamesById;
//    }

}

