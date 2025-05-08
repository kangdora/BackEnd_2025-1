package com.example.bcsd.model;

public class Board {
    private Long id;
    private String title;

    public Board(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String Title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }
}
