package com.example.coconuts.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {
    private String loginId;
    private String password;
    private String introduction;
    private Integer goalCpm;
}
