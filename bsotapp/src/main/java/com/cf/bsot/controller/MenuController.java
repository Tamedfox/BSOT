package com.cf.bsot.controller;

import com.cf.bsot.common.api.Result;
import com.cf.bsot.common.constant.OperateConstant;
import com.cf.bsot.model.pojo.Menu;
import com.cf.bsot.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author cf
 * @version 1.0
 * @date 2020/9/20 17:50
 */
@Api(tags = "MenuController", description = "权限信息管理")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation("添加权限信息")
    @PostMapping("")
    public Result addMenuInfo(@RequestBody @ApiParam("权限信息") Menu menu){
        Integer result = menuService.addMenuInfo(menu);
        if(OperateConstant.SUCCESS.equals(result)){
            return Result.success();
        }else {
            return Result.failed();
        }
    }

    @ApiOperation("删除指定id权限信息")
    @DeleteMapping("/{id}")
    public Result deleteMenuInfoById(@PathVariable("id") @ApiParam("权限id") Long id) {
        Integer result = menuService.deleteMenuInfoById(id);
        if(OperateConstant.SUCCESS.equals(result)){
            return Result.success();
        }else {
            return Result.failed();
        }
    }

    @ApiOperation("更新指定id用户信息")
    @PutMapping("")
    public Result updateMenuInfo(@RequestBody @ApiParam("权限信息") Menu menu){
        Integer result = menuService.updateMenuInfo(menu);
        if(OperateConstant.SUCCESS.equals(result)){
            return Result.success();
        }else {
            return Result.failed();
        }
    }

    @ApiOperation("获取指定id权限信息")
    @GetMapping("/{id}")
    public Result getMenuInfoById(@PathVariable("id") @ApiParam("权限id") Long id){
        return Result.success(menuService.getMenuInfoById(id));
    }

}
