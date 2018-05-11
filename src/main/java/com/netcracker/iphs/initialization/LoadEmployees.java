package com.netcracker.iphs.initialization;

import com.netcracker.iphs.database.model.employee.Employee;
import com.netcracker.iphs.resources.Position;
import com.netcracker.iphs.services.serviceImpl.ServiceToSearchForTagsInStringImpl;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class LoadEmployees {

  @Autowired
  private ServiceToSearchForTagsInStringImpl serviceToSearchForTagsInString;


  protected List<Employee> createEmployeesWithParametrs() {
    return new ArrayList<Employee>() {{
      add(new Employee("Jonny Cash", "jcash", "jcash", 21,
          "+19105554247", Position.MANAGER,
          new ArrayList<String>() {{
            add("Python,");
            add("Agile");
            add("Diplomatic");
            add("Problem solving");
          }}, "Developer",
          new ArrayList<String>() {{
            add("I can play guitar");
          }},
          new ArrayList<String>() {{
            add("Schedule 2/7");
            add("Daily syncopation");
            add("ZP from 100k");
          }}, "Boston"));

      add(new Employee("Barbara Konopleva", "bkono", "bkono", 19,
          "+891078945612", Position.EMPLOYER, new ArrayList<String>() {{
        add("java se");
        add("kotlin");
        add("android");
      }}, "Software Engineer",
          new ArrayList<String>() {{
            add("I can play guitar");
          }},
          new ArrayList<String>() {{
            add("Schedule 2/7");
            add("Daily syncopation");
            add("ZP from 100k");
          }}, "Moscow"));

      add(new Employee("Mark Elliot Zuckerberg", "mark", "mark", 33,
          "+89127563429", Position.EMPLOYER,
          new ArrayList<String>() {{
            add("Agile");
            add("Scrum");
            add("Angular");
            add("Communicative");
            add("Problem-solving");
            add("Critical thinking");
          }}, "Director",
          new ArrayList<String>() {{
            add("I can play guitar");
          }},
          new ArrayList<String>() {{
            add("Schedule 2/7");
            add("Daily syncopation");
            add("ZP from 100K");
          }}, "New York"));
    }};
  }

  public List<Employee> getEmployeesWithTags(List<Employee> employees) {
    employees.forEach(employee -> {
      StringBuilder dis = new StringBuilder();
      employee.getSkills().forEach(dis::append);
      employee.getConditions().forEach(dis::append);
      employee.setTags(serviceToSearchForTagsInString.findAllTagsInString(
          employee.getJobRole() + employee.getLocation() + dis.toString()));
    });
    return employees;
  }
}
