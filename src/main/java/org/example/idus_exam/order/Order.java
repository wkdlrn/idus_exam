package org.example.idus_exam.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.idus_exam.member.model.Member;

import java.time.LocalDateTime;
@Table(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String productName;
    private LocalDateTime paymentDate;

    @ManyToOne
    @JoinColumn(name = "member_idx")
    private Member member;
}
