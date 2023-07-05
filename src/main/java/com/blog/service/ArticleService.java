package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.entity.Article;
import com.blog.model.dto.ArticleDTO;
import com.blog.model.dto.ConditionDTO;
import com.blog.model.vo.ArticleBackVO;
import com.blog.model.vo.ArticleInfoVO;
import com.blog.model.vo.PageResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文章业务接口
 **/
public interface ArticleService extends IService<Article> {

    /**
     * 查看后台文章列表
     *
     * @param condition 条件
     * @return 后台文章列表
     */
    PageResult<ArticleBackVO> listArticleBackVO(ConditionDTO condition);

    /**
     * 添加文章
     *
     * @param article 文章
     */
    void addArticle(ArticleDTO article);

    /**
     * 编辑文章
     *
     * @param articleId 文章id
     * @return 文章
     */
    ArticleInfoVO getInfo(Integer articleId);

    /**
     * 上传文章图片
     *
     * @param file 文件
     * @return 文章图片地址
     */
    String saveArticleImages(MultipartFile file);
}
