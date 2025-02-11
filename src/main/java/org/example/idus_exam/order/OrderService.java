package org.example.idus_exam.order;

import lombok.RequiredArgsConstructor;
import org.example.idus_exam.member.MemberRepository;
import org.example.idus_exam.member.model.Member;
import org.example.idus_exam.member.model.MemberDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<OrderDto.OrderResponse> findOrdersByMemberIdx(Long memberIdx) {
        Member member = memberRepository.findById(memberIdx).orElseThrow();
        List<Order> orders = orderRepository.findByMember(member);
        return OrderDto.OrderListResponse(orders);
    }
}


