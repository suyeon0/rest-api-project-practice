package com.example.projectpractice.advice;

import com.example.projectpractice.response.DefaultResponse;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandlerAdvice {

  @ExceptionHandler(RuntimeException.class)
  public DefaultResponse runtimeException(final RuntimeException e) {
    log.error(e.getMessage(), e);
    return DefaultResponse.builder()
        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .data(e.getMessage())
        .build();
  }

  /**
   * @Valid 유효성 체크 exception
   */
  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public DefaultResponse methodArgumentNotValidException(final MethodArgumentNotValidException e) {
    log.error(e.getMessage(), e);
    return DefaultResponse.builder()
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .message(e.getBindingResult().getAllErrors().toString())
        .build();
  }

  @ExceptionHandler(value = NoSuchElementException.class)
  public DefaultResponse noSuchElementException(final NoSuchElementException e) {
    log.error(e.getMessage(), e);
    return DefaultResponse.builder()
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(value = IllegalArgumentException.class)
  public DefaultResponse illegalArgumentException(final IllegalArgumentException e) {
    log.error(e.getMessage(), e);
    return DefaultResponse.builder()
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .message(e.getMessage())
        .build();
  }

}
