package com.example.coconuts.repository;

import com.example.coconuts.dto.friend.FriendListResponseDto;
import com.example.coconuts.entity.FriendEntity;
import com.example.coconuts.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendRepository extends JpaRepository<FriendEntity, Integer> {
    List<FriendEntity> findByFromUser(UserEntity fromUser);
    boolean existsByFromUserAndToUser(UserEntity toUser, UserEntity fromUser);
}
