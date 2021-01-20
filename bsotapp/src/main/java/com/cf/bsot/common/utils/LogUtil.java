package com.cf.bsot.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cf
 * @version 1.0
 * @date 2020/10/10 23:35
 */
public class LogUtil {

    private static Logger getLogger(String name){
        return LoggerFactory.getLogger(name);
    }

    public static Logger getDebugLog(){
        return getLogger("debugLogger");
    }

    public static Logger getQuartzLogger(){
        return getLogger("quartzLogger");
    }
}
