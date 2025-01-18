package com.example.gdgoc_2025_whitesheepserver.JPARepository;

import com.example.gdgoc_2025_whitesheepserver.entity.BookmarkList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkListRepository extends JpaRepository<BookmarkList, String> {
}
