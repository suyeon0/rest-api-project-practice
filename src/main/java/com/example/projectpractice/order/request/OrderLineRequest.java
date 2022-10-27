package com.example.projectpractice.order.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OrderLineRequest {

    @NotEmpty
    private String productNo;

    @Min(value = 1)
    private Long quantity;
}
