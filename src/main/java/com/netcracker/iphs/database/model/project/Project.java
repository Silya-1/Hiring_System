package com.netcracker.iphs.database.model.project;

import com.netcracker.iphs.database.model.claim.Claim;
import com.netcracker.iphs.database.model.vacancy.Vacancy;
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
@Table(name = "projects")
public class Project implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "managerName")
  private String managerName;

  @Enumerated
  @ElementCollection(targetClass = Tag.class)
  private List<Tag> tags;

  @ElementCollection(targetClass = String.class)
  private List<String> description;

  @ElementCollection(targetClass = String.class)
  private List<String> requirements;

  @ElementCollection(targetClass = Long.class)
  private List<Long> employees;

  @ElementCollection(targetClass = Vacancy.class)
  private List<Vacancy> vacancies;

  @ElementCollection(targetClass = Claim.class)
  private List<Claim> claims;

  public Project(String name, String managerName, List<String> description, List<String> requirements) {
    this.name = name;
    this.managerName = managerName;
    this.description = description;
    this.requirements = requirements;
  }
}
