package com.ugly.blog.service.impl;

import com.ugly.blog.dto.LoginUser;
import com.ugly.blog.exception.CustomException;
import com.ugly.blog.exception.user.CaptchaException;
import com.ugly.blog.exception.user.UserPasswordNotMatchException;
import com.ugly.blog.service.UserLoginService;
import com.ugly.blog.util.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author JwZheng
 * @date 2021/4/17 20:28
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(String username, String password, String code) {
        String verifyKey = "";
        if (StringUtils.isEmpty(code)) {
            throw new CaptchaException();
        }
        if (!verifyKey.equals(code)) {
            throw new CaptchaException();
        }
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new UserPasswordNotMatchException();
            } else {
                throw new CustomException(e.getMessage());
            }
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return TokenUtils.createToken(loginUser);
    }
}
