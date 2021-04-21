package com.ugly.blog.constant;

/**
 * @author JwZheng
 * @date 2021/4/17 21:08
 */
public class Constants {

    /**
     * 角色的key
     **/
    public static final String ROLE_CLAIMS = "role";

    /**
     * rememberMe 为 false 的时候过期时间是1个小时
     */
    public static final long EXPIRATION = 60 * 60L;

    /**
     * rememberMe 为 true 的时候过期时间是7天
     */
    public static final long EXPIRATION_REMEMBER = 60 * 60 * 24 * 7L;


    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";


    public static final String LOGIN_URL = "/loginVerify";
}
