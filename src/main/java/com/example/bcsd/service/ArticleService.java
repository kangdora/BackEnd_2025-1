package com.example.bcsd.service;

import com.example.bcsd.dao.ArticleDao;
import com.example.bcsd.dto.ArticleResponseDto;
import com.example.bcsd.dto.ArticleSaveRequestDto;
import com.example.bcsd.dto.ArticleUpdateRequestDto;
import com.example.bcsd.dto.BoardResponseDto;
import com.example.bcsd.model.Article;
import com.example.bcsd.repository.ArticleRepository;
import com.example.bcsd.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleDao articleDao;
    private final MemberRepository memberRepository;

    @Autowired
    public ArticleService(ArticleDao articleDao, MemberRepository memberRepository) {
        this.articleDao = articleDao;
        this.memberRepository = memberRepository;
    }

    public void saveArticle(ArticleSaveRequestDto dto) {
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
        articleDao.insertArticle(article);
    }

    public ArticleResponseDto getArticleById(Long id) {
        Article article = articleDao.getArticle(id);
        return new ArticleResponseDto(
                memberRepository.getMember(article.getAuthorId()).getName(),
                article.getTitle(),
                article.getContent(),
                article.getCreatedDate()
        );
    }

    public List<ArticleResponseDto> getArticlesByBoardId(Long boardId) {
        return articleDao.findByBoardId(boardId).stream()
                .map(article -> new ArticleResponseDto(
                        memberRepository.getMember(article.getAuthorId()).getName(),
                        article.getTitle(),
                        article.getContent(),
                        article.getCreatedDate()
                ))
                .collect(Collectors.toList());
    }

    public List<ArticleResponseDto> getAllArticles() {
        return articleDao.getArticles().stream()
                .map(article -> new ArticleResponseDto(
                        memberRepository.getMember(article.getAuthorId()).getName(),
                        article.getTitle(),
                        article.getContent(),
                        article.getCreatedDate()
                ))
                .collect(Collectors.toList());
    }

    public BoardResponseDto getPosts() {
        return new BoardResponseDto("자유 게시판", getAllArticles());
    }

    public void deleteArticle(Long id){
        articleDao.deleteArticle(id);
    }

    public void editArticle(Long id, ArticleUpdateRequestDto dto){
        articleDao.editArticle(id, dto.title(), dto.content(), LocalDate.now().toString());
    }
}
