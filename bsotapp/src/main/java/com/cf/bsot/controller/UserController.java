package com.cf.bsot.controller;

import com.cf.bsot.common.api.PageResult;
import com.cf.bsot.common.api.Result;
import com.cf.bsot.common.constant.OperateConstant;
import com.cf.bsot.model.pojo.User;
import com.cf.bsot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cf
 * @version 1.0
 * @date 2020/9/19 20:34
 */
@Api(tags = "UserController", description = "用户信息管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("获取指定id用户信息")
    @GetMapping("/detail")
    public Result getUserInfoById(@RequestParam("id") @ApiParam("用户id") Long id) {
        return Result.success(userService.getUserById(id));
    }

    @ApiOperation("分页带条件获取所有用户的信息列表")
    @GetMapping("/list/info")
    public Result pageListUsersInfo(@RequestParam(value = "page", defaultValue = "1") @ApiParam("页码") Integer page,
                                    @RequestParam(value = "size", defaultValue = "10") @ApiParam("数量") Integer size,
                                    @RequestParam(value = "username") @ApiParam("用户名") String username,
                                    @RequestParam(value = "email") @ApiParam("邮箱") String email,
                                    @RequestParam(value = "status", defaultValue = "") @ApiParam("状态") Integer status) {
        User searchUser = new User(username,email,status);
        List<User> pageUsers = userService.pageListUserInfo(page, size, searchUser);
        return Result.success(PageResult.transPage(pageUsers));
    }

    @ApiOperation("添加用户信息")
    @PostMapping("/add")
    public Result addUserInfo(@RequestBody @ApiParam("用户信息") User user) {
        Integer result = userService.addUserInfo(user);
        if (OperateConstant.SUCCESS.equals(result)) {
            return Result.success();
        } else {
            return Result.failed();
        }
    }

    @ApiOperation("删除指定id用户信息")
    @GetMapping("/del")
    public Result deleteUserInfoById(@RequestParam("id") @ApiParam("用户id") Long id) {
        Integer result = userService.deleteUserById(id);
        if (OperateConstant.SUCCESS.equals(result)) {
            return Result.success();
        } else {
            return Result.failed();
        }
    }

    @ApiOperation("更新指定id用户信息")
    @PostMapping("/update")
    public Result updateUserInfo(@RequestBody @ApiParam("用户信息") User user) {
        Integer result = userService.updateUserById(user);
        if (OperateConstant.SUCCESS.equals(result)) {
            return Result.success();
        } else {
            return Result.failed();
        }
    }

    @ApiOperation("获取指定id用户的角色信息")
    @GetMapping("/roles")
    public Result getRolesInfoByUserId(@RequestParam("id") @ApiParam("用户id") Long id) {
        return Result.success(userService.getRolesByUserId(id));
    }

    @ApiOperation("获取指定id用户的菜单信息")
    @GetMapping("/menus")
    public Result getMenusInfoByUserId(@RequestParam("id") @ApiParam("用户id") Long id) {
        return Result.success(userService.getMenusByUserId(id));
    }

    @ApiOperation("分配指定id用户的角色信息")
    @GetMapping("/role/update")
    public Result updateUserRoleRel(@RequestParam("userId") @ApiParam("用户id") Long userId,
                                    @RequestParam("roleIds") @ApiParam("所有角色id") Long[] roleIds) {
        if (userService.updateUserRoleRelInfo(userId, roleIds)) {
            return Result.success();
        } else {
            return Result.failed();
        }
    }

    @ApiOperation("获取指定用户的菜单信息")
    @GetMapping("/list/nav")
    public Result getNavListByUsername() {
        return Result.success(userService.getNavListByUsername());
    }

}
