package com.example.bcsd.model;

public class Article {
    private final Long id;
    private String title;
    private final Long authorId;
    private final Long boardId;
    private final String createdAt;
    private String content;
    private String modificationDate = "";

    public Article(Long id, String title, Long authorId, Long boardId, String createdAt, String content) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.boardId = boardId;
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

    public void setModificationDate(String date) {
        this.modificationDate = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}