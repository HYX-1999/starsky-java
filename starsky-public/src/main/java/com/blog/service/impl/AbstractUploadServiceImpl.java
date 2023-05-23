package com.blog.service.impl;

import com.aliyun.oss.ServiceException;
import com.blog.service.UploadService;
import com.blog.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * 抽象上传模板
 *
 * @author ican
 */
@Service
public abstract class AbstractUploadServiceImpl implements UploadService {
    @Override
    public String uploadFile(MultipartFile file, String path) {
        try {
            String md5 = FileUtils.getMd5(file.getInputStream());
            String extName = FileUtils.getExtension(file);
            String fileName = md5 + "." + extName;
            if (!exists(path + fileName)) {
                upload(path, fileName, file.getInputStream());
            }
            return getFileAccessUrl(path + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("文件上传失败");
        }
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath 文件路径
     * @return {@link Boolean}
     */
    public abstract Boolean exists(String filePath);

    /**
     * 上传
     *
     * @param path        路径
     * @param fileName    文件名
     * @param inputStream 输入流
     * @throws IOException io异常
     */
    public abstract void upload(String path, String fileName, InputStream inputStream) throws IOException;

    /**
     * 获取文件访问url
     *
     * @param filePath 文件路径
     * @return {@link String} 文件url
     */
    public abstract String getFileAccessUrl(String filePath);
}
