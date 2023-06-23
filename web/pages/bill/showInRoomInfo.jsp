<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<% String basePath = request.getScheme() + "://" + request.getServerName()
        + ":" + request.getServerPort() + request.getContextPath();
%>
<html>
<head>
    <title>入住信息查询</title>

    <script src="/js/vue2.6.11.js"></script>
    <script src="https://unpkg.com/vue-router/dist/vue-router.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="/js/vue-resource1.5.1.js"></script>


</head>
<body>

<table class="tablelist" id="tb" border="1px" width="1000px">
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
    <tr v-for="(map,index) in roomList">
        <td><input name="" type="checkbox" value=""/></td>
        <td>{{index+1}}</td>
        <td>{{map.room_num}}</td>
        <td>{{map.room_type_name}}</td>
        <td>{{map.customer_name}}</td>
        <%--                <td>{{map.is_vip=='1'?'是':'否'}}</td>--%>
        <%--                <td>{{map.is_vip=='1'?map.vip_rate:'无'}}</td>--%>
        <td>{{map.gender=='1'?'男':'女'}}</td>
        <td>{{map.idcard}}</td>
        <td>{{map.phone}}</td>
        <td>{{map.money}}</td>
        <td>{{map.create_date}}</td>
        <td>
            <c:choose>
                <c:when test="{{map.out_room_status=='0'}">
                    <a href="out.jsp" class="tablelink">退房</a>
                </c:when>
                <c:otherwise>
                    <a href="javaScript:void(0)" class="tablelink">已退房</a>
                    <a href="#" class="tablelink"> 删除</a>
                </c:otherwise>
            </c:choose>

        </td>
    </tr>
    </tbody>
</table>
<script type="text/javascript">
    const vue1 = new Vue({
        el: '#tb',
        data: {
            roomList: [],
        },
        computed: {
            fmtData() {

            }
        },
        mounted() {
            this.$http.post('<%=basePath%>/getInRoomInfoByVue.do').then(
                function (rs) {
                    // console.log(rs)
                    this.roomList=rs.body
                },
                function (err){
                    console.log("出错了")
                }
            )
        }
    })
</script>
</body>
</html>
