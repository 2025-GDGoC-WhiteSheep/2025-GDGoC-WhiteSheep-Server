package com.example.gdgoc_2025_whitesheepserver.service;

import com.example.gdgoc_2025_whitesheepserver.JPARepository.CorrectListRepository;
import com.example.gdgoc_2025_whitesheepserver.JPARepository.UserInfoRepository;
import com.example.gdgoc_2025_whitesheepserver.dto.CorrectTypeCountDTO;
import com.example.gdgoc_2025_whitesheepserver.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private CorrectListRepository correctListRepository;

    @Override
    public List<UserInfo> getAllUserInfo() {
        return userInfoRepository.findAll();
    }

    @Override
    public Integer getTotalScore(String id, LocalDate correctdate) {
        return correctListRepository.findTotalScoreByIdAndDate(id, correctdate);
    }

    @Override
    public Integer getTodayScore(String id) {
        return correctListRepository.findTodayScore(id);
    }

    @Override
    public Integer getWeeklyScore(String id) {
        return correctListRepository.findWeeklyScore(id);
    }

    @Override
    public Integer getMonthlyScore(String id) {
        return correctListRepository.findMonthlyScore(id);
    }

    @Override
    public List<Map<String, Object>> getTodayScores() {
        return correctListRepository.findTodayScores();
    }

    @Override
    public List<Map<String, Object>> getWeeklyScores() {
        return correctListRepository.findWeeklyScores();
    }

    @Override
    public List<Map<String, Object>> getMonthlyScores() {
        return correctListRepository.findMonthlyScores();
    }

}
