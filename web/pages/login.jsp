﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<% String basePath = request.getScheme() + "://" + request.getServerName()
        + ":" + request.getServerPort() + request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>欢迎登录后台管理系统</title>
    <link href="<%=basePath%>/css/style.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" src="<%=basePath%>/js/jquery-2.1.4.min.js"></script>
    <script src="<%=basePath%>/js/cloud.js" type="text/javascript"></script>

    <script language="javascript">
        $(function () {
            $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            $(window).resize(function () {
                $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            })
        });
    </script>

</head>

<body style="background-color:#df7611; background-image:url(<%=basePath%>/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">


<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>


<div class="logintop">
    <span>欢迎登录酒店客房管理界面平台</span>
</div>

<div class="loginbody">

<%--    <span class="systemlogo"></span>--%>

    <div class="loginbox">
        <form action="<%=basePath%>/login.do" method="post">
            <ul>
                <li><input name="username" type="text" class="loginuser" value="admin"
                           onclick="JavaScript:this.value=''"/>
                </li>
                <li><input name="pwd" type="text" class="loginpwd" value="密码" onclick="JavaScript:this.value=''"/></li>
                <li>
                    <input name="" type="submit" class="loginbtn" value="登录 "/>
                    <label><input name="" type="checkbox" value="" checked="checked"/>记住密码</label>
                    <label><a href="#">忘记密码？</a></label>
                </li>
            </ul>
        </form>
    </div>

</div>


<!--<div class="loginbm">版权所有  2014  <a href="http://www.uimaker.com">uimaker.com</a>  仅供学习交流，勿用于任何商业用途</div>-->


</body>

</html>
