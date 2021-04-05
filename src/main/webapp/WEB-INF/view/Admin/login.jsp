<%--
  Created by IntelliJ IDEA.
  User: JwZheng
  Date: 2021/3/31
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>UglyBlog-Login</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/layui/css/layui.css"/>" media="all"/>
    <style type="text/css">
        .login-main {
            position: relative;
            top: 180px;
            right: auto;
            bottom: revert;
            left: auto;
            width: 401px;
            height: 300px;
            background: silver;
            border-radius: 19px;
            border-bottom: dashed;
            padding: 0;
            margin: 0 auto 1em;
        }


        body {
            background-color: #b2b2b2;
        }
    </style>
</head>


<body>

<div class="login-main">
    <hr/>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>登录</legend>
    </fieldset>

    <form class="layui-form" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">账号：</label>
            <div class="layui-input-block">
                <input type="text" name="account" required lay-verify="required" placeholder="请输入用户名或邮箱"
                       autocomplete="off" style="width: 80%"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-inline">
                <input type="password" name="password" required lay-verify="required" placeholder="请输入密码"
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">记住我</label>
            <div class="layui-input-block">
                <input type="checkbox" name="remember" lay-skin="switch"/>
            </div>
        </div>

<%--                <div class="layui-form-item">--%>
<%--                    <label class="layui-form-label">验证码</label>--%>
<%--                    <div class="layui-input-inline">--%>
<%--                        <input type="text" name="code" required lay-verify="required" placeholder="请输入验证码"--%>
<%--                               autocomplete="off"--%>
<%--                               class="layui-input">--%>
<%--                        <img src="kaptcha.jpg" style="float: right; margin-right: 40px ; width:100px;height: 40px;"/>--%>
<%--                    </div>--%>
<%--                </div>--%>


        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="submitBtn">立即提交</button>
            </div>
        </div>
    </form>
</div>


<script src="/layui/layui.js"></script>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script>
    layui.use('form', function () {
        const form = layui.form;
        //监听提交
        form.on('submit(submitBtn)', function (data) {
            $.ajax({
                async: false,//同步，待请求完毕后再执行后面的代码
                type: "POST",
                url: '/loginVerify',
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