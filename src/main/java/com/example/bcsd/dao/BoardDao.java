package com.example.bcsd.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {

    private final JdbcTemplate jdbcTemplate;

    public BoardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getBoardName(Long boardId) {
        String sql = "SELECT name FROM board WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, boardId);
    }
}
