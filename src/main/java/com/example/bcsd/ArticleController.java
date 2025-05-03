package com.example.bcsd;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final Map<Long, Article> articles = new HashMap<>();
    private Long id = 1L;

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        Article postArticle = new Article(article.title(), article.content());
        articles.put(id++, postArticle);
        return ResponseEntity.ok(postArticle);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        Article article = articles.get(id);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long id) {
        Article removeArticle = articles.remove(id);
        if (removeArticle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("deleted id:" + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        Article newArticle = new Article(article.title(), article.content());
        articles.put(id, newArticle);
        return ResponseEntity.ok(newArticle);
    }
}