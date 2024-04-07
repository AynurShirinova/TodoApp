package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.User;
import org.example.dto.UsersDTO;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
        Scanner scanner = new Scanner(System.in);
//    @Autowired  burani yaxshi yazmisan
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public UsersDTO findById(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(this::mapToUserDTO).orElse(null);
    }

    public UsersDTO findByMail(String mail) {
        User user = userRepository.findByMail(mail);
        return user != null ? mapToUserDTO(user) : null;
    }

    public void logIn() {
        System.out.println("mail daxil ele");
        String mail = scanner.nextLine();
        System.out.println("password daxil ele");
        String password = scanner.nextLine();
        boolean bool = userRepository.existsByMailAndPassword(mail, password);
        System.out.println(bool ? "giris basarili" : "giris basarisiz");
    }

    public UUID logUp() {
        System.out.println("mail daxil ele");
        String mail = scanner.nextLine();
        System.out.println("username daxil ele");
        String username = scanner.nextLine();
        System.out.println("password daxil ele");
        String password = scanner.nextLine();
        User user = new User();
        user.setMail(mail);
        user.setUserName(username);
        user.setPassword(password);
        userRepository.save(user);
        return user.getId();
    }
    public UsersDTO mapToUserDTO(User user) {
        return UsersDTO.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .mail(user.getMail())
                .password(user.getPassword())
                .build();
    }


//    private final UserRepository userRepository;
//
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public void logUp(User user) {
//        userRepository.logUp(user);
//    }
//
//    public void logIn(User user) {
//        userRepository.logIn(user);
//    }
//
//    public String getEmailByUserId(UUID userId) {
//        return userRepository.getEmailByUserId(userId);
//    }
//
//    public String getUsernameById(UUID uuid) {
//        return userRepository.getUsernameById(uuid);
//    }
//
//    public String getStatusById(UUID uuid) {
//        return userRepository.getStatusById(uuid);
//    }
//
//    public Map<UUID, String> getAllUserIds() {
//        return userRepository.getAllUserNamesById();
//    }
}

