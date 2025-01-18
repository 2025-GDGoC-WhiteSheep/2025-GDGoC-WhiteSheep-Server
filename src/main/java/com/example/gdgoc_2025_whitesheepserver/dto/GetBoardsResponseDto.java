package com.example.gdgoc_2025_whitesheepserver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record GetBoardsResponseDto(
        List<GetBoardsBoardResponseDto> boards
) {
    public record GetBoardsBoardResponseDto(
            @Schema(description = "게시물 id", example = "1")
            Long boardId,
            @Schema(description = "게시물 주소", example = "https://ko.wix.com/blog/post/what-is-ux-design")
            String url,
            @Schema(description = "게시물 제목", example = "UX/UI 디자인이란?")
            String name
    ){
    }
}
