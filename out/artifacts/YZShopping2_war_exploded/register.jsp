<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head></head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员注册</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
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

font {
	color: #3164af;
	font-size: 18px;
	font-weight: normal;
	padding: 0 10px;
}
.red {
	color: red;
}
</style>
</head>
<body>

	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>

	<div class="container"
		style="width: 100%; background: url('image/regist_bg.jpg');">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8"
				style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
				<font>会员注册</font>USER REGISTER
				<form class="form-horizontal" style="margin-top: 5px;" method="post"
				action="${pageContext.request.contextPath}/user" id="myForm">
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="username" name="username"
								placeholder="请输入用户名">
							<span class="red" id="usernameTS"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="password" name="pwd"
								placeholder="请输入密码">
							<span class="red" id="passwordTS"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="confirmpwd"
								placeholder="请输入确认密码">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" id="inputEmail3"
								placeholder="Email">
						</div>
					</div>
					<div class="form-group">
						<label for="usercaption" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="usercaption"
								placeholder="请输入姓名">
						</div>
					</div>
					<div class="form-group opt">
						<label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-6">
							<label class="radio-inline"> <input type="radio"
								name="inlineRadioOptions" id="inlineRadio1" value="option1">
								男
							</label> <label class="radio-inline"> <input type="radio"
								name="inlineRadioOptions" id="inlineRadio2" value="option2">
								女
							</label>
						</div>
					</div>
					<%--<div class="form-group">
						<label for="date" class="col-sm-2 control-label">出生日期</label>
						<div class="col-sm-6">
							<input type="date" class="form-control">
						</div>
					</div>--%>

					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">验证码</label>
						<div class="col-sm-3">
							<input type="text" class="form-control">

						</div>
						<div class="col-sm-2">
							<img src="./image/captcha.jhtml" />
						</div>

					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<!--
							在form标签中的submit按钮，会直接提交form标签中的action请求地址，如果未使用action，请求的是当前页面
							如果不想提交action请求地址：使用button按钮，如果需要检验参数，可以按钮添加单击事件
							-->
							<input type="button" width="100" value="注册" name="submit" onclick="register()"
								style="background: url('./images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
						</div>
					</div>
				</form>
			</div>

			<div class="col-md-2"></div>

		</div>
	</div>

	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>
	<script>
		function register() {
			/*判断用户名和密码，判断用户名和密码是否符合条件
			*用户名不能为空，5-18位由字母数字下划线组成
			* 密码不能为空，5位数字
			* 密码和确认密码相同
			* 符号条件，提交表单保持用户名和密码
			*
			* ajax json
			*
			* json是一种数据格式
			*  var json=[0,1,2,3]; 取值：json[0]
			* var zs={'name':'张三','age':12}; 取值： zs.name
			* var students=[{"name":"张三","age":12},{"name":"张二","age":14},{"name":"张一","age":10}];
			* 取值：students[1].name
			* var classes={"count":3,"students":[{"name":"张三","age":12},{"name":"张二","age":14},{"name":"张一","age":10}]};
			* 取值：classes.students[1].name
			 */
			/*获取用户名和密码,判断用户名和密码是否符合条件*/
			var username = $("#username").val();//得到id=username对应的标签中，获取标签中value的属性值
			if(username==""||username==null){
				$("#usernameTS").html("用户名不能为空");
				return;//遇到return后面代码不执行
			}else{
				var reg=/^[a-zA-Z0-9_]{5,10}$/;
				if(reg.test(username)){
					$("#usernameTS").html("")
				}else{
					$("#usernameTS").html("用户名5-10由字母数字下划线组成");
					return;
				}
			}
			var pwd=$("#password").val();
			var regPwd=/[0-9]{5}/;
			if(regPwd.test(pwd)){
				$("#passwordTS").html("");
			}else {
				$("#passwordTS").html("密码5为数字");
				return;
			}
			var confirmpwd=$("#confirmpwd").val();
			if(pwd==confirmpwd) {
				$("#passwordTS").html("");
			}else{
				$("#passwordTS").html("两次密码不一致");
				return;
			}
			//执行到之一块表示前面的代码符合要求
			/*格式：$.post("地址"[,参数],回调函数[,dataType])*
			 */
			$.post("${pageContext.request.contextPath}/user",$("#myFrom").serialize(),function(data){
				if(data=="true"){
					location.href="${pageContext.request.contextPath}/login.jsp";
				}

			})
		}

	</script>

</body>
</html>




