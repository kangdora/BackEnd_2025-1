package com.example.bcsd.dto;

import java.util.List;

public record BoardResponseDto(
        Long boardId,
        List<ArticleDto> postIds
) {}
