package org.example.controller;

import lombok.Builder;
import org.example.domain.User;
import org.example.service.UserService;

import java.util.Scanner;
import java.util.UUID;

@Builder
public class UserController {
    private final Scanner scanner = new Scanner(System.in);
    private final UUID id = UUID.randomUUID();

    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    public void loging() {
        String choice;
        do {
            System.out.println("1.log in");
            System.out.println("2. log up");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    logIn();
                    break;
                case "2":
                    logUp();
                    break;
                case "0":
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println("-----------------------");
        } while (!choice.equals("0"));

    }

    private void logUp() {
        System.out.print("E-poçt daxil edin: ");
        String mail = scanner.nextLine();
        System.out.print("İstifadəçi adı daxil edin: ");
        String userName = scanner.nextLine();
        System.out.print("Şifrə daxil edin: ");
        String password = scanner.nextLine();
        User user = User.builder()
                .mail(mail)
                .userName(userName)
                .password(password)
                .build();
        userService.logUp(user);
        System.out.println(user.getUserName());
    }

    private void logIn() {
        System.out.print("E-poçt daxil edin: ");
        String mail = scanner.nextLine();
        System.out.print("Şifrə daxil edin: ");
        String password = scanner.nextLine();
         User user = User.builder()
                 .mail(mail)
                 .password(password)
                 .build();
         userService.logIn(user);
    }
}
