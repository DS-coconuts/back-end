package com.example.coconuts.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /*
     * 400 BAD_REQUEST: 잘못된 요청
     */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    DUPLICATE_ID_REQUEST(HttpStatus.BAD_REQUEST, "중복된 아이디가 있습니다."),
    EMPTY_COMMENT_CONTENT(HttpStatus.BAD_REQUEST, "댓글 내용이 비어있습니다."),
    LANGUAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "코드의 언어가 일치하지 않습니다." ),

    /*
     * 404 NOT_FOUND: 리소스를 찾을 수 없음
     */
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하는 사용자가 없습니다"),
    USERID_NOT_FOUND(HttpStatus.NOT_FOUND, "아이디가 존재하지 않습니다."),
    PASSWORD_NOT_MATCH(HttpStatus.NOT_FOUND, "비밀번호가 올바르지 않습니다."),
    FRIENDSHIP_ALREADY_EXISTS(HttpStatus.NOT_FOUND, "이미 친구 관계입니다."),
    PROFILE_NOT_FOUND(HttpStatus.NOT_FOUND, "프로필 정보가 없습니다." ),
    DATA_NOT_FOUND(HttpStatus.NOT_FOUND, "코드에 대한 정보가 없습니다." ),
    SCORE_NOT_FOUND(HttpStatus.NOT_FOUND, "등록된 기록이 없습니다."),


    /*
     * 500 INTERNAL_SERVER_ERROR: 내부 서버 오류
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류입니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
