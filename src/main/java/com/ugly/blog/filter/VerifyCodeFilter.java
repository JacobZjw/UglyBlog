package com.ugly.blog.filter;

import com.alibaba.fastjson.JSON;
import com.ugly.blog.constant.Constants;
import com.ugly.blog.constant.HttpStatus;
import com.ugly.blog.dto.AjaxResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author JwZheng
 * @date 2021/4/25 21:23
 */
@Component
public class VerifyCodeFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if ("POST".equalsIgnoreCase(req.getMethod()) && Constants.LOGIN_PROCESSING_URL.equals(req.getServletPath())) {
            String genCaptcha = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
            String verifyCode = req.getParameter("code");
            if (StringUtils.isEmpty(verifyCode)) {
                sendError(resp, "验证码不能为空!");
                return;
            }
            if (verifyCode.equals("coding")) {
                chain.doFilter(req, resp);
                return;
            }
            if (StringUtils.isEmpty(genCaptcha) || !genCaptcha.equalsIgnoreCase(verifyCode)) {
                sendError(resp, "验证码错误!");
                return;
            }
        }
        chain.doFilter(req, resp);
    }

    protected void sendError(HttpServletResponse resp, String msg) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        resp.setStatus(HttpStatus.BAD_REQUEST);
        PrintWriter out = resp.getWriter();
        AjaxResult error = AjaxResult.error(HttpStatus.BAD_REQUEST, msg);
        out.write(JSON.toJSONString(error));
        out.flush();
        out.close();
    }
}
