package org.example.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.var;
import org.example.Main;
import org.example.domain.Project;
import org.example.message.GenericResponse;
import org.example.repository.ProjectRepository;
import org.example.service.ProjectService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Builder
@Getter
@Setter
public class ProjectController {
    private final Scanner scanner = new Scanner(System.in);
    private final ProjectService projectService;
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    public void manageProjects() {
        while (true) {
            System.out.println("Yeni bir proje yaratmak istiyor musunuz? (Evet/Hayır)");
            String cevap = scanner.nextLine();
            if ("Evet".equalsIgnoreCase(cevap)) {
                createNewProject();
            } else if ("Hayır".equalsIgnoreCase(cevap)) {
                if (projectService.getAllProjects().isEmpty()) {
                    System.out.println("Mevcut proje yok.");
                } else {
                    listAllProjects();
                }
            } else {
                System.out.println("Geçersiz seçim. Lütfen 'Evet' veya 'Hayır' yazın.");
            }
            System.out.println("Devam etmek istiyor musunuz? (Evet/Hayır)");
            if ("Hayır".equalsIgnoreCase(scanner.nextLine())) {
                break;
            }
        }
    }

    private void listAllProjects() {
        System.out.println("Mevcut projeler:");
        List<Project> projects= projectService.getAllProjects();
        projects.forEach(project -> System.out.println("ID: " + project.getId() + ", Başlık: " + project.getTitle()));
        System.out.println("Daxil olmaq istediğiniz proje ID'sini girin:");
        UUID projectId = UUID.fromString(scanner.nextLine());
         Project selectedProject=projectService.getProjectById(projectId);

        if (selectedProject != null) {
            System.out.println("Şu anki proje: " + selectedProject.getTitle());
            new Main().todoController.run();
        } else {
            System.out.println("Proje bulunamadı.");
        }
    }

    private void createNewProject() {
        System.out.println("Proje başlığını girin:");
        String title = scanner.nextLine();
        System.out.println("description:");
        String description = scanner.nextLine();

        Project newProject = Project.builder()
                .title(title)
                .description(description)
                .build();
        var response = projectService.createNewProject(newProject);
        if(response.isSuccess()){
            System.out.println("Yeni proje oluşturuldu: " + response.getData().getTitle() );

        }
        else{
            System.out.println(response.getMessage());
        }
//         System.out.println("Yeni proje oluşturuldu: " + response.getData().getTitle() + response.getMessage());

    }

}
