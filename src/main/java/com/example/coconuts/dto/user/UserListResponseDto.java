package com.example.coconuts.dto.user;

import com.example.coconuts.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserListResponseDto {

    private Integer userId;
    private String loginId;
    private String introduction;

    private Integer goalCpm;

    private String image;

    public UserListResponseDto(UserEntity user) {
        this.userId = user.getId();
        this.loginId = user.getLoginId();
        this.introduction = user.getIntroduction();
        this.goalCpm = user.getGoalCpm();
        this.image = user.getImage();
    }

}
