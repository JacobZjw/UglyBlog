package com.ugly.blog.exception.user;

import com.ugly.blog.exception.BaseException;

/**
 * 用户信息异常类
 *
 * @author JwZheng
 * @date 2021/4/14 15:59
 */
public class UserException extends BaseException {


    private static final long serialVersionUID = -9144771886635960398L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
