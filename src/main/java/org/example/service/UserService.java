package org.example.service;

import org.springframework.stereotype.Service;
import org.example.repository.UserRepository;
import org.example.domain.User;

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

    public Map<UUID, String> getAllUserIds() {
        return userRepository.getAllUserNamesById();
    }
}

