package com.blog.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blog.entity.User;
import com.blog.mapper.UserMapper;
import com.blog.model.dto.LoginDTO;
import com.blog.model.dto.RegisterDTO;
import com.blog.service.LoginService;
import com.blog.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(LoginDTO login) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().select(User::getId)
                                                                       .eq(User::getUsername, login.getUsername())
                                                                       .eq(User::getPassword, SecurityUtils.sha256Encrypt(login.getPassword())));
        Assert.notNull(user, "用户名不存在或密码错误");
        StpUtil.checkDisable(user.getId());
        StpUtil.login(user.getId());
        return StpUtil.getTokenValue();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(RegisterDTO register) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().select(User::getUsername)
                                                                       .eq(User::getUsername, register.getUsername()));
        Assert.isNull(user, "用户名已注册");
        User newUser = User.builder()
                           .username(register.getUsername())
                           .role(register.getRole())
                           .password(SecurityUtils.sha256Encrypt(register.getPassword()))
                           .build();
        userMapper.insert(newUser);
    }
}
