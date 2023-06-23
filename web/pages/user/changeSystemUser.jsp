<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<% String basePath = request.getScheme() + "://" + request.getServerName()
        + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <link href="<%=basePath%>/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>/css/select.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=basePath%>/js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/select-ui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/editor/kindeditor.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/laydate/laydate.js"></script>
    <script type="text/javascript">
        KE.show({
            id: 'content7',
            cssPath: './index.css'
        });
    </script>
    <script type="text/javascript">
        $(document).ready(function (e) {
            $(".select1").uedSelect({
                width: 345
            });
            $(".select2").uedSelect({
                width: 167
            });
            $(".select3").uedSelect({
                width: 100
            });
        });

    </script>
</head>

<body>
<div class="place"><span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">系统用户管理</a></li>
        <li><a href="#">修改用户</a></li>
    </ul>
</div>
<div class="formbody">
    <div class="formtitle"><span>修改用户</span></div>
    <div id="usual1" class="usual">
        <div id="tab1" class="tabson">
            <ul class="forminfo">
                <li>
                    <label for="username">系统账号<b>*</b></label>
                    <div class="vocation">
                        <input id="username" name="username" type="text" class="dfinput" placeholder="请填写系统账号"
                               style="width:344px;"/>
                    </div>
                </li>
                <br/><br/><br/>


<%--                <li>--%>
<%--                    <label for="pwd">密码<b>*</b></label>--%>
<%--                    <div class="vocation">--%>
<%--                        <input id="pwd" name="pwd" type="text" class="dfinput" placeholder="请填写密码"--%>
<%--                               style="width:344px;"/>--%>
<%--                    </div>--%>
<%--                </li>--%>
<%--                <br/>--%>
<%--                <li>--%>
<%--                    <label for="idcard">身份证号<b>*</b></label>--%>
<%--                    <div class="vocation">--%>
<%--                        <input id="idcard" name="idcard" type="text" class="dfinput" placeholder="请填写身份证号"--%>
<%--                               style="width:344px;"/>--%>
<%--                    </div>--%>
<%--                </li>--%>
<%--                <br/>--%>
<%--                <li>--%>
<%--                    <label for="phone">手机号码<b>*</b></label>--%>
<%--                    <div class="vocation">--%>
<%--                        <input id="phone" name="phone" type="text" class="dfinput" placeholder="请填写手机号码"--%>
<%--                               style="width:344px;"/>--%>
<%--                    </div>--%>
<%--                </li>--%>
<%--                <br/>--%>

                <li>
                    <label for="">员工身份<b>*</b></label>
                    <select style="opacity: 100;width: 100px;margin-top: 5px;margin-left: 0" class="vocation" name="position">
                        <c:forEach items="${positionList}" var="position">
                            <option value="${position.id}">${position.position}</option>
                        </c:forEach>
                    </select>
                </li>
                <br/>

                <li>
                    <label>权限<b>*</b></label>
                    <div class="vocation">
                        <table border="1px" width="800">
                            <c:forEach items="${sqAuthorityList}" var="oneMenu">
                                <tr>
                                        <%--一级--%>
                                    <td>${oneMenu.oneName}</td>
                                    <td>
                                        <c:forEach items="${oneMenu.twoMenuList}" var="twoMenu">
                                            <input oneId="${oneMenu.oneId}" type="checkbox" name="twoId"
                                                   value="${twoMenu.twoId}"/>${twoMenu.twoName}
                                        </c:forEach>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </li>


                <li style="margin-top:20px">
                    <label>&nbsp;</label>
                    <input style="margin-top: 20px" name="" type="submit" class="btn" value="提交"/>
                </li>
            </ul>
        </div>
    </div>
    <script type="text/javascript">

        $("#usual1 ul").idTabs();
    </script>
    <script type="text/javascript">
        $(function () {
            $(".btn").click(function () {
                $(this).attr("disabled", "disabled")
                var $cks = $(".vocation input[type=checkbox]:checked")
                var oneIds = ""
                var twoIds = ""
                $cks.each(function (index, dom) {
                    var $c = $(dom);
                    oneIds += $c.attr("oneId") + ","
                    twoIds += $c.val() + ","
                })
                // alert(oneIds)
                // alert(twoIds)
                $.ajax({
                    url: '<%=basePath%>/changeSystemUser.do',
                    dataType: "json",
                    type: "get",
                    data: {
                        "username": $("input[name=username]").val(),
                        "position": $("select[name=position]").find('option:selected').val(),
                        "oneIds": oneIds,
                        "twoIds": twoIds
                    },
                    success: function (rs) {
                        alert(rs);
                        if (rs) {
                            window.location.href = "<%=basePath%>/pages/user/systemUserManage.html"
                        }
                    },
                    error: function () {
                        window.location.href = "<%=basePath%>/pages/error/error.jsp"
                    }
                })

            })
        });


    </script>
</div>
</body>
</html>
