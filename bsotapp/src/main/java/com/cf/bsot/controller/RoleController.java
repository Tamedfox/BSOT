package com.cf.bsot.controller;

import com.cf.bsot.common.api.PageResult;
import com.cf.bsot.common.api.Result;
import com.cf.bsot.common.constant.OperateConstant;
import com.cf.bsot.model.dto.RoleDTO;
import com.cf.bsot.model.pojo.Role;
import com.cf.bsot.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/add")
    public Result addRoleInfo(@RequestBody @ApiParam("用户角色信息") Role role){
        Integer result = roleService.insertRoleInfo(role);
        if(OperateConstant.SUCCESS.equals(result)){
            return Result.success();
        }else {
            return Result.failed();
        }
    }

    @ApiOperation("获取角色信息列表")
    @GetMapping("/list")
    public Result ListRoleInfo() {
        return Result.success(roleService.listRoleInfo());
    }

    @ApiOperation("分页带条件获取角色信息列表")
    @GetMapping("/list/info")
    public Result pageListRoleInfo(@RequestParam(value = "page", defaultValue = "1") @ApiParam("页码") Integer page,
                                   @RequestParam(value = "size", defaultValue = "10") @ApiParam("数量") Integer size,
                                   @RequestParam(value = "name") @ApiParam("姓名") String name,
                                   @RequestParam(value = "status") @ApiParam("状态") Integer status){
        Role searchRole = new Role(name,status);
        List<Role> pageRoles = roleService.pageListUserInfo(page,size,searchRole);
        return Result.success(PageResult.transPage(pageRoles));
    }

    @ApiOperation("删除指定id用户角色信息")
    @GetMapping("/del")
    public Result deleteRoleInfoById(@RequestParam("id") @ApiParam("用户角色id") Long id){
        Integer result = roleService.deleteRoleInfoById(id);
        if(OperateConstant.SUCCESS.equals(result)){
            return Result.success();
        }else {
            return Result.failed();
        }
    }

    @ApiOperation("更新指定id用户角色信息")
    @PostMapping("/update")
    public Result updateRoleInfo(@RequestBody @ApiParam("用户角色信息") RoleDTO role){
        Integer result = roleService.updateRoleInfo(role);
        boolean menuIdsUpdateResult = roleService.updateRoleMenuRelInfo(role.getId(), role.getMenuIds());
        if(OperateConstant.SUCCESS.equals(result)  && menuIdsUpdateResult){
            return Result.success();
        }else {
            return Result.failed();
        }
    }

    @ApiOperation("查询指定id用户角色信息")
    @GetMapping("/detail")
    public Result getUserInfoBRoleId(@RequestParam("id") @ApiParam("用户角色id") Long id){
        return Result.success(roleService.getRoleInfoById(id));
    }

    @ApiOperation("查询指定id角色的权限信息")
    @GetMapping("/menus")
    public Result getMenusInfoByRoleId(@RequestParam("id") @ApiParam("用户角色id") Long id){
        return Result.success(roleService.getMenusByRoleId(id));
    }

    @ApiOperation("分配指定id角色的权限信息")
    @GetMapping("/menu/update")
    public Result updateRoleMenuRel(@RequestParam("roleId") @ApiParam("角色id") Long roleId,
                             @RequestParam("menuIds") @ApiParam("所有权限id") Long[] menuIds){
        if(roleService.updateRoleMenuRelInfo(roleId,menuIds)){
            return Result.success();
        } else {
            return Result.failed();
        }
    }
    
}
