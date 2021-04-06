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
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css"/>
    <title>注册</title>
</head>
<body>
<div class="layui-container"
     style="background-color: whitesmoke; height:410px;width: 440px; margin: 0 auto; margin-top: 180px; border: 1px  #5FB878; border-radius: 19px;">
    <br/>
    <fieldset class="layui-elem-field layui-field-title">
        <legend>注册</legend>
    </fieldset>
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" name="username" required lay-verify="required" placeholder="请输入5-12位数字、字母、下划线组合"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">昵称</label>
        <div class="layui-input-block">
            <input type="text" name="nickname" required lay-verify="required" placeholder="请输入5-12位数字、字母、下划线组合"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">电子邮箱</label>
        <div class="layui-input-block">
            <input type="text" name="email" required lay-verify="email" placeholder="请输入电子邮箱" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">登录密码</label>
        <div class="layui-input-block">
            <input type="password" name="password" required lay-verify="required" placeholder="请输入密码"
                   autocomplete="off" class="layui-input">
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
        <a href="" class="layui-form-label">登录</a>
        <div class="layui-input-block">
            <button class="layui-btn" style="width: 100%;" lay-submit lay-filter="submitBtn">立即提交</button>
        </div>
    </div>
    </form>
</div>
</body>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="/layui/layui.js"></script>
<script>
    $(function () {
        $(".layui-btn").click(function () {
            let usernameText = $(".layui-input[name='username']").val();
            let patten = /\w{5,12}/;
            if (!patten.test(usernameText)) {
                layer.msg("用户名不合法", {icon: 5});
                return false;
            }
            let pwdText = $(".layui-input[name='password']").val();
            if (!patten.test(pwdText)) {
                layer.msg("密码不合法", {icon: 5});
                return false;
            }
            let confirmPwd = $(".layui-input[name='confirmPassword]").val();
            console.log(confirmPwd);
            if (confirmPwd !== pwdText) {
                layer.msg("两次密码不一致", {icon: 5});
                return false;
            }
        });
    });
</script>
</html>