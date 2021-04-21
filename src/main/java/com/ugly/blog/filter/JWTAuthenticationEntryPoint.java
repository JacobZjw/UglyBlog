package com.ugly.blog.filter;

import com.ugly.blog.constant.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JwZheng
 * @date 2021/4/19 19:43
 */
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * 当用户尝试访问需要权限才能的REST资源而不提供Token或者Token错误或者过期时，
     * 将调用此方法发送401响应以及错误信息
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String msg = "请求访问：" + request.getRequestURI() + "，认证失败，无法访问系统资源 ";
        response.sendError(HttpStatus.UNAUTHORIZED, msg);
    }
}
