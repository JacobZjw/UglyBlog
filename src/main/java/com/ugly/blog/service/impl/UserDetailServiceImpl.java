package com.ugly.blog.service.impl;

import com.ugly.blog.constant.UserConstant;
import com.ugly.blog.domain.User;
import com.ugly.blog.dto.LoginUser;
import com.ugly.blog.exception.BaseException;
import com.ugly.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/17 14:03
 */
@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws RuntimeException {
        User user = userService.getUserByNameOrEmail(username);
        if (user == null) {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        } else if (UserConstant.USER_IS_DISABLED.equals(user.getStatus())) {
            log.info("登录用户：{} 已被禁用.", username);
            throw new BaseException("用户：" + username + " 已被禁用,请联系管理员。");
        } else if (UserConstant.USER_IS_DELETE.equals(user.getIsDelete())) {
            log.info("登录用户：{} 已被删除.", username);
            throw new BaseException("用户：" + username + " 已被删除。");
        }
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_user"));
        if (UserConstant.USER_IS_ADMIN.equals(user.getRole())) {
            list.add(new SimpleGrantedAuthority("ROLE_admin"));
        }
        return createUser(user, list);
    }

    protected UserDetails createUser(User user, List<GrantedAuthority> list) {
        return new LoginUser(user, list);
    }

}
