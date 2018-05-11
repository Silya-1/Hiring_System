package com.netcracker.iphs.initialization;

import com.netcracker.iphs.database.model.project.Project;
import com.netcracker.iphs.services.serviceImpl.ServiceToSearchForTagsInStringImpl;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class LoadProjects {

  private ServiceToSearchForTagsInStringImpl serviceToSearchForTagsInString;


  protected List<Project> createProjectsWithParametrs() {
    return new ArrayList<Project>() {
      {
        add(new Project("Mano",
            "Jonny Cash",
            new ArrayList<String>() {{
              add("Data centers became more and more popular.");
              add(" Data size significantly goes up and clouds in DC are simple and appropriate way to solve that problem.");
              add(" System is dealing with cloud technologies in telecom's sphere.");
              add(" Wide range of trend instruments: python, C++, Go is used by out team. Join us!");
            }},
            new ArrayList<String>() {{
              add("good knowledge of OOP");
              add("experience in agile");
            }}));

        add(new Project("UNO",
            "Jonny Cash",
            new ArrayList<String>() {{
              add("Today's trend to improve level of education is supported by wide range of countries and corporations.");
              add(" People are interested in large amount of specialists who can solve tricky problems and can think critical.");
              add(" Our system based on Go servers and use SQL databases.");
              add(" We need to make api for android and ios smartphones.");
            }},
            new ArrayList<String>() {{
              add("good knowledge of OOP");
              add("experience in agile");
            }}));

        add(new Project("Research tea",
            "Jonny Cash",
            new ArrayList<String>() {{
              add("We are research market team, we provide an opportunity for company to be in trend and solve problems with risk management and diplomatic sphere.");
              add(" Total stream and pace of development determination is our main goal.");
              add(" We use machine learning, python and Java EE as stack of technologies.");
            }},
            new ArrayList<String>() {{
              add("good knowledge of OOP");
              add("experience in agile");
            }}));

        add(new Project("Network orchestrator",
            "Jonny Cash",
            new ArrayList<String>() {{
              add("In NFV sphere significant breakthrough is made by our specialists.");
              add(" So, servers on python and c++ in combination with machine learning give a large flexibility to networking in telecom.");
              add(" However, one of the most competitive areas needs to be improved and streamed in profitable direction.");
              add(" That's what we are doing now.");
            }},
            new ArrayList<String>() {{
              add("good knowledge of OOP");
              add("experience in agile");
            }}));
      }
    };
  }

  public List<Project> getProjectsWithTags(List<Project> projects) {
    projects.forEach(project -> {
      StringBuilder dis = new StringBuilder();
      project.getDescription().forEach(dis::append);
      project.getRequirements().forEach(dis::append);
      project.setTags(serviceToSearchForTagsInString.findAllTagsInString(dis.toString()));
    });
    return projects;
  }
}
