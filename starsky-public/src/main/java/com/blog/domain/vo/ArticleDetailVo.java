package com.blog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailVo {
    private Integer id;

    private String title;

    private String summary;

    private Integer cid;

    private String content;

    private String img;

    private Date createdAt;

    private Date updatedAt;
}
