package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.entity.Article;
import com.blog.model.dto.ArticleDTO;

/**
 * 文章业务接口
 **/
public interface ArticleService extends IService<Article> {

    /**
     * 添加文章
     *
     * @param article 文章
     */
    void addArticle(ArticleDTO article);
}
