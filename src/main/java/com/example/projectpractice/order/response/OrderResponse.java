package com.example.projectpractice.order.response;

import com.example.projectpractice.order.enums.OrderStatus;
import com.example.projectpractice.shared.domain.order.entity.Order;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class OrderResponse {

    private String orderNo;

    private OrderStatus orderStatus;

    private List<OrderLineResponse> orderLines;

    private List<OrderResponse> orderList;

    public static OrderResponse from(Order order){
        return OrderResponse.builder()
            .orderNo(order.getOrderNo())
            .orderStatus(order.getOrderStatus())
            .orderLines(OrderLineResponse.from(order.getOrderLines()))
            .build();
    }

    public static List<OrderResponse> from(List<Order> orderList){
        return orderList.stream().map(OrderResponse::from).collect(Collectors.toList());
    }

}
