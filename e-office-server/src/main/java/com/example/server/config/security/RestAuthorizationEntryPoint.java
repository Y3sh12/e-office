package com.example.server.config.security;

import com.example.server.pojo.RespBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author dengyinshan
 * @version 1.0.0
 * @createTime 2021/10/31 23:41
 * @Description 未登录或者token失效时调用访问接口时，自定义的返回结果
 */
@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        RespBean respBean = new RespBean(401, "尚未登录，请先登录！",null);
        response.setStatus(401);
        writer.write(new ObjectMapper().writeValueAsString(respBean));
        writer.flush();
        writer.close();
    }
}
