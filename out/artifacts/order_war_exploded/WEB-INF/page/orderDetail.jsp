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

</head>
<body>
<div class="mobile">
    <table style="width: 96%;margin: auto;border: 0px;" class="needToDisPlay">
        <thead>
        <tr>
            <td style="width: 60%;height: 50px;padding-left: 40px">菜名</td>
            <td style="width: 10%;text-align: center;">数量</td>
            <td style="width: 14%;text-align: center;">价格</td>
        </tr>
        </thead>
        <c:forEach var="myShoppingCarFood" items="${myShoppingCarFoodList }">
            <tr>
                <td style="padding-left: 40px">${myShoppingCarFood.foodName }</td>
                <td style="text-align: center;">${myShoppingCarFood.orderCount }</td>
                <td style="text-align: center;">${myShoppingCarFood.total } ￥</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="5">
                <hr>
            </td>
        </tr>
        <tr>
            <td style="text-align: center;">总价</td>
            <td></td>
            <td></td>
            <td></td>
            <td style="text-align: center;">${money }</td>
        </tr>
        <tr>
            <td colspan="5">
                <hr>
            </td>
        </tr>
    </table>

    <div class="copyright">Copyright © 2019-2020 ffcs 版权所有</div>
</div>
</body>

</html>