package com.example.projectpractice.advice;

import com.example.projectpractice.v1.response.DefaultResponse;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice(basePackages = "com.example.projectpractice")
public class GlobalControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public DefaultResponse runtimeException(final RuntimeException e) {
        log.error(e.getMessage(), e);
        return DefaultResponse.builder()
            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
            .data(e.getMessage())
            .build();
    }

    /**
     * @Valid 유효성 체크 exception
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public DefaultResponse methodArgumentNotValidException(final MethodArgumentNotValidException e) {

        return DefaultResponse.builder()
            .statusCode(HttpStatus.BAD_REQUEST.toString())
            .responseMessage(e.getBindingResult().getAllErrors().toString())
            .build();
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public DefaultResponse noSuchElementException(final NoSuchElementException e) {
        log.error(e.getMessage(), e);
        return DefaultResponse.builder()
            .statusCode(HttpStatus.BAD_REQUEST.toString())
            .responseMessage(e.getMessage())
            .build();
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public DefaultResponse illegalArgumentException(final IllegalArgumentException e) {
        log.error(e.getMessage(), e);
        return DefaultResponse.builder()
            .statusCode(HttpStatus.BAD_REQUEST.toString())
            .responseMessage(e.getMessage())
            .build();
    }

}
