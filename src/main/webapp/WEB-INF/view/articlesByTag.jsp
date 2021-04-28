<%--
  Created by IntelliJ IDEA.
  User: JwZheng
  Date: 2021/4/6
  Time: 4:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>标签-UglyBlog</title>
    <link rel="stylesheet" type="text/css" href="/lib/layui-v2.6.4/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="/lib/font-awesome-v4.7.0/css/font-awesome.min.css" media="all">
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
						<a href="/index">首页</a>
						<a href="/tag/all">标签</a>
						<a><cite>标签名</cite></a>
					</span>
        </div>
    </div>
    <div class="layui-row">

        <div class="layui-col-md8">
            <jsp:include page="global/article-list.jsp"/>
        </div>

        <div class="layui-col-md4 layui-hide-xs">
            <jsp:include page="global/sidebar-tag.jsp"/>
        </div>

    </div>
</div>

<jsp:include page="global/footer.jsp"/>
<script src="/lib/jQuery-v3.6.0/jquery-3.6.0.min.js"></script>
<script src="/lib/layui-v2.6.4/layui.js" type="text/javascript" charset="UTF-8"></script>
<script src="/js/sidebar.js" type="text/javascript"></script>
<script>
    $(function () {
        layui.use(['element', 'laypage'], function () {
            const element = layui.element, laypage = layui.laypage;

            element.init();
            element.on('nav(demo)', function (elem) {
                //console.log(elem)
                layer.msg(elem.text());
            });
            element.render();

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

        $.getJSON("/tag/tagName/${tagId}", function (result) {
            if (result.code === 200) {
                $(".layui-breadcrumb > a:eq(2) > cite").text(result.data);
            }
        });
    });
</script>
</body>
</html>
