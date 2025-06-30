package com.example.bcsd.service;

import com.example.bcsd.dto.ArticleDto;
import com.example.bcsd.dto.ArticleInfo;
import com.example.bcsd.dto.ArticleSummaryDto;
import com.example.bcsd.exception.common.BaseException;
import com.example.bcsd.exception.common.ErrorCode;
import com.example.bcsd.model.Article;
import com.example.bcsd.model.Board;
import com.example.bcsd.model.User;
import com.example.bcsd.repository.ArticleRepository;
import com.example.bcsd.repository.BoardRepository;
import com.example.bcsd.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public ArticleSummaryDto getBoardSummary(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BaseException(ErrorCode.BOARD_NOT_FOUND));
        List<ArticleInfo> articles = board.getArticles().stream()
                .map(a -> new ArticleInfo(a.getId(), a.getTitle()))
                .collect(Collectors.toList());
        return new ArticleSummaryDto(board.getTitle(), articles);
    }

    public List<ArticleDto> getBoardArticles(Long boardId) {
        return articleRepository.findByBoard_Id(boardId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ArticleDto getArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.ARTICLE_NOT_FOUND));
        return toDto(article);
    }

    @Transactional
    public ArticleDto createArticle(ArticleDto dto) {
        User user = userRepository.findById(dto.authorId())
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));
        Board board = boardRepository.findById(dto.boardId())
                .orElseThrow(() -> new BaseException(ErrorCode.BOARD_NOT_FOUND));
        Article article = Article.builder()
                .user(user)
                .board(board)
                .title(dto.title())
                .content(dto.content())
                .build();
        Article saved = articleRepository.save(article);
        return toDto(saved);
    }

    @Transactional
    public ArticleDto updateArticle(Long id, ArticleDto dto) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.ARTICLE_NOT_FOUND));
        if (dto.title() != null) {
            article.setTitle(dto.title());
        }
        if (dto.content() != null) {
            article.setContent(dto.content());
        }
        if (dto.boardId() != null && !dto.boardId().equals(article.getBoard().getId())) {
            Board board = boardRepository.findById(dto.boardId())
                    .orElseThrow(() -> new BaseException(ErrorCode.BOARD_NOT_FOUND));
            article.setBoard(board);
        }
        return toDto(article);
    }

    @Transactional
    public void deleteArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.ARTICLE_NOT_FOUND));
        articleRepository.delete(article);
    }

    private ArticleDto toDto(Article article) {
        return new ArticleDto(
                article.getId(),
                article.getUser().getId(),
                article.getBoard().getId(),
                article.getTitle(),
                article.getContent(),
                article.getCreatedDate(),
                article.getModifiedDate()
        );
    }
}
