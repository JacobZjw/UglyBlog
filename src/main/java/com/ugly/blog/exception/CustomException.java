package com.ugly.blog.exception;

/**
 * 自定义异常
 *
 * @author JwZheng
 * @date 2021/4/14 15:56
 */
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = -5727582779508670455L;
    /**
     * 错误消息
     */
    private final String message;
    /**
     * 错误码
     */
    private Integer code;


    public CustomException(String message) {
        this.message = message;
    }

    public CustomException(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    public CustomException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }


    public Integer getCode() {
        return code;
    }
}
