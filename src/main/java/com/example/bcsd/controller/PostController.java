package com.example.bcsd.controller;

import com.example.bcsd.dto.BoardResponseDto;
import com.example.bcsd.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/posts")
@Controller
public class PostController {

    private final ArticleService articleService;

    public PostController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String getAllPosts(Model model){
        BoardResponseDto dto = articleService.getPosts();

        model.addAttribute("title", dto.title());
        model.addAttribute("articles", dto.articles());
        return "posts";
    }
}
