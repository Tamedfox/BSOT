package com.cf.bsot.controller;

import com.cf.bsot.common.api.PageResult;
import com.cf.bsot.common.api.Result;
import com.cf.bsot.common.constant.OperateConstant;
import com.cf.bsot.model.pojo.QuartzJob;
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
    @GetMapping("/detail")
    public Result getQuartzJobInfoById(@RequestParam("id") @ApiParam("定时任务id") Long id){
        return Result.success(quartzJobService.getQuartzJobInfoById(id));
    }

    @ApiOperation("分页带条件获取所有定时任务信息列表")
    @GetMapping("/list/info")
    public Result<PageResult> pageListQuartzJobsInfo(@RequestParam(value = "page", defaultValue = "1") @ApiParam("页码") Integer page,
                                                     @RequestParam(value = "size", defaultValue = "10") @ApiParam("数量") Integer size){
        QuartzJob searchQuartzJob = new QuartzJob();
        List<QuartzJob> pageUsers = quartzJobService.pageListQuartzJobsInfo(page,size,searchQuartzJob);
        return Result.success(PageResult.transPage(pageUsers));
    }

    @ApiOperation("添加定时任务信息")
    @PostMapping("/add")
    public Result addQuartzJobInfo(@RequestBody @ApiParam("定时任务信息") QuartzJob quartzJob){
        Integer result = quartzJobService.addQuartzJobInfo(quartzJob);
        if(OperateConstant.SUCCESS.equals(result)){
            return Result.success();
        }else {
            return Result.failed();
        }
    }

    @ApiOperation("删除指定id定时任务信息")
    @GetMapping("/del")
    public Result deleteUserInfoById(@RequestParam("id") @ApiParam("定时任务id") Long id) {
        Integer result = quartzJobService.deleteQuartzJobInfoById(id);
        if(OperateConstant.SUCCESS.equals(result)){
            return Result.success();
        }else {
            return Result.failed();
        }
    }

    @ApiOperation("更新指定id定时任务信息")
    @PostMapping("/update")
    public Result updateQuartzJobInfo(@RequestBody @ApiParam("定时任务信息") QuartzJob quartzJob){
        Integer result = quartzJobService.updateQuartzJobInfoById(quartzJob);
        if(OperateConstant.SUCCESS.equals(result)){
            return Result.success();
        }else {
            return Result.failed();
        }
    }

    @ApiOperation("暂停指定id定时任务")
    @GetMapping("/pause")
    public Result pauseQuartzJob(@RequestParam("id") @ApiParam("定时任务id") Long id) {
        if(!quartzJobService.deleteRunningJob(id)){
            return Result.success();
        }
        return Result.failed();
    }

    @ApiOperation("执行指定id定时任务")
    @GetMapping("/run")
    public Result runQuartzJob(@RequestParam("id") @ApiParam("定时任务id") Long id) {
        if(quartzJobService.runningQuartzJob(id)){
            return Result.success();
        }
        return Result.failed();
    }
}
