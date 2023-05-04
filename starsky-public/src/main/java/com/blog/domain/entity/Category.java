package com.blog.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Category)表实体类
 *
 * @author makejava
 * @since 2023-05-04 16:35:39
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    
    private Integer id;
    
    private String name;
}

