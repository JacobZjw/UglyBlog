package com.ugly.blog.handler;

import com.alibaba.fastjson.JSON;
import com.ugly.blog.constant.HttpStatus;
import com.ugly.blog.dto.AjaxResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 认证失败处理
 *
 * @author JwZheng
 * @date 2021/4/25 15:33
 */
public class SimpleAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(HttpStatus.FORBIDDEN);
        AjaxResult result = AjaxResult.error("认证失败");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(result));
        writer.flush();
        writer.close();
    }
}
