package com.example.huewith.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column
    String email;

    @Column
    String password;

    @Column
    String introduce;

    @Column
    String name;

    @Column
    String image;
}
