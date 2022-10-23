package com.example.projectpractice.v1.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class DefaultResponse<T> {

    private final String statusCode;
    private final String responseMessage;
    private final T data;

}
