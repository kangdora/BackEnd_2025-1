package com.example.bcsd.dto;

import com.example.bcsd.model.Article;

import java.util.List;

public record BoardResponseDto(String title, List<Article> articles) {
}
