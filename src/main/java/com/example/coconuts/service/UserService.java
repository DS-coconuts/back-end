package com.example.coconuts.service;

import com.example.coconuts.dto.user.UserListResponseDto;

import java.util.List;

public interface UserService {
    List<UserListResponseDto> searchUsers(Integer userId, String query);
}
