package com.example.coconuts.service;

import com.example.coconuts.code.ErrorCode;
import com.example.coconuts.dto.friend.AddFriendResponseDto;
import com.example.coconuts.dto.friend.FriendListResponseDto;
import com.example.coconuts.entity.FriendEntity;
import com.example.coconuts.entity.UserEntity;
import com.example.coconuts.exception.FriendshipAlreadyExistsException;
import com.example.coconuts.exception.UserIdNotFoundException;
import com.example.coconuts.repository.FriendRepository;
import com.example.coconuts.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Override
    public AddFriendResponseDto addFriend(Integer userId, String friendLoginId) {// Find the user who wants to add a friend
        // 팔로우 요청을 보낸 사용자 찾기
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserIdNotFoundException(ErrorCode.USERID_NOT_FOUND));

        // 팔로우 요청을 받은 사용자 찾기
        Optional<UserEntity> friendUser = userRepository.findByLoginId(friendLoginId);
        if (friendUser == null) {
            throw new UserIdNotFoundException(ErrorCode.USERID_NOT_FOUND);
        }

        // 요청 받은 사용자가 친구인지 확인
        if(friendRepository.existsByFromUserAndToUser(user, friendUser.get())) {
            throw new FriendshipAlreadyExistsException(ErrorCode.FRIENDSHIP_ALREADY_EXISTS);
        }

        // 새로운 FriendEntity 생성 및 DB 추가
        FriendEntity newFriendShip = new FriendEntity();
        newFriendShip.setFromUser(user);
        newFriendShip.setToUser(friendUser.get());
        friendRepository.save(newFriendShip);

        return new AddFriendResponseDto(newFriendShip);
    }

    // DTO에 매핑
    private List<FriendListResponseDto> convertToDtoList(List<FriendEntity> friendList) {
        return friendList.stream()
                .map(FriendListResponseDto::new)
                .collect(Collectors.toList());
    }

}
