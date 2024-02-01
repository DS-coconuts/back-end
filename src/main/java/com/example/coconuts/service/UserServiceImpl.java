package com.example.coconuts.service;

import com.example.coconuts.dto.friend.FriendListResponseDto;
import com.example.coconuts.dto.user.UserListResponseDto;
import com.example.coconuts.entity.FriendEntity;
import com.example.coconuts.entity.UserEntity;
import com.example.coconuts.repository.FriendRepository;
import com.example.coconuts.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public List<UserListResponseDto> searchUsers(Integer userId, String query) {

        // 검색어로 사용자 찾기
        List<UserEntity> searchResults = userRepository.findNonFriendUsersBySearchCriteria(userId, query);

        return convertToDtoList(searchResults);
    }

    private List<UserListResponseDto> convertToDtoList(List<UserEntity> userList) {
        return userList.stream()
                .map(UserListResponseDto::new)
                .collect(Collectors.toList());
    }
}
