package com.ugly.blog.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author JwZheng
 * @date 2021/4/15 14:04
 */
@Data
public class LoginBody {
    @JsonProperty("username")
    @JsonAlias("userName")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("remember-me")
    private Boolean rememberMe;
    @JsonProperty("code")
    private String code;
}
