package org.example.idus_exam.order;

import lombok.RequiredArgsConstructor;
import org.example.idus_exam.member.MemberService;
import org.example.idus_exam.member.model.MemberDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{memberIdx}")
    public ResponseEntity<MemberDto.MemberOrderListResponse> getOrdersByMember(@PathVariable Long memberIdx) {
        List<Order> orders = orderService.getOrdersByMember(memberIdx);
        String memberName = orderService.getMemberName(memberIdx);

        // DTO 메서드를 호출하여 변환 및 응답 생성
        return ResponseEntity.ok(OrderDto.OrderResponse.toResponse(orders, memberName));
    }
}


