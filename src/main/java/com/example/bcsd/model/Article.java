package com.example.bcsd.model;

public class Article {
    private final Long id;
    private String title;
    private final String author;
    private final String createdAt;
    private String content;

    public Article(Long id, String title, String author, String createdAt, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.createdAt = createdAt;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}