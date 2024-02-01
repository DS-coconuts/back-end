package com.example.coconuts.service;

import com.example.coconuts.code.ErrorCode;
import com.example.coconuts.dto.friend.FriendListResponseDto;
import com.example.coconuts.entity.FriendEntity;
import com.example.coconuts.entity.UserEntity;
import com.example.coconuts.exception.UserIdNotFoundException;
import com.example.coconuts.repository.FriendRepository;
import com.example.coconuts.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FriendServiceImpl implements FriendService{

    private final FriendRepository friendRepository;

    private final UserRepository userRepository;

    @Override
    public List<FriendListResponseDto> getFriendList(Integer userId) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserIdNotFoundException(ErrorCode.USERID_NOT_FOUND);
        }
        List<FriendEntity> friendEntities = friendRepository.findByFromUser(user);
        return convertToDtoList(friendEntities);
    }

    private List<FriendListResponseDto> convertToDtoList(List<FriendEntity> friendEntities) {
        return friendEntities.stream()
                .map(FriendListResponseDto::new)
                .collect(Collectors.toList());
    }
}
