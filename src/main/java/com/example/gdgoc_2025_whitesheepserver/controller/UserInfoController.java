package com.example.gdgoc_2025_whitesheepserver.controller;

import com.example.gdgoc_2025_whitesheepserver.entity.UserInfo;
import com.example.gdgoc_2025_whitesheepserver.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/info")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping
    public List<UserInfo> getAllPost() {
        return userInfoService.getAllUserInfo();
    }

    @GetMapping("/score")
    public int getUserScore(@RequestParam String id, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate correctdate) {
        return userInfoService.getTotalScore(id, correctdate);
    }

    @GetMapping("/score/today")
    public Integer getTodayScore(@RequestParam String id) {
        return userInfoService.getTodayScore(id);
    }

    @GetMapping("/score/week")
    public Integer getWeeklyScore(@RequestParam String id) {
        return userInfoService.getWeeklyScore(id);
    }

    @GetMapping("/score/month")
    public Integer getMonthlyScore(@RequestParam String id) {
        return userInfoService.getMonthlyScore(id);
    }

    @GetMapping("/scores/daily")
    public ResponseEntity<List<Map<String, Object>>> getTodayScores() {
        return ResponseEntity.ok(userInfoService.getTodayScores());
    }

    @GetMapping("/scores/weekly")
    public ResponseEntity<List<Map<String, Object>>> getWeeklyScores() {
        return ResponseEntity.ok(userInfoService.getWeeklyScores());
    }

    @GetMapping("/scores/monthly")
    public ResponseEntity<List<Map<String, Object>>> getMonthlyScores() {
        return ResponseEntity.ok(userInfoService.getMonthlyScores());
    }
}
