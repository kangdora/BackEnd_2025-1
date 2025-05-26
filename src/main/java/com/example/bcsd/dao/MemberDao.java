package com.example.bcsd.dao;

import com.example.bcsd.model.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MemberDao {
    private final JdbcTemplate jdbcTemplate;

    public MemberDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = true)
    public Member getMember(Long id) {
        String sql = "SELECT * FROM member WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new Member(
                rs.getLong("id"),
                rs.getString("email"),
                rs.getString("name"),
                rs.getString("password")
        ));
    }

    @Transactional
    public void deleteMember(Long id) {
        String sql = "DELETE FROM member WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}