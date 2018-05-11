package com.netcracker.iphs.initialization;

import com.netcracker.iphs.database.model.employee.Employee;
import com.netcracker.iphs.database.model.project.Project;
import com.netcracker.iphs.database.model.vacancy.Vacancy;
import com.netcracker.iphs.database.service.employee.EmployeeServiceImpl;
import com.netcracker.iphs.database.service.project.ProjectServiceImpl;
import com.netcracker.iphs.database.service.vacancy.VacancyServiceImpl;
import com.netcracker.iphs.services.serviceImpl.ServiceToSearchForTagsInStringImpl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Maks on 21.04.2018.
 */

@Component
public class InitDataBases implements ApplicationRunner {

  @Autowired
  private ServiceToSearchForTagsInStringImpl serviceToSearchForTagsInString;

  @Autowired
  private EmployeeServiceImpl employeeService;

  @Autowired
  private ProjectServiceImpl projectService;

  @Autowired
  private VacancyServiceImpl vacancyService;

  public void run(ApplicationArguments args) {
    addEmployeesInDataBase();
    addProjectsInDataBase();
    addVacanciesInDataBase();
    Employee employee1 = employeeService.getEmployeeById(1L);
    Employee employee2 = employeeService.getEmployeeById(2L);
    Employee employee3 = employeeService.getEmployeeById(3L);
    Project project1 = projectService.getProjectById(1L);
    Project project2 = projectService.getProjectById(2L);
    Vacancy vacancy = vacancyService.getVacancyById(1L);
    //1
    List<Long> projectsId = new ArrayList<Long>() {{
      add(project1.getId());
    }};
    List<Long> employeesId = new ArrayList<Long>() {{
      add(employee1.getId());
      add(employee3.getId());
    }};
    project1.setEmployees(employeesId);
    employee1.setProjects(projectsId);
    employeeService.saveEmployee(employee1);
    projectService.saveProject(project1);

    //2
    projectsId.remove(project1.getId());
    projectsId.add(project2.getId());
    employeesId.remove(employee1.getId());
    employeesId.add(employee2.getId());
    project2.setEmployees(employeesId);
    employee2.setProjects(projectsId);
    employeeService.saveEmployee(employee2);
    projectService.saveProject(project2);

    //3
    projectsId.add(project1.getId());
    employee3.setProjects(projectsId);
    employeeService.saveEmployee(employee3);

  }

  private void addProjectsInDataBase() {
    LoadProjects loadProjects = new LoadProjects(serviceToSearchForTagsInString);

    List<Project> projects = loadProjects.getProjectsWithTags(loadProjects.createProjectsWithParametrs());
    projects.forEach(project -> projectService.saveProject(project));
  }

  private void addEmployeesInDataBase() {
    LoadEmployees loadEmployees = new LoadEmployees(serviceToSearchForTagsInString);

    List<Employee> employees = loadEmployees.getEmployeesWithTags(loadEmployees.createEmployeesWithParametrs());
    employees.forEach(employee -> employeeService.saveEmployee(employee));
  }

  private void addVacanciesInDataBase() {
    LoadVacancies loadVacancies = new LoadVacancies(serviceToSearchForTagsInString);

    List<Vacancy> vacancies = loadVacancies.getVacanciesWithTags(loadVacancies.createVacanciesWithParametrs());
    vacancies.forEach(vacancy -> vacancyService.saveVacancy(vacancy));
  }
}
