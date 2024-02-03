package com.example.coconuts.service;

import com.example.coconuts.dto.score.ScoreResponseDto;
import com.example.coconuts.dto.score.ScoreRequestDto;

public interface ScoreService {
    ScoreResponseDto createScore(ScoreRequestDto scoreRequest);
}
