package com.example.huewith.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberJoinDto {

    private String email;
    private String password;
    private String introduce;
    private String name;

}
