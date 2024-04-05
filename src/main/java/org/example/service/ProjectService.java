package org.example.service;

import lombok.Builder;
import org.example.domain.Project;
import org.example.message.GenericResponse;
import org.example.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;

@SuppressWarnings("ALL")
@Service
public class ProjectService {
    private ProjectRepository projectRepository;
    private TodoService todoService;
    @Builder
    public ProjectService(ProjectRepository projectRepository, TodoService todoService) {
        this.projectRepository = projectRepository;
        this.todoService = todoService;
    }

    public GenericResponse<Project> createNewProject(Project newProject) {
        projectRepository.addProject(newProject);
        return GenericResponse.success(newProject);
    }

    public List<Project> getAllProjects() {
        return projectRepository.getProjectList();
    }

    public Project getProjectById(UUID projectId){
        return projectRepository.getProjectById(projectId);
    }
}