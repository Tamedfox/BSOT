package com.cf.bsot.common.security;

import com.cf.bsot.common.api.Result;
import com.cf.bsot.common.api.ResultCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义无权限访问结果
 *
 * @author cf
 * @version 1.0
 * @date 2020/9/21 23:09
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Result result = Result.failed(ResultCode.FORBIDDEN);
        response.getWriter().println(objectMapper.writeValueAsString(result));
        response.getWriter().flush();
    }
}
