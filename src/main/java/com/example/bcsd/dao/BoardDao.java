package com.example.bcsd.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BoardDao {

    private final JdbcTemplate jdbcTemplate;

    public BoardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = true)
    public String getBoardName(Long boardId) {
        String sql = "SELECT name FROM board WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, boardId);
    }
}
