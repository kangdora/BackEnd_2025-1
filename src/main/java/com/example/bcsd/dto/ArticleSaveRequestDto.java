package com.example.bcsd.dto;

public record ArticleSaveRequestDto(Long id, String title, Long authorId, Long boardId, String createdAt,
                                    String content) {
}
