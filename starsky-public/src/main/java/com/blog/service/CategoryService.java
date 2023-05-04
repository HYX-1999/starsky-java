package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.domain.ResponseResult;
import com.blog.domain.entity.Category;
import com.blog.domain.vo.CategoryVo;

import java.util.List;

public interface CategoryService extends IService<Category> {

    ResponseResult getList();
}
