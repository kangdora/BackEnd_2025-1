package com.example.bcsd.dto;

public record ArticleUpdateRequestDto(Long id, String title, Long authorId, String modificationDate, String content) {
}
