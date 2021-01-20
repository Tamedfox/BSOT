package com.cf.bsot.config;

import com.cf.bsot.common.quartz.QuartzJobFactory;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 定时任务调度配置
 *
 * @author cf
 * @version 1.0
 * @date 2020/10/11 15:48
 */
@Configuration
public class QuartzConfig {

    @Autowired
    private QuartzJobFactory jobFactory;

    /**
     * 注入scheduler到spring
     * @param quartzJobFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "scheduler")
    public Scheduler scheduler(QuartzJobFactory quartzJobFactory) throws Exception {
        SchedulerFactoryBean factoryBean=new SchedulerFactoryBean();
        factoryBean.setJobFactory(jobFactory);
        factoryBean.afterPropertiesSet();
        Scheduler scheduler=factoryBean.getScheduler();
        scheduler.start();
        return scheduler;
    }

}
