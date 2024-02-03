package com.example.coconuts.service;

import com.example.coconuts.code.ErrorCode;
import com.example.coconuts.dto.data.DataResponseDto;
import com.example.coconuts.dto.score.ScoreRequestDto;
import com.example.coconuts.dto.score.ScoreResponseDto;
import com.example.coconuts.entity.DataEntity;
import com.example.coconuts.entity.ScoreEntity;
import com.example.coconuts.entity.UserEntity;
import com.example.coconuts.exception.DataNotFoundException;
import com.example.coconuts.exception.NotSameLanguageException;
import com.example.coconuts.exception.UserIdNotFoundException;
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
        // 존재하는 유저인지 확인
        UserEntity user = userRepository.findById(scoreRequestDto.getUserId()).orElse(null);
        if (user == null) {
            throw new UserIdNotFoundException(ErrorCode.USERID_NOT_FOUND);
        }

        // 존재하는 데이터 코드인지 확인
        DataEntity data = dataRepository.findById(scoreRequestDto.getDataId()).orElse(null);
        if (data == null) {
            throw new DataNotFoundException(ErrorCode.DATA_NOT_FOUND);
        }

        // DTO 언어와 코드의 언어가 일치하는지 확인
        if(scoreRequestDto.getLanguage() != data.getLanguage()) {
            throw new NotSameLanguageException(ErrorCode.LANGUAGE_NOT_FOUND);
        }

        // DB 저장하기 위한 Score 엔티티 생성하기
        ScoreEntity score = ScoreEntity.builder()
                .createdAt(LocalDate.now())
                .cpm(scoreRequestDto.getCpm())
                .wpm(scoreRequestDto.getWpm())
                .acc(scoreRequestDto.getAcc())
                .countTime(scoreRequestDto.getCountTime())
                .language(scoreRequestDto.getLanguage())
                .data(data)
                .user(user)
                .build();

        scoreRepository.save(score);

        ScoreResponseDto responseDto = new ScoreResponseDto(score);
        return responseDto;

    }

}
