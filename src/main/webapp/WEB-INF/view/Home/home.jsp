<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: JwZheng
  Date: 2021/4/2
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>layui</title>
    <link rel="stylesheet" type="text/css" href="/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="/css/blog.css"/>
    <link rel="stylesheet" type="text/css" href="/css/footer.css"/>
    <link rel="stylesheet" type="text/css" href="/css/header.css"/>
    <link rel="stylesheet" type="text/css" href="/css/sidebar.css"/>
    <link rel="stylesheet" type="text/css" href="/css/global.css"/>

    <script src="layui/layui.js" type="text/javascript" charset="UTF-8"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.js"></script>
    <script src="/js/sidebar.js" type="text/javascript"></script>
    <script>
        $(function () {
            //分页
            layui.use('laypage', function () {
                const laypage = layui.laypage;
                //执行一个laypage实例
                laypage.render({
                    elem: 'laypage', //注意，这里的 test1 是 ID，不用加 # 号
                    count: ${articlePage.totalCount}, //数据总数，从服务端得到
                    limit: ${articlePage.pageSize},
                    curr:  ${articlePage.pageNo},
                    limits: [10, 20, 30],
                    layout: ['prev', 'page', 'next', 'limit'],
                    jump: function (obj, first) {
                        //obj包含了当前分页的所有参数，比如：
                        console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                        console.log(obj.limit); //得到每页显示的条数

                        //首次不执行
                        if (!first) {
                            location.href = "${pageContext.request.contextPath}?pageIndex=" + obj.curr;
                            <%--$.getJSON("${pageContext.request.contextPath}/" + (obj.curr + 1), null, function (data) {--%>
                            <%--    if (data != null) {--%>
                            <%--       --%>
                            <%--    }--%>
                            <%--});--%>
                        }
                    }
                });
            });
        });
    </script>

</head>
<body>
<jsp:include page="global/header.jsp"/>

<div class="layui-container">
    <div class="layui-row">
        <div class="layui-col-md8">
            <div class="blog-list layui-row">
                <article class="article-list">
                    <c:forEach items="${articlePage.items}" var="article">
                        <fmt:formatDate value="${article.articleCreateTime}" pattern="yyyy" var="year"/>
                        <fmt:formatDate value="${article.articleCreateTime}" pattern="MM" var="month"/>
                        <fmt:formatDate value="${article.articleCreateTime}" pattern="dd" var="day"/>

                        <section class="article-item">
                            <h5 class="title">
                                <c:if test="${article.articleIsReprint == 1}">
                                    <span class="fc-red">【转载】</span>
                                </c:if>
                                <c:if test="${article.articleIsReprint != 1}">
                                    <span class="fc-blue">【原创】</span>
                                </c:if>
                                <a href="/article/${article.articleId}">${article.articleTitle}</a>
                            </h5>
                            <div class="time">
                                <span class="day">${day}</span>
                                <span class="month fs-18">${month}<span
                                        class="fs-14">月</span></span>
                                <span class="year fs-18 ml10">${year}</span>
                            </div>
                            <div class="content">${article.articleSummary}</div>
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
                                        <i class="num">${article.articleViewCount}</i>
                                    </span>
                                    <span class="ml20">
											<i class="fa fa-comments fs-16"></i>
											<a href="javascript:void(0)"
                                               class="num fc-grey">${article.articleCommentCount}</a>
                                    </span>
                                </div>
                            </aside>
                        </section>
                    </c:forEach>
                </article>
            </div>
            <div class="layui-row layui-laypage" id="laypage"></div>
        </div>

        <div class="layui-col-md4 layui-hide-xs">
            <jsp:include page="global/sideBar.jsp"/>
        </div>

    </div>
</div>

<jsp:include page="global/footer.jsp"/>
</body>
</html>
