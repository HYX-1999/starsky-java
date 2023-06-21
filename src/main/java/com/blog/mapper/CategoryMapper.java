package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.Category;
import com.blog.model.vo.CategoryVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分类 Mapper
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 查询分类列表
     *
     * @return 类列表
     */
    List<CategoryVO> selectCategoryVO();
}
