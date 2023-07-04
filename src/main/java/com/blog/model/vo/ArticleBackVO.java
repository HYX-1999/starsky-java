package com.blog.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 文章后台VO
 */
@Data
@ApiModel(description = "文章后台VO")
public class ArticleBackVO {

    /**
     * 文章id
     */
    @ApiModelProperty(value = "文章id")
    private Integer id;

    /**
     * 文章缩略图
     */
    @ApiModelProperty(value = "文章缩略图")
    private String cover;

    /**
     * 文章标题
     */
    @ApiModelProperty(value = "文章标题")
    private String title;


    /**
     * 文章分类名
     */
    @ApiModelProperty(value = "文章分类ID")
    private Integer cid;


    /**
     * 发表时间
     */
    @ApiModelProperty(value = "发表时间")
    private LocalDateTime createTime;

}
