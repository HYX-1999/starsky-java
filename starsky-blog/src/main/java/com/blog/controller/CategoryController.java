package com.blog.controller;

import com.blog.domain.ResponseResult;
import com.blog.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@Api(tags = "分类模块")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("getList")
    public ResponseResult getList() {
        return categoryService.getList();
    }
}
