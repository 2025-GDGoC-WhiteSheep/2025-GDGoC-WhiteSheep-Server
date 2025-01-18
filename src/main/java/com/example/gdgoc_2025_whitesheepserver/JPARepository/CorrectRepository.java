package com.example.gdgoc_2025_whitesheepserver.JPARepository;

import com.example.gdgoc_2025_whitesheepserver.entity.Correct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface CorrectRepository extends JpaRepository<Correct, String> {

    @Query(value = """
        SELECT 
            SUM(CASE WHEN correcttype = 1 THEN 1 ELSE 0 END) * 1 +
            SUM(CASE WHEN correcttype = 2 THEN 1 ELSE 0 END) * 2 +
            SUM(CASE WHEN correcttype = 3 THEN 1 ELSE 0 END) * 3 AS totalScore
        FROM 
            correct
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
            correct
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
            correct
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
            correct
        WHERE 
            id = :id AND MONTH(correctdate) = MONTH(CURRENT_DATE) AND YEAR(correctdate) = YEAR(CURRENT_DATE)
    """, nativeQuery = true)
    Integer findMonthlyScore(@Param("id") String id);

    // 오늘의 점수
    @Query(value = "SELECT id, " +
            "SUM(CASE WHEN correcttype = 1 THEN 1 ELSE 0 END) + " +
            "SUM(CASE WHEN correcttype = 2 THEN 2 ELSE 0 END) + " +
            "SUM(CASE WHEN correcttype = 3 THEN 3 ELSE 0 END) AS score " +
            "FROM correct " +
            "WHERE DATE(correctdate) = CURDATE() " +
            "GROUP BY id " +
            "ORDER BY score DESC", nativeQuery = true)
    List<Map<String, Object>> findTodayScores();

    // 이번 주의 점수
    @Query(value = "SELECT id, " +
            "SUM(CASE WHEN correcttype = 1 THEN 1 ELSE 0 END) + " +
            "SUM(CASE WHEN correcttype = 2 THEN 2 ELSE 0 END) + " +
            "SUM(CASE WHEN correcttype = 3 THEN 3 ELSE 0 END) AS score " +
            "FROM correct " +
            "WHERE YEARWEEK(correctdate, 1) = YEARWEEK(CURDATE(), 1) " +
            "GROUP BY id " +
            "ORDER BY score DESC", nativeQuery = true)
    List<Map<String, Object>> findWeeklyScores();

    // 이번 달의 점수
    @Query(value = "SELECT id, " +
            "SUM(CASE WHEN correcttype = 1 THEN 1 ELSE 0 END) + " +
            "SUM(CASE WHEN correcttype = 2 THEN 2 ELSE 0 END) + " +
            "SUM(CASE WHEN correcttype = 3 THEN 3 ELSE 0 END) AS score " +
            "FROM correct " +
            "WHERE YEAR(correctdate) = YEAR(CURDATE()) AND MONTH(correctdate) = MONTH(CURDATE()) " +
            "GROUP BY id " +
            "ORDER BY score DESC", nativeQuery = true)
    List<Map<String, Object>> findMonthlyScores();
}
