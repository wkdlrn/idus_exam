package org.example.idus_exam.order;

import lombok.RequiredArgsConstructor;
import org.example.idus_exam.member.MemberRepository;
import org.example.idus_exam.member.model.Member;
import org.example.idus_exam.member.model.MemberDto;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    public List<Order> getOrdersByMember(Long memberIdx) {
        Member member = memberRepository.findById(memberIdx)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        return orderRepository.findByMember(member);
    }

    public String getMemberName(Long memberIdx) {
        return memberRepository.findById(memberIdx)
                .orElseThrow()
                .getName();
    }
}

