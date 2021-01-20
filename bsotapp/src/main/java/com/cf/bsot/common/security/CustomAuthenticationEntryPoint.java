package com.cf.bsot.common.security;

import com.cf.bsot.common.api.Result;
import com.cf.bsot.common.api.ResultCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 匿名用户访问无权限资源结果
 *
 * @author cf
 * @version 1.0
 * @date 2020/9/21 23:11
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Result result = Result.failed(ResultCode.NOLOGIN);
        response.getWriter().println(objectMapper.writeValueAsString(result));
        response.getWriter().flush();
    }
}
