package com.example.bcsd.controller;

import com.example.bcsd.dto.ArticleDto;
import com.example.bcsd.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private ArticleService articleService;

    @GetMapping()
    public List<ArticleDto> getArticles(@RequestParam Long boardId){
        return articleService.getBoardArticles(boardId);
    }

    @GetMapping("/{id}")
    public ArticleDto getArticle(@PathVariable Long id) {
        return articleService.getArticle(id);
    }

    @PostMapping
    public void createArticle(@RequestBody ArticleDto dto) {
        articleService.createArticle(dto);
    }

    @PutMapping("/{id}")
    public void updateArticle(@PathVariable Long id, @RequestBody ArticleDto dto) {
        articleService.updateArticle(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }

}
