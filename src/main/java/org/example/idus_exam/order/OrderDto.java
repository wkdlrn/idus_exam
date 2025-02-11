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
    public static class OrderRegister {
        private Long orderIdx;
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

        // 엔티티 리스트를 DTO 리스트로 변환하고 응답 객체 생성
        public static MemberDto.MemberOrderListResponse toResponse(List<Order> orders, String memberName) {
            List<OrderResponse> orderResponses = orders.stream()
                    .map(OrderResponse::from)
                    .collect(Collectors.toList());

            return MemberDto.MemberOrderListResponse.builder()
                    .name(memberName)
                    .orders(orderResponses)
                    .build();
        }
    }
}

