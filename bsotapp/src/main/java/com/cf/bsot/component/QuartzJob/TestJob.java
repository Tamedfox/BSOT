package com.cf.bsot.component.QuartzJob;

import org.springframework.stereotype.Component;

/**
 * @author cf
 * @version 1.0
 * @date 2020/10/12 23:52
 */
@Component
public class TestJob {

    public void test(){
        System.out.println("QuartzJob定时任务测试");
    }

    public void testParams(String params){
        System.out.println("QuartzJob定时任务测试，参数：" + params);
    }
}
