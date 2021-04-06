<%--
  Created by IntelliJ IDEA.
  User: JwZheng
  Date: 2021/4/6
  Time: 4:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                            location.href = "${pageContext.request.contextPath}/category/${categoryId}?pageIndex=" + obj.curr + "&pageSize=" + obj.limit;
                        }
                    }
                });
            });

            layui.use(['element'], function () {
                var element = layui.element;
                element.init();
                element.on('nav(demo)', function (elem) {
                    layer.msg(elem.text());
                });
                element.render();
            });
        });
    </script>
</head>

<body>
<jsp:include page="global/header.jsp"/>

<div class="layui-container">
    <div class="layui-row breadcrumb">
        <div class="layui-row" style="margin-bottom: 20px;"></div>
        <div class="layui-row">
					<span class="layui-breadcrumb" lay-separator=">">
						<a href="">首页</a>
						<a href="">${categoryId}</a>
						<a><cite>正文</cite></a>
					</span>
        </div>
    </div>
    <div class="layui-row">

        <div class="layui-col-md8">
            <jsp:include page="global/article-list.jsp"/>
        </div>

        <div class="layui-col-md4 layui-hide-xs">
            <jsp:include page="global/sidebar-category.jsp"/>
        </div>

    </div>
</div>

<jsp:include page="global/footer.jsp"/>
</body>
</html>
