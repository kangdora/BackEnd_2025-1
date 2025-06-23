package com.example.bcsd.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;

@Getter
@Entity
@Table(name = "boards")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Article> articles = new ArrayList<>();

    public Board(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Board changeTitle(String title) {
        this.title = title;
        return this;
    }

    public void addArticle(Article article) {
        articles.add(article);
        article.setBoard(this);
    }

    public void removeArticle(Article article) {
        articles.remove(article);
        article.setBoard(null);
    }
}
