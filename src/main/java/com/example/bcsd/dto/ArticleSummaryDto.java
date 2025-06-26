package com.example.bcsd.dto;

import java.util.List;
import java.util.Map;

public record ArticleSummaryDto(
        String title,
        List<Map<Long, String>> articles
) {}
