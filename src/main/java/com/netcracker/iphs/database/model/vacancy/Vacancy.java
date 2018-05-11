package com.netcracker.iphs.database.model.vacancy;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "vacancies")
public class Vacancy implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "project")
  private String project;

  @Column(name = "projectID")
  private Long projectID;

  @ElementCollection(targetClass = String.class)
  private List<String> conditions;

  @Column(name = "location")
  private String location;

  @Enumerated
  @ElementCollection(targetClass = Tag.class)
  private List<Tag> tags;

  @ElementCollection(targetClass = String.class)
  private List<String> requirements;

  @ElementCollection(targetClass = String.class)
  private List<String> responsibilities;


  public Vacancy(String name, List<String> conditions, String location, List<String> requirements, List<String> responsibilities) {
    this.name = name;
    this.conditions = conditions;
    this.location = location;
    this.requirements = requirements;
    this.responsibilities = responsibilities;
  }
}
