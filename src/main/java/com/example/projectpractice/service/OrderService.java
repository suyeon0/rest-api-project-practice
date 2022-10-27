package com.example.projectpractice.service;

import com.example.projectpractice.shared.domain.order.entity.Order;
import com.example.projectpractice.shared.domain.order.entity.OrderLine;
import com.example.projectpractice.order.enums.OrderStatus;
import com.example.projectpractice.shared.domain.order.repository.OrderRepository;
import com.example.projectpractice.order.request.OrderRequest;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    /**
     * 주문 생성
     *
     * @param orderRequest
     */
    @Transactional
    public void order(OrderRequest orderRequest) {
        List<OrderLine> orderLines = orderRequest.createOrderLineEntity();

        Order order = Order.from(orderLines);
        this.orderRepository.save(order);
    }

    /**
     * 주문 단건 조회
     *
     * @param orderNo
     * @return Order
     */
    public Order getOrder(String orderNo) {
        Optional<Order> order = this.orderRepository.findById(orderNo);
        if (!order.isPresent()) {
            throw new NoSuchElementException("order not found");
        }

        return order.get();
    }

    /**
     * 주문 전체 조회
     *
     * @return List<Order>
     */
    public List<Order> getOrderAll(OrderStatus statusParam) {
        List<Order> orderList = this.orderRepository.findAll();
        if (statusParam != null) {
            orderList = orderList.stream()
                .filter(order -> order.getOrderStatus().equals(statusParam))
                .collect(Collectors.toList());
        }

        return orderList;
    }

    /**
     * 주문 취소
     *
     * @param orderNo
     */
    public void cancelOrder(String orderNo) {
        Order order = this.getOrder(orderNo);
        order.cancel();

        this.orderRepository.save(order);
    }
}
