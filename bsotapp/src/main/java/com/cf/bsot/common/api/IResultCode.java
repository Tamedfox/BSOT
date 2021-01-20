package com.cf.bsot.common.api;

/**
 * @author cf
 * @version 1.0
 * @date 2020/9/19 21:55
 */
public interface IResultCode {

    /**
     * 获取错误码
     * @return
     */
    Integer getCode();

    /**
     * 获取返回信息
     * @return
     */
    String getMessage();
}
