package com.cf.bsot.controller;

import com.cf.bsot.common.api.Result;
import com.cf.bsot.common.constant.OperateConstant;
import com.cf.bsot.model.pojo.Role;
import com.cf.bsot.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author cf
 * @version 1.0
 * @date 2020/9/20 17:42
 */
@Api(tags = "RoleController", description = "用户角色信息管理")
@RestController
@RequestMapping("/role")
public class RoleController {
    
    @Autowired
    private RoleService roleService;
    
    @ApiOperation("添加用户角色信息")
    @PostMapping("")
    public Result addRoleInfo(@RequestBody @ApiParam("用户角色信息") Role role){
        Integer result = roleService.insertRoleInfo(role);
        if(OperateConstant.SUCCESS.equals(result)){
            return Result.success();
        }else {
            return Result.failed();
        }
    }

    @ApiOperation("删除指定id用户角色信息")
    @DeleteMapping("/{id}")
    public Result deleteRoleInfoById(@PathVariable("id") @ApiParam("用户角色id") Long id){
        Integer result = roleService.deleteRoleInfoById(id);
        if(OperateConstant.SUCCESS.equals(result)){
            return Result.success();
        }else {
            return Result.failed();
        }
    }

    @ApiOperation("更新指定id用户角色信息")
    @PutMapping("")
    public Result updateRoleInfo(@RequestBody @ApiParam("用户角色信息") Role role){
        Integer result = roleService.updateRoleInfo(role);
        if(OperateConstant.SUCCESS.equals(result)){
            return Result.success();
        }else {
            return Result.failed();
        }
    }

    @ApiOperation("查询指定id用户角色信息")
    @GetMapping("/{id}")
    public Result getUserInfoBRoleId(@PathVariable("id") @ApiParam("用户角色id") Long id){
        return Result.success(roleService.getRoleInfoById(id));
    }

    @ApiOperation("查询指定id角色的权限信息")
    @GetMapping("/menus/{id}")
    public Result getMenusInfoByRoleId(@PathVariable("id") @ApiParam("用户角色id") Long id){
        return Result.success(roleService.getMenusByRoleId(id));
    }

    @ApiOperation("分配指定id角色的权限信息")
    @PutMapping("/menu/update")
    public Result updateRoleMenuRel(@RequestParam("roleId") @ApiParam("角色id") Long roleId,
                             @RequestParam("menuIds") @ApiParam("所有权限id") Long[] menuIds){
        if(roleService.updateRoleMenuRelInfo(roleId,menuIds)){
            return Result.success();
        } else {
            return Result.failed();
        }
    }
    
}
