package com.example.coconuts.repository;

import com.example.coconuts.entity.DataEntity;
import com.example.coconuts.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<ScoreEntity, Integer> {
    List<ScoreEntity> findByLanguage(String language);
}
