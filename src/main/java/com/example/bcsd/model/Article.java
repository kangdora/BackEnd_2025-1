package com.example.bcsd.model;

public class Article {
    private final Long id;
    private final String title;
    private final Long authorId;
    private final Long boardId;
    private final String createdAt;
    private final String content;
    private final String modificationDate;

    public Article(Long id, String title, Long authorId, Long boardId, String createdAt, String content, String modificationDate) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.boardId = boardId;
        this.createdAt = createdAt;
        this.content = content;
        this.modificationDate = modificationDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Article changeTitle(String title) {
        return new Article(id, title, authorId, boardId, createdAt, content, modificationDate);
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public Article updateModificationDate(String date) {
        return new Article(id, title, authorId, boardId, createdAt, content, date);
    }

    public String getContent() {
        return content;
    }

    public Article changeContent(String content) {
        return new Article(id, title, authorId, boardId, createdAt, content, modificationDate);
    }
}
