package com.blog.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.BlogFile;
import com.blog.mapper.BlogFileMapper;
import com.blog.service.BlogFileService;
import com.blog.strategy.UploadStrategy;
import com.blog.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件业务接口实现类
 */
@Service
public class BlogFileServiceImpl extends ServiceImpl<BlogFileMapper, BlogFile> implements BlogFileService {

    @Autowired
    private BlogFileMapper blogFileMapper;

    @Autowired
    private UploadStrategy uploadStrategy;

    @Override
    public void uploadFile(MultipartFile file, String path) {
        try {
            String uploadPath = "/" + path;
            // 上传文件
            String url = uploadStrategy.uploadFile(file, path);
            // 获取文件md5值
            String md5 = FileUtils.getMd5(file.getInputStream());
            // 获取文件扩展名
            String extName = FileUtils.getExtension(file);
            BlogFile existFile = blogFileMapper.selectOne(new LambdaQueryWrapper<BlogFile>()
                    .select(BlogFile::getId)
                    .eq(BlogFile::getFileName, md5)
                    .eq(BlogFile::getFilePath, uploadPath));
            Assert.isNull(existFile, "文件已存在");
            // 保存文件信息
            BlogFile newFile = BlogFile.builder()
                                       .fileUrl(url)
                                       .fileName(md5)
                                       .filePath(uploadPath)
                                       .extendName(extName)
                                       .fileSize((int) file.getSize())
                                       .isDir(0)
                                       .build();
            blogFileMapper.insert(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
