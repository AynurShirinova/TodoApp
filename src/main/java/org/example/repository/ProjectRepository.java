package org.example.repository;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import org.example.domain.Project;

@SuppressWarnings("ALL")

public class ProjectRepository {
    public static List<Project> projectList;

    public ProjectRepository() {
        this.projectList = new ArrayList<>();
    }

    public void addProject(Project project) {
        projectList.add(project);
    }

    public Project getProjectById(UUID id) {
        for (Project project : projectList) {
            if (project.getId().equals(id)) {
                return project;
            }
        }
        return null;
    }

    public List<Project> getProjectList() {
        return new ArrayList<>(projectList); // Projelerin bir kopyasını döner
    }

    public void deleteProject(UUID id) {
        projectList.removeIf(project -> project.getId().equals(id));
    }

    public void updateProject(UUID id, Project updatedProject) {
        for (int i = 0; i < projectList.size(); i++) {
            Project project = projectList.get(i);
            if (project.getId().equals(id)) {
                project.setTitle(updatedProject.getTitle());
                project.setDescription(updatedProject.getDescription());
                // Tipik olaraq, createdAt yaradıldığı zaman sabit qalır və yenilənmir
                // Əgər yenilənməli olsa, aşağıdakı xətti şərhden çıxarın
                // project.setCreatedAt(updatedProject.getCreatedAt());
                return;
            }
        }
    }

}