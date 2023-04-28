package com.blog.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.jdbc.Null;

/**
 * 文章表(Article)表实体类
 *
 * @author makejava
 * @since 2023-04-19 16:11:08
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    
    private Integer id;
    
    private Date createdAt;
    
    private Date updatedAt;

    @TableLogic(value = "NULL", delval = "unix_timestamp(now())")
    private Date deletedAt;
    
    private String title;
    //分类ID
    private Integer cid;
    
    private String content;
    
    private String img;
    //摘要
    private String summary;
}

