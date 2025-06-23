package com.example.bcsd.service;


import com.example.bcsd.dto.*;
import com.example.bcsd.exception.CustomException;
import com.example.bcsd.exception.ErrorCode;
import com.example.bcsd.model.Article;
import com.example.bcsd.model.Board;
import com.example.bcsd.model.Member;
import com.example.bcsd.repository.ArticleRepository;
import com.example.bcsd.repository.BoardRepository;
import com.example.bcsd.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    @Autowired
    public ArticleService(ArticleRepository articleRepository,
                          BoardRepository boardRepository,
                          MemberRepository memberRepository
    ) {
        this.articleRepository = articleRepository;
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
    }

    private ArticleResponseDto toResponseDto(Article article) {
        return new ArticleResponseDto(
                article.getId(),
                article.getAuthor().getId(),
                article.getBoard().getId(),
                article.getTitle(),
                article.getContent(),
                article.getCreatedDate(),
                article.getModifiedDate()
        );
    }

    public ArticleResponseDto saveArticle(ArticleSaveRequestDto dto) {
        if (dto.authorId() == null) {
            throw new CustomException(ErrorCode.USER_REFERENCE_REQUIRED);
        }
        if (dto.boardId() == null) {
            throw new CustomException(ErrorCode.BOARD_REFERENCE_REQUIRED);
        }

        Member author = memberRepository.findById(dto.authorId())
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_USER_OR_BOARD_REFERENCE));
        Board board = boardRepository.findById(dto.boardId())
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_USER_OR_BOARD_REFERENCE));

        String now = LocalDate.now().toString();

        Article article = Article.builder()
                .title(dto.title())
                .author(author)
                .board(board)
                .createdAt(now)
                .modifiedDate(now)
                .content(dto.content())
                .build();
        articleRepository.save(article);

        return toResponseDto(article);
    }

    public ArticleResponseDto getArticleById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.ARTICLE_NOT_FOUND));
        return toResponseDto(article);
    }

    public List<ArticleResponseDto> getArticlesByBoardId(Long boardId) {
        return articleRepository.findByBoardId(boardId).stream()
                .map(this::toResponseDto)
                .toList();
    }

    public PostResponseDto getArticleIdsById(Long boardId) {
        String boardName = boardRepository.findById(boardId)
                .map(Board::getTitle)
                .orElseThrow(() -> new CustomException(ErrorCode.BOARD_REFERENCE_REQUIRED));

        List<Long> articleIds = articleRepository.findByBoardId(boardId).stream()
                .map(Article::getId)
                .toList();

        return new PostResponseDto(boardName, articleIds);
    }

    public void deleteArticle(Long id) {
        if (!articleRepository.existsById(id)) {
            throw new CustomException(ErrorCode.ARTICLE_NOT_FOUND);
        }
        articleRepository.deleteById(id);
    }

    public ArticleResponseDto editArticle(Long id, ArticleUpdateRequestDto dto) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.ARTICLE_NOT_FOUND));

        article.changeArticle(dto.title(), dto.content(), LocalDate.now().toString());
        articleRepository.save(article);

        return toResponseDto(article);
    }
}
