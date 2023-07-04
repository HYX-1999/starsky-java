package com.blog.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.blog.model.dto.ArticleDTO;
import com.blog.model.dto.ConditionDTO;
import com.blog.model.vo.ArticleBackVO;
import com.blog.model.vo.PageResult;
import com.blog.model.vo.Result;
import com.blog.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "文章模块")
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 查看后台文章列表
     *
     * @param condition 条件
     * @return {@link Result<ArticleBackVO>} 后台文章列表
     */
    @ApiOperation(value = "查看后台文章列表")
    @SaCheckPermission("blog:article:list")
    @GetMapping("/admin/article/list")
    public Result<PageResult<ArticleBackVO>> listArticleBackVO(ConditionDTO condition) {
        return Result.success(articleService.listArticleBackVO(condition));
    }

    /**
     * 添加文章
     *
     * @param article 文章信息
     * @return {@link Result<>}
     */
    @ApiOperation(value = "添加文章")
    @SaCheckPermission("blog:article:add")
    @PostMapping("/admin/article/add")
    public Result<?> addArticle(@Validated @RequestBody ArticleDTO article) {
        articleService.addArticle(article);
        return Result.success();
    }
}
