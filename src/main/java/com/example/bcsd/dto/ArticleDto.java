package com.example.bcsd.dto;

import java.time.LocalDateTime;
import java.util.Date;

public record ArticleDto(
        Long id,
        Long authorId,
        Long boardId,
        String title,
        String content,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate
) {}
