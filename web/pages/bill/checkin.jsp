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
        <li><a href="#">入住信息管理</a></li>
        <li><a href="#">添加入住信息</a></li>
    </ul>
</div>
<div class="formbody">
    <div class="formtitle"><span>入住信息</span></div>
    <div id="usual1" class="usual">
        <div id="tab1" class="tabson">
            <ul class="forminfo">
                <li>
                    <label>房间号<b>*</b></label>
                    <div class="vocation">
                        <!--   <input name="" type="text" class="dfinput" value="请填写单位名称"  style="width:518px;"/>-->
                        <select id="ruzhuroomId" name="roomId" class="select1">
                            <option roomnum="" value=""></option>
                            <c:forEach items="${roomList}" var="map">
                                <option roomnum="${map.room_num}" value="map.id">${map.room_num}</option>
                            </c:forEach>
                        </select>
                    </div>
                </li>
                <br/>
                <li style="margin-top:20px;" id="li_house_type">
                    <label>房间类型<b>*</b></label>
                    <div class="vocation">
                        <select name="room_type" class="select1" value="房间类型" style="width:344px;">

                        </select>
                    </div>
                </li>

                <li style="margin-top:20px;">
                    <label for="name">客人姓名<b>*</b></label>
                    <div class="vocation">
                        <input name="name" type="text" class="dfinput" placeholder="请填写客户姓名" style="width:344px;"/>
                    </div>
                </li>
                <br/>
                <li>
                    <label for="sex">性别<b>*</b></label>
                    <input name="sex" type="radio" value="男" checked="checked"/>
                    男&nbsp;&nbsp;&nbsp;&nbsp;
                    <input name="sex" type="radio" value="女"/>
                    女
                </li>
                <br/>
                <li><cite>
                    <label for="sex">会员<b>*</b></label>
                    <input name="sex" type="radio" value="是" checked="checked"/>
                    是&nbsp;&nbsp;&nbsp;&nbsp;
                    <input name="sex" type="radio" value="否"/>
                    否</cite></li>
                <br/>
                <li>
                    <label for="name">身份证号码<b>*</b></label>
                    <div class="vocation">
                        <input name="id" type="text" class="dfinput" placeholder="请填写客户身份证号码" style="width:344px;"/>
                    </div>
                </li>
                <br/>
                <li>
                    <label for="name">手机号码<b>*</b></label>
                    <div class="vocation">
                        <input name="phone" type="text" class="dfinput" placeholder="请填写客户手机号码" style="width:344px;"/>
                    </div>
                </li>
                <br/>
                <li>
                    <label for="name">押金<b>*</b></label>
                    <div class="vocation">
                        <input name="money" type="text" class="dfinput" placeholder="输入押金金额" style="width:344px;"/>
                    </div>
                </li>
                <br/>
                <li>
                    <label for="date">入住时间<b>*</b></label>
                    <div class="vocation">
                        <input type="text" class="laydate-icon span1-1" id="Calendar"
                               style="width:324px; height:30px; line-height:28px; text-indent:10px; "/>
                    </div>
                </li>
                <br/>
                <li>
                    <label>&nbsp;</label>
                    <input name="" type="button" class="btn" value="入住"/>
                </li>
            </ul>
        </div>
    </div>

    <script type="text/javascript">
        <%--        选中房间号自动添加房价类型--%>
        $(function () {
            $("select[name='roomId']").change(function () {
                // alert($(this).find("option:selected"))
                var roomnum = $(this).find("option:selected").attr("roomnum")
                $.ajax({
                    url: "<%=basePath%>/getRoomTypeByRoomNum.do",
                    type: "get",
                    dataType: "json",
                    data: {'roomNum': roomnum},
                    success: function (rs) {
                        var content = "<option roomnum='' value=''></option>";
                        for (var i in rs) {
                            content += `<option value='\${rs[i].room_type_id}' selected="selected">\${rs[i].room_type_name}</option>`;
                        }
                        $("select[name='room_type']").html(content)
                    },
                    error: function (err) {
                        alert("失败")
                    }
                })
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
