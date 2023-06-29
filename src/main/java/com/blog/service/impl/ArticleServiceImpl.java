package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.Article;
import com.blog.entity.Category;
import com.blog.mapper.ArticleMapper;
import com.blog.mapper.CategoryMapper;
import com.blog.model.dto.ArticleDTO;
import com.blog.service.ArticleService;
import com.blog.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addArticle(ArticleDTO article) {
        Integer categoryId = saveArticleCategory(article);
        Article newArticle = BeanCopyUtils.copyBean(article, Article.class);
        newArticle.setCid(categoryId);
        baseMapper.insert(newArticle);
    }

    private Integer saveArticleCategory(ArticleDTO article) {
        Category category = categoryMapper.selectOne(new LambdaQueryWrapper<Category>().select(Category::getId)
                                                                                       .eq(Category::getId, article.getCid()));
        if (Objects.isNull(category)) {
            category = Category.builder().id(article.getCid()).build();
            categoryMapper.insert(category);
        }
        return category.getId();
    }
}
