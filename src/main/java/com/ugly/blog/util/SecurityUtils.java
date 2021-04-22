package com.ugly.blog.util;

import com.ugly.blog.constant.HttpStatus;
import com.ugly.blog.constant.UserConstant;
import com.ugly.blog.dto.LoginUser;
import com.ugly.blog.exception.CustomException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

/**
 * @author JwZheng
 * @date 2021/4/14 16:28
 */
public class SecurityUtils {


    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 是否为管理员
     *
     * @param user 用户
     * @return 结果
     */
    public static boolean isAdmin(LoginUser user) {
        if (Utils.isNull(user.getUser())) {
            throw new CustomException(HttpStatus.ERROR, "无法获取用户信息");
        }
        return UserConstant.USER_IS_ADMIN.equals(user.getUser().getRole());
    }


    /**
     * 是否为管理员
     *
     * @return 结果
     */
    public static boolean isAdmin() {
        Authentication auth = getAuthentication();
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        return authorities.contains(new SimpleGrantedAuthority("ROLE_admin"));
    }


    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
