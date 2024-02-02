package com.example.coconuts.exception;

import com.example.coconuts.code.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserIdNotFoundException extends RuntimeException{
    private final ErrorCode errorCode;
}