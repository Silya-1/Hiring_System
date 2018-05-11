package com.netcracker.iphs.database.service.employee;

import com.netcracker.iphs.database.model.employee.Employee;
import com.netcracker.iphs.database.repositories.employee.EmployeeRepository;
import com.netcracker.iphs.services.ServiceToSearchForTagsInString;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private EmployeeRepository employeeRepository;

  private ServiceToSearchForTagsInString serviceToSearchForTagsInString;

  @Autowired
  public EmployeeServiceImpl(
      EmployeeRepository employeeRepository,
      ServiceToSearchForTagsInString serviceToSearchForTagsInString) {
    this.employeeRepository = employeeRepository;
    this.serviceToSearchForTagsInString = serviceToSearchForTagsInString;
  }

  @Override
  public Employee saveEmployee(Employee employee) {
    return this.employeeRepository.save(employee);
  }

  @Override
  public void deleteEmployee(Long id) {
    this.employeeRepository.delete(id);
  }

  @Override
  public Employee getEmployeeById(Long id) {
    return this.employeeRepository.findOne(id);
  }

  @Override
  public List<Employee> listEmployee() {
    List<Employee> employees = new ArrayList<>();
    this.employeeRepository.findAll().forEach(employees::add);
    return employees;
  }

  public Employee getEmployeeByLogin(String login) {
    return
        listEmployee().stream().filter(employee -> employee.getLogin().equals(login)).findFirst()
            .orElseThrow(null);
  }

  @Override
  public boolean existEmployee(String name) {
    return StreamSupport.stream(employeeRepository.findAll().spliterator(), false)
        .anyMatch(employee -> employee.getName().equals(name));
  }

  public void updateTags(Employee employee) {
    StringBuilder skills = new StringBuilder();
    employee.getSkills().forEach(skills::append);
    employee.setTags(serviceToSearchForTagsInString.findAllTagsInString(
        employee.getJobRole() + employee.getLocation() + skills.toString()));
    employeeRepository.save(employee);
  }

}
