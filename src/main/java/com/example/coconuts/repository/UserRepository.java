package com.example.coconuts.repository;

import com.example.coconuts.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findById(Integer userId);
    Optional<UserEntity> findByLoginId(String loginId);
}

