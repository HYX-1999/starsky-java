package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.Article;
import org.springframework.stereotype.Repository;

/**
 * 文章 Mapper
 **/
@Repository
public interface ArticleMapper extends BaseMapper<Article> {
}
