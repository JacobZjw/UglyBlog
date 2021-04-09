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
                <h5 class="title">
                    <c:if test="${article.isReprint == 1}">
                        <span class="fc-red">【转载】</span>
                    </c:if>
                    <c:if test="${article.isReprint != 1}">
                        <span class="fc-blue">【原创】</span>
                    </c:if>
                    <a href="/article/${article.articleId}">${article.title}</a>
                </h5>
                <div class="time">
                    <span class="day">${day}</span>
                    <span class="month fs-18">${month}<span
                            class="fs-14">月</span></span>
                    <span class="year fs-18 ml10">${year}</span>
                </div>
                <div class="content">${article.summary}</div>
                <div class="read-more">
                    <a href="/article/${article.articleId}" class="fc-black f-fwb">继续阅读</a>
                </div>

                <aside class="f-oh footer">
                    <div class="f-fl tags">
                        <span class="fa fa-tags fs-16"></span>
                        <a class="tag">ASP.NET MVC</a>
                    </div>
                    <div class="f-fr">
									<span class="read">
                                        <i class="fa fa-eye fs-16"></i>
                                        <i class="num">${article.viewCount}</i>
                                    </span>
                        <span class="ml20">
											<i class="fa fa-comments fs-16"></i>
											<a href="javascript:void(0)"
                                               class="num fc-grey">${article.commentCount}</a>
                                    </span>
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
