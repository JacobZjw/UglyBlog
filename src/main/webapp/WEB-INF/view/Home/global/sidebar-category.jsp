<%--
  Created by IntelliJ IDEA.
  User: JwZheng
  Date: 2021/4/6
  Time: 4:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="side-bar">
    <aside class="layui-card category-card">
        <div class="layui-card-header category-card-header">
            <label class="search-wrap">
                <input type="text" id="search-word" placeholder="输入关键字搜索"/>
                <span class="layui-icon layui-icon-search search-icon"></span>
            </label>
            <ul class="search-result">
                <li><a href="#">Javaaaaaaaaaaaa</a></li>
                <li><a href="#">Javaaaaaaaaaaaaa</a></li>
                <li><a href="#">Javaaaaaaaaaaaaaaaa</a></li>
            </ul>
        </div>

        <div class="layui-card-body category-card-body">
            <ul class="category" id="category">
                <li><a href="/category/all">全部文章</a></li>
                <c:forEach items="${categoryList}" var="category">
                    <li>
                        <a href="<c:url value="/category/${category.categoryId}/"/>">${category.categoryName}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </aside>
</div>
