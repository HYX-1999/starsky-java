package com.blog.controller;

import com.blog.domain.ResponseResult;
import com.blog.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
@Api(tags = "文章模块")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    @ApiOperation(value = "文章列表")
    public ResponseResult articleList(Integer current, Integer size) {

        return articleService.articleList(current, size);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "文章详情")
    public ResponseResult getDetail(@PathVariable Long id) {
        return articleService.getDetail(id);
    }
}
