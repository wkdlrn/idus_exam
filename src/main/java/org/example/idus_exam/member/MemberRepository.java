package org.example.idus_exam.member;

import org.example.idus_exam.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String username);

    List<Member> findByNameContaining(String name);
    List<Member> findByEmailContaining(String email);
}
