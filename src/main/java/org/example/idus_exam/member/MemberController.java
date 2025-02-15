package org.example.idus_exam.member;

import org.example.idus_exam.member.model.MemberDto;
import lombok.RequiredArgsConstructor;
import org.example.idus_exam.order.OrderDto;
import org.example.idus_exam.order.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final OrderService orderService;

    @GetMapping("/verify")
    public void verify(String uuid) {
        memberService.verify(uuid);
    }

    @PostMapping("/signup")
    public void signup(@RequestBody MemberDto.SignupRequest dto) {
        memberService.signup(dto);
    }

    @GetMapping("/list")
    public ResponseEntity<MemberDto.MemberPageResponse> list(int page, int size) {
        MemberDto.MemberPageResponse response = memberService.list(page, size);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{memberIdx}")
    public ResponseEntity<MemberDto.MemberResponse> read(@PathVariable Long memberIdx) {
        MemberDto.MemberResponse response = memberService.read(memberIdx);

        return ResponseEntity.ok(response);
    }



    @GetMapping("/search/name")
    public ResponseEntity<List<MemberDto.MemberResponse>> searchByName(@RequestParam String name) {
        List<MemberDto.MemberResponse> response = memberService.searchByName(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/email")
    public ResponseEntity<List<MemberDto.MemberResponse>> searchByEmail(@RequestParam String email) {
        List<MemberDto.MemberResponse> response = memberService.searchByEmail(email);
        return ResponseEntity.ok(response);
    }


}
