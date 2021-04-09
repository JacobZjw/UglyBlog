package com.ugly.blog.domain;

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

    private Integer userId;

    private String username;

    private String password;

    private String nickname;

    private String email;

    private String avatar;

    private String lastLoginIp;

    private Date registerTime;

    private Date lastLoginTime;

    private Integer status;

    /**
     * 用户角色：admin/user
     */
    private String role;
}
