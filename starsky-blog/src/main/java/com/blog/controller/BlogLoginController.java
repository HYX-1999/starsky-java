package com.blog.controller;

import com.blog.domain.ResponseResult;
import com.blog.domain.entity.User;
import com.blog.enums.AppHttpCodeEnum;
import com.blog.exception.SystemException;
import com.blog.service.BlogLoginService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "登录模块")
public class BlogLoginController {

    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUsername())) {
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult logout() {
        return blogLoginService.logout();
    }
}
