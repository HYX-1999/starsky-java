package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.domain.entity.Category;
import com.blog.domain.vo.CategoryVo;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryVo> selectCategoryVo();
}
