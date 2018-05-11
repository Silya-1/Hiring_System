package com.netcracker.iphs.database.service.project;

import com.netcracker.iphs.database.model.project.Project;
import java.util.List;

public interface ProjectService {

  public Project saveProject(Project project);

  public void deleteProject(Long id);

  public Project getProjectById(Long id);

  public List<Project> getAllProjects();

  public boolean existProject(String name);
}
