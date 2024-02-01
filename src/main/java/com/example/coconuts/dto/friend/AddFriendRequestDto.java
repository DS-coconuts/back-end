package com.example.coconuts.dto.friend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddFriendRequestDto {

    private Integer userId;

    private String friendLoginId;
}
