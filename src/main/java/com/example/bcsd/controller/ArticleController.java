package com.example.bcsd.controller;

import com.example.bcsd.dto.ArticleDto;
import com.example.bcsd.dto.BoardResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @GetMapping()
    public BoardResponseDto getArticle(@RequestParam String boardId){
        return null;
    }

    @GetMapping("/{id}")
    public ArticleDto getArticle(@PathVariable Long id) {
        return null;
    }

    @PostMapping
    public void createArticle(@RequestBody ArticleDto dto) {

    }

    @PutMapping("/{id}")
    public void updateArticle(@PathVariable Long id, @RequestBody ArticleDto dto) {}

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {}

}
