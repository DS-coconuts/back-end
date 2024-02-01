package com.example.coconuts.service;

import com.example.coconuts.dto.friend.FriendListResponseDto;

import java.util.List;

public interface FriendService {

    List<FriendListResponseDto> getFriendList(Integer userId);
}
