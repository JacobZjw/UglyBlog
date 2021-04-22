package com.ugly.blog.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ugly.blog.constant.Constants;
import com.ugly.blog.constant.HttpStatus;
import com.ugly.blog.dto.AjaxResult;
import com.ugly.blog.dto.LoginBody;
import com.ugly.blog.dto.LoginUser;
import com.ugly.blog.exception.CustomException;
import com.ugly.blog.util.TokenUtils;
import com.ugly.blog.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * 认证过滤器
 *
 * @author JwZheng
 * @date 2021/4/19 18:02
 */
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final ThreadLocal<Boolean> rememberMe = new ThreadLocal<>();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        HttpSession session = request.getSession();
        //获取session中的验证码
        String sessionValidateCode = (String) session.getAttribute(KAPTCHA_SESSION_KEY);
        session.removeAttribute(KAPTCHA_SESSION_KEY);

        try {
            // 从输入流中获取到登录的信息
            LoginBody loginBody = new ObjectMapper().readValue(request.getInputStream(), LoginBody.class);
            String code = loginBody.getCode();
            if (Utils.isNull(code) || !code.equals(sessionValidateCode)) {
                throw new AuthenticationServiceException("验证码错误");
            }
            rememberMe.set(loginBody.getRememberMe());
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    loginBody.getUsername(), loginBody.getPassword());
            return this.getAuthenticationManager().authenticate(authRequest);
        } catch (IOException e) {
            log.error("无法读取登录信息");
            throw new CustomException(HttpStatus.ERROR, "无法读取登录信息");
        }
    }


    /**
     * 如果验证成功，就生成token并返回
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authentication) throws IOException {
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        loginUser.setIsRememberMe(rememberMe.get());
        String token = TokenUtils.createToken(loginUser);

        // Http Response Header 中返回 Token
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader(Constants.TOKEN_HEADER, token);
        AjaxResult result = AjaxResult.success("登录成功");
        result.put(Constants.TOKEN_HEADER, token);
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(result));
        writer.flush();

    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        AjaxResult error;
        if (exception instanceof LockedException) {
            error = AjaxResult.error(HttpStatus.FORBIDDEN, "账户被锁定，请联系管理员!");
        } else if (exception instanceof AccountExpiredException) {
            error = AjaxResult.error(HttpStatus.FORBIDDEN, "账户过期，请联系管理员!");
        } else if (exception instanceof DisabledException) {
            error = AjaxResult.error(HttpStatus.FORBIDDEN, "账户被禁用，请联系管理员!");
        } else if (exception instanceof BadCredentialsException) {
            error = AjaxResult.error(HttpStatus.BAD_REQUEST, "用户名或者密码输入错误，请重新输入!");
        } else {
            error = AjaxResult.error(HttpStatus.BAD_REQUEST, exception.getMessage());
        }

        out.write(JSON.toJSONString(error));
        out.flush();
        out.close();
    }
}
