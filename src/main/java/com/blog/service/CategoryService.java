package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.entity.Category;
import com.blog.model.dto.CategoryDTO;
import com.blog.model.dto.ConditionDTO;
import com.blog.model.vo.CategoryBackVO;
import com.blog.model.vo.CategoryVO;
import com.blog.model.vo.PageResult;

import java.util.List;

/**
 * 分类业务接口
 **/
public interface CategoryService extends IService<Category> {

    /**
     * 查看后台分类列表
     *
     * @param condition 查询条件
     * @return 后台分类列表
     */
    PageResult<CategoryBackVO> pageCategoryBackVO(ConditionDTO condition);

    /**
     * 添加分类
     *
     * @param category 分类
     */
    void addCategory(CategoryDTO category);

    /**
     * 删除分类
     *
     * @param categoryIdList 分类id集合
     */
    void deleteCategory(List<Integer> categoryIdList);

    /**
     * 修改分类
     *
     * @param category 分类
     */
    void updateCategory(CategoryDTO category);

    /**
     * 查看分类列表
     *
     * @return 分类列表
     */
    List<CategoryVO> listCategoryVO();
}
