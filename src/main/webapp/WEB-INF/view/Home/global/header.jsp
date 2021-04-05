<%--
  Created by IntelliJ IDEA.
  User: JwZheng
  Date: 2021/4/2
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<header class="header">
    <div class="layui-header">
        <div class="header-logo">
            <h1>CodingStartup</h1>
        </div>
        <div class="header-inner" style="float: right;">
            <ul class="layui-nav">
                <li class="layui-nav-item">
                    <a href="<c:url value="/index"/>">首页</a>
                </li>
                <li class="layui-nav-item layui-this">
                    <a href="javascript:">博客</a>
                </li>
                <li class="layui-nav-item">
                    <a href="">关于</a>
                </li>
                <li class="layui-nav-item">
                    <a href="">个人中心<span class="layui-badge-dot"></span></a>
                </li>
                <li class="layui-nav-item" lay-unselect="">
                    <a href="javascript:;">
                        <img src="<c:url value="//t.cn/RCzsdCq"/>" class="layui-nav-img">我</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">修改信息</a></dd>
                        <dd><a href="javascript:;">安全管理</a></dd>
                        <dd><a href="javascript:;">退了</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
</header>

