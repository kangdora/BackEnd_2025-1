package com.example.bcsd.service;

import com.example.bcsd.dao.ArticleDao;
import com.example.bcsd.dao.BoardDao;
import com.example.bcsd.dto.*;
import com.example.bcsd.exception.CustomException;
import com.example.bcsd.exception.ErrorCode;
import com.example.bcsd.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleDao articleDao;
    private final BoardDao boardDao;

    @Autowired
    public ArticleService(ArticleDao articleDao, BoardDao boardDao) {
        this.articleDao = articleDao;
        this.boardDao = boardDao;
    }

    public ArticleResponseDto saveArticle(ArticleSaveRequestDto dto) {
        if (dto.authorId() == null) {
            throw new CustomException(ErrorCode.USER_REFERENCE_REQUIRED);
        }
        if (dto.boardId() == null) {
            throw new CustomException(ErrorCode.BOARD_REFERENCE_REQUIRED);
        }

        String now = LocalDate.now().toString();
        Article article = new Article(
                null,
                dto.title(),
                dto.authorId(),
                dto.boardId(),
                now,
                dto.content(),
                now
        );
        try {
            articleDao.insertArticle(article);
        } catch (EmptyResultDataAccessException e) {
            throw new CustomException(ErrorCode.INVALID_USER_OR_BOARD_REFERENCE);
        }

        return new ArticleResponseDto(
                article.getId(),
                article.getAuthorId(),
                article.getBoardId(),
                article.getTitle(),
                article.getContent(),
                article.getCreatedDate(),
                article.getModifiedDate()
        );
    }

    public ArticleResponseDto getArticleById(Long id) {
        try {
            Article article = articleDao.getArticle(id);
            return new ArticleResponseDto(
                    article.getId(),
                    article.getAuthorId(),
                    article.getBoardId(),
                    article.getTitle(),
                    article.getContent(),
                    article.getCreatedDate(),
                    article.getModifiedDate()
            );
        } catch (EmptyResultDataAccessException e) {
            throw new CustomException(ErrorCode.ARTICLE_NOT_FOUND);
        }
    }


    public List<ArticleResponseDto> getArticlesByBoardId(Long boardId) {
        return articleDao.findByBoardId(boardId).stream()
                .map(article -> new ArticleResponseDto(
                        article.getId(),
                        article.getAuthorId(),
                        article.getBoardId(),
                        article.getTitle(),
                        article.getContent(),
                        article.getCreatedDate(),
                        article.getModifiedDate()
                ))
                .collect(Collectors.toList());
    }

    public PostResponseDto getArticleIdsById(Long BoardId) {
        return new PostResponseDto(
                boardDao.getBoardName(BoardId),
                articleDao.findByBoardId(BoardId).stream()
                        .map(Article::getId)
                        .toList()
        );
    }

    public void deleteArticle(Long id) {
        articleDao.deleteArticle(id);
    }

    public ArticleResponseDto editArticle(Long id, ArticleUpdateRequestDto dto) {
        try {
            articleDao.editArticle(id, dto.title(), dto.content(), LocalDate.now().toString());
            return getArticleById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CustomException(ErrorCode.INVALID_USER_OR_BOARD_REFERENCE);
        }
    }
}
