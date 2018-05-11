package com.netcracker.iphs.database.service.project;

import com.netcracker.iphs.database.model.claim.Claim;
import com.netcracker.iphs.database.model.project.Project;
import com.netcracker.iphs.database.model.vacancy.Vacancy;
import com.netcracker.iphs.database.repositories.project.ProjectRepository;
import com.netcracker.iphs.services.ServiceToSearchForTagsInString;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

  private ProjectRepository projectRepository;

  private ServiceToSearchForTagsInString serviceToSearchForTagsInString;

  @Autowired
  public ProjectServiceImpl(
      ProjectRepository projectRepository,
      ServiceToSearchForTagsInString serviceToSearchForTagsInString) {
    this.projectRepository = projectRepository;
    this.serviceToSearchForTagsInString = serviceToSearchForTagsInString;
  }

  @Override
  public Project saveProject(Project project) {
    return projectRepository.save(project);
  }

  @Override
  public void deleteProject(Long id) {
    projectRepository.delete(id);
  }

  @Override
  public Project getProjectById(Long id) {
    return projectRepository.findOne(id);
  }

  @Override
  public List<Project> getAllProjects() {
    List<Project> result = new ArrayList<>();
    projectRepository.findAll().forEach(result::add);
    return result;
  }

  @Override
  public boolean existProject(String name) {
    return getAllProjects().stream().anyMatch(project -> project.getName().equals(name));
  }

  public void addVacancyToProject(Vacancy vacancy){
    Project project = getProjectById(vacancy.getProjectID());
    project.getVacancies().add(vacancy);
    projectRepository.save(project);
  }

  public void addClaimToProect(Claim claim, Vacancy vacancy){
    Project project = getProjectById(vacancy.getProjectID());
    project.getClaims().add(claim);
    projectRepository.save(project);
  }

  public Long getProjectByName(String name){
    System.out.println(name);
    return getAllProjects().stream().filter(project -> project.getName().equals(name)).findFirst().get().getId();
  }

  public void updateTags(Project project) {
    StringBuilder dis = new StringBuilder();
    project.getDescription().forEach(dis::append);
    project.getRequirements().forEach(dis::append);
    project.setTags(serviceToSearchForTagsInString.findAllTagsInString(dis.toString()));
    projectRepository.save(project);
  }
}
