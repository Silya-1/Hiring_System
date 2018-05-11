package com.netcracker.iphs.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Position {

  MANAGER("manager"), EMPLOYER("employer");

  private final String value;

  @JsonCreator
  public static Position parseString(String value) {
    for (Position position : Position.values()) {
      if (position.getValue().equalsIgnoreCase(value)) {
        return position;
      }
    }
    return null;
  }
}

