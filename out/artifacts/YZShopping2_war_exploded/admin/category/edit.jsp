<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
</HEAD>

<body>
<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/category?type=updateCategory" method="post" >
	<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
		<tr>
			<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="2" height="26">
				<strong>编辑分类</strong>
			</td>
		</tr>
		<tr>
			<td width="30%" align="center" bgColor="#f5fafe" class="ta_01">
				分类名称：
			</td>
			<td class="ta_01" bgColor="#ffffff">
				<input type="text" name="cname" value="${category.cname}" id="userAction_save_do_logonName" class="bg"/>
			</td>
		</tr>
		<tr>
			<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="2">
				<button type="button" id="userAction_save_do_submit" value="确定" class="button_ok">
					确定
				</button>
				<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
				<button type="reset" value="重置" class="button_cancel">重置</button>
				<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
				<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
				<span id="Label1"></span>
			</td>
		</tr>
		<input type="hidden" value="${category.cid}" name="cid">
	</table>
</form>
<script>
	$(document).ready(function() {
		$('#userAction_save_do_submit').click(function(e) {
			e.preventDefault();
			$.ajax({
				type: 'POST',
				url: $('#userAction_save_do').attr('action'),
				data: $('#userAction_save_do').serialize(),
				success: function(data) {
					if(data == 'true') {
						alert('更新成功');
						location.href = "${pageContext.request.contextPath}/category?type=list";
					} else {
						alert('更新失败');
					}
				}
			});
		});
	});
</script>
</body>
</HTML>