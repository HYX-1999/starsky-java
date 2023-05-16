package com.blog.domain.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2023-05-12 15:56:54
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    
    private String id;
    
    private Date createdAt;
    
    private Date updatedAt;
    
    private Date deletedAt;
    
    private String username;
    
    private String password;
    
    private Integer role;
}

