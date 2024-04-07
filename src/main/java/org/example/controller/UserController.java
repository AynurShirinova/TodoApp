package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.example.domain.User;
import org.example.dto.UsersDTO;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Scanner;
import java.util.UUID;

@Builder
@Validated
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> getUserById(@PathVariable UUID id) {
        UsersDTO userDTO = userService.findById(id);
        if (userDTO != null) {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/mail/{mail}")
    public ResponseEntity<UsersDTO> getUserByMail(@PathVariable String mail) {
        UsersDTO userDTO = userService.findByMail(mail);
        if (userDTO != null) {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private final Scanner scanner = new Scanner(System.in);
    private final UUID id = UUID.randomUUID();

//    public UserController(UserService userService){
//        this.userService = userService;
//    }
//
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
                    userService.logIn();
                    break;
                case "2":
                    userService.logUp();
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




//
//    private void logUp() {
//        System.out.print("E-poçt daxil edin: ");
//        String mail = scanner.nextLine();
//        System.out.print("İstifadəçi adı daxil edin: ");
//        String userName = scanner.nextLine();
//        System.out.print("Şifrə daxil edin: ");
//        String password = scanner.nextLine();
//        User user = User.builder()
//                .mail(mail)
//                .userName(userName)
//                .password(password)
//                .build();
//        userService.logUp(user);
//        System.out.println(user.getUserName());
//    }
//
//    private void logIn() {
//        System.out.print("E-poçt daxil edin: ");
//        String mail = scanner.nextLine();
//        System.out.print("Şifrə daxil edin: ");
//        String password = scanner.nextLine();
//         User user = User.builder()
//                 .mail(mail)
//                 .password(password)
//                 .build();
//         userService.logIn(user);
//    }
}
