package com.example.coconuts.repository;

import com.example.coconuts.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScoreRepository extends JpaRepository<ScoreEntity, Integer> {
    List<ScoreEntity> findByLanguageOrderByCpmDesc(String language);
    List<ScoreEntity> findByUserId(Integer userId);
}
