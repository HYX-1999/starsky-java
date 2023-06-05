package com.blog.controller;

import com.blog.model.dto.RegisterDTO;
import com.blog.model.vo.Result;
import com.blog.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "登录模块")
@RestController
//@RequestMapping("")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public Result<?> register(@Validated @RequestBody RegisterDTO register) {
        loginService.register(register);
        return Result.success();
    }
}
