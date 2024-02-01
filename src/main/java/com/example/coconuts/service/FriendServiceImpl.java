package com.example.coconuts.service;

import com.example.coconuts.dto.friend.FriendListResponseDto;
import com.example.coconuts.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FriendServiceImpl implements FriendService{

    private final FriendRepository friendRepository;

    @Override
    public List<FriendListResponseDto> getFriendList(Integer userId) {
        friendRepository.findByFromUserId(userId);
        return null;
    }
}
