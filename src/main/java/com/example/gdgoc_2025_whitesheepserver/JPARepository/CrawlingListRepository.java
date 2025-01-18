package com.example.gdgoc_2025_whitesheepserver.JPARepository;

import com.example.gdgoc_2025_whitesheepserver.entity.CrawlingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawlingListRepository extends JpaRepository<CrawlingList, String> {
}
