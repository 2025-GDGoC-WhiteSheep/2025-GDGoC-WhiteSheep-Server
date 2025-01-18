package com.example.gdgoc_2025_whitesheepserver.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "correct")
@Getter
@NoArgsConstructor
public class Correct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "correct_type")
    private int correctType;

    @Column(name = "user_id")
    private String userId;

    @CreationTimestamp
    @Column(name = "create_at")
    private LocalDateTime createAt;

    //수정전 필드
    @CreationTimestamp
    @Column(name = "correctdate")
    private LocalDate correctdate;

    //수정전 필드
    private int correcttype;

    public Correct(int correctType, String userId) {
        this.correctType = correctType;
        this.userId = userId;
        this.correcttype = correctType;
    }
}
