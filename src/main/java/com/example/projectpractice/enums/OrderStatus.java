package com.example.projectpractice.enums;

import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    ORDER_DONE("주문 완료"),
    READY("상품 준비"),
    SHIPPING("배송중"),
    SHIPPING_DONE("배송 완료"),
    CANCEL("취소");

    private String value;

    public static List<OrderStatus> cancelImpossibleGroup() {
        return Arrays.asList(SHIPPING_DONE, CANCEL);
    }

}
