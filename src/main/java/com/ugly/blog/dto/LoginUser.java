package com.ugly.blog.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ugly.blog.domain.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author JwZheng
 * @date 2021/4/17 20:47
 */
@Data
public class LoginUser implements UserDetails {

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 是否记住
     */
    private Boolean isRememberMe;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 权限列表
     */
    private Collection<? extends GrantedAuthority> permissions;

    /**
     * 用户信息
     */
    private User user;

    public LoginUser(User user, Collection<? extends GrantedAuthority> permissions) {
        this.user = user;
        this.permissions = permissions;
    }


    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public Integer getId() {
        return user.getUserId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
