package com.example.gdgoc_2025_whitesheepserver.controller;

import com.example.gdgoc_2025_whitesheepserver.global.dto.QuestionDto;
import com.example.gdgoc_2025_whitesheepserver.global.enums.LevelType;
import com.example.gdgoc_2025_whitesheepserver.global.service.ChatgptService;
import com.example.gdgoc_2025_whitesheepserver.global.service.YoutubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class DataController {

    private final YoutubeService youtubeService;
    private final ChatgptService chatgptService;

    //글로 문제 만들기
    @GetMapping("/url")
    public ResponseEntity<QuestionDto> getQuestionByUrl(@RequestParam String str, @RequestParam LevelType level) {
        QuestionDto question = chatgptService.getQuestionByUrl(str, level);
        return ResponseEntity.ok(question);
    }

    //videoId로 유튜브 자막 추출해서 문제 만들기
    @GetMapping("/video")
    public ResponseEntity<QuestionDto> getQuestionByVideoId(@RequestParam String str, @RequestParam LevelType level) {
        String subtitle = youtubeService.getVideoSubtitle(str);
        QuestionDto question = chatgptService.getQuestionByText(subtitle, level);
        return ResponseEntity.ok(question);
    }

    @GetMapping("/urls")
    public ResponseEntity<QuestionDto> getQuestionsByUrl(@RequestParam String str) {
        QuestionDto easyQuestion = chatgptService.getQuestionByUrl(str, LevelType.easy);
        QuestionDto mediumQuestion = chatgptService.getQuestionByUrl(str, LevelType.medium);
        QuestionDto hardQuestion = chatgptService.getQuestionByUrl(str, LevelType.hard);
        return ResponseEntity.ok(easyQuestion);
    }

    @GetMapping("/videos")
    public ResponseEntity<QuestionDto> getQuestionsByVideoId(@RequestParam String str) {
        String subtitle = youtubeService.getVideoSubtitle(str);
        QuestionDto easyQuestion = chatgptService.getQuestionByText(subtitle, LevelType.easy);
        QuestionDto mediumQuestion = chatgptService.getQuestionByText(subtitle, LevelType.medium);
        QuestionDto hardQuestion = chatgptService.getQuestionByText(subtitle, LevelType.hard);
        return ResponseEntity.ok(easyQuestion);
    }

    // 검색어로 유튜브 영상 id 가져오기
//    @GetMapping("/youtube")
//    public ResponseEntity<String> youtube(@RequestParam String str) {
//        List<String> videoIds = youtubeService.getVideoIds(str);
//        for (String a : videoIds) {
//            System.out.println(a);
//        }
//        youtubeService.getVideoSubtitle("R4j_hDQuBOc");
//
//        return ResponseEntity.ok("");
//    }

}
