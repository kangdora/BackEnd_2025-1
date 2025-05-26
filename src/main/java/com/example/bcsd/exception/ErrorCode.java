package com.example.bcsd.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_USER_OR_BOARD_REFERENCE(BAD_REQUEST, "존재하지 않는 사용자이거나 게시판입니다."),
    USER_REFERENCE_REQUIRED(BAD_REQUEST, "사용자의 입력이 필요합니다."),
    BOARD_REFERENCE_REQUIRED(BAD_REQUEST, "게시판의 입력이 필요합니다."),
    ARTICLE_REFERENCE_REQUIRED(BAD_REQUEST, "게시글의 입력이 필요합니다."),
    MEMBER_HAS_EXISTING_ARTICLES(BAD_REQUEST, "사용자가 작성한 글이 존재합니다."),
    BOARD_HAS_EXISTING_ARTICLES(BAD_REQUEST, "게시판에 작성된 글이 존재합니다."),

    MEMBER_NOT_FOUND(NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다"),
    BOARD_NOT_FOUND(NOT_FOUND, "해당 게시판 정보를 찾을 수 없습니다"),
    ARTICLE_NOT_FOUND(NOT_FOUND, "해당 게시글 정보를 찾을 수 없습니다"),

    DUPLICATE_EMAIL(CONFLICT, "이메일이 이미 존재합니다");

    private final HttpStatus httpStatus;
    private final String detail;
}