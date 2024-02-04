package com.example.coconuts.exception;

import com.example.coconuts.code.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LanguageNotFoundException extends RuntimeException{
    private final ErrorCode errorCode;
}
