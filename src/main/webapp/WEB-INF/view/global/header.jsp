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
                <li class="layui-nav-item">
                    <a href="javascript:">博客</a>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:">关于</a>
                </li>
                <li class="layui-nav-item">
                    <a href="<c:url value="/sys"/>">后台管理</a>
                </li>
            </ul>
        </div>
    </div>
</header>
