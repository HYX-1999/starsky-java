package com.blog.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.blog.annotation.VisitLogger;
import com.blog.model.dto.CategoryDTO;
import com.blog.model.vo.CategoryVO;
import com.blog.model.vo.Result;
import com.blog.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类控制器
 **/
@Api(tags = "分类模块")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 添加分类
     *
     * @param category 分类信息
     * @return {@link Result<>}
     */
    @ApiOperation(value = "添加分类")
    @SaCheckPermission("blog:category:add")
    @PostMapping("/admin/category/add")
    public Result<?> addCategory(@Validated @RequestBody CategoryDTO category) {
        categoryService.addCategory(category);
        return Result.success();
    }

    /**
     * 查看分类列表
     *
     * @return {@link Result<CategoryVO>} 分类列表
     */
    @VisitLogger(value = "文章分类")
    @ApiOperation(value = "查看分类列表")
    @GetMapping("/category/list")
    public Result<List<CategoryVO>> listCategoryVO() {
        return Result.success(categoryService.listCategoryVO());
    }
}
