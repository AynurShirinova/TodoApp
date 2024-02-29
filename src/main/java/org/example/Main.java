package org.example;

import org.example.controller.TodoController;
import org.example.repository.ProjectRepository;
import org.example.repository.TodoRepository;
import org.example.service.ProjectService;
import org.example.service.TodoService;
import org.example.service.UserService;
@SuppressWarnings("ALL")


public class Main {
    TodoRepository repository = new TodoRepository();
    ProjectRepository projectRepository = new ProjectRepository();

    UserService userService=new UserService();
    TodoService todoService = new TodoService(repository,userService);
    public TodoController todoController = new TodoController(todoService);
    public ProjectService projectService = new ProjectService(projectRepository, todoService);


    public static void main(String[] args) {
        new Main().userService.loging();



    }


}