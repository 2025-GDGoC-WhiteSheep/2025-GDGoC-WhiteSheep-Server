package com.example.gdgoc_2025_whitesheepserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "board")
@Getter
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "name")
    private String name;

    public Board(String url, String keyword, String name) {
        this.url = url;
        this.keyword = keyword;
        this.name = name;
    }
}