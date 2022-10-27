package com.example.projectpractice.order.response;

import com.example.projectpractice.shared.domain.order.entity.OrderLine;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class OrderLineResponse {

  private Long orderLineSeq;

  private String productNo;

  private Long quantity;

  public static OrderLineResponse from(OrderLine orderLine) {
    return OrderLineResponse.builder()
        .orderLineSeq(orderLine.getOrderLineSeq())
        .productNo(orderLine.getProductNo())
        .quantity(orderLine.getQuantity())
        .build();
  }

  public static List<OrderLineResponse> from(List<OrderLine> orderLineList) {
    return orderLineList.stream().map(OrderLineResponse::from).collect(Collectors.toList());
  }

}
