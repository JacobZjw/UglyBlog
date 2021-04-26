package com.ugly.blog.annotation;

import com.ugly.blog.enums.CheckType;

import java.lang.annotation.*;

/**
 * @author JwZheng
 * @date 2021/4/26 10:30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckAuthority {
    CheckType type();
}
