package com.cf.bsot.common.logger;

import com.cf.bsot.common.constant.LogConstant;
import com.cf.bsot.common.utils.FileUtil;

/**
 * 自定义日志环境变量
 *
 * @author cf
 * @version 1.0
 * @date 2020/10/11 10:38
 */
public class LoggerEnvironment {

    public static void initLoggerEnvironment(){
        initLoggerConfigParams();
    }

    private static void initLoggerConfigParams(){
        if(FileUtil.isWindows()){
            System.setProperty(LogConstant.BSOT_LOG_PATH,"D:\\applog\\BSOT");
        } else {
            System.setProperty(LogConstant.BSOT_LOG_PATH, "/applog/BSOT");
        }
    }

}
