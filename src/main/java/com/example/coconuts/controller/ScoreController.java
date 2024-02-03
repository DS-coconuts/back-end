package com.example.coconuts.controller;

import com.example.coconuts.code.ResponseCode;
import com.example.coconuts.dto.ResponseDTO;
import com.example.coconuts.dto.score.RankResponseDto;
import com.example.coconuts.dto.score.ScoreRequestDto;
import com.example.coconuts.dto.score.ScoreResponseDto;
import com.example.coconuts.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/scores")
public class ScoreController {

    private final ScoreService scoreService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createScore(@RequestBody ScoreRequestDto scoreRequestDto) {

        ScoreResponseDto res = scoreService.createScore(scoreRequestDto);

        return ResponseEntity
                .status(ResponseCode.SUCCESS_CREATE_SCORE.getStatus().value())
                .body(new ResponseDTO(ResponseCode.SUCCESS_CREATE_SCORE, res));
    }

    @GetMapping("/rank")
    public ResponseEntity<ResponseDTO> getRank(@RequestParam("language") String language) {

        List<RankResponseDto> res = scoreService.getRank(language);

        return ResponseEntity
                .status(ResponseCode.SUCCESS_GET_RANK.getStatus().value())
                .body(new ResponseDTO(ResponseCode.SUCCESS_GET_RANK, res));
    }
}