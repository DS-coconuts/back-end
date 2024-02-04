package com.example.coconuts.service;

import com.example.coconuts.dto.score.RankResponseDto;
import com.example.coconuts.dto.score.ScoreResponseDto;
import com.example.coconuts.dto.score.ScoreRequestDto;

import java.util.List;

public interface ScoreService {
    ScoreResponseDto createScore(ScoreRequestDto scoreRequest);

    List<RankResponseDto> getRank(String language);
}
