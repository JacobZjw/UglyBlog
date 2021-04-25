package com.ugly.blog.handler;

import com.alibaba.fastjson.JSON;
import com.ugly.blog.constant.HttpStatus;
import com.ugly.blog.dto.AjaxResult;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录失败处理
 * @author JwZheng
 * @date 2021/4/25 15:41
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpStatus.FORBIDDEN);
        PrintWriter out = response.getWriter();
        AjaxResult error;
        if (exception instanceof LockedException) {
            error = AjaxResult.error(HttpStatus.FORBIDDEN, "账户被锁定，请联系管理员!");
        } else if (exception instanceof AccountExpiredException) {
            error = AjaxResult.error(HttpStatus.FORBIDDEN, "账户过期，请联系管理员!");
        } else if (exception instanceof DisabledException) {
            error = AjaxResult.error(HttpStatus.FORBIDDEN, "账户被禁用，请联系管理员!");
        } else if (exception instanceof BadCredentialsException) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            error = AjaxResult.error(HttpStatus.BAD_REQUEST, "用户名或者密码输入错误，请重新输入!");
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST);
            error = AjaxResult.error(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
        out.write(JSON.toJSONString(error));
        out.flush();
        out.close();
    }
}
