<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<% String basePath = request.getScheme() + "://" + request.getServerName()
        + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <link href="<%=basePath%>/css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=basePath%>/js/jquery.js"></script>

    <%--    引入bootstrap分页--%>
    <link rel="stylesheet" href="<%=basePath%>/js/bootstrap/bootstrap.css"/>
    <%--    <script type="text/javascript" src="<%=basePath%>/js/bootstrap/jquery.min.js"></script>--%>
    <script type="text/javascript" src="<%=basePath%>/js/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/bootstrap/bootstrap-paginator.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $(".click").click(function () {
                $(".tip").fadeIn(200);
            });

            $(".tiptop a").click(function () {
                $(".tip").fadeOut(200);
            });

            $(".sure").click(function () {
                $(".tip").fadeOut(100);
            });

            $(".cancel").click(function () {
                $(".tip").fadeOut(100);
            });

        });
    </script>
    <script type="text/javascript">
        $(function () {
            $(".pagination").bootstrapPaginator({
                //设置版本号
                bootstrapMajorVersion: 3,
                // 显示第几页
                currentPage: ${requestScope.pageInfo.pageNum},
                // 总页数
                totalPages: ${requestScope.pageInfo.pages},
                //跳转界面
                pageUrl: function (type, page, current) {
                    return 'getInRoomInfo.do?pageNum=' + page;
                },
                itemTexts: function (type, page, current) {
                    switch (type) {
                        case "first":
                            return "首页";
                        case "prev":
                            return "上一页";
                        case "next":
                            return "下一页";
                        case "last":
                            return "末页";
                        case "page":
                            return page;
                    }
                }
            })
        })
    </script>
</head>

<body>
<div class="place"><span>位置：</span>
    <ul class="placeul">
        <li><a href="main.html">首页</a></li>
        <li><a href="#">入住管理</a></li>
        <li><a href="#">入住信息查询</a></li>
    </ul>
</div>
<div class="rightinfo">
    <div class="tools">
        <ul class="toolbar">
            <li class="click"><span><img src="<%=basePath%>/images/t01.png"/></span>添加</li>
            <li class="click"><span><img src="<%=basePath%>/images/t02.png"/></span>修改</li>
            <li><span><img src="images/t03.png"/></span>删除</li>
        </ul>

        <div class="toolbar1">
            <table>
                <form method="get" name="search" action="<%=basePath%>/getInRoomInfo.do">
                    <tr>
                        <td class="zi"><span>选择分类：</span></td>
                        <td><select id="conditionType">
                            <option value="roomNum" selected="selected">房间号</option>
                            <option value="customerName">客人姓名</option>
                            <option value="phone">手机号码</option>
                            <%--                            <option>身份证号码</option>--%>
                        </select></td>
                        <td class="zi"><span>关键字：</span></td>
                        <td><input name="roomNum" id="keyword" type="text" placeholder="与分类关联"/></td>
                        <td><input type="submit" value="查询" class="button"/></td>
                    </tr>
                </form>
            </table>
        </div>
    </div>

    <script type="application/javascript">
        $(function () {
            $("#conditionType").change(function () {
                var v = $(this).find('option:selected').val();
                $('#keyword').attr("name", v)
            })
        })
    </script>
    <table class="tablelist">
        <thead>
        <tr>
            <th><input name="" type="checkbox" value="" checked="checked"/></th>
            <th>编号</th>
            <th>房间号</th>
            <th>房间类型</th>
            <th>客人姓名</th>
            <%--            <th>vip</th>--%>
            <%--            <th>折扣</th>--%>
            <th>性别</th>
            <th>身份证号码</th>
            <th>手机号码</th>
            <th>押金</th>
            <th>入住时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageInfo.list}" var="map" varStatus="num">
            <tr>
                <td><input name="" type="checkbox" value=""/></td>
                <td>${num.count}</td>
                <td>${map.room_num}</td>
                <td>${map.room_type_name}</td>
                <td>${map.customer_name}</td>
                    <%--                <td>${map.is_vip=='1'?'是':'否'}</td>--%>
                    <%--                <td>${map.is_vip=='1'?map.vip_rate:'无'}</td>--%>
                <td>${map.gender=='1'?'男':'女'}</td>
                <td>${map.idcard}</td>
                <td>${map.phone}</td>
                <td>${map.money}</td>
                <td>${map.create_date}</td>
                <td>
                    <c:choose>
                        <c:when test="${map.out_room_status=='0'}">
                            <a href="out.jsp" class="tablelink">退房</a>
                        </c:when>
                        <c:otherwise>
                            <a href="javaScript:void(0)" class="tablelink">已退房</a>
                            <a href="#" class="tablelink"> 删除</a>
                        </c:otherwise>
                    </c:choose>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <ul class="pagination" style="align-content: flex-start"></ul>


    <div class="tip">
        <div class="tiptop"><span>提示信息</span><a></a></div>
        <div class="tipinfo"><span><img src="images/ticon.png"/></span>
            <div class="tipright">
                <p>是否确认对信息的修改 ？</p>
                <cite>如果是请点击确定按钮 ，否则请点取消。</cite></div>
        </div>
        <div class="tipbtn">
            <input name="" type="button" class="sure" value="确定"/>
            &nbsp;
            <input name="" type="button" class="cancel" value="取消"/>
        </div>
    </div>
</div>
<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
