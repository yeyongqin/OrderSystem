<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="applicable-device" content="mobile" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />

<link href="../css/public.css" rel="stylesheet" type="text/css" />
<link href="../css/user.css" rel="stylesheet" type="text/css">
<script src="../js/jquery-1.8.3.min.js"></script>
<script src="../layer/layer.js"></script>

<title>顾客个人中心</title>
</head>
<body>
	<div class="mobile">
		<!--header 开始-->
		<header>
			<div class="header">
				<a class="new-a-back" href="javascript:history.back();"> 
					<span><img src="../images/iconfont-fanhui.png"></span>
				</a>
				<h2>会员中心</h2>
				<div class="header_right shaixuan">
					<a href="../"><img src="../images/iconfont-shouye.png"></a>
				</div>
			</div>
		</header>
		<!--header 结束-->

		<div class="user_top w">
			<div class="user_logo">
				<div class="img">
					<img src="../images/user_logo.jpg">
				</div>
			</div>
			<div class="user_info">
				<div class="user_name">${loginUser.phone}</div>
			</div>
		</div>
		<div class="user_nav_list w">
			<ul>
				<li>
					<a href="#">
						<span class="u_nav_icon tixian"></span>
						<span class="u_nav_name">用户存款</span>
						<span class="nt_icon"></span>
						<span class="u_money"><i>${deposit }</i></span>
					</a>
				</li>

				<li>
					<a href="/foodOrderDetail/shoppingCar">
						<span class="u_nav_icon money"></span>
						<span class="u_nav_name">待付款</span> 
						<span class="nt_icon"></span>
					</a>
				</li>

				<li>
					<a href="#"> 
						<span class="u_nav_icon dingdan"></span>
						<span class="u_nav_name">已支付</span>
						<span class="nt_icon"></span>
						<span class="u_money"><i>${pNum }</i></span>
					</a>
				</li>

				<li>
					<a href="#"> 
						<span class="u_nav_icon dingdan"></span>
						<span class="u_nav_name">退款中</span>
						<span class="nt_icon"></span>
						<span class="u_money"><i>${riNum }</i></span>
					</a>
				</li>

				<li>
					<a href="#">
						<span class="u_nav_icon dingdan"></span>
						<span class="u_nav_name">已退款</span>
						<span class="nt_icon"></span>
						<span class="u_money"><i>${rdNum }</i></span>
					</a>
				</li>

				<li>
					<a href="/foodOrderDetail/orderCenter">
						<span class="u_nav_icon dingdan"></span>
						<span class="u_nav_name">订单中心</span>
						<span class="nt_icon"></span>
					</a>
				</li>
				
			</ul>
		</div>
		<div class="login_out">
			<a href="javascript:void(0);"><span><img src="../images/iconfont-tuichu.png"></span><i>安全退出</i></a>
		</div>
		<div class="copyright">Copyright © 2019-2020 ffcs 版权所有</div>
	</div>
</body>

<script type="text/javascript">
    /* 退出时的确认弹框 */
    $(document).ready(function(){
        $(".login_out").click(function(){
            layer.confirm('您确定要退出吗？',  function(index){
                layer.close(index);
                layer.msg('拜拜！欢迎下次光临！', {shift: 6, time: 1500},function(){
                    window.location='/user/zhuxiao';
                });
            });
        });
    });
</script>

</html>