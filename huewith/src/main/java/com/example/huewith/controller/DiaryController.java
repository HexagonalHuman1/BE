package com.example.huewith.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/diary")
@RequiredArgsConstructor
@Tag(name="diary", description = "일기 관련 api")
public class DiaryController {
}
