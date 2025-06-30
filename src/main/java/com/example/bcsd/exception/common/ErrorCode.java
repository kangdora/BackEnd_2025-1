package com.example.bcsd.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 등록된 이메일입니다."),
    NULL_REQUEST(HttpStatus.BAD_REQUEST, "요청 값이 null입니다."),
    INVALID_REFERENCE(HttpStatus.BAD_REQUEST, "잘못된 참조입니다."),
    DELETION_NOT_ALLOWED(HttpStatus.FORBIDDEN, "삭제가 허용되지 않은 리소스입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
