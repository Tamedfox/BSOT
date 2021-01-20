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
    @GetMapping("/{id}")
    public Result getUserInfoById(@PathVariable("id") @ApiParam("用户id") Long id){
        return Result.success(userService.getUserById(id));
    }

    @ApiOperation("分页带条件获取所有用户的信息列表")
    @GetMapping("/list/{page}/{size}")
    public Result<PageResult> pageListUsersInfo(@PathVariable("page") @ApiParam("页码") Integer page,
                                                @PathVariable("size") @ApiParam("数量") Integer size){
        User searchUser = new User();
        List<User> pageUsers = userService.pageListUserInfo(page,size,searchUser);
        return Result.success(PageResult.transPage(pageUsers));
    }

    @ApiOperation("添加用户信息")
    @PostMapping("")
    public Result addUserInfo(@RequestBody @ApiParam("用户信息") User user){
        Integer result = userService.addUserInfo(user);
        if(OperateConstant.SUCCESS.equals(result)){
            return Result.success();
        }else {
            return Result.failed();
        }
    }

    @ApiOperation("删除指定id用户信息")
    @DeleteMapping("/{id}")
    public Result deleteUserInfoById(@PathVariable("id") @ApiParam("用户id") Long id) {
        Integer result = userService.deleteUserById(id);
        if(OperateConstant.SUCCESS.equals(result)){
            return Result.success();
        }else {
            return Result.failed();
        }
    }

    @ApiOperation("更新指定id用户信息")
    @PutMapping("")
    public Result updateUserInfo(@RequestBody @ApiParam("用户信息") User user){
        Integer result = userService.updateUserById(user);
        if(OperateConstant.SUCCESS.equals(result)){
            return Result.success();
        }else {
            return Result.failed();
        }
    }

    @ApiOperation("获取指定id用户的角色信息")
    @GetMapping("/roles/{id}")
    public Result getRolesInfoByUserId(@PathVariable("id") @ApiParam("用户id") Long id){
        return Result.success(userService.getRolesByUserId(id));
    }

    @ApiOperation("获取指定id用户的权限信息")
    @GetMapping("/menus/{id}")
    public Result getMenusInfoByUserId(@PathVariable("id") @ApiParam("用户id") Long id){
        return Result.success(userService.getMenusByUserId(id));
    }

    @ApiOperation("分配指定id用户的角色信息")
    @PutMapping("/role/update")
    public Result updateUserRoleRel(@RequestParam("userId") @ApiParam("用户id") Long userId,
                             @RequestParam("roleIds") @ApiParam("所有角色id") Long[] roleIds){
        if(userService.updateUserRoleRelInfo(userId,roleIds)){
            return Result.success();
        } else {
            return Result.failed();
        }
    }

    @ApiOperation("获取指定用户的菜单信息")
    @GetMapping("/list/nav")
    public Result getNavListByUsername(){
        return Result.success(userService.getNavListByUsername());
    }

}
