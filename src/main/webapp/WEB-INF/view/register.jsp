<%--
  Created by IntelliJ IDEA.
  User: JwZheng
  Date: 2021/4/1
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/lib/layui-v2.6.4/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="/css/registerAndLogin.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="/css/global.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="/lib/font-awesome-v4.7.0/css/font-awesome.min.css" media="all"/>
    <title>注册-UglyBlog</title>
</head>
<body>
<div class="header-logo">
    <h1>CodingStartup</h1>
</div>
<div class="layui-container register-container">
    <br/>
    <fieldset class="layui-elem-field layui-field-title">
        <legend>注册</legend>
    </fieldset>
    <form class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" name="username" required lay-verify="required"
                       placeholder="请输入5-12位数字、字母、下划线组合" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">昵称</label>
            <div class="layui-input-block">
                <input type="text" name="nickname" required lay-verify="required"
                       placeholder="请输入5-12位数字、字母、下划线组合" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">电子邮箱</label>
            <div class="layui-input-block">
                <input type="text" name="email" required lay-verify="email" placeholder="请输入电子邮箱"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">登录密码</label>
            <div class="layui-input-block">
                <input type="password" name="password" required lay-verify="required"
                       placeholder="请输入5-12位数字、字母、下划线组合" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-block">
                <input type="password" name="confirmPassword" required lay-verify="required" placeholder="请确认密码"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label code-label">验证码</label>
            <div class="layui-input-inline" style="width: 140px">
                <input type="text" name="code" lay-verify="required"
                       placeholder="验证码" autocomplete="off" class="layui-input"/>
            </div>
            <img src="/captcha.jpg" style="width: 80px;height: 40px;"/>
        </div>

        <div class="layui-form-item">
            <a href="/login" class="layui-form-label switching-label">登录</a>
            <div class="layui-input-block">
                <button class="layui-btn" style="width: 100%;" lay-submit lay-filter="submitBtn">立即提交</button>
            </div>
        </div>


    </form>
</div>

<script src="/lib/layui-v2.6.4/layui.js"></script>
<script src="/lib/jQuery-v3.6.0/jquery-3.6.0.min.js"></script>
<script>
    $(function () {
        $(".layui-btn").click(function () {
            let usernameText = $(".layui-input[name='username']").val();
            let patten = /\w{5,12}/;
            if (!patten.test(usernameText)) {
                layer.msg("用户名不合法", {
                    icon: 5
                });
                return false;
            }
            let pwdText = $(".layui-input[name='password']").val();
            if (!patten.test(pwdText)) {
                layer.msg("密码不合法", {
                    icon: 5
                });
                return false;
            }
            let confirmPwd = $(".layui-input[name='confirmPassword]").val();
            console.log(confirmPwd);
            if (confirmPwd !== pwdText) {
                layer.msg("两次密码不一致", {
                    icon: 5
                });
                return false;
            }
            $.ajax({
                async: false,
                type: "POST",
                url:,
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                data: $(".layui-form").serialize(),
                dataType: "json",
                success: function (res) {
                    if (res.code === 0) {
                        layer.msg(res.msg);
                    } else {
                        layer.msg("登录成功");
                        alert("登录成功");
                        // location.href = "login";
                    }
                },
                error: function () {
                    layer.msg("登陆失败，请重试");
                }
            });
        });
    });
</script>
</body>
</html>
