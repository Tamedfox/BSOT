package com.cf.bsot.common.quartz;

import com.cf.bsot.common.utils.LogUtil;
import com.cf.bsot.model.pojo.QuartzJob;
import com.cf.bsot.service.QuartzJobService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author cf
 * @version 1.0
 * @date 2020/10/11 17:22
 */
@Component
public class QuartzJobEnvironment implements ApplicationRunner {

    private static final Logger logger = LogUtil.getQuartzLogger();

    @Autowired
    private QuartzJobService quartzJobService;

    @Autowired
    private QuartzManager quartzManager;

    /**
     * 项目启动时获取相关定时任务加入系统
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("--------------定时任务开始注入--------------");
        //获取数据库存储的所有定时任务
        List<QuartzJob> quartzJobInfoList = quartzJobService.getAllOpenQuartzJobInfo();
        for (QuartzJob quartzJob : quartzJobInfoList) {
            quartzManager.addJob(quartzJob);
            logger.info("添加定时任务id:{},名称: {} 成功!",quartzJob.getJobId(),quartzJob.getJobName());
        }
        //注入获取的所有定时任务
        logger.info("--------------定时任务注入成功--------------");
    }
}
