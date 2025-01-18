package com.example.gdgoc_2025_whitesheepserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "crawlinglist")
@Getter
@NoArgsConstructor
public class CrawlingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String crawlingurl;

    @Column(name = "crawlingdate")
    private LocalDate crawlingdate;

}
