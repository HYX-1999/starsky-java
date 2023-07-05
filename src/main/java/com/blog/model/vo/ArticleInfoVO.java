package com.blog.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 文章信息
 */
@Data
@ApiModel(description = "文章信息")
public class ArticleInfoVO {

    /**
     * 文章id
     */
    @ApiModelProperty(value = "文章id")
    private Integer id;

    /**
     * 分类id
     */
    @JsonIgnore
    @ApiModelProperty(value = "分类id")
    private Integer cId;

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
     * 文章内容
     */
    @ApiModelProperty(value = "文章内容")
    private String content;
}
