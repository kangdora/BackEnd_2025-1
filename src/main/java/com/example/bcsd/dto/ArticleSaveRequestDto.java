package com.example.bcsd.dto;

public record ArticleSaveRequestDto(
        String title,
        Long authorId,
        Long boardId,
        String content) {
}
