package com.example.bcsd.repository;

import com.example.bcsd.model.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private final List<Article> articles = new ArrayList<>();

    public List<Article> getArticles() {
        return articles;
    }

    public Article getArticle(Long id) {
        for (Article article : articles) {
            if (article.getId().equals(id)) {
                return article;
            }
        }
        return null;
    }

    public void addArticle(Article article) {
        articles.add(article);
    }

    public void editArticle(Long id, String title, String content) {
        for (Article article : articles) {
            if (article.getId().equals(id)) {
                article.setTitle(title);
                article.setContent(content);
            }
        }
    }

    public void removeArticle(Long id) {
        articles.removeIf(article -> (article.getId().equals(id)));
    }
}
