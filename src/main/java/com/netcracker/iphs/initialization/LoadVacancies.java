package com.netcracker.iphs.initialization;

import com.netcracker.iphs.database.model.vacancy.Vacancy;
import com.netcracker.iphs.services.serviceImpl.ServiceToSearchForTagsInStringImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class LoadVacancies {

  private ServiceToSearchForTagsInStringImpl serviceToSearchForTagsInString;


  protected List<Vacancy> createVacanciesWithParametrs() {
    return new ArrayList<Vacancy>() {{
      add(new Vacancy("Team lead analyst",
          new ArrayList<>(Arrays.asList(
              "In implementation and research team analyst is needed. We offer open-space office",
              " close to Okrujnaya with competitive salary and annual bonuses",
              ", business trips and friendly young team.")),
          "Moscow",
          new ArrayList<>(Arrays.asList(
              "We expect from successful candidate basics of: telecom, python,",
              " risk management, communicative and solving problem skills. Quite good will be",
              " acquainted with agile and machine learning.")),
          new ArrayList<>(Arrays.asList("The ability to program in Java",
              "development of high-load systems"))
      ));
    }};
  }

  public List<Vacancy> getVacanciesWithTags(List<Vacancy> vacancies) {
    vacancies.forEach(vacancy -> {
      StringBuilder dis = new StringBuilder();
      vacancy.getConditions().forEach(dis::append);
      vacancy.getRequirements().forEach(dis::append);
      vacancy.getResponsibilities().forEach(dis::append);
      vacancy.setTags(serviceToSearchForTagsInString.findAllTagsInString(dis.toString()));
    });
    return vacancies;
  }
}
