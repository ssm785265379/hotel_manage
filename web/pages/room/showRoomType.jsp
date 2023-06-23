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
</head>

<body>
<div class="place"><span>位置：</span>
    <ul class="placeul">
        <li><a href="main.html">客房管理</a></li>
        <li><a href="#">客房类型管理</a></li>
    </ul>
</div>
<div class="rightinfo">
    <div class="tools">
        <ul class="toolbar">
            <li class="click"><span><img src="<%=basePath%>/images/t01.png"/></span>添加</li>
            <li class="click"><span><img src="<%=basePath%>/images/t02.png"/></span>修改</li>
            <li><span><img src="<%=basePath%>/images/t03.png"/></span>删除</li>
        </ul>

        <div class="toolbar1">
            <table>
                <form method="get" name="serch">
                    <tr>
                        <td class="zi"><span>选择分类：</span></td>
                        <td><select>
                            <option>房间号</option>
                            <option>客人姓名</option>
                            <option>手机号码</option>
                            <option>身份证号码</option>
                        </select></td>
                        <td class="zi"><span>关键字：</span></td>
                        <td><input type="text" placeholder="与分类关联"/></td>
                        <td><input type="submit" value="查询" class="button"/></td>
                    </tr>
                </form>
            </table>
        </div>

    </div>
    <table class="tablelist">
        <thead>
        <tr>
            <th><input name="" type="checkbox" value="" checked="checked"/></th>
            <th>编号</th>
            <th>房间类型</th>
            <th>房间单价</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${roomTypeList}" var="map" varStatus="num">
            <tr>
                <td><input name="" type="checkbox" value=""/></td>
                <td>${num.count}</td>
                <td>${map.room_type_name}</td>
                <td>${map.room_price}</td>
                <td>${map.use_status=='1'?'启用':"禁用"}</td>
                    <%--                <td>--%>
                    <%--                    <c:choose>--%>
                    <%--                        <c:when test="${map.is_admin==1}">--%>
                    <%--                            <a href=“javascript:void(0)” class="tablelink">无操作</a>--%>
                    <%--                        </c:when>--%>
                    <%--                        <c:otherwise>--%>
                    <%--                            <a href="<%=basePath%>/updateSystemUserStatus.do?userId=${map.id}&flag=${map.use_status=='1'?'0':"1"}"--%>
                    <%--                               class="tablelink">--%>
                    <%--                                    ${map.use_status=='1'?"禁用":'启用'}--%>
                    <%--                            </a>--%>
                    <%--                        </c:otherwise>--%>
                    <%--                    </c:choose>--%>
                    <%--                </td>--%>
            </tr>
        </c:forEach>
        <%--        <tr>--%>
        <%--            <td><input name="" type="checkbox" value=""/></td>--%>
        <%--            <td>1</td>--%>
        <%--            <td>8201</td>--%>
        <%--            <td>赵子龙</td>--%>
        <%--            <td><a href="#" class="tablelink">查看</a> <a href="#" class="tablelink"> 删除</a></td>--%>
        <%--        </tr>--%>

        </tbody>
    </table>
    <div class="pagin">
        <div class="message">共<i class="blue">1234</i>条记录，当前显示第&nbsp;<i class="blue">1&nbsp;</i>页</div>
        <ul class="paginList">
            <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
            <li class="paginItem current"><a href="javascript:;">1</a></li>
            <li class="paginItem"><a href="javascript:;">2</a></li>
            <li class="paginItem"><a href="javascript:;">3</a></li>
            <li class="paginItem"><a href="javascript:;">4</a></li>
            <li class="paginItem"><a href="javascript:;">5</a></li>
            <li class="paginItem more"><a href="javascript:;">...</a></li>
            <li class="paginItem"><a href="javascript:;">10</a></li>
            <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
        </ul>
    </div>
    <div class="tip">
        <div class="tiptop"><span>提示信息</span><a></a></div>
        <div class="tipinfo"><span><img src="<%=basePath%>/images/ticon.png"/></span>
            <%--            <div class="tipright">--%>
            <%--                <p>是否确认对信息的修改 ？</p>--%>
            <%--                <cite>如果是请点击确定按钮 ，否则请点取消。</cite></div>--%>
            <form action="">
                <ul class="">
                    <li>
                        <label style="cursor:pointer">房间类型名<b>*</b></label>
                        <div class="">
                            <input type="text" class="dfinput" style="width: 200px" name="room_type_name"/>
                        </div>

                    </li>
                    <li>
                        <label style="cursor:pointer">房间单价<b>*</b></label>
                        <div class="">
                            <input type="text" class="dfinput" style="width: 200px" name="room_price"/>
                        </div>
                    </li>
                </ul>
            </form>
        </div>
        <div class="tipbtn">
            <input name="" type="button" class="sure" value="确定"/>
            <input name="" type="button" class="cancel" value="取消"/>
        </div>
    </div>
</div>
<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
