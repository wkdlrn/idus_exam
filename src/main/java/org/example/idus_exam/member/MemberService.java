package org.example.idus_exam.member;

import org.example.idus_exam.emailverify.EmailVerifyService;
import org.example.idus_exam.member.model.Member;
import org.example.idus_exam.member.model.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailVerifyService emailVerifyService;

    @Transactional
    public void signup(MemberDto.SignupRequest dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        Member member = memberRepository.save(dto.toEntity(encodedPassword, "USER"));

//        emailVerifyService.signup(member.getIdx(), member.getEmail());

    }
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> result = memberRepository.findByEmail(username);

        if (result.isPresent()) {
            Member member = result.get();
            return member;
        }

        return null;
    }

    @Transactional
    public void verify(String uuid) {
        Member member = emailVerifyService.verify(uuid);
        if(member != null) {
            member.verify();
            memberRepository.save(member);
        }
    }

    @Transactional(readOnly = true)
    public MemberDto.MemberPageResponse list(int page, int size) {
        Page<Member> result = memberRepository.findAll(PageRequest.of(page, size));
        return MemberDto.MemberPageResponse.from(result);
    }

    @Transactional(readOnly = true)
    public MemberDto.MemberResponse read(Long memberIdx) {
        Member member = memberRepository.findById(memberIdx).orElseThrow();
        return MemberDto.MemberResponse.from(member);
    }

    public List<MemberDto.MemberResponse> searchByName(String name) {
        List<Member> members = memberRepository.findByNameContaining(name);
        return members.stream().map(MemberDto.MemberResponse::from).collect(Collectors.toList());
    }

}
