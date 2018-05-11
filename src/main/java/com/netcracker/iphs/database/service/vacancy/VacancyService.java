package com.netcracker.iphs.database.service.vacancy;

import com.netcracker.iphs.database.model.vacancy.Vacancy;
import java.util.List;

/**
 * Created by Maks on 21.04.2018.
 */
public interface VacancyService {

  public Vacancy saveVacancy(Vacancy vacancy);

  public void deleteVacancy(Long id);

  public Vacancy getVacancyById(Long id);

  public List<Vacancy> getAllVacancies();

  public boolean existVacancy(String name);
}
