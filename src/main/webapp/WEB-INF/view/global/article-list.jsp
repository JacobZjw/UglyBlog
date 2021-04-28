<%--
  Created by IntelliJ IDEA.
  User: JwZheng
  Date: 2021/4/6
  Time: 4:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="blog-list layui-row">
    <article class="article-list">
        <c:forEach items="${articlePage.items}" var="article">
            <fmt:formatDate value="${article.createTime}" pattern="yyyy" var="year"/>
            <fmt:formatDate value="${article.createTime}" pattern="MM" var="month"/>
            <fmt:formatDate value="${article.createTime}" pattern="dd" var="day"/>

            <section class="article-item">
                <aside class="article-info" style="line-height:1.5; 	border-bottom: 1px solid #e8e9e7;">
                    <fmt:formatDate value="${article.updateTime}" dateStyle="medium" timeStyle="medium"
                                    var="updateTime"/>

                    <h5 class="title" style="border-bottom: none;">
                        <c:if test="${article.isOriginal == 1}">
                            <span class="fc-red">【转载】</span>
                        </c:if>
                        <c:if test="${article.isOriginal != 1}">
                            <span class="fc-blue">【原创】</span>
                        </c:if>
                        <a href="/article/${article.articleId}">${article.title}</a>
                    </h5>
                    <small>
                        作者：<a href="javascript:void(0)" target="_blank" class="fc-link"
                              style="color: #01AAED">${article.user.nickname}</a>
                    </small>
                    <small class="ml10">阅读数: <i class="readcount">${article.clickCount}</i></small>
                    <small class="ml10">更新于: <label>${updateTime}</label></small>
                    <small class="ml10">分类: <a style="color: #01AAED"
                            href="/category/${article.category.categoryId}">${article.category.categoryName}</a></small>
                </aside>

                <div class="time">
                    <span class="day">${day}</span>
                    <span class="month fs-18">${month}<span
                            class="fs-14">月</span></span>
                    <span class="year fs-18 ml10">${year}</span>
                </div>
                <div class="content">${article.summary}</div>
                <div class="read-more">
                    <a href="/article/${article.articleId}" class="fc-black f-fwb" style="font-size: 18px;font-weight:500;font-family: mono">继续阅读</a>
                </div>

                <aside class="f-oh footer">
                    <div class="f-fl tags">
                        <c:forEach items="${article.tagList}" var="tag">
                            <span class="fa fa-tags fs-16">
                                <a class="tag" href="/tag/${tag.tagId}">${tag.tagName}</a>
                            </span>
                        </c:forEach>
                    </div>
                </aside>
            </section>
        </c:forEach>
    </article>
</div>
<c:if test="${articlePage.totalCount > 0}">
    <div class="layui-row layui-laypage" id="laypage"></div>
</c:if>

<c:if test="${articlePage.totalCount == 0}">
    <div class="layui-row" style="font-size: 20px;margin-top: 30px;text-align: center;color: cornflowerblue;"><h1>
        暂时还没有文章</h1></div>
</c:if>
