package com.example.coconuts.service;

import com.example.coconuts.dto.score.ScoreRequestDto;
import com.example.coconuts.dto.score.ScoreResponseDto;
import com.example.coconuts.entity.DataEntity;
import com.example.coconuts.entity.ScoreEntity;
import com.example.coconuts.entity.UserEntity;
import com.example.coconuts.repository.DataRepository;
import com.example.coconuts.repository.ScoreRepository;
import com.example.coconuts.repository.UserRepository;
import com.example.coconuts.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {
    private final UserRepository userRepository;
    private final DataRepository dataRepository;
    private final ScoreRepository scoreRepository;

    @Override
    public ScoreResponseDto createScore(ScoreRequestDto scoreRequestDto) {
        Integer userId = scoreRequestDto.getUserId();
        UserEntity user = userRepository.findById(userId).orElse(null);
        Integer dataId = scoreRequestDto.getDataId();
        DataEntity data = dataRepository.findById(dataId).orElse(null);

        ScoreEntity score = ScoreEntity.builder()
                .createdAt(LocalDate.now())
                .cpm(scoreRequestDto.getCpm())
                .wpm(scoreRequestDto.getWpm())
                .acc(scoreRequestDto.getAcc())
                .countTime(scoreRequestDto.getCountTime())
                .language(scoreRequestDto.getLanguage())
                .dataId(data)
                .userId(user)
                .build();

        ScoreEntity createdScore = scoreRepository.save(score);
        return entityToProjectionScore(createdScore);

//        ScoreEntity score = new ScoreEntity(user, data, scoreRequestDto);
//        scoreRepository.save(score);
//        return new ScoreResponseDto(score);
    }

    private ScoreResponseDto entityToProjectionScore(ScoreEntity score) {
        return ScoreResponseDto.builder()
                .userId(score.getUserId().getId())
                .scoreId(score.getId())
                .dataId(score.getDataId().getId())
                .createdAt(score.getCreatedAt())
                .cpm(score.getCpm())
                .wpm(score.getWpm())
                .acc(score.getAcc())
                .countTime(score.getCountTime())
                .language(score.getLanguage())
                .build();
    }
}
