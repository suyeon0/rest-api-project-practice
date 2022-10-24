package com.example.projectpractice.v1.response;

import com.example.projectpractice.enums.OrderStatus;
import com.example.projectpractice.v1.entity.Order;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class OrderResponse {

    private String orderNo;

    private OrderStatus orderStatus;

    private List<OrderLineResponse> orderLines = new ArrayList<>();

    private List<OrderResponse> orderList;

    public static OrderResponse from(Order order){
        return OrderResponse.builder()
            .orderNo(order.getOrderNo())
            .orderStatus(order.getOrderStatus())
            .orderLines(OrderLineResponse.from(order.getOrderLines()))
            .build();
    }

    public static List<OrderResponse> from(List<Order> orderList){
        List<OrderResponse> orderResponseList = new ArrayList<>();
        for(Order order : orderList){
            orderResponseList.add(OrderResponse.from(order));
        }
        return orderResponseList;
    }

}
