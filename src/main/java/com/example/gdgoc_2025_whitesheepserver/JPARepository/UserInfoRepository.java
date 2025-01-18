package com.example.gdgoc_2025_whitesheepserver.JPARepository;

import com.example.gdgoc_2025_whitesheepserver.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
}
