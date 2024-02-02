package com.example.coconuts.repository;

import com.example.coconuts.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findById(Integer userId);
    Optional<UserEntity> findByLoginId(String loginId);

    // 특정 검색을 위한 쿼리문
    @Query("SELECT u FROM UserEntity u WHERE u.loginId LIKE %:query% AND u.id != :userId AND u.id NOT IN (SELECT f.toUser.id FROM FriendEntity f WHERE f.fromUser.id = :userId)")
    List<UserEntity> findNonFriendUsersBySearchCriteria(@Param("userId") Integer userId, @Param("query") String query);

}
