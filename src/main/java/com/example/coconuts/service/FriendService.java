package com.example.coconuts.service;

import com.example.coconuts.dto.friend.FriendListResponseDto;
import com.example.coconuts.entity.FriendEntity;

import java.util.List;

public interface FriendService {

    List<FriendListResponseDto> getFriendList(Integer userId);
}
