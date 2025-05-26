package com.example.bcsd.controller;

import com.example.bcsd.dto.PostResponseDto;
import com.example.bcsd.service.ArticleService;
import com.example.bcsd.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/posts")
@Controller
public class PostController {

    private final ArticleService articleService;
    private final PostService postService;

    public PostController(ArticleService articleService, PostService postService) {
        this.articleService = articleService;
        this.postService = postService;
    }

    @GetMapping
    public String getPostByBoardId(@RequestParam(name = "boardId", required = false, defaultValue = "World") Long id, Model model){
        PostResponseDto dto = articleService.getArticleIdsById(id);

        model.addAttribute("boardName", dto.BoardName());
        model.addAttribute("articles", dto.ArticleIds());
        return "post";
    }

    @ResponseBody
    @DeleteMapping
    public ResponseEntity<String> deletePostByBoardId(@RequestParam(name = "boardId", required = false, defaultValue = "World") Long id, Model model){
        postService.deleteBoard(id);
        return ResponseEntity.ok("deleted id:" + id);
    }
}
