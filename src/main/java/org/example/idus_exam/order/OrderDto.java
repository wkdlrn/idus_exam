package org.example.idus_exam.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.idus_exam.member.model.Member;

import java.time.LocalDateTime;

public class OrderDto {

    @Getter
    public static class OrderRegister {
        private Long orderidx;
        private String productName;
        private LocalDateTime paymentDate;

        public Order toEntity(Member member) {
            return Order.builder()
                    .productName(productName)
                    .paymentDate(paymentDate)
                    .member(member)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class OrderResponse {
        private Long orderidx;
        private String productName;
        private LocalDateTime paymentDate;

        public static OrderResponse from(Order order) {
            return OrderResponse.builder()
                    .orderidx(order.getIdx())
                    .productName(order.getProductName())
                    .paymentDate(order.getPaymentDate())
                    .build();
        }
    }



}
