package org.example;

import org.example.controller.ProjectController;
import org.example.controller.TodoController;
import org.example.controller.UserController;
import org.example.domain.User;
import org.example.repository.ProjectRepository;
import org.example.repository.TodoRepository;
import org.example.repository.UserRepository;
import org.example.service.ProjectService;
import org.example.service.TodoService;
import org.example.service.UserService;

@SuppressWarnings("ALL")
public class Main {

        // Repository sınıflarının oluşturulması
        TodoRepository todoRepository = new TodoRepository();
        ProjectRepository projectRepository = new ProjectRepository();
        UserRepository userRepository = new UserRepository();

        // Service sınıflarının oluşturulması
        User user=new User();
        UserService userService = new UserService(userRepository);
        TodoService todoService = new TodoService(todoRepository);
        ProjectService projectService = new ProjectService(projectRepository, todoService);

        public TodoController todoController = new TodoController(user, userService, todoService);
        public ProjectController projectController = new ProjectController(projectService);
        UserController userController = new UserController(userService);
    public static void main(String[] args) {
        // Kullanıcı arayüzünün başlatılması
        new Main().userController.loging();
    }
}
