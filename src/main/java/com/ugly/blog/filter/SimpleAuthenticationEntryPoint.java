package com.ugly.blog.filter;

import com.alibaba.fastjson.JSON;
import com.ugly.blog.constant.HttpStatus;
import com.ugly.blog.dto.AjaxResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 未认证处理，解决匿名用户访问需要权限的资源时的异常，返回JSON给前端，暂时使用默认重定向到登录页
 *
 * @author JwZheng
 * @date 2021/4/19 19:43
 */
public class SimpleAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * 当用户尝试访问需要权限才能访问的REST资源而不提供Token或者Token错误或者过期时，
     * 将调用此方法发送401响应以及错误信息
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String msg = "认证失败，无法访问系统资源 ";
        AjaxResult error = AjaxResult.error(HttpStatus.UNAUTHORIZED, msg);
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED);

        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(error));
        writer.flush();
        writer.close();
    }
}
