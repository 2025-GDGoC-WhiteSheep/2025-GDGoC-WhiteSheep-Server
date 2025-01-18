package com.example.gdgoc_2025_whitesheepserver.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CorrectTypeCountDTO {
    private String id;
    private LocalDate correctdate;
    private int type1Count;
    private int type2Count;
    private int type3Count;
}
