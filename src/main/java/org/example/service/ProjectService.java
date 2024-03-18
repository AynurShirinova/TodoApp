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
    @Builder
    public ProjectService(ProjectRepository projectRepository, TodoService todoService) {
        this.projectRepository = projectRepository;
        this.todoService = todoService;
    }

    public void createNewProject(Project newProject) {
        projectRepository.addProject(newProject);
        System.out.println("Yeni proje oluşturuldu: " + newProject.getTitle());
    }

    public List<Project> getAllProjects() {
        return projectRepository.getProjectList();
    }

    public Project getProjectById(UUID projectId){
        return projectRepository.getProjectById(projectId);
    }
}