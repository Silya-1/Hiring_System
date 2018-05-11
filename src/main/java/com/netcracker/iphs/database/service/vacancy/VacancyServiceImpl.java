package com.netcracker.iphs.database.service.vacancy;

import com.netcracker.iphs.database.model.vacancy.Vacancy;
import com.netcracker.iphs.database.repositories.vacancy.VacancyRepository;
import com.netcracker.iphs.services.ServiceToSearchForTagsInString;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Maks on 21.04.2018.
 */
@Service
public class VacancyServiceImpl implements VacancyService {

  private VacancyRepository vacancyRepository;

  private ServiceToSearchForTagsInString serviceToSearchForTagsInString;

  @Autowired
  public VacancyServiceImpl(
      VacancyRepository vacancyRepository,
      ServiceToSearchForTagsInString serviceToSearchForTagsInString) {
    this.vacancyRepository = vacancyRepository;
    this.serviceToSearchForTagsInString = serviceToSearchForTagsInString;
  }

  @Override
  public Vacancy saveVacancy(Vacancy vacancy) {
    return vacancyRepository.save(vacancy);
  }

  @Override
  public void deleteVacancy(Long id) {
    vacancyRepository.delete(id);
  }

  @Override
  public Vacancy getVacancyById(Long id) {
    return vacancyRepository.findOne(id);
  }

  @Override
  public List<Vacancy> getAllVacancies() {
    List<Vacancy> result = new ArrayList<>();
    vacancyRepository.findAll().forEach(result::add);
    return result;
  }

  @Override
  public boolean existVacancy(String name) {
    return getAllVacancies().stream().anyMatch(vacancy -> vacancy.getName().equals(name));
  }

  public void updateTags(Vacancy vacancy) {
    StringBuilder dis = new StringBuilder();
    vacancy.getConditions().forEach(dis::append);
    vacancy.getConditions().forEach(dis::append);
    vacancy.getRequirements().forEach(dis::append);
    vacancy.getResponsibilities().forEach(dis::append);
    vacancy.setTags(serviceToSearchForTagsInString.findAllTagsInString(dis.toString()));
    vacancyRepository.save(vacancy);
  }
}
