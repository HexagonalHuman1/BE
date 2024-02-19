package com.example.huewith.repository;

import com.example.huewith.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String userEmail);

    Optional<Member> findByEmailAndPassword(String email, String password);
}
