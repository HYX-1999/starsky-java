package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.entity.Category;
import com.blog.model.dto.CategoryDTO;

/**
 * 分类业务接口
 **/
public interface CategoryService extends IService<Category> {

    /**
     * 添加分类
     *
     * @param category 分类
     */
    void addCategory(CategoryDTO category);
}
