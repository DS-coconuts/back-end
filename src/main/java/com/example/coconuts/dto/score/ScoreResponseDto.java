package com.example.coconuts.dto.score;

import com.example.coconuts.entity.ScoreEntity;
import com.example.coconuts.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScoreResponseDto {
    private Integer userId;
    private Integer scoreId;
    private Integer dataId;
    private LocalDate createdAt;
    private Integer cpm;
    private Integer wpm;
    private Integer acc;
    private Integer countTime;
    private String language;

    public ScoreResponseDto(ScoreEntity score) {
        this.scoreId = score.getId();
        this.createdAt = score.getCreatedAt();
        this.cpm = score.getCpm();
        this.wpm = score.getWpm();
        this.acc = score.getAcc();
        this.countTime = score.getCountTime();
        this.language = score.getLanguage();
        this.userId = score.getUser().getId();
        this.dataId = score.getData().getId();
    }
}