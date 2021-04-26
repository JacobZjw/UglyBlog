package com.ugly.blog.aspect;

import com.ugly.blog.annotation.CheckAuthority;
import com.ugly.blog.dto.LoginUser;
import com.ugly.blog.enums.CheckType;
import com.ugly.blog.service.ArticleService;
import com.ugly.blog.service.UserService;
import com.ugly.blog.util.SecurityUtils;
import com.ugly.blog.util.Utils;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author JwZheng
 * @date 2021/4/26 10:28
 */
@Aspect
@Component
public class AuthorityAspect {
    private final UserService userService;
    private final ArticleService articleService;

    @Autowired
    public AuthorityAspect(UserService userService, ArticleService articleService) {
        this.userService = userService;
        this.articleService = articleService;
    }

    @Pointcut("@annotation(com.ugly.blog.annotation.CheckAuthority)")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void doBefore(JoinPoint point) {
        CheckAuthority checkAuthority = getAnnotationLog(point);
        if (Utils.isNull(checkAuthority)) {
            return;
        }
        LoginUser user = SecurityUtils.getCurUser();

        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();

        if (Utils.isNotNull(user)) {
            if (CheckType.USER.equals(checkAuthority.type())) {
                int userIdIndex = ArrayUtils.indexOf(parameterNames, "userId");
                userService.checkAuthority((Integer) point.getArgs()[userIdIndex]);
            } else if (CheckType.ARTICLE.equals(checkAuthority.type())) {
                int articleIdIndex = ArrayUtils.indexOf(parameterNames, "articleId");
                articleService.checkAuthority((Integer) point.getArgs()[articleIdIndex]);
            }
        }
    }

    private CheckAuthority getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(CheckAuthority.class);
        }
        return null;
    }


}
