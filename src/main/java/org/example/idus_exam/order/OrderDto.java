package org.example.idus_exam.order;

import lombok.Getter;
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



}
