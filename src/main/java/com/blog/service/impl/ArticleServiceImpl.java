package com.blog.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.Article;
import com.blog.entity.BlogFile;
import com.blog.mapper.ArticleMapper;
import com.blog.mapper.BlogFileMapper;
import com.blog.model.dto.ArticleDTO;
import com.blog.model.dto.ConditionDTO;
import com.blog.model.vo.ArticleBackVO;
import com.blog.model.vo.ArticleInfoVO;
import com.blog.model.vo.PageResult;
import com.blog.service.ArticleService;
import com.blog.strategy.UploadStrategy;
import com.blog.utils.BeanCopyUtils;
import com.blog.utils.FileUtils;
import com.blog.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.blog.enums.FilePathEnum.ARTICLE;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UploadStrategy uploadStrategy;

    @Autowired
    private BlogFileMapper blogFileMapper;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateArticle(ArticleDTO article) {
        Article newArticle = BeanCopyUtils.copyBean(article, Article.class);
        baseMapper.updateById(newArticle);
    }

    @Override
    public ArticleInfoVO getInfo(Integer articleId) {
        ArticleInfoVO articleInfoVO = articleMapper.selectArticleInfoById(articleId);
        Assert.notNull(articleInfoVO, "没有该文章");
        return articleInfoVO;
    }

    @Override
    public String saveArticleImages(MultipartFile file) {
        String url = uploadStrategy.uploadFile(file, ARTICLE.getPath());
        try {
            // 获取文件md5值
            String md5 = FileUtils.getMd5(file.getInputStream());
            // 获取文件扩展名
            String extName = FileUtils.getExtension(file);
            BlogFile existFile = blogFileMapper.selectOne(new LambdaQueryWrapper<BlogFile>()
                    .select(BlogFile::getId)
                    .eq(BlogFile::getFileName, md5)
                    .eq(BlogFile::getFilePath, ARTICLE.getFilePath()));
            if (Objects.isNull(existFile)) {
                // 保存文件信息
                BlogFile newFile = BlogFile.builder()
                                           .fileUrl(url)
                                           .fileName(md5)
                                           .filePath(ARTICLE.getFilePath())
                                           .extendName(extName)
                                           .fileSize((int) file.getSize())
                                           .isDir(0)
                                           .build();
                blogFileMapper.insert(newFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }
}
