package com.ugly.blog.controller.admin;

import cn.hutool.json.JSONObject;
import com.ugly.blog.domain.User;
import com.ugly.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.ugly.blog.util.Utils.getIpAddr;

/**
 * @author JwZheng
 * @date 2021/3/31 15:04
 */
@Controller
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping("login")
    public String loginPage() {
        return "Admin/login";
    }


    @RequestMapping(value = "/loginVerify", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String loginVerify(HttpServletRequest request, HttpServletResponse response) {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        boolean remember = Boolean.parseBoolean(request.getParameter("remember"));

        Map<String, Object> respJson = new HashMap<>(2);
        User user = userService.getUserByNameOrEmail(account);
        if (user == null) {
            respJson.put("code", 0);
            respJson.put("msg", "用户名无效！");
        } else if (!password.equals(user.getUserPwd())) {
            respJson.put("code", 0);
            respJson.put("msg", "密码错误！");
        } else {
            respJson.put("code", 1);
            respJson.put("msg", "");
            request.getSession().setAttribute("user", user);
            if (remember) {
                //创建两个Cookie对象
                Cookie nameCookie = new Cookie("account", account);
                //设置Cookie的有效期为3天
                nameCookie.setMaxAge(60 * 60 * 24 * 3);
                Cookie pwdCookie = new Cookie("password", password);
                pwdCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
            }
            user.setUserLastLoginIp(getIpAddr(request));
//            userService.updateUser(user);
        }
        System.out.println(new JSONObject(respJson).toString());
        return new JSONObject(respJson).toString();
    }
}
