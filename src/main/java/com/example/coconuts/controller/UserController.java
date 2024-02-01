package com.example.coconuts.controller;

import com.example.coconuts.code.ResponseCode;
import com.example.coconuts.dto.ResponseDTO;
import com.example.coconuts.dto.user.SearchUserRequestDto;
import com.example.coconuts.dto.user.UserListResponseDto;
import com.example.coconuts.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // JSON 형태의 결과값 반환
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/search")
    public ResponseEntity<ResponseDTO> searchUsers(@RequestParam("q") String query,
                                                   @RequestBody SearchUserRequestDto searchUserRequestDto) {
        List<UserListResponseDto> res = userService.searchUsers(searchUserRequestDto.getUserId(), query);

        return ResponseEntity
                .status(ResponseCode.SUCCESS_SEARCH_USERS.getStatus().value())
                .body(new ResponseDTO(ResponseCode.SUCCESS_SEARCH_USERS, res));
    }
}
