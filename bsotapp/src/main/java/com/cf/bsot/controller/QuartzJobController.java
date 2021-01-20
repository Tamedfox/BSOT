package com.cf.bsot.controller;

import com.cf.bsot.common.api.PageResult;
import com.cf.bsot.common.api.Result;
import com.cf.bsot.common.constant.OperateConstant;
import com.cf.bsot.model.pojo.QuartzJob;
import com.cf.bsot.model.pojo.User;
import com.cf.bsot.service.QuartzJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cf
 * @version 1.0
 * @date 2020/10/13 11:10
 */
@Api(tags = "QuartzJobController", description = "定时任务信息管理")
@RestController
@RequestMapping("/quartz")
public class QuartzJobController {

    @Autowired
    private QuartzJobService quartzJobService;

    @ApiOperation("获取指定id的定时任务信息")
    @RequestMapping("/{id}")
    public Result getQuartzJobInfoById(@PathVariable("id") @ApiParam("定时任务id") Long id){
        return Result.success(quartzJobService.getQuartzJobInfoById(id));
    }

    @ApiOperation("分页带条件获取所有定时任务信息列表")
    @GetMapping("/{page}/{size}")
    public Result<PageResult> pageListQuartzJobsInfo(@PathVariable("page") @ApiParam("页码") Integer page,
                                                @PathVariable("size") @ApiParam("数量") Integer size){
        QuartzJob searchQuartzJob = new QuartzJob();
        List<User> pageUsers = quartzJobService.pageListQuartzJobsInfo(page,size,searchQuartzJob);
        return Result.success(PageResult.transPage(pageUsers));
    }

    @ApiOperation("添加定时任务信息")
    @PostMapping("")
    public Result addUserInfo(@RequestBody @ApiParam("定时任务信息") QuartzJob quartzJob){
        Integer result = quartzJobService.addQuartzJobInfo(quartzJob);
        if(OperateConstant.SUCCESS.equals(result)){
            return Result.success();
        }else {
            return Result.failed();
        }
    }

    @ApiOperation("删除指定id定时任务信息")
    @DeleteMapping("/{id}")
    public Result deleteUserInfoById(@PathVariable("id") @ApiParam("定时任务id") Long id) {
        Integer result = quartzJobService.deleteQuartzJobInfoById(id);
        if(OperateConstant.SUCCESS.equals(result)){
            return Result.success();
        }else {
            return Result.failed();
        }
    }

    @ApiOperation("更新指定id定时任务信息")
    @PutMapping("")
    public Result updateUserInfo(@RequestBody @ApiParam("定时任务信息") QuartzJob quartzJob){
        Integer result = quartzJobService.updateQuartzJobInfoById(quartzJob);
        if(OperateConstant.SUCCESS.equals(result)){
            return Result.success();
        }else {
            return Result.failed();
        }
    }
}
