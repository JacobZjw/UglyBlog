package com.ugly.blog.exception.user;

/**
 * 验证码错误异常类
 *
 * @author JwZheng
 * @date 2021/4/14 16:02
 */
public class CaptchaException extends UserException {
    private static final long serialVersionUID = 1L;

    public CaptchaException() {
        super("user.captcha.error", null);
    }
}
