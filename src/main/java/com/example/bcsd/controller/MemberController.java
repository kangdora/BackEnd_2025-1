package com.example.bcsd.controller;

import com.example.bcsd.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final ArticleService articleService;

    @Autowired
    public MemberController(ArticleService articleService) {
        this.articleService = articleService;
    }
}
