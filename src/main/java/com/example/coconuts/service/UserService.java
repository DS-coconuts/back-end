package com.example.coconuts.service;

import com.example.coconuts.dto.user.UserLoginDTO;
import com.example.coconuts.dto.user.UserRegisterDTO;
import com.example.coconuts.dto.user.UserUpdateDTO;
import com.example.coconuts.dto.user.UserListResponseDto;
import com.example.coconuts.entity.DataEntity;
import com.example.coconuts.entity.ScoreEntity;
import com.example.coconuts.entity.UserEntity;
import com.example.coconuts.projection.user.GetUser;

import java.util.List;
import java.util.Optional;

public interface UserService {
    GetUser register(UserRegisterDTO userRegisterDTO);

    GetUser login(UserLoginDTO userLoginDTO);

    void logout(String loginId);

    UserEntity updateProfile(Integer userId, UserUpdateDTO userUpdateDTO);

    Optional<UserEntity> getProfile(Integer userId);

    List<UserEntity> getUserList();
  
    List<UserListResponseDto> searchUsers(Integer userId, String query);

    List<ScoreEntity> getUserScoreList(Integer userId);
}
