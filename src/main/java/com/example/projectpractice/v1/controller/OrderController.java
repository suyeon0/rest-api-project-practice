package com.example.projectpractice.v1.controller;

import com.example.projectpractice.v1.entity.Order;
import com.example.projectpractice.enums.OrderStatus;
import com.example.projectpractice.v1.response.DefaultResponse;
import com.example.projectpractice.v1.request.OrderRequest;
import com.example.projectpractice.v1.response.OrderResponse;
import com.example.projectpractice.v1.service.OrderService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/order")
public class OrderController {

    private final OrderService orderService;

    /**
     * 주문 등록
     *
     * @param orderRequest
     */
    @PostMapping
    public DefaultResponse postOrder(@Valid @RequestBody OrderRequest orderRequest) {
        this.orderService.order(orderRequest);

        return DefaultResponse.builder()
            .statusCode(HttpStatus.CREATED.toString())
            .build();
    }

    /**
     * 주문 단건 조회
     *
     * @param orderNo 주문번호
     */
    @GetMapping("/{orderNo}")
    public DefaultResponse getOrder(@PathVariable String orderNo) {
        Order order = this.orderService.getOrder(orderNo);
        OrderResponse res = OrderResponse.from(order);

        return DefaultResponse.builder()
            .statusCode(HttpStatus.OK.toString())
            .data(res)
            .build();
    }

    /**
     * 주문 리스트 조회
     *
     * @param orderStatus 주문상태
     */
    @GetMapping("/list")
    public DefaultResponse getOrderAll(@RequestParam(required = false, name = "orderStatus") OrderStatus orderStatus) {
        List<Order> orderList = this.orderService.getOrderAll(orderStatus);
        OrderResponse res = OrderResponse.from(orderList);

        return DefaultResponse.builder()
            .statusCode(HttpStatus.OK.toString())
            .data(res)
            .build();
    }

    /**
     * 주문 취소
     *
     * @param orderNo 주문번호
     */
    @PatchMapping("/{orderNo}")
    public DefaultResponse cancelOrder(@PathVariable String orderNo) {
        this.orderService.cancelOrder(orderNo);

        return DefaultResponse.builder()
            .statusCode(HttpStatus.OK.toString())
            .build();
    }
}
