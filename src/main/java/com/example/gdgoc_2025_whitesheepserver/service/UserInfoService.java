package com.example.gdgoc_2025_whitesheepserver.service;

import com.example.gdgoc_2025_whitesheepserver.dto.CorrectTypeCountDTO;
import com.example.gdgoc_2025_whitesheepserver.entity.UserInfo;

import java.time.LocalDate;
import java.util.List;

public interface UserInfoService {
    List<UserInfo> getAllUserInfo();
    Integer getTotalScore(String id, LocalDate correctdate);

    Integer getTodayScore(String id);

    Integer getWeeklyScore(String id);

    Integer getMonthlyScore(String id);

}
