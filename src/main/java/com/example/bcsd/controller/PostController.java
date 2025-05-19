package com.example.bcsd.controller;

import com.example.bcsd.dto.BoardResponseDto;
import com.example.bcsd.dto.PostResponseDto;
import com.example.bcsd.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/posts")
@Controller
public class PostController {

    private final ArticleService articleService;

    public PostController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String getPostByBoardId(@RequestParam(name = "id", required = false, defaultValue = "World") Long id, Model model){
        PostResponseDto dto = articleService.getArticleIdsById(id);

        model.addAttribute("boardName", dto.BoardName());
        model.addAttribute("articles", dto.ArticleIds());
        return "post";
    }
}
