package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.Article;
import com.blog.model.dto.ConditionDTO;
import com.blog.model.vo.ArticleBackVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文章 Mapper
 **/
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 查询后台文章数量
     *
     * @param condition 条件
     * @return 文章数量
     */
    Long countArticleBackVO(@Param("condition") ConditionDTO condition);

    /**
     * 查询后台文章列表
     *
     * @param limit     页码
     * @param size      大小
     * @param condition 条件
     * @return 后台文章列表
     */
    List<ArticleBackVO> selectArticleBackVO(@Param("limit") Long limit, @Param("size") Long size, @Param("condition") ConditionDTO condition);
}
