package com.blog.service;

import org.springframework.web.multipart.MultipartFile;

public interface BlogFileService {

    /**
     * 上传文件
     *
     * @param file 文件
     * @param path 文件路径
     */
    String uploadFile(MultipartFile file, String path);
}
