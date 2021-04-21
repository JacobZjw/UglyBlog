<%--
  Created by IntelliJ IDEA.
  User: JwZheng
  Date: 2021/4/6
  Time: 4:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="side-bar">
    <aside class="layui-card tag-cloud-card">
        <div class="layui-card-header">全部标签</div>
        <div class="layui-card-body">
            <div class="tag-cloud">
                <c:forEach items="${tagList}" var="tag">
                    <a href="/tag/${tag.tagId}" class="tag-cloud-link">${tag.tagName}</a>
                </c:forEach>
            </div>
        </div>
    </aside>
</div>