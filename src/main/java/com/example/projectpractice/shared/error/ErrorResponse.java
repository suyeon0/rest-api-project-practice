package com.example.projectpractice.shared.error;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ErrorResponse {

  private String message;
  private int statusCode;
  private List<FieldError> errors;
  private String code;

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public static class FieldError {
    private String field;
    private String value;
    private String reason;
  }

}
