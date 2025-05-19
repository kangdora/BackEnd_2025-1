package com.example.bcsd.dto;

import java.util.List;

public record PostResponseDto(String BoardName, List<Long> ArticleIds) {
}
