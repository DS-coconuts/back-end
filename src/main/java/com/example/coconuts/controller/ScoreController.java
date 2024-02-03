package com.example.coconuts.controller;

import com.example.coconuts.code.ResponseCode;
import com.example.coconuts.dto.ResponseDTO;
import com.example.coconuts.dto.score.ScoreRequestDto;
import com.example.coconuts.dto.score.ScoreResponseDto;
import com.example.coconuts.entity.ScoreEntity;
import com.example.coconuts.service.FriendService;
import com.example.coconuts.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createScore(@RequestBody ScoreRequestDto scoreRequestDto) {
        ScoreResponseDto res = scoreService.createScore(scoreRequestDto);

        return ResponseEntity
                .status(ResponseCode.SUCCESS_GET_FRIEND_LIST.getStatus().value())
                .body(new ResponseDTO(ResponseCode.SUCCESS_GET_FRIEND_LIST, res));
    }
}