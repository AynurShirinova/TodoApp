package org.example;

import lombok.AllArgsConstructor;
import org.example.controller.ProjectController;
import org.example.controller.UserController;
import org.example.domain.User;
import org.example.repository.ProjectRepository;
import org.example.repository.TodoRepository;
import org.example.service.ProjectService;
import org.example.service.TodoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SuppressWarnings("ALL")
@SpringBootApplication
@EnableDiscoveryClient
@AllArgsConstructor
@EnableWebMvc
@EntityScan(basePackages = {"org.example.domain"})
@EnableJpaRepositories(basePackages = {"org.example.repository"})
public class Main {
    private final UserController userController;
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}
