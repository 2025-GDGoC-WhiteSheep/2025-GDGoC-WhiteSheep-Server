package com.example.gdgoc_2025_whitesheepserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "correctlist")
@Getter
@NoArgsConstructor
public class CorrectList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "correctdate")
    private LocalDate correctdate;

    private int correcttype;

}
