<%--
  Created by IntelliJ IDEA.
  User: JwZheng
  Date: 2021/4/5
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>${article.articleTitle}</title>
    <link rel="stylesheet" type="text/css" href="/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="/css/blog.css"/>
    <link rel="stylesheet" type="text/css" href="/css/footer.css"/>
    <link rel="stylesheet" type="text/css" href="/css/header.css"/>
    <link rel="stylesheet" type="text/css" href="/css/sidebar.css"/>
    <link rel="stylesheet" type="text/css" href="/css/global.css"/>

    <script src="layui/layui.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
<jsp:include page="global/header.jsp"/>


<div class="layui-container">
    <div class="layui-row layui-col-space30">
        <div class="layui-col-md8">
            <article class="article">
                <fmt:formatDate value="${article.articleUpdateTime}" dateStyle="medium" timeStyle="medium"
                                var="updateTime"/>
                <fmt:formatDate value="${article.articleCreateTime}" pattern="yyyy" var="year"/>
                <fmt:formatDate value="${article.articleCreateTime}" pattern="MM" var="month"/>
                <fmt:formatDate value="${article.articleCreateTime}" pattern="dd" var="day"/>

                <section class="article-item">
                    <aside class="article-info" style="line-height:1.5; 	border-bottom: 1px solid #e8e9e7;">
                        <h4 class="title" style="border-bottom: none;">
                            <c:if test="${article.articleIsReprint == 1}">
                                <span class="fc-red">【转载】</span>
                            </c:if>
                            <c:if test="${article.articleIsReprint != 1}">
                                <span class="fc-blue">【原创】</span>
                            </c:if>
                            <a>${article.articleTitle}</a>
                        </h4>
                        <small>
                            作者：<a href="javascript:void(0)" target="_blank" class="fc-link"
                                  style="color: #01AAED">${article.user.userName}</a>
                        </small>
                        <small class="ml10">阅读数：<i class="readcount">${article.articleViewCount}</i></small>
                        <small class="ml10">更新于 <label>${updateTime}</label> </small>
                    </aside>

                    <div class="time">
                        <span class="day">${day}</span>
                        <span class="month fs-18">${month}<span
                                class="fs-14">月</span></span>
                        <span class="year fs-18 ml10">${year}</span>
                    </div>

                    <div class="content full-article">${article.articleContent}</div>

                    <div class="copyright mt20" style="background-color: #f8f9f7;">
                        <p class="f-toe">
                            本文标题：
                            <a href="javascript:void(0)" class="r-title">${article.articleTitle}</a>
                        </p>
                        <c:set var="fullPath"
                               value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/blogs/${article.articleId}"/>
                        <p class="f-toe">
                            本文网址：
                            <a href="${fullPath}">${fullPath}</a>
                        </p>
                        <p class="f-toe fc-black">
                            非特殊说明，本文版权归 <a href="#" style="color: #1E9FFF">${article.user.userName}</a> 所有，转载请注明出处.
                        </p>
                    </div>

                </section>
            </article>
        </div>
        <div class="layui-col-md4">
            <div class="layui-col-md4 layui-hide-xs">
                <jsp:include page="global/sideBar.jsp"/>
            </div>
        </div>
    </div>
    <div class="layui-row layui-col-space30">
        <div class="layui-col-md6">
            <div class="layui-panel">
                <div style="padding: 30px; text-align: center;"><a href="#">SpringMVC中使用Interceptor拦截器</a> 上一篇</div>
            </div>
        </div>
        <div class="layui-col-md6">
            <div class="layui-panel">
                <div style="padding: 30px; text-align: center;">下一篇 <a href="#">SpringBoot + mongodb 整合,
                    记录网站操作日志，常用查询操作</a>
                </div>
            </div>
        </div>
    </div>
</div>


<jsp:include page="global/footer.jsp"/>

</body>
</html>