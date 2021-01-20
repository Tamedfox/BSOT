package com.cf.bsot.common.api;

/**
 * 自定义runtime异常类
 *
 * @author cf
 * @version 1.0
 * @date 2020/9/25 22:13
 */
public class CustomException extends RuntimeException {

    private Integer code;
    private String message;

    public CustomException(IResultCode resultCode){
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public CustomException(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
