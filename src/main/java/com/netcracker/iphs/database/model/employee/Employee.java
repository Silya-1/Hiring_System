package com.netcracker.iphs.database.model.employee;


import com.netcracker.iphs.resources.Position;
import com.netcracker.iphs.resources.Tag;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "full_name")
  private String name;

  @Column(name = "login")
  private String login;

  @Column(name = "password")
  private String password;

  @Column(name = "age")
  private Integer age;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "position")
  private Position position;

  @ElementCollection(targetClass = String.class)
  private List<String> skills;

  @Column(name = "job_role")
  private String jobRole;

  @ElementCollection(targetClass = String.class)
  private List<String> addSkills;

  @ElementCollection(targetClass = String.class)
  private List<String> conditions;

  @Column(name = "location")
  private String location;

  @ElementCollection(targetClass = Long.class)
  private List<Long> projects;

  @ElementCollection(targetClass = Long.class)
  private List<Long> claims;

  @Enumerated
  @ElementCollection(targetClass = Tag.class)
  private List<Tag> tags;

  public Employee(String name, String login, String password, Integer age,
      String phoneNumber, Position position, List<String> skills, String jobRole,
      List<String> addSkills, List<String> conditions, String location) {
    this.name = name;
    this.login = login;
    this.password = password;
    this.age = age;
    this.phoneNumber = phoneNumber;
    this.position = position;
    this.skills = skills;
    this.jobRole = jobRole;
    this.addSkills = addSkills;
    this.conditions = conditions;
    this.location = location;
  }
}



