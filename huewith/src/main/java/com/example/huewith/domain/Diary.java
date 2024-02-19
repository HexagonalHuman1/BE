package com.example.huewith.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="diary_id")
    private Long id;

    @Column
    private String title;

    @Column
    private Date date;

    @Column
    private String content;

    @Column
    private String image;
}
