<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta name="format-detection" content="telephone=no">
    <title>ffcs点餐系统</title>
    <link href="css/owl.carousel.css" rel="stylesheet">
    <link href="css/public.css" rel="stylesheet" type="text/css"/>
    <link href="css/index.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="/static/common/layui/css/layui.css" media="all">
    <script src="js/jquery-1.8.3.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="layer/layer.js"></script>
    <script src="/static/common/layui/layui.js"></script>

    <script type="text/javascript">
        /* 用户搜索 */
        $(document).ready(function () {
            $("#userSearch").click(function () {
                window.location = "food/userSearch?key=" + $('#key').val();
            });
        });
    </script>
</head>

<body>
<div class="mobile">

    <%--导航条--%>
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header layui-bg-green">
            <div class="layui-logo" style="color: white">福富订餐网</div>
            <!-- 头部区域 -->
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item"><a href="">你想要的美食，这里都有</a></li>
            </ul>
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item"><a href="">管理员入口</a></li>
            </ul>
        </div>
    </div>

    <div class="top w">
        <!-- 轮播广告 -->
        <div class="layui-carousel" id="lunbo">
            <div carousel-item>
                <div><img src="images/lunbo/foodlunbo4.jpg" style="height: 100%;width: 100%"></div>
                <div><img src="images/lunbo/foodlunbo3.jpg" style="height: 100%;width: 100%"></div>
            </div>
        </div>
    </div>

    <!-- 类目栏 -->
    <div class="top w">
        <div class="m_nav">
            <a href="/user/toCustomerCenter" id="customerCenter"><img src="images/m-index_27.png"><span>个人中心</span></a>
            <a href="/food/menu"><img src="images/m-index_26.png"><span>菜品分类</span></a>
            <a id="myShoppingCar" href="foodOrderDetail/shoppingCar"><img
                    src="images/m-index_26.png"><span>购物车</span></a>
            <a href="user/toNotice"><img src="images/m-index_26.png"><span>店铺公告</span></a>
        </div>
    </div>

    <!-- 食客最爱栏目 -->
    <div class="m_mall w">
        <div class="mall_title">
            <span>食客最爱</span>
        </div>
        <div class="mall_list">
            <c:forEach var="favouriteFood" items="${favouriteFoodList }">
                <a href="javascript:void(0);" class="mall">
                    <span class="mall_logo"><img src="${favouriteFood.foodImage}"
                                                 style="width: 300px;height: 200px"/></span><br/>
                    <span>${favouriteFood.foodName}</span><br/>
                    <span>${favouriteFood.price}元</span><br/>
                    <span><i class="doAddShoppingCar" data-foodid='${favouriteFood.id}'>加入购物车</i></span>
                </a>
            </c:forEach>
        </div>
    </div>

    <!-- 每日推荐栏目 -->
    <div class="m_mall w">
        <div class="mall_title">
            <span>每日推荐</span>
        </div>
        <div class="mall_list">
            <c:forEach var="recomendFood" items="${recomendFoodList }">
                <a href="javascript:void(0);" class="mall">
                    <span class="mall_logo"><img src="${recomendFood.foodImage}"
                                                 style="width: 300px;height: 200px"/></span><br/>
                    <span>${recomendFood.foodName}</span><br/>
                    <span>${recomendFood.price}元</span><br/>
                    <span><i class="doAddShoppingCar" data-foodid='${recomendFood.id}'>加入购物车</i></span>
                </a>
            </c:forEach>
        </div>
    </div>

    <!-- 菜谱栏目 -->
    <div class="m_mall w">
        <div class="mall_title">
            <span>菜谱</span>
        </div>
        <div class="mall_list">
            <c:forEach var="foods" items="${foodsList }">
                <a href="javascript:void(0);" class="mall">
                    <span class="mall_logo"><img src="${foods.foodImage}"
                                                 style="width: 300px;height: 200px"/></span><br/>
                    <span>${foods.foodName}</span><br/>
                    <span>${foods.price}元</span><br/>
                    <span><i class="doAddShoppingCar" data-foodid='${foods.id}'>加入购物车</i></span>
                </a>
            </c:forEach>
        </div>
    </div>

    <div class="copyright">Copyright © 2019-2020 ffcs 版权所有</div>
</div>

</body>
<script type="text/javascript">

    layui.use('carousel', function () {
        var carousel = layui.carousel;
        //建造实例
        carousel.render({
            elem: '#lunbo'
            , width: '100%' //设置容器宽度
            , height: '50%' //设置容器高度
            , arrow: 'always' //始终显示箭头
        });
    });

    /* 加入购物车 */
    $(document).ready(function () {
        $(".doAddShoppingCar").click(function () {
            if ('' == '${loginUser}' || '${loginUser}' == null) {
                var index = layer.open(
                    {
                        type: 1,
                        title: false,
                        closeBtn: false,
                        shadeClose: true,
                        offset: '25%',
                        content: "<div class='no_login_show'>"
                        + "<h1>亲！您还没登录哦！</h1>"
                        + "<a href='../user/toUserLogin'>马上登录</a>"
                        + "<a href='../user/toRegister'>免费注册</a>"
                        + "<a href='javascript:layer.closeAll();'>取消</a>"
                        + "</div>"
                    });
            } else {
                $.ajax({
                    type: "POST",
                    data: {foodId: $(this).data('foodid')},
                    url: "/foodOrderDetail/doAddShoppingCar",
                    success: function (data) {
                        parent.layer.msg(data);
                    },
                    error: function () {
                        console.log("请求失败");
                    }
                });
            }
        });
    });

</script>
</html>
