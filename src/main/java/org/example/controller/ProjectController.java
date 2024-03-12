package org.example.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.repository.ProjectRepository;
import org.example.service.ProjectService;
import java.util.Scanner;
@Builder
@Getter
@Setter
public class ProjectController {
    private final Scanner scanner = new Scanner(System.in);
    private final ProjectService projectService;
    private final ProjectRepository projectRepository;
    public ProjectController(ProjectService projectService,ProjectRepository projectRepository) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
    }

    public void manageProjects() {
        while (true) {
            System.out.println("Yeni bir proje yaratmak istiyor musunuz? (Evet/Hayır)");
            String cevap = scanner.nextLine();
            if ("Evet".equalsIgnoreCase(cevap)) {
                projectService.createNewProject();
            } else if ("Hayır".equalsIgnoreCase(cevap)) {
                if (projectRepository.getProjectList().isEmpty()) {
                    System.out.println("Mevcut proje yok.");
                } else {
                    projectService.accessExistingProject();
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
}
