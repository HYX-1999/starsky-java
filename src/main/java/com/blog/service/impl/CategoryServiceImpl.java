package com.blog.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.Article;
import com.blog.entity.Category;
import com.blog.mapper.ArticleMapper;
import com.blog.mapper.CategoryMapper;
import com.blog.model.dto.CategoryDTO;
import com.blog.model.dto.ConditionDTO;
import com.blog.model.vo.CategoryBackVO;
import com.blog.model.vo.CategoryVO;
import com.blog.model.vo.PageResult;
import com.blog.service.CategoryService;
import com.blog.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * 分类业务接口实现类
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public PageResult<CategoryBackVO> pageCategoryBackVO(ConditionDTO condition) {
        // 查询分类数量
        Long total = categoryMapper.selectCount(new LambdaQueryWrapper<Category>()
                .like(StringUtils.hasText(condition.getKeyword()), Category::getCategoryName,
                        condition.getKeyword()));
        if (total == 0) {
            return new PageResult<>();
        }
        // 分页查询分类列表
        List<CategoryBackVO> categoryList = categoryMapper.selectCategoryBackVO(PageUtils.getLimit(),
                PageUtils.getSize(), condition.getKeyword());
        return new PageResult<>(categoryList, total, PageUtils.getCurrent(), PageUtils.getSize());
    }

    @Override
    public void addCategory(CategoryDTO category) {
        // 分类是否存在
        Category existCategory = categoryMapper.selectOne(new LambdaQueryWrapper<Category>().select(Category::getId)
                                                                                        .eq(Category::getCategoryName, category.getCategoryName()));
        Assert.isNull(existCategory, category.getCategoryName() + "分类已存在");
        // 添加新分类
        Category newCategory = Category.builder()
                                 .categoryName(category.getCategoryName())
                                 .build();
        baseMapper.insert(newCategory);
    }

    @Override
    public void deleteCategory(List<Integer> categoryIdList) {
        // 分类下是否有文章
        Long count = articleMapper.selectCount(new LambdaQueryWrapper<Article>().in(Article::getCategoryId, categoryIdList));
        Assert.isFalse(count > 0, "删除失败，分类下存在文章");
        // 批量删除分类
        categoryMapper.deleteBatchIds(categoryIdList);
    }

    @Override
    public void updateCategory(CategoryDTO category) {
        // 分类是否存在
        Category existCategory = categoryMapper.selectOne(new LambdaQueryWrapper<Category>()
                .select(Category::getId)
                .eq(Category::getCategoryName, category.getCategoryName()));
        Assert.isFalse(Objects.nonNull(existCategory) && !existCategory.getId().equals(category.getId()),
                category.getCategoryName() + "分类已存在");
        // 修改分类
        Category newCategory = Category.builder()
                                       .id(category.getId())
                                       .categoryName(category.getCategoryName())
                                       .build();
        baseMapper.updateById(newCategory);
    }

    @Override
    public List<CategoryVO> listCategoryVO() {
        return categoryMapper.selectCategoryVO();
    }
}
