package com.example.projectpractice.v1.response;

import com.example.projectpractice.v1.entity.Order;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class OrderResponse {

    private Order order;

    private List<Order> orderList;

    public static OrderResponse from(Order order){
        return OrderResponse.builder()
            .order(order)
            .build();
    }

    public static OrderResponse from(List<Order> orderList){
        return OrderResponse.builder()
            .orderList(orderList)
            .build();
    }

}
