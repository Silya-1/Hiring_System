package com.netcracker.iphs.database.service.employee;

import com.netcracker.iphs.database.model.employee.Employee;
import java.util.List;

public interface EmployeeService {

  public Employee saveEmployee(Employee employee);

  public void deleteEmployee(Long id);

  public Employee getEmployeeById(Long id);

  public List<Employee> listEmployee();

  public boolean existEmployee(String name);
}
