package com.example.bcsd.dto;

import java.util.Date;

public record ArticleDto(
        Long id,
        Long authorId,
        Long boardId,
        String title,
        String content,
        Date createdDate,
        Date modifiedDate
) {}
