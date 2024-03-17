package org.example.service;

import lombok.Builder;
import org.example.Main;
import org.example.domain.Project;
import org.example.repository.ProjectRepository;
import java.util.Scanner;
import java.util.UUID;
import java.util.List;
@SuppressWarnings("ALL")

public class ProjectService {
    private ProjectRepository projectRepository;
    private TodoService todoService;
    private Scanner scanner = new Scanner(System.in);
    @Builder
    public ProjectService(ProjectRepository projectRepository, TodoService todoService) {
        this.projectRepository = projectRepository;
        this.todoService = todoService;
    }


    public void createNewProject() {
        System.out.println("Proje başlığını girin:");
        String title = scanner.nextLine();
        System.out.println("description:");
        String description = scanner.nextLine();

        Project newProject = Project.builder()
                .title(title)
                .description(description)
                .build();;
        projectRepository.addProject(newProject);
        System.out.println("Yeni proje oluşturuldu: " + title);
    }

    public void accessExistingProject() {
        System.out.println("Mevcut projeler:");
        List<Project> projects = projectRepository.getProjectList();
        projects.forEach(project -> System.out.println("ID: " + project.getId() + ", Başlık: " + project.getTitle()));
        System.out.println("Daxil olmaq istediğiniz proje ID'sini girin:");
        UUID projectId = UUID.fromString(scanner.nextLine());
        Project selectedProject = projectRepository.getProjectById(projectId);

        if (selectedProject != null) {
            System.out.println("Şu anki proje: " + selectedProject.getTitle());
            new Main().todoController.run();
        } else {
            System.out.println("Proje bulunamadı.");
        }
    }
}