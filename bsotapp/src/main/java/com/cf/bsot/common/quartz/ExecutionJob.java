package com.cf.bsot.common.quartz;

import com.cf.bsot.common.constant.OperateConstant;
import com.cf.bsot.common.utils.LogUtil;
import com.cf.bsot.common.utils.SpringContextUtil;
import com.cf.bsot.model.pojo.QuartzJob;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author cf
 * @version 1.0
 * @date 2020/10/11 18:04
 */
public class ExecutionJob extends QuartzJobBean {

    private static final Logger logger = LogUtil.getQuartzLogger();

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        //获取数据库中的定时任务信息
        QuartzJob quartzJob = (QuartzJob) context.getMergedJobDataMap().get(QuartzJob.JOB_KEY);
        logger.info("------定时任务{}开始执行，任务名称：{}------",quartzJob.getJobId(),quartzJob.getJobName());
        long startTime = System.currentTimeMillis();
        try {
            excuteJobMethod(quartzJob);
            long costTime = (System.currentTimeMillis() - startTime) / 1000;
            logger.info("------定时任务{}执行完毕，任务名称：{}, 耗时：{}秒------",quartzJob.getJobId(),quartzJob.getJobName(),costTime);
        } catch (Exception e) {
            logger.error("------定时任务{}执行失败！！！ 任务名称：{}------",quartzJob.getJobId(),quartzJob.getJobName());
            logger.error("错误信息：{}",e.getMessage());
            if(quartzJob.getPauseAfterFailure()){
                quartzJob.setIsStop(OperateConstant.FAIL);
                //更新job状态
            }
        }

    }

    private void excuteJobMethod(QuartzJob quartzJob) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String beanName = quartzJob.getBeanName();
        String methodStr = quartzJob.getMethodName();
        String params = quartzJob.getParams();
        SpringContextUtil.getInstance().getApplicationAllInfo();
        Object target = SpringContextUtil.getInstance().getBean(beanName);
        Method method;
        if(StringUtils.isNotBlank(params)){
            method = target.getClass().getDeclaredMethod(methodStr,String.class);
        } else {
            method = target.getClass().getDeclaredMethod(methodStr);
        }
        ReflectionUtils.makeAccessible(method);
        if(StringUtils.isNotBlank(params)){
            method.invoke(target,params);
        } else {
            method.invoke(target);
        }
    }

}
