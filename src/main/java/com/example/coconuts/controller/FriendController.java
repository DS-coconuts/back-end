package com.example.coconuts.controller;

import com.example.coconuts.code.ResponseCode;
import com.example.coconuts.dto.ResponseDTO;
import com.example.coconuts.dto.friend.FriendListRequestDto;
import com.example.coconuts.dto.friend.FriendListResponseDto;
import com.example.coconuts.entity.FriendEntity;
import com.example.coconuts.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // JSON 형태의 결과값 반환
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/friends")
public class FriendController {

    private final FriendService friendService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getFriendsList(@RequestBody FriendListRequestDto friendListRequestDto) {

        List<FriendListResponseDto> res = friendService.getFriendList(friendListRequestDto.getUserId());

        return ResponseEntity
                .status(ResponseCode.SUCCESS_GET_FRIEND_LIST.getStatus().value())
                .body(new ResponseDTO(ResponseCode.SUCCESS_GET_FRIEND_LIST, res));
    }
}
