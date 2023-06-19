package com.blog.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.Category;
import com.blog.mapper.CategoryMapper;
import com.blog.model.dto.CategoryDTO;
import com.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 分类业务接口实现类
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void addCategory(CategoryDTO category) {
        // 分类是否存在
        Category existCategory = categoryMapper.selectOne(new LambdaQueryWrapper<Category>().select(Category::getId)
                                                                                        .eq(Category::getName, category.getName()));
        Assert.isNull(existCategory, category.getName() + "分类已存在");
        // 添加新分类
        Category newCategory = Category.builder()
                                 .name(category.getName())
                                 .build();
        baseMapper.insert(newCategory);
    }
}
