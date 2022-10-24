package com.example.projectpractice.v1.response;

import com.example.projectpractice.v1.entity.OrderLine;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.ArrayList;
import java.util.List;
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
    List<OrderLineResponse> responseList = new ArrayList<>();
    for (OrderLine orderLine : orderLineList) {
      responseList.add(OrderLineResponse.from(orderLine));
    }
    return responseList;
  }

}
