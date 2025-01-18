package com.example.gdgoc_2025_whitesheepserver.service;

import com.example.gdgoc_2025_whitesheepserver.JPARepository.CorrectRepository;
import com.example.gdgoc_2025_whitesheepserver.JPARepository.UserInfoRepository;
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
    private CorrectRepository correctRepository;

    @Override
    public List<UserInfo> getAllUserInfo() {
        return userInfoRepository.findAll();
    }

    @Override
    public Integer getTotalScore(String id, LocalDate correctdate) {
        return correctRepository.findTotalScoreByIdAndDate(id, correctdate);
    }

    @Override
    public Integer getTodayScore(String id) {
        return correctRepository.findTodayScore(id);
    }

    @Override
    public Integer getWeeklyScore(String id) {
        return correctRepository.findWeeklyScore(id);
    }

    @Override
    public Integer getMonthlyScore(String id) {
        return correctRepository.findMonthlyScore(id);
    }

    @Override
    public List<Map<String, Object>> getTodayScores() {
        return correctRepository.findTodayScores();
    }

    @Override
    public List<Map<String, Object>> getWeeklyScores() {
        return correctRepository.findWeeklyScores();
    }

    @Override
    public List<Map<String, Object>> getMonthlyScores() {
        return correctRepository.findMonthlyScores();
    }

}
