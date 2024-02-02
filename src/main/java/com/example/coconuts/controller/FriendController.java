package com.example.coconuts.controller;

import com.example.coconuts.code.ResponseCode;
import com.example.coconuts.dto.ResponseDTO;
import com.example.coconuts.dto.friend.AddFriendRequestDto;
import com.example.coconuts.dto.friend.AddFriendResponseDto;
import com.example.coconuts.dto.friend.FriendListRequestDto;
import com.example.coconuts.dto.friend.FriendListResponseDto;
import com.example.coconuts.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addFriend(@RequestBody AddFriendRequestDto addFriendRequestDto) {

        AddFriendResponseDto res = friendService.addFriend(addFriendRequestDto.getUserId(), addFriendRequestDto.getFriendLoginId());

        return ResponseEntity
                .status(ResponseCode.SUCCESS_ADD_FRIEND.getStatus().value())
                .body(new ResponseDTO(ResponseCode.SUCCESS_ADD_FRIEND, res));
    }

//    @GetMapping("/search")
//    public ResponseEntity<ResponseDTO> searchFriends(@RequestParam("q") String query) {
//
//        List<FriendListResponseDto> res = friendService.searchFriends(query);
//
//        return ResponseEntity
//                .status(ResponseCode.SUCCESS_SEARCH_FRIENDS.getStatus().value())
//                .body(new ResponseDTO(ResponseCode.SUCCESS_SEARCH_FRIENDS, null));
//    }

}
