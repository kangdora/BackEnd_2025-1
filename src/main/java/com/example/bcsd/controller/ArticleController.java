package com.example.bcsd.controller;

import com.example.bcsd.dto.ArticleResponseDto;
import com.example.bcsd.dto.ArticleSaveRequestDto;
import com.example.bcsd.dto.ArticleUpdateRequestDto;
import com.example.bcsd.dto.ArticlesResponseDto;
import com.example.bcsd.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // post /articles
    @PostMapping
    public ResponseEntity<ArticleSaveRequestDto> createArticle(@RequestBody ArticleSaveRequestDto articleSaveRequestDto) {
        articleService.saveArticle(articleSaveRequestDto);
        return ResponseEntity.ok(articleSaveRequestDto);
    }

    // get /articles/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> getArticle(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.ok("deleted id:" + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleUpdateRequestDto> updateArticle(@PathVariable Long id, @RequestBody ArticleUpdateRequestDto dto) {
        articleService.editArticle(id, dto);
        return ResponseEntity.ok(dto);
    }

    // GET /articles?boardId={boardId}
    @GetMapping
    public ResponseEntity<List<ArticlesResponseDto>> getArticlesByBoardId(@RequestParam(name = "id", required = false, defaultValue = "World") Long id, Model model) {
        return ResponseEntity.ok(articleService.getArticlesByBoardId(id));
    }
}
