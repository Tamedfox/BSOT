package com.cf.bsot.common.api;

/**
 * @author cf
 * @version 1.0
 * @date 2020/9/19 21:53
 */
public class Result<T> {

    private Integer code;

    private String message;

    protected T data;

    protected Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     *  通用操作成功
     * @return
     */
    public static <T> Result success(){
        return new Result(ResultCode.SUCCESS.getCode(),null,null);
    }

    /**
     * 获得数据，成功并返回结果
     * @param date 需要返回的数据
     * @return
     */
    public static <T> Result success(T date){
        return new Result(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),date);
    }

    /**
     *  获取数据，返回的信息，成功并返回结果
     * @param message 需要返回的提示信息
     * @param date 需要返回的数据
     * @return
     */
    public static <T> Result success(String message, T date){
        return new Result(ResultCode.SUCCESS.getCode(),message,date);
    }

    /**
     *  通用操作失败
     * @return
     */
    public static <T> Result failed(){
        return new Result(ResultCode.FAILED.getCode(),"操作失败",null);
    }

    /**
     *  返回失败，无数据
     * @param resultCode 返回码
     * @return
     */
    public static <T> Result failed(IResultCode resultCode){
        return new Result(resultCode.getCode(),resultCode.getMessage(),null);
    }

    /**
     *  返回失败，自定义提示信息
     * @param message 自定义提示信息
     * @return
     */
    public static <T> Result failed(String message){
        return new Result(ResultCode.FAILED.getCode(),message,null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
