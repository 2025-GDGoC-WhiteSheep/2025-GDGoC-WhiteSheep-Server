package com.example.gdgoc_2025_whitesheepserver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record GetBoardMissionResponseDto(
        @Schema(description = "미션 id", example = "1")
        Long missionId,
        @Schema(description = "미션 이름", example = "UI/UX 디자이너")
        String name,
        @Schema(description = "미션 질문", example = "UI/UX디자이너가 가장 많이 사용하는 협업툴은?")
        String question,
        @Schema(description = "미션 선택지", example = "[\n"
                + "        \"1. 제품의 미적 외관 향상\",\n"
                + "        \"2. 제품의 기획 및 제작 과정 단순화\",\n"
                + "        \"3. 사용자가 제품을 쉽게 이해하고 사용할 수 있도록 만드는 것\",\n"
                + "        \"4. 제품의 가격을 낮추는 것\"\n"
                + "    ]")
        List<String> options,
        @Schema(description = "미션 정답", example = "2. 제품의 기획 및 제작 과정 단순화")
        String answer
) {
}
