package com.example.projectpractice.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class DefaultResponse<T> {

    private final int statusCode;
    private final String message;
    private final T data;

}
