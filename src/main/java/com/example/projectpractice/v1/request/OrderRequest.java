package com.example.projectpractice.v1.request;

import com.example.projectpractice.v1.entity.OrderLine;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(Include.NON_NULL)
public class OrderRequest {

    @NotNull
    private List<@Valid OrderLineRequest> orderLines;

    public List<OrderLine> createOrderLineEntity() {
        List<OrderLine> orderLineList = new ArrayList<>();
        for (OrderLineRequest request : this.orderLines) {
            orderLineList.add(OrderLine.from(request));
        }
        return orderLineList;
    }
}
