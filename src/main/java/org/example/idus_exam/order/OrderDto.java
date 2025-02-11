package org.example.idus_exam.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.idus_exam.member.model.Member;

import java.time.LocalDateTime;
import org.example.idus_exam.member.model.MemberDto;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class OrderResponse {
        private Long idx;
        private String productName;
        private LocalDateTime paymentDate;

        public static OrderResponse from(Order order) {
            return OrderResponse.builder()
                    .idx(order.getIdx())
                    .productName(order.getProductName())
                    .paymentDate(order.getPaymentDate())
                    .build();
        }
    }

    public static List<OrderResponse> OrderListResponse(List<Order> orders) {
        return orders.stream().map(OrderResponse::from).collect(Collectors.toList());
    }
}

