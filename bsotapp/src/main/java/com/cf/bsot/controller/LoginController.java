package com.cf.bsot.controller;

import com.cf.bsot.common.api.CustomException;
import com.cf.bsot.common.api.Result;
import com.cf.bsot.model.dto.UserLoginDTO;
import com.cf.bsot.model.pojo.User;
import com.cf.bsot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cf
 * @version 1.0
 * @date 2020/9/23 22:45
 */
@Api(tags = "LoginController", description = "登录退出管理")
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result userLogin(@RequestBody @ApiParam("用户登录验证数据") UserLoginDTO userLoginDTO, HttpServletRequest request){
        String token = null;
        try {
            token = userService.userLogin(userLoginDTO, request);
        } catch (CustomException e) {
            return Result.failed(e.getMessage());
        }
        return Result.success(token);
    }

    @ApiOperation("获取登录用户信息")
    @GetMapping("/info")
    public Result getUserInfo() {
       return Result.success(userService.getLoginUserInfo());
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result userRegister(@RequestBody @ApiParam("注册用户信息") User user){
        return Result.success(userService.addUserInfo(user));
    }

    @ApiOperation("用户退出")
    @PostMapping("/logout")
    public Result userLogout(){
        return Result.success("退出登录成功");
    }
}
