package org.example.idus_exam.member.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.idus_exam.order.OrderDto;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberDto {
    @Getter
    public static class SignupRequest {
        private String name;
        private String nickName;
        private String password;
        private String phoneNum;
        private String email;
        private String gender;




        public Member toEntity(String encodedPassword, String type) {
                return Member.builder()
                        .name(name)
                        .nickName(nickName)
                        .password(encodedPassword)
                        .phoneNum(phoneNum)
                        .email(email)
                        .gender(gender)
                        .role("USER")
                        .build();

        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MemberPageResponse {
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;
        private boolean hasNext;
        private boolean hasPrevious;

        private List<MemberResponse> members;

        public static MemberPageResponse from(Page<Member> memberPage) {
            return MemberPageResponse.builder()
                    .page(memberPage.getNumber())
                    .size(memberPage.getSize())
                    .totalElements(memberPage.getTotalElements())
                    .totalPages(memberPage.getTotalPages())
                    .hasNext(memberPage.hasNext())
                    .hasPrevious(memberPage.hasPrevious())
                    .members(memberPage.stream().map(MemberDto.MemberResponse::from).collect(Collectors.toList()))
                    .build();
        }

    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MemberResponse {
        private String name;
        private String nickName;
        private String password;
        private String phoneNum;
        private String email;
        private String gender;


        public static MemberResponse from(Member member) {
            return MemberResponse.builder()
                    .name(member.getName())
                    .nickName(member.getNickName())
                    .password(member.getPassword())
                    .phoneNum(member.getPhoneNum())
                    .email(member.getEmail())
                    .gender(member.getGender())
                    .build();
        }
    }


}
