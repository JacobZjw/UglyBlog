package com.ugly.blog.handler;

import com.alibaba.fastjson.JSON;
import com.ugly.blog.constant.Constants;
import com.ugly.blog.dto.AjaxResult;
import com.ugly.blog.dto.LoginUser;
import com.ugly.blog.util.TokenUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录成功处理
 * @author JwZheng
 * @date 2021/4/23 0:25
 */
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String token = TokenUtils.createToken(loginUser);

        response.setContentType("application/json; charset=UTF-8");
        response.setHeader(Constants.TOKEN_HEADER, token);
        AjaxResult result = AjaxResult.success("登录成功");
        result.put(Constants.TOKEN_HEADER, token);
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(result));
        writer.flush();
    }
}
