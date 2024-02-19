package com.example.huewith.service;

import com.example.huewith.DataNotFoundException;
import com.example.huewith.domain.Member;
import com.example.huewith.dto.MemberJoinDto;
import com.example.huewith.repository.MemberRepository;
import com.example.huewith.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    @Value("${jwt.secret}")
    private String secretKey;
    private final MemberRepository memberRepository;
    public void join(MemberJoinDto userJoinDto) {
        String userEmail = userJoinDto.getEmail();
        Optional<Member> optUser = memberRepository.findByEmail(userEmail);
        if (optUser.isPresent()) throw new DataNotFoundException("아이디가 겹칩니다.");
        else {
            Member member = Member.from(userJoinDto);
            memberRepository.save(member);
        }
    }

    private Long expiredMs=1000*60*60l;  //1시간
    public String login(String email, String password){
        Optional<Member> optUser=memberRepository.findByEmailAndPassword(email, password);
        if(optUser.isPresent()) {
            return JwtUtil.createJwt(email, secretKey, expiredMs);
        }
        else throw new DataNotFoundException("일치하는 회원 정보가 없습니다.");
    }

    public String checkEmail(String email){
        Optional<Member> optUser=memberRepository.findByEmail(email);
        if(optUser.isEmpty()) return "사용 가능한 이메일입니다.";
        else return "중복된 이메일입니다.";
    }
}
