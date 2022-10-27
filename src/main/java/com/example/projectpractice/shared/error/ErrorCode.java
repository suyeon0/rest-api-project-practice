package com.example.projectpractice.shared.error;

public enum ErrorCode {

  // Common
  INVALID_INPUT_VALUE("C001", "invalid parameter %s"),
  FAILED_REQUEST_PARSING("C002", "Failed request JSON parsing: %s"),
  FATAL("C999", "unknown exception"),

  // Service
  ENTITY_NOT_FOUND("S001", "%s entity not found"),
  ENTITY_EXIST("S002", "%s entity existed");

  private final String code;
  private final String message;

  ErrorCode(String code, String message) {
    this.code = code;
    this.message = message;
  }
}
