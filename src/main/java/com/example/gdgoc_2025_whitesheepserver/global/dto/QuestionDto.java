package com.example.gdgoc_2025_whitesheepserver.global.dto;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class QuestionDto {
    private String question;
    private List<String> options;
    private String correct_answer;
}
