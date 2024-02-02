package com.example.coconuts.repository;

import com.example.coconuts.entity.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataRepository  extends JpaRepository<DataEntity, Integer> {

    List<DataEntity> findByLanguage(String language);
}
