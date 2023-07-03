package com.blog.strategy;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传策略
 */
@Service
public interface UploadStrategy {
    /**
     * 上传文件
     *
     * @param file 文件
     * @param path 上传路径
     * @return {@link String} 文件地址
     */
    String uploadFile(MultipartFile file, String path);
}
