package com.example.projectpractice.shared.domain.order.entity;

import com.example.projectpractice.order.request.OrderLineRequest;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "order_line")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_line_seq")
    private Long orderLineSeq;

    @Column(name = "product_no")
    private String productNo;

    @Min(0)
    private Long quantity;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_no")
    private Order order;

    @Builder
    private OrderLine(String productNo, Long quantity) {
        this.productNo = productNo;
        this.quantity = quantity;
    }

    public static OrderLine from(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
            .productNo(orderLineRequest.getProductNo())
            .quantity(orderLineRequest.getQuantity())
            .build();
    }
}
