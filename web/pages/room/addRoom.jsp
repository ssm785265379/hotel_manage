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
    <base href="<%=basePath%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>结账退房</title>
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
        <li><a href="#">客房管理</a></li>
        <li><a href="#">添加客房</a></li>
    </ul>
</div>
<div class="formbody">
    <div class="formtitle"><span>消费信息</span></div>
    <div id="usual1" class="usual">
        <div id="tab1" class="tabson">
            <ul class="forminfo">
                <li>
                    <label>房间号<b>*</b></label>
                    <div class="vocation">

                        <!--   <input name="" type="text" class="dfinput" value="请填写单位名称"  style="width:518px;"/>-->
                        <%--                        <select class="select1" name="roomid">--%>
                        <%--                            <option>8201</option>--%>
                        <%--                        </select>--%>
                        <input id=type="text" class="dfinput" name="roomId"/>

                    </div>
                </li>
                <br>
                <li style="margin-top:20px;" id="li_house_type">
                    <label>房间类型<b>*</b></label>
                    <div class="vocation">
                        <select id="room_type" name="room_type" class="select1" value="房间类型"
                                style="width:344px;">
                        </select>
                    </div>
                </li>
                <br/>
                <li>
                    <label>房间单价<b>*</b></label>
                    <div class="vocation">
                        <input id="room_price" type="text" class="dfinput" name="room_price" readonly="readonly"/>
                    </div>
                </li>
                <br/>

                <li>
                    <label>房间状态<b>*</b></label>
                    <div class="vocation">
                        <select id="room_status" class="select1" name="room_status">
                            <option>空闲</option>
                            <option>打扫</option>
                            <option>已入住</option>
                        </select>
                    </div>
                </li>
                <br/>

                <li>
                    <label>&nbsp;</label>
                    <input name="" type="submit" class="btn" value="提交"/>
                </li>
            </ul>
        </div>
    </div>
    <script type="text/javascript">
        $(function () {
            $("#room_type").mouseenter(function () {
                $.ajax({
                    url: "<%=basePath%>/getAllRoomType.do",
                    type: "post",
                    dataType: "json",
                    success: function (rs) {
                        var content = "";
                        for (var i in rs) {
                            <%--content += `${rs[i]}`--%>
                            content += `<option price='\${rs[i].room_price}' value='\${rs[i].id}'>\${rs[i].room_type_name}</option>`;
                        }
                        console.log(content)
                        $("#room_type").html(content)
                    }
                })
            })
        })

        $(function () {
            $("#room_type").change(function () {
                $("#room_price").val($(this).find("option:selected").attr("price"))
            })
        })


    </script>
    <script type="text/javascript">
        $("#usual1 ul").idTabs();
    </script>
    <script type="text/javascript">
        $('.tablelist tbody tr:odd').addClass('odd');
        !function () {
            laydate.skin('qianhuang');
            laydate({elem: '#Calendar'});
            laydate.skin('qianhuang');
            laydate({elem: '#Calendar2'});
        }();
        $(function dd() {
            var d = new Date(), str = "";
            str += (d.getFullYear() + "-");
            str += "0";
            str += (d.getMonth() + 1 + "-");
            str += d.getDate();
            $("#Calendar").attr("value", str);
            $("#Calendar2").attr("value", str);
        });
    </script>
</div>
</body>
</html>
