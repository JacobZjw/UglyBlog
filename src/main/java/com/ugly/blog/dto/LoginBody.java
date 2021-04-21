package com.ugly.blog.dto;

import lombok.Data;

/**
 * @author JwZheng
 * @date 2021/4/15 14:04
 */
@Data
public class LoginBody {
    private String username;
    private String password;
    private Boolean rememberMe;
    private String code;
}
