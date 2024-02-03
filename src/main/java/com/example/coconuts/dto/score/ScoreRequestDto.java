package com.example.coconuts.dto.score;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScoreRequestDto {
    private Integer userId;
    private Integer dataId;
    private Integer cpm;
    private Integer wpm;
    private Integer acc;
    private Integer countTime;
    private String language;
}