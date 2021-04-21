package com.ugly.blog.constant;

/**
 * 用户常量信息
 *
 * @author JwZheng
 * @date 2021/4/14 16:06
 */
public class UserConstant {


    /**
     * 正常状态
     */
    public static final Integer USER_IS_ENABLED = 1;

    /**
     * 用户封禁状态
     */
    public static final Integer USER_IS_DISABLED = 0;


    /**
     * 用户删除状态
     */
    public static final Integer USER_IS_DELETE = 1;

    /**
     * 用户是管理员
     */
    public static final Integer USER_IS_ADMIN = 0;


    /**
     * 校验返回结果码
     */
    public final static String UNIQUE = "1";
    public final static String NOT_UNIQUE = "0";
}
