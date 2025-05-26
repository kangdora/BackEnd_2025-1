package com.example.bcsd.service;

import com.example.bcsd.dao.ArticleDao;
import com.example.bcsd.dao.BoardDao;
import com.example.bcsd.exception.CustomException;
import com.example.bcsd.exception.ErrorCode;
import com.example.bcsd.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final BoardDao boardDao;
    private final ArticleDao articleDao;

    @Autowired
    public PostService(BoardDao boardDao, ArticleDao articleDao) {
        this.boardDao = boardDao;
        this.articleDao = articleDao;
    }

    public void deleteBoard(Long boardId) {
        Board board = boardDao.getBoard(boardId);
        if (board == null) {
            throw new CustomException(ErrorCode.BOARD_NOT_FOUND);
        }

        if (articleDao.isExistArticleByBoard(boardId)) {
            throw new CustomException(ErrorCode.BOARD_HAS_EXISTING_ARTICLES);
        }

        boardDao.deleteBoard(boardId);
    }
}
