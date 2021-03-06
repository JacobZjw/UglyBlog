<%--
  Created by IntelliJ IDEA.
  User: JwZheng
  Date: 2021/3/31
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录-UglyBlog</title>
    <link rel="stylesheet" href="/lib/layui-v2.6.4/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="/css/registerAndLogin.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="/css/global.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="/lib/font-awesome-v4.7.0/css/font-awesome.min.css"/>
</head>
<body>
<div class="header-logo">
    <h1>CodingStartup</h1>
</div>
<div class="layui-container login-container">
    <br/>
    <fieldset class="layui-elem-field layui-field-title form-title">
        <legend>登录</legend>
    </fieldset>
    <form class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">账号</label>
            <div class="layui-input-block">
                <input type="text" name="username" required lay-verify="required" placeholder="请输入用户名或邮箱"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="password" name="password" required lay-verify="required" placeholder="请输入密码"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">记住我</label>
                <div class="layui-input-inline" style="margin-right: 1px; width: 20px;">
                    <input type="hidden" name="remember-me" value="false">
                    <input type="checkbox" name="remember-me" lay-skin="switch" value="true"/>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label code-label" style="padding: 9px 7px; width: 50px;">验证码</label>
                <div class="layui-input-inline" style="margin-right: 0px; width: 80px;">
                    <input type="text" name="code" lay-verify="required"
                           placeholder="验证码" autocomplete="off" class="layui-input"/>
                </div>
                <img id="validateCode" src="/captcha.jpg" style="float: right; width:80px;height: 40px;"/>
            </div>
        </div>
        <div class="layui-form-item">
            <a href="/register" class="layui-form-label switching-label">注册</a>
            <div class="layui-input-block">
                <button class="layui-btn" style="width: 100%;" lay-submit lay-filter="submitBtn">立即提交</button>
            </div>
        </div>

    </form>
</div>


<script src="/lib/layui-v2.6.4/layui.js"></script>
<script src="/lib/jQuery-v3.6.0/jquery-3.6.0.min.js"></script>

<script>

    $("#validateCode").click(function () {
        $(this).attr('src', '/captcha.jpg?' + Math.floor(Math.random() * 100));
    });


    layui.use('form', function () {
        const form = layui.form;
        //监听提交
        form.on('submit(submitBtn)', function (data) {
            $.ajax({
                async: false, //同步，待请求完毕后再执行后面的代码
                type: "POST",
                url: '/loginVerify',
                dataType: "json",
                data: data.field,
                complete: function (XMLHttpRequest, status) {
                    let json = XMLHttpRequest.responseJSON;
                    if (json.code !== 200) {
                        layer.msg(json.msg);
                        return false;
                    }
                    localStorage.setItem("Authorization",json.Authorization);
                    window.location.href = "/sys";
                },
            });
            return false;
        });
    });
</script>
</body>
</html>


