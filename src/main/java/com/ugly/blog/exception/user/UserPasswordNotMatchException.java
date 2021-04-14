package com.ugly.blog.exception.user;

/**
 * @author JwZheng
 * @date 2021/4/14 16:01
 */
public class UserPasswordNotMatchException extends UserException {

    private static final long serialVersionUID = -5779135525132853609L;

    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
