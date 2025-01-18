package com.example.gdgoc_2025_whitesheepserver.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record PostBoardRequestDto(
        @Schema(description = "키워드", example = "UI/UX디자인")
        String keyword,
        @Schema(description = "게시물 주소", example = "https://ko.wix.com/blog/post/what-is-ux-design")
        String url,
        @Schema(description = "게시물 제목", example = "UX/UI 디자인이란?")
        String name
) {
}