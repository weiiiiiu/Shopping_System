<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>会员登录</title>
	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
	<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<link rel="stylesheet" href="css/style.css" type="text/css" />

	<style>
		body {
			margin-top: 20px;
			margin: 0 auto;
		}

		.carousel-inner .item img {
			width: 100%;
			height: 300px;
		}

		.container .row div {
		}

		font {
			color: #666;
			font-size: 22px;
			font-weight: normal;
			padding-right: 17px;
		}
	</style>

	<script>
		$(document).ready(function() {
			var loginStatus = '<%=session.getAttribute("login_status")%>';
			if (loginStatus == 'failure') {
				alert('登录失败');
				<%session.removeAttribute("login_status");%>
			}

			// 从Cookie中获取用户名和密码
			var username = getCookie("username");
			var password = getCookie("password");
			if (username && password) {
				$("#username").val(username);
				$("#password").val(password);
			}
		});

		// 获取Cookie的函数
		function getCookie(name) {
			var cookies = document.cookie.split("; ");
			for (var i = 0; i < cookies.length; i++) {
				var cookie = cookies[i].split("=");
				if (cookie[0] == name) {
					return cookie[1];
				}
			}
			return "";
		}
	</script>

</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>
<div class="container"
	 style="width: 100%; height: 460px; background: #FF2C4C url('images/loginbg.jpg') no-repeat;">
	<div class="row">
		<div class="col-md-7">
		</div>

		<div class="col-md-5">
			<div
					style="width: 440px; border: 1px solid #E7E7E7; padding: 20px 0 20px 30px; border-radius: 5px; margin-top: 60px; background: #fff;">

				<div>&nbsp;</div>
				<form action="/YZShopping2_war_exploded/LoginServlet" method="post" id="form">
					<h1 id="loginMsg">用户登录</h1>
					<div id="errorMsg">${login_msg} ${register_msg}</div>
					<p>用户名:<input id="username" name="username" type="text"></p>
					<p>密码:<input id="password" name="password" type="password"></p>
					<p>记住我:<input id="remember" name="remember" value="1" type="checkbox"></p>
					<div id="subDiv">
						<input type="submit" class="button" value="登录">
						<input type="reset" class="button" value="重置">&nbsp;&nbsp;&nbsp;
						<a href="register.jsp">没有账号？</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>