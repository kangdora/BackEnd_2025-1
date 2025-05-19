package com.example.bcsd.dto;

public record ArticleResponseDto(Long id, Long authorId, Long boardId, String title, String content, String createdAt, String modifiedDate) {
}
