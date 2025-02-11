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

    @GetMapping("/member/{memberIdx}")
    public ResponseEntity<List<OrderDto.OrderResponse>> getOrdersByMemberIdx(@PathVariable Long memberIdx) {
        List<OrderDto.OrderResponse> orders = orderService.findOrdersByMemberIdx(memberIdx);
        return ResponseEntity.ok(orders);
    }
}



