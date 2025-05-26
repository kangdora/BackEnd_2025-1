package com.example.bcsd.dao;

import com.example.bcsd.model.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ArticleDao {
    private final JdbcTemplate jdbcTemplate;

    public ArticleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = true)
    public List<Article> getArticles() {
        String sql = "SELECT * FROM article";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Article(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getLong("author_id"),
                rs.getLong("board_id"),
                rs.getString("created_date"),
                rs.getString("content"),
                rs.getString("modified_date")
        ));
    }

    @Transactional(readOnly = true)
    public Article getArticle(Long id) {
        String sql = "SELECT * FROM article WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new Article(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getLong("author_id"),
                rs.getLong("board_id"),
                rs.getString("created_date"),
                rs.getString("content"),
                rs.getString("modified_date")
        ));
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void insertArticle(Article article) {
        String sql = "INSERT INTO article (title, auther_id, board_id, created_date, content, modified_date) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                article.getTitle(),
                article.getAuthorId(),
                article.getBoardId(),
                article.getCreatedDate(),
                article.getContent(),
                article.getModifiedDate()
        );
    }

    @Transactional(readOnly = true)
    public List<Article> findByBoardId(Long boardId) {
        String sql = "SELECT * FROM article WHERE board_id = ?";
        return jdbcTemplate.query(sql, new Object[]{boardId}, (rs, rowNum) -> new Article(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getLong("author_id"),
                rs.getLong("board_id"),
                rs.getString("created_date"),
                rs.getString("content"),
                rs.getString("modified_date")
        ));
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void editArticle(Long id, String title, String content, String modifiedDate) {
        String sql = "UPDATE article SET title = ?, content = ?, modified_date = ? WHERE id = ?";
        jdbcTemplate.update(sql, title, content, modifiedDate, id);
    }

    public void deleteArticle(Long id) {
        String sql = "DELETE FROM article WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public boolean isExistArticleByAuthorId(Long authorId) {
        String sql = "SELECT COUNT(*) FROM article WHERE author_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, authorId);
        return count != null && count > 0;
    }

    public boolean isExistArticleByBoard(Long boardId) {
        String sql = "SELECT COUNT(*) FROM article WHERE board_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, boardId);
        return count != null && count > 0;
    }
}
