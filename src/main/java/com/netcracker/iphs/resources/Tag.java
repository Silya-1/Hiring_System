package com.netcracker.iphs.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Maks on 20.04.2018.
 */

@Getter
@AllArgsConstructor
public enum Tag {

  // Skills:
  JAVAEE("javaee"), JAVA8("java8.0"), JAVA7("java7.0"), JAVA9("java9.0"), ML(
      "machine learning"),
  NEURALNETWORK("neural network"), GO(" go "), SCALA("scala"), JS("javascript"), SQL("sql"), PHP(
      "php"), UML("uml"), C(" c "), CPP("c++"), CSHARP("c#"), KOTLIN("kotlin"), ANDROID(
      "android"), MAVEN("maven"), CSS("css"), TELECOM("telecom"), PYTHON("python"),
  SWIFT("swift"), AGILE("agile"), SCRUM("scrum"), ANGULAR("angular"), COMMUNICATIVE(
      "communicative"),
  PROBLEMSOLVING("problem solving"), CRITICALTHINKING("critical thinking"), DIPLOMATIC(
      "diplomatic"),
  MICROSOFTOFFICE("microsoft office"), PROJECTMANAGEMENT("project management"), RISKMANAGEMENT(
      "risk management"),
  STATANAL("statistical analysis"), CLOUDTECHNOLOGIES("cloud technologies"),
  // Job role
  SOFTWARE("software engineer"), JSOFTWARE("junior software engineer"), SSOFTWARE(
      "senior software engineer"),
  TSOFTWARE("teamlead software engineer"), PROJECTMANAGER("project manager"), HR("human resources"),
  QA("quality assurance"), TMANAGER("technical manager"),
  // Locations:
  MOSCOW("moscow"), STPETERSBURG("st. petersburg"), AMSTERDAM("amsterdam"), NEWYORK("new york"),
  RIODEJANEIRO("rio de janeiro"), CHELYABINSK("chelyabinsk"), EKATERINBURG("ekaterinburg"),
  MINSK("minsk"), KAZAN("kazan"), NOTSTATED("not stated"), BOSTON("boston");

  private final String value;

  @JsonCreator
  public static Tag parseString(String value) {
    for (Tag tag : Tag.values()) {
      if (tag.getValue().equalsIgnoreCase(value)) {
        return tag;
      }
    }
    return null;
  }

  public static List<Tag> getAllTags() {
    return Arrays.asList(Tag.values());
  }

}
