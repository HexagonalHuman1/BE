package com.example.huewith.controller;

import com.example.huewith.dto.MemberJoinDto;
import com.example.huewith.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
@Tag(name="member", description = "회원 관련 api")
public class MemberController {
    private final MemberService memberService;


    @Operation(summary="회원가입 api")
    @ApiResponse(responseCode = "200", description = "성공")
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody MemberJoinDto memberJoinDto){
        memberService.join(memberJoinDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary="로그인 api")
    @ApiResponse(responseCode = "200", description = "성공")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberJoinDto userDto){
        //토큰만 발급됨(로그인 성공시)
        String email= userDto.getEmail();
        String password=userDto.getPassword();
        return ResponseEntity.ok().body(memberService.login(email, password));
    }


    @Operation(summary="아이디 중복확인 api")
    @ApiResponse(responseCode = "200", description = "성공")
    @PostMapping("/email")
    public ResponseEntity<String> checkId(@RequestBody String email){
        //토큰만 발급됨(로그인 성공시)
        return ResponseEntity.ok().body(memberService.checkEmail(email));
    }
}
