package com.netcracker.iphs.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Maks on 21.04.2018.
 */
@Getter
@AllArgsConstructor
public enum  Status {

  INPROGRESS("in_progress"), ACCEPT("accept");

  private final String value;

  @JsonCreator
  public static Status parseString(String value) {
    for (Status status : Status.values()) {
      if (status.getValue().equalsIgnoreCase(value)) {
        return status;
      }
    }
    return null;
  }
}