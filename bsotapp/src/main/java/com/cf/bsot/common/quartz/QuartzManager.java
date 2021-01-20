package com.cf.bsot.common.quartz;

import com.cf.bsot.common.constant.OperateConstant;
import com.cf.bsot.common.utils.LogUtil;
import com.cf.bsot.model.pojo.QuartzJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 定时任务管理类
 *
 * @author cf
 * @version 1.0
 * @date 2020/10/11 18:01
 */
@Component
public class QuartzManager {

    private static final Logger logger = LogUtil.getQuartzLogger();

    private static final String JOB_NAME = "QUARTZ_TASK_";

    @Autowired
    private Scheduler scheduler;

    public void addJob(QuartzJob quartzJob) {
        try {
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(ExecutionJob.class).withIdentity(JOB_NAME + quartzJob.getJobId()).build();

            //通过触发器名和cron 表达式创建 Trigger
            Trigger cronTrigger = newTrigger()
                    .withIdentity(JOB_NAME + quartzJob.getJobId())
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression()))
                    .build();

            //将数据库存储的job信息存入jobDataMap中，运行时获取相关信息
            cronTrigger.getJobDataMap().put(QuartzJob.JOB_KEY, quartzJob);

            //执行定时任务
            scheduler.scheduleJob(jobDetail, cronTrigger);

            // 暂停任务
            if (OperateConstant.SUCCESS.equals(quartzJob.getIsStop())) {
                pauseJob(quartzJob);
            }
        } catch (SchedulerException e) {
            logger.error("创建定时任务失败",e.getMessage());
        }
    }

    /**
     * 删除当前定时任务
     * @param quartzJob
     */
    public void deleteJob(QuartzJob quartzJob){
        JobKey jobKey;
        try {
            jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getJobId());
            scheduler.pauseJob(jobKey);
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            logger.error("定时任务 {} 删除失败!{}",quartzJob.getJobId(), e.getMessage());
        }
    }

    /**
     * 恢复当前定时任务
     * @param quartzJob
     */
    public void resumeJob(QuartzJob quartzJob){
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME + quartzJob.getJobId());
            Trigger trigger = scheduler.getTrigger(triggerKey);
            //如果不存在则创建一个
            if(trigger == null){
                addJob(quartzJob);
            }
            JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getJobId());
            scheduler.resumeJob(jobKey);
        } catch (Exception e) {
            logger.error("定时任务 {} 恢复执行失败!{}",quartzJob.getJobId(), e.getMessage());
        }
    }

    /**
     * 立即执行定时任务
     * @param quartzJob
     */
    public void runJobNow(QuartzJob quartzJob){
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME + quartzJob.getJobId());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 如果不存在则创建一个定时任务
            if(trigger == null) {
                addJob(quartzJob);
            }
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(QuartzJob.JOB_KEY, quartzJob);
            JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getJobId());
            scheduler.triggerJob(jobKey,dataMap);
        } catch (Exception e){
            logger.error("定时任务 {} 执行失败!{}",quartzJob.getJobId(), e.getMessage());
        }
    }

    /**
     * 暂停当前定时任务
     * @param quartzJob
     */
    public void pauseJob(QuartzJob quartzJob) {
        try {
            JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getJobId());
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            logger.error("定时任务 {} 暂停执行失败!{}",quartzJob.getJobId(), e.getMessage());
        }
    }

}
