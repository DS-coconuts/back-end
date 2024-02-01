package com.example.coconuts.repository;

import com.example.coconuts.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByLoginId(String loginId);
}
