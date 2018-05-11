package com.netcracker.iphs.services.serviceImpl;

import com.netcracker.iphs.resources.Tag;
import com.netcracker.iphs.services.ServiceToSearchForTagsInString;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Maks on 21.04.2018.
 */
@RunWith(DataProviderRunner.class)
public class ServiceToSearchForTagsInStringImplTest {

  private ServiceToSearchForTagsInString serviceToSearchForTagsInString;

  @Before
  public void setUp() {
    serviceToSearchForTagsInString = new ServiceToSearchForTagsInStringImpl();
  }

  @DataProvider
  public static Object[][] dataProviderForFinder() {
    return new Object[][]{
        {"In implementation and research team analyst is needed. We offer open-space office close "
            + "to Okrujnaya with competitive salary and annual bonuses, business trips and friendly "
            + "young team. We expect from successful candidate basics of: telecom, Python 1.2, risk management, "
            + "communicative and problem-solving skills. Quite good will be acquainted with agile "
            + "and machine learning.", 6},
        {"Our project is creating in Moscow", 1},
        {"We need man with some skills in SQL as senior software engineer", 3},
    };
  }

  @Test
  @UseDataProvider("dataProviderForFinder")
  public void findAllTagsInString(String row, int expected) throws Exception {
    List<Tag> result = serviceToSearchForTagsInString.findAllTagsInString(row);
    int actualResult = result.size();
    System.out.println(result.toString());
    Assert.assertEquals(expected, actualResult);
  }

}