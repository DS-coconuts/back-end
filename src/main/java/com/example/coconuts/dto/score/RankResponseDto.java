package com.example.coconuts.dto.score;

import com.example.coconuts.entity.ScoreEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RankResponseDto {

    private Integer scoreId;

    private Integer userId;

    private String loginId;

    private String image;

    private Integer cpm;

    public RankResponseDto(ScoreEntity score) {
        this.scoreId = score.getId();
        this.cpm = score.getCpm();
        this.userId = score.getUser()!= null ? score.getUser().getId() : null;
        this.loginId = score.getUser()!= null ? score.getUser().getLoginId() : null;
        this.image = score.getUser()!= null ? score.getUser().getImage() : null;
    }
}
