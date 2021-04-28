<%--
  Created by IntelliJ IDEA.
  User: JwZheng
  Date: 2021/4/5
  Time: 21:05
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
            <ul class="search-result"></ul>
        </div>

        <div class="layui-card-body category-card-body">
            <ul class="category" id="category">
                <li><a href="<c:url value="/index"/>">全部文章</a></li>
                <c:forEach items="${categoryList}" var="category">
                    <li>
                        <a href="<c:url value="/category/${category.categoryId}/"/>">${category.categoryName}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </aside>

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

    <aside class="layui-card article-card">
        <div class="layui-card-header">热门文章</div>
        <div class="layui-card-body">
            <ul class="article-card-list" id="hot-article-list">
                <c:forEach items="${hotArticles}" var="article">
                    <li><a href="/article/${article.articleId}">${article.title}</a></li>
                </c:forEach>
            </ul>
        </div>
    </aside>
</div>