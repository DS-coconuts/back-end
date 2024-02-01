package com.example.coconuts.dto.friend;

import com.example.coconuts.entity.FriendEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FriendListResponseDto {

    private Integer friendId;

    private Integer toUserId;

    private String toUserLoginId;

    private String toUserIntroduction;

    private String toUserImage;

    private Integer toUserGoalCpm;

    public FriendListResponseDto(FriendEntity friend) {
        this.friendId = friend.getId();
        this.toUserId = friend.getToUser() != null ? friend.getToUser().getId() : null;
        this.toUserLoginId = friend.getToUser() != null ? friend.getToUser().getLoginId() : null;
        this.toUserIntroduction = friend.getToUser() != null ? friend.getToUser().getIntroduction() : null;
        this.toUserImage = friend.getToUser() != null ? friend.getToUser().getImage() : null;
        this.toUserGoalCpm = friend.getToUser() != null ? friend.getToUser().getGoalCpm() : null;
    }
}
