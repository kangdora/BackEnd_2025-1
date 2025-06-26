package com.example.bcsd.controller;

import com.example.bcsd.dto.ArticleSummaryDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String getBoardPosts(@RequestParam String boardId, Model model) {
        // BoardId 기반으로 id들과 이름 가져와서 넣기

        model.addAttribute("boardName", boardName);
        model.addAttribute("articles", articles);

        return "post";
    }

}
