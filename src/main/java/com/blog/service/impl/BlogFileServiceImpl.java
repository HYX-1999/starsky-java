package com.blog.service.impl;

import com.blog.service.BlogFileService;
import com.blog.strategy.UploadStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件业务接口实现类
 */
public class BlogFileServiceImpl implements BlogFileService {

    @Autowired
    private UploadStrategy uploadStrategy;

    @Override
    public String uploadFile(MultipartFile file, String path) {
        String uploadPath = "/".equals(path) ? path : path + "/";
        return uploadStrategy.uploadFile(file, uploadPath);
    }
}
