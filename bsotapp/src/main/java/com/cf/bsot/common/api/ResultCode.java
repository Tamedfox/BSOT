package com.cf.bsot.common.api;

/**
 * @author cf
 * @version 1.0
 * @date 2020/9/19 21:56
 */
public enum ResultCode implements IResultCode {

    /**
     * 操作成功
     */
    SUCCESS(200,"操作成功"),
    /**
     * 操作失败
     */
    FAILED(500,"操作失败"),
    /**
     * 无权限访问
     */
    FORBIDDEN(304,"无权限访问!"),
    /**
     * 登录后访问
     */
    NOLOGIN(305,"未登录，请登录后访问!"),
    /**
     * 用户名或密码错误
     */
    NAME_PASS_ERROR(1000,"用户名或密码错误");


    private Integer Code;
    private String message;

    ResultCode(Integer code, String message) {
        Code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return Code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
