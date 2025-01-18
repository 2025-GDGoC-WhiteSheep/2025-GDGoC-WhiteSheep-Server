package com.example.gdgoc_2025_whitesheepserver.JPARepository;

import com.example.gdgoc_2025_whitesheepserver.dto.CorrectTypeCountDTO;
import com.example.gdgoc_2025_whitesheepserver.entity.CorrectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CorrectListRepository extends JpaRepository<CorrectList, String> {

    @Query(value = """
        SELECT 
            SUM(CASE WHEN correcttype = 1 THEN 1 ELSE 0 END) * 1 +
            SUM(CASE WHEN correcttype = 2 THEN 1 ELSE 0 END) * 2 +
            SUM(CASE WHEN correcttype = 3 THEN 1 ELSE 0 END) * 3 AS totalScore
        FROM 
            correctlist
        WHERE 
            id = :id AND correctdate = :correctdate
    """, nativeQuery = true)
    Integer findTotalScoreByIdAndDate(@Param("id") String id, @Param("correctdate") LocalDate correctdate);

    @Query(value = """
        SELECT 
            SUM(CASE WHEN correcttype = 1 THEN 1 ELSE 0 END) * 1 +
            SUM(CASE WHEN correcttype = 2 THEN 1 ELSE 0 END) * 2 +
            SUM(CASE WHEN correcttype = 3 THEN 1 ELSE 0 END) * 3 AS totalScore
        FROM 
            correctlist
        WHERE 
            id = :id AND correctdate = CURRENT_DATE
    """, nativeQuery = true)
    Integer findTodayScore(@Param("id") String id);

    @Query(value = """
        SELECT 
            SUM(CASE WHEN correcttype = 1 THEN 1 ELSE 0 END) * 1 +
            SUM(CASE WHEN correcttype = 2 THEN 1 ELSE 0 END) * 2 +
            SUM(CASE WHEN correcttype = 3 THEN 1 ELSE 0 END) * 3 AS totalScore
        FROM 
            correctlist
        WHERE 
            id = :id AND YEARWEEK(correctdate, 1) = YEARWEEK(CURRENT_DATE, 1)
    """, nativeQuery = true)
    Integer findWeeklyScore(@Param("id") String id);

    @Query(value = """
        SELECT 
            SUM(CASE WHEN correcttype = 1 THEN 1 ELSE 0 END) * 1 +
            SUM(CASE WHEN correcttype = 2 THEN 1 ELSE 0 END) * 2 +
            SUM(CASE WHEN correcttype = 3 THEN 1 ELSE 0 END) * 3 AS totalScore
        FROM 
            correctlist
        WHERE 
            id = :id AND MONTH(correctdate) = MONTH(CURRENT_DATE) AND YEAR(correctdate) = YEAR(CURRENT_DATE)
    """, nativeQuery = true)
    Integer findMonthlyScore(@Param("id") String id);
}
