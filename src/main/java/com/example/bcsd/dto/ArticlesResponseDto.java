package com.example.bcsd.dto;

public record ArticlesResponseDto(Long id, Long authorId, Long boardId, String title, String content, String createdAt, String modifiedDate) {
}
