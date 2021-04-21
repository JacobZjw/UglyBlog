package com.ugly.blog.handler;

import com.ugly.blog.constant.HttpStatus;
import com.ugly.blog.dto.AjaxResult;
import com.ugly.blog.exception.BaseException;
import com.ugly.blog.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author JwZheng
 * @date 2021/4/14 18:12
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 基础异常
     */
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public AjaxResult baseException(BaseException e) {
        return AjaxResult.error(e.getMessage());
    }


    /**
     * 业务异常
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public AjaxResult businessException(CustomException e) {
        return e.getCode() == null ? AjaxResult.error(e.getMessage()) : AjaxResult.error(e.getCode(), e.getMessage());
    }


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public AjaxResult handleAuthorizationException(AccessDeniedException e) {
        log.error(e.getMessage());
        return AjaxResult.error(HttpStatus.FORBIDDEN, "没有权限，请联系管理员授权");
    }

}
