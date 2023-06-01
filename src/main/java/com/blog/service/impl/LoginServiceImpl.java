package com.blog.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blog.entity.User;
import com.blog.mapper.UserMapper;
import com.blog.model.dto.RegisterDTO;
import com.blog.service.LoginService;
import com.blog.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(RegisterDTO register) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().select(User::getUsername).eq(User::getUsername, register.getUsername()));
        Assert.isNull(user, "用户名已注册");
        User newUser = User.builder().username(register.getUsername()).role(register.getRole()).password(SecurityUtils.sha256Encrypt(register.getPassword())).build();
        userMapper.insert(newUser);
    }
}
