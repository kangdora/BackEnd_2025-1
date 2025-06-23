package com.example.bcsd.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "boards")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    public Board(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Board changeTitle(String title) {
        return new Board(id, title);
    }
}
