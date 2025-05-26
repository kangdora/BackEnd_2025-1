package com.example.bcsd.dao;

import com.example.bcsd.model.Board;
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

    @Transactional(readOnly = true)
    public Board getBoard(Long id) {
        String sql = "SELECT * FROM board WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new Board(
                rs.getLong("id"),
                rs.getString("name")
        ));
    }

    @Transactional
    public void deleteBoard(Long id) {
        String sql = "DELETE FROM board WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
