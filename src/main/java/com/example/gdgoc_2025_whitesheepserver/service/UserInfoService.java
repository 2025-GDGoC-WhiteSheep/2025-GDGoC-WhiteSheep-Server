package com.example.gdgoc_2025_whitesheepserver.service;

import com.example.gdgoc_2025_whitesheepserver.dto.CorrectTypeCountDTO;
import com.example.gdgoc_2025_whitesheepserver.entity.UserInfo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface UserInfoService {
    List<UserInfo> getAllUserInfo();
    Integer getTotalScore(String id, LocalDate correctdate);

    Integer getTodayScore(String id);

    Integer getWeeklyScore(String id);

    Integer getMonthlyScore(String id);

    List<Map<String, Object>> getTodayScores();

    List<Map<String, Object>> getWeeklyScores();

    List<Map<String, Object>> getMonthlyScores();
}
