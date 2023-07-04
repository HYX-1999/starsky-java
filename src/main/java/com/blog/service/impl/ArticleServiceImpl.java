package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.Article;
import com.blog.mapper.ArticleMapper;
import com.blog.model.dto.ArticleDTO;
import com.blog.model.dto.ConditionDTO;
import com.blog.model.vo.ArticleBackVO;
import com.blog.model.vo.PageResult;
import com.blog.service.ArticleService;
import com.blog.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public PageResult<ArticleBackVO> listArticleBackVO(ConditionDTO condition) {
        Long total = articleMapper.countArticleBackVO(condition);
        if (total == 0) {
            return new PageResult<>(new ArrayList<>(), total, null, null);
        }
        List<ArticleBackVO> articleBackVOList = articleMapper.selectArticleBackVO(PageUtils.getLimit(), PageUtils.getSize(), condition);
        return new PageResult<>(articleBackVOList, total, PageUtils.getCurrent(), PageUtils.getSize());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addArticle(ArticleDTO article) {
        Article newArticle = Article.builder()
                                    .cid(article.getCid())
                                    .content(article.getContent())
                                    .cover(article.getCover())
                                    .title(article.getTitle())
                                    .build();
        baseMapper.insert(newArticle);
    }
}
