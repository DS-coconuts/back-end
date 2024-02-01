package com.example.coconuts.service;

import com.example.coconuts.dto.friend.FriendListResponseDto;
import com.example.coconuts.entity.UserEntity;
import com.example.coconuts.repository.FriendRepository;
import com.example.coconuts.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FriendServiceImpl implements FriendService{

    private final FriendRepository friendRepository;

    private final UserRepository userRepository;

    @Override
    public List<FriendListResponseDto> getFriendList(Integer userId) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            friendRepository.findByFromUser(user);
        }
        return null;
    }
}
