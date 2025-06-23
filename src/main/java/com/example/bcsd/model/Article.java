package com.example.bcsd.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "articles")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Member author;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    private String createdDate;

    private String content;

    private String modifiedDate;

    @Builder
    public Article(Long id, String title, Member author, Board board, String createdAt, String content, String modifiedDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.board = board;
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
