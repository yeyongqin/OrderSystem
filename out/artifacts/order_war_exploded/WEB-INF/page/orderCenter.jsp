<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="applicable-device" content="mobile"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>

    <link href="../css/public.css" rel="stylesheet" type="text/css"/>
    <link href="../css/user.css" rel="stylesheet" type="text/css">
    <script src="../js/jquery-1.8.3.min.js"></script>
    <script src="../layer/layer.js"></script>

    <script type="text/javascript">
        <%--未登录跳转至登录页面--%>
        if ('' == '${loginUser}' || '${loginUser}' == null) {
            window.location = "../user/toUserLogin";
        }
    </script>

    <title>订单中心</title>
</head>
<body>
<div class="mobile">
    <header>
        <div class="header">
            <a class="new-a-back" href="javascript:history.back();">
                <span><img src="../images/iconfont-fanhui.png"></span>
            </a>
            <h2>订单中心</h2>
            <div class="header_right shaixuan">
                <a href="../"><img src="../images/iconfont-shouye.png"></a>
            </div>
        </div>
    </header>

    <table style="width: 96%;margin: auto;border: 0px;" class="needToDisPlay">
        <thead>
        <tr>
            <td style="width: 1%;height: 50px;padding-left: 40px;text-align: center;">订单号</td>
            <td style="width: 10%;text-align: center;">创建时间</td>
            <td style="width: 10%;text-align: center;">订单状态</td>
            <td style="width: 10%;text-align: center;">操作</td>
        </tr>
        </thead>
        <c:forEach var="foodOrderRecords" items="${foodOrderRecordsList }">
            <tr>
                <td style="padding-left: 40px" id="recordsNo">${foodOrderRecords.recordsNo }</td>
                <td style="text-align: center;">${foodOrderRecords.createTime }</td>
                <td style="text-align: center;">${foodOrderRecords.orderState }</td>
                <td style="text-align: center;">
                    <a href="javascript:void(0);" class="seeOrder"
                       onclick="showDetail(${foodOrderRecords.id })">订单详细</a>
                    <a id="cancelOrder" href="javascript:void(0);" class="cancelOrder"
                       onclick="cancelDetail(${foodOrderRecords.id })">取消订单</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="5">
                <hr>
            </td>
        </tr>
    </table>

    <div class="copyright">Copyright © 2019-2020 ffcs 版权所有</div>
</div>
</body>
<script type="text/javascript">

    // 订单详细
    function showDetail(data) {
        var id = data;
        var index = layer.open(
            {
                type: 2
                , title: '订单详细'
                , offset: 'auto'
                , area: [$(window).width() * 0.4 + 'px', $(window).height() - 50 + 'px']
                , fix: false
                , maxmin: true
                , shadeClose: true
                , shade: 0.4
                , id: 'layer'
                , content: "/foodOrderDetail/orderDetail?id=" + id
                , yes: function () {
                    layer.closeAll();
                }
            });
    }

    // 取消订单
    function cancelDetail(data) {
        $.ajax({
            type: "Get",
            url: "/foodOrderDetail/cancelOrder?id=" + data,
            success: function (data) {
                layer.msg(data, function () {
                    window.location = "orderCenter";
                });
            },
            error: function () {
                console.log("取消失败，请联系管理员");
            }
        });
    }

</script>

</html>