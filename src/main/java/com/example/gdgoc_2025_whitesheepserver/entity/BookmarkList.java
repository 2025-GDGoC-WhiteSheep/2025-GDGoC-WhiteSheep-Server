package com.example.gdgoc_2025_whitesheepserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "bookmarklist")
@Getter
@NoArgsConstructor
public class BookmarkList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookmarkedurl;

    @Column(name = "bookmarkeddate")
    private LocalDate bookmarkeddate;
}
