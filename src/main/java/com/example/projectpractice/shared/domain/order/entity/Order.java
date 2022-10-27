package com.example.projectpractice.shared.domain.order.entity;

import com.example.projectpractice.order.enums.OrderStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

/**
 * 주문
 * - 주문번호 생성규칙: UUID
 * - 주문은 우선 주문번호, 주문상태, 주문내역 로 이루어진다고 가정.
 * - 주문 내역은 무조건 하나 이상!
 * - 주문 상태는 주문 완료, 상품 준비, 배송중, 배송완료, 취소로만 해놓았다
 * - 주문 취소는 배송완료 이전 단계일 때만 가능하다고 가정
 */
@Slf4j
@Entity
@Getter
@NoArgsConstructor
@Table(name = "order_master")
public class Order {

    @Id
    @Column(name = "order_no")
    private String orderNo;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "order")
    private List<OrderLine> orderLines = new ArrayList<>();

    @Builder
    private Order(String orderNo, OrderStatus orderStatus, List<OrderLine> orderLines) {
        Assert.notNull(orderNo, "orderNo must be not null!");
        this.validateOrderLines(orderLines);

        this.orderNo = orderNo;
        this.orderStatus = orderStatus;
        for (OrderLine orderLine : orderLines) {
            this.addOrderLine(orderLine);
        }
    }

    private void addOrderLine(OrderLine orderLine) {
        orderLine.setOrder(this);
        this.getOrderLines().add(orderLine);
    }

    private void validateOrderLines(List<OrderLine> orderLines) {
        if (orderLines == null || orderLines.isEmpty()) {
            throw new IllegalArgumentException("orderLine must be at least one!");
        }
    }

    /**
     * 주문 entity 생성
     *
     * @param orderLines
     * @return
     */
    public static Order from(List<OrderLine> orderLines) {
        return Order.builder()
            .orderNo(UUID.randomUUID().toString())
            .orderStatus(OrderStatus.ORDER_DONE)
            .orderLines(orderLines)
            .build();
    }

    /**
     * 주문 취소
     */
    public void cancel() {
        this.validateStatusToCancel();
        this.orderStatus = OrderStatus.CANCEL;
    }

    private void validateStatusToCancel() {
        if (OrderStatus.cancelImpossibleGroup().contains(this.orderStatus)) {
            throw new IllegalArgumentException("주문 취소 불가 상태!");
        }
    }
}
