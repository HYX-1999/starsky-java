package com.blog.controller;

import com.blog.domain.ResponseResult;
import com.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    public ResponseResult articleList(Integer current, Integer size) {

        return articleService.articleList(current, size);
    }

    @GetMapping("/{id}")
    public ResponseResult getDetail(@PathVariable Long id) {
        return articleService.getDetail(id);
    }
}
