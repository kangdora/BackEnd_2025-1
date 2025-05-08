package com.example.bcsd.service;

import com.example.bcsd.dto.ArticleResponseDto;
import com.example.bcsd.dto.ArticleSaveRequestDto;
import com.example.bcsd.dto.ArticleUpdateRequestDto;
import com.example.bcsd.dto.BoardResponseDto;
import com.example.bcsd.model.Article;
import com.example.bcsd.repository.ArticleRepository;
import com.example.bcsd.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, MemberRepository memberRepository) {
        this.articleRepository = articleRepository;
        this.memberRepository = memberRepository;
        memberRepository.testMemberRepository(); // 테스트용
    }

    public void saveArticle(ArticleSaveRequestDto dto) {
        Article article = new Article(
                null,
                dto.title(),
                dto.authorId(),
                dto.boardId(),
                null,
                dto.content(),
                null
        );
        articleRepository.addArticle(article);
    }

    public ArticleResponseDto getArticleById(Long id) {
        Article article = articleRepository.getArticle(id);
        return new ArticleResponseDto(
                memberRepository.getMember(article.getAuthorId()).getName(),
                article.getTitle(),
                article.getContent(),
                article.getCreatedAt()
        );
    }

    public List<ArticleResponseDto> getAllArticles() {
        return articleRepository.getArticles().stream()
                .map(article -> new ArticleResponseDto(
                        memberRepository.getMember(article.getAuthorId()).getName(),
                        article.getTitle(),
                        article.getContent(),
                        article.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    public BoardResponseDto getPosts() {
        return new BoardResponseDto("자유 게시판", getAllArticles());
    }

    public void deleteArticle(Long id){
        articleRepository.deleteArticle(id);
    }

    public void editArticle(Long id, ArticleUpdateRequestDto dto){
        articleRepository.editArticle(id, dto.title(), dto.content());
    }
}
