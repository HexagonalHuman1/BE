package com.example.huewith.domain;

import com.example.huewith.dto.MemberJoinDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String introduce;

    @Column
    private String name;

    public static Member from(MemberJoinDto userJoinDto) {
        return Member.builder()
                .email(userJoinDto.getEmail())
                .password(userJoinDto.getPassword())
                .introduce(userJoinDto.getIntroduce())
                .name(userJoinDto.getName())
                .build();
    }
}
