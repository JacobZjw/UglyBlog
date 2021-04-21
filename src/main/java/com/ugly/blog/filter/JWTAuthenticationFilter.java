package com.ugly.blog.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ugly.blog.constant.Constants;
import com.ugly.blog.constant.HttpStatus;
import com.ugly.blog.dto.LoginBody;
import com.ugly.blog.dto.LoginUser;
import com.ugly.blog.exception.CustomException;
import com.ugly.blog.exception.user.CaptchaException;
import com.ugly.blog.exception.user.UserPasswordNotMatchException;
import com.ugly.blog.util.TokenUtils;
import com.ugly.blog.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * 认证过滤器
 *
 * @author JwZheng
 * @date 2021/4/19 18:02
 */
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final ThreadLocal<Boolean> rememberMe = new ThreadLocal<>();
    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        // 设置登录请求的 URL
        super.setFilterProcessesUrl(Constants.LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        HttpSession session = request.getSession();
        //获取session中的验证码
        String sessionValidateCode = (String) session.getAttribute(KAPTCHA_SESSION_KEY);
        session.removeAttribute(KAPTCHA_SESSION_KEY);

        sessionValidateCode = "1234";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // 从输入流中获取到登录的信息
            LoginBody loginBody = objectMapper.readValue(request.getInputStream(), LoginBody.class);
            String code = loginBody.getCode();
            if (Utils.isNull(code) || !code.equals(sessionValidateCode)) {
                throw new CaptchaException();
            }
            rememberMe.set(loginBody.getRememberMe());
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    loginBody.getUsername(), loginBody.getPassword());
            return authenticationManager.authenticate(authRequest);
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
                                            Authentication authentication) {
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        loginUser.setIsRememberMe(rememberMe.get());
        String token = TokenUtils.createToken(loginUser);
        // Http Response Header 中返回 Token
        response.setHeader(Constants.TOKEN_HEADER, token);
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) {
        throw new UserPasswordNotMatchException();
    }
}
