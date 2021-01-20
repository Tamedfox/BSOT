package com.cf.bsot.controller;

import com.cf.bsot.common.api.Result;
import com.cf.bsot.common.utils.OnlineCounterUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页展示控制层
 *
 * @author cf
 * @version 1.0
 * @date 2020/10/9 23:15
 */
@Api(tags = "DashboardController", description = "首页展示数据管理")
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @ApiOperation("获取当前在线的活跃用户")
    @GetMapping("/online")
    public Result getOnlineUserCount(){
        return Result.success(OnlineCounterUtil.getOnlineUserCount());
    }

}
