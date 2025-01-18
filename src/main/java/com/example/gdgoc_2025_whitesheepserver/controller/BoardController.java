package com.example.gdgoc_2025_whitesheepserver.controller;

import com.example.gdgoc_2025_whitesheepserver.dto.GetBoardMissionResponseDto;
import com.example.gdgoc_2025_whitesheepserver.dto.GetBoardsResponseDto;
import com.example.gdgoc_2025_whitesheepserver.dto.PostBoardRequestDto;
import com.example.gdgoc_2025_whitesheepserver.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/info")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards")
    @Operation(summary = "탐색 목록 조회하기")
    public ResponseEntity<GetBoardsResponseDto> getBoards(
            @Parameter(description = "키워드", example = "UI/UX디자인") @RequestParam String keyword
    ) {
        return ResponseEntity.ok(boardService.getBoards(keyword));
    }

    @GetMapping("/mission")
    @Operation(summary = "미션 조회하기")
    public ResponseEntity<GetBoardMissionResponseDto> getMission(
            @Parameter(description = "게시물 id", example = "1") @RequestParam Long boardId,
            @Parameter(description = "난이도 단계", example = "1") @RequestParam Integer level
    ) {
        return ResponseEntity.ok(boardService.getMission(boardId, level));
    }

    @PostMapping("/mission")
    @Operation(summary = "미션 제출하기")
    public ResponseEntity<Void> submitMission(
            @Parameter(description = "미션 id", example = "1") @RequestParam Long missionId,
            @Parameter(description = "정답", example = "2. 제품의 조작성") @RequestParam String answer,
            @Parameter(description = "유저 id", example = "1") @RequestParam String userId
    ) {
        boardService.submitMission(missionId, answer, userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("board")
    @Operation(summary = "컨텐츠 등록하기")
    public ResponseEntity<Void> postBoard(
            @RequestBody PostBoardRequestDto requestDto
    ) {
        boardService.postBoard(requestDto);
        return ResponseEntity.ok().build();
    }
}
