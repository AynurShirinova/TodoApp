package org.example.controller;

import lombok.Builder;
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
                    this.userService.logIn();
                    break;
                case "2":
                    this.userService.logUp();
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
}
