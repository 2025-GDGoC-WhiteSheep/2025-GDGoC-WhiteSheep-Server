package com.example.gdgoc_2025_whitesheepserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "lookedlist")
@Getter
@NoArgsConstructor
public class LookedList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String lookedurl;

    @Column(name = "lookeddate")
    private LocalDate lookeddate;
}
