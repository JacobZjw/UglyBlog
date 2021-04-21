package com.ugly.blog.filter;

import com.ugly.blog.util.SecurityUtils;
import com.ugly.blog.util.TokenUtils;
import com.ugly.blog.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权过滤器
 *
 * @author JwZheng
 * @date 2021/4/19 18:01
 */
@Slf4j
@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = TokenUtils.getToken(request);
        if (Utils.isNotNull(token) && Utils.isNull(SecurityUtils.getAuthentication())) {
            UsernamePasswordAuthenticationToken authenticationToken = TokenUtils.getAuthentication(token);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}
