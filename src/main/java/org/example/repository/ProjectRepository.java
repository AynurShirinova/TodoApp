package org.example.repository;


import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

import org.example.domain.Project;

@SuppressWarnings("ALL")

public class ProjectRepository {
    private List<Project> projectList;

    public ProjectRepository() {
        this.projectList = new ArrayList<>();
    }

    public void addProject(Project project) {
        projectList.add(project);
    }

    public Project getProjectById(UUID id) {
        return projectList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

//        for (Project project : projectList) {
//            if (project.getId().equals(id)) {
//                return project;
//            }
//        }
//        return null; // Proje bulunamazsa null döner
    }

    public List<Project> getProjectList() {
        return new ArrayList<>(projectList); // Projelerin bir kopyasını döner
    }

    public void deleteProject(UUID id) {
        projectList.removeIf(project -> project.getId().equals(id));
    }

}
