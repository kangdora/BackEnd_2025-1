package com.example.bcsd.repository;

import com.example.bcsd.model.Article;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleRepository {
    private final List<Article> articles = new ArrayList<>();
    private static final Long firstId = 1L; // const

    public List<Article> getArticles() {
        return articles;
    }

    public Article getArticle(Long id) {
        return articles.stream()
                .filter(article -> article.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void addArticle(Article article) {
        Article newArticle = new Article(
                getNextId(),
                article.getTitle(),
                article.getAuthorId(),
                article.getBoardId(),
                LocalDate.now().toString(),
                article.getContent(),
                "");
        articles.add(newArticle);
    }

    private Long getNextId() {
        if (articles.isEmpty()) {
            return firstId;
        }
        return findLastArticleId() + 1;
    }

    private Long findLastArticleId() {
        return articles.get(articles.size() - 1).getId();
    }

    public List<Article> findByBoardId(Long boardId) {
        return articles.stream()
                .filter(article -> article.getBoardId().equals(boardId))
                .toList();
    }

    public void editArticle(Long id, String title, String content) {
        for (Article article : articles) {
            if (article.getId().equals(id)) {
                Article updatedArticle = article.changeArticle(title, content, LocalDate.now().toString());
                deleteArticle(id);
                articles.add(updatedArticle);
            }
        }
    }

    public void deleteArticle(Long id) {
        articles.removeIf(article -> (article.getId().equals(id)));
    }
}
