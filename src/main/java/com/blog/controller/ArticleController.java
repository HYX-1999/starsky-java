package com.blog.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.blog.model.dto.ArticleDTO;
import com.blog.model.dto.ConditionDTO;
import com.blog.model.vo.ArticleBackVO;
import com.blog.model.vo.ArticleInfoVO;
import com.blog.model.vo.PageResult;
import com.blog.model.vo.Result;
import com.blog.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 获取文章详情
     *
     * @param articleId 文章id
     * @return {@link Result<ArticleInfoVO>} 后台文章
     */
    @ApiOperation(value = "编辑文章")
    @SaCheckPermission("blog:article:getInfo")
    @GetMapping("/admin/article/getInfo/{articleId}")
    public Result<ArticleInfoVO> getInfo(@PathVariable("articleId") Integer articleId) {
        return Result.success(articleService.getInfo(articleId));
    }

    /**
     * 上传文章图片
     *
     * @param file 文件
     * @return {@link Result<String>} 文章图片地址
     */
    @ApiOperation(value = "上传文章图片")
    @ApiImplicitParam(name = "file", value = "文章图片", required = true, dataType = "MultipartFile")
    @SaCheckPermission("blog:article:upload")
    @PostMapping("/admin/article/upload")
    public Result<String> saveArticleImages(@RequestParam("file") MultipartFile file) {
        return Result.success(articleService.saveArticleImages(file));
    }
}
