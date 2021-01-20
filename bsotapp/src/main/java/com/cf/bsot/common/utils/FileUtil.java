package com.cf.bsot.common.utils;

import java.io.File;

/**
 * @author cf
 * @version 1.0
 * @date 2020/10/11 10:48
 */
public class FileUtil {

    /** Windows路径分隔符 */
    private static final char WINDOWS_SEPARATOR = '\\';

    /**
     * 判断是否为windows环境
     * @return
     */
    public static boolean isWindows(){
        return WINDOWS_SEPARATOR == File.separatorChar;
    }

}
