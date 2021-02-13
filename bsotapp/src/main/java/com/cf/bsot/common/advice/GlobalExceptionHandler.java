package com.cf.bsot.common.advice;

import com.cf.bsot.common.api.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cf
 * @version 1.0
 * @date 2020/6/13 20:59
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result handle(Exception e) {
        return Result.failed(e.getMessage());
    }
}
