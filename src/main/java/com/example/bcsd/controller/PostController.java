package com.example.bcsd.controller;

import com.example.bcsd.dto.ArticleInfo;
import com.example.bcsd.dto.ArticleSummaryDto;
import com.example.bcsd.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private ArticleService articleService;

    @GetMapping("/posts")
    public String getBoardPosts(@RequestParam Long boardId, Model model) {
        ArticleSummaryDto dto = articleService.getBoardSummary(boardId);

        String boardName = dto.title();
        List<ArticleInfo> articles = dto.articles();

        model.addAttribute("boardName", boardName);
        model.addAttribute("articles", articles);

        return "post";
    }

}