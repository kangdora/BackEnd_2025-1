package com.example.bcsd.dto;

import java.util.List;

public record ArticleSummaryDto(
        String title,
        List<ArticleInfo> articles
) {}
