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
    <title>${article.title}</title>
    <link rel="stylesheet" type="text/css" href="/lib/layui-v2.6.4/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="/css/blog.css"/>
    <link rel="stylesheet" type="text/css" href="/css/footer.css"/>
    <link rel="stylesheet" type="text/css" href="/css/header.css"/>
    <link rel="stylesheet" type="text/css" href="/css/sidebar.css"/>
    <link rel="stylesheet" type="text/css" href="/css/global.css"/>
</head>
<body>

<jsp:include page="global/header.jsp"/>

<div class="layui-container">
    <div class="layui-row breadcrumb">
        <div class="layui-row" style="margin-bottom: 20px;"></div>
        <div class="layui-row">
					<span class="layui-breadcrumb" lay-separator=">">
						<a href="<c:url value="/index"/>">首页</a>
						<a href="<c:url value="/article/${articleId}"/>">${article.title}</a>
						<a><cite>正文</cite></a>
					</span>
        </div>
    </div>
    <div class="layui-row layui-col-space30">
        <div class="layui-col-md8">
            <article class="article">
                <fmt:formatDate value="${article.updateTime}" dateStyle="medium" timeStyle="medium"
                                var="updateTime"/>
                <fmt:formatDate value="${article.createTime}" pattern="yyyy" var="year"/>
                <fmt:formatDate value="${article.createTime}" pattern="MM" var="month"/>
                <fmt:formatDate value="${article.createTime}" pattern="dd" var="day"/>

                <section class="article-item">
                    <aside class="article-info" style="line-height:1.5; 	border-bottom: 1px solid #e8e9e7;">
                        <h4 class="title" style="border-bottom: none;">
                            <c:if test="${article.isOriginal == 1}">
                                <span class="fc-red">【转载】</span>
                            </c:if>
                            <c:if test="${article.isOriginal != 1}">
                                <span class="fc-blue">【原创】</span>
                            </c:if>
                            <a>${article.title}</a>
                        </h4>
                        <small>
                            作者：<a href="javascript:void(0)" target="_blank" class="fc-link"
                                  style="color: #01AAED">${article.user.nickname}</a>
                        </small>
                        <small class="ml10">阅读数：<i class="readcount">${article.clickCount}</i></small>
                        <small class="ml10">更新于 <label>${updateTime}</label> </small>
                    </aside>

                    <div class="time">
                        <span class="day">${day}</span>
                        <span class="month fs-18">${month}<span
                                class="fs-14">月</span></span>
                        <span class="year fs-18 ml10">${year}</span>
                    </div>

                    <div class="content full-article">${article.content}</div>

                    <div class="copyright mt20" style="background-color: #f8f9f7;">
                        <p class="f-toe">
                            本文标题：
                            <a href="javascript:void(0)" class="r-title">${article.title}</a>
                        </p>
                        <c:set var="fullPath"
                               value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/blogs/${article.articleId}"/>
                        <p class="f-toe">
                            本文网址：
                            <a href="${fullPath}">${fullPath}</a>
                        </p>
                        <p class="f-toe fc-black">
                            非特殊说明，本文版权归 <a href="#" style="color: #1E9FFF">${article.user.nickname}</a> 所有，转载请注明出处.
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
                <div style="padding: 30px; text-align: center;" id="prevArticle"><a
                        href="#">SpringMVC中使用Interceptor拦截器</a> 上一篇
                </div>
            </div>
        </div>
        <div class="layui-col-md6">
            <div class="layui-panel">
                <div style="padding: 30px; text-align: center;" id="nextArticle">下一篇 <a href="#">SpringBoot + mongodb
                    整合,
                    记录网站操作日志，常用查询操作</a>
                </div>
            </div>
        </div>
    </div>
</div>


<jsp:include page="global/footer.jsp"/>

<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.js"></script>
<script src="/lib/layui-v2.6.4/layui.js" type="text/javascript" charset="UTF-8"></script>
<script src="/js/sidebar.js" type="text/javascript"></script>

<script>
    $(function () {
        layui.use(['element'], function () {
            const element = layui.element;
            element.init();
            element.on('nav(demo)', function (elem) {
                //console.log(elem)
                layer.msg(elem.text());
            });
            element.render();
        });
        $.getJSON("/article/${articleId}/prevAndNext", function (data) {
            if (data.prevArticleTitle == null) {
                $("#prevArticle").text("当前是第一篇")
            }
            if (data.nextArticleTitle == null) {
                $("#nextArticle").text("当前是最后一篇")
            }
            $("#prevArticle > a").text(data.prevArticleTitle).attr("href", "/article/" + data.prevArticleId);
            $("#nextArticle > a").text(data.nextArticleTitle).attr("href", "/article/" + data.nextArticleId);
        });
    });
</script>
</body>
</html>
