package com.example.coconuts.repository;

import com.example.coconuts.entity.FriendEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<FriendEntity, Integer> {
}
