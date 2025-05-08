package com.example.bcsd.dto;

import java.util.List;

public record BoardResponseDto(String boardTitle, List<ArticleResponseDto> articleResponseDtos) {
}
