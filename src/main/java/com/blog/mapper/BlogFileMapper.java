package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.BlogFile;
import com.blog.model.vo.FileVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文件Mapper
 */
@Repository
public interface BlogFileMapper extends BaseMapper<BlogFile> {

    /**
     * 查询后台文件列表
     *
     * @param limit    页码
     * @param size     大小
     * @param filePath 文件路径
     * @return 后台文件列表
     */
    List<FileVO> selectFileVOList(@Param("limit") Long limit, @Param("size") Long size, @Param("filePath") String filePath);
}
