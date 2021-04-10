package com.ugly.blog.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author JwZheng
 * @date 2021/3/31 14:47
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -4415517704211731385L;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    @JsonProperty
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 最后一次登录IP地址
     */
    private String lastLoginIp;

    /**
     * 注册时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerTime;

    /**
     * 上次登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    /**
     * 状态(0正常 1停用)
     */
    private Integer status;

    /**
     * 用户角色(admin管理员 user普通用户)
     */
    private String role;
}
