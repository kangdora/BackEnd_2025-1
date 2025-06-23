package com.example.bcsd.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "articles")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Long authorId;
    private Long boardId;
    private String createdDate;
    private String content;
    private String modifiedDate;

    @Builder
    public Article(Long id, String title, Long authorId, Long boardId, String createdAt, String content, String modifiedDate) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.boardId = boardId;
        this.createdDate = createdAt;
        this.content = content;
        this.modifiedDate = modifiedDate;
    }

    public void changeArticle(String title, String content, String modifiedDate) {
        this.title = title;
        this.content = content;
        this.modifiedDate = modifiedDate;
    }
}
