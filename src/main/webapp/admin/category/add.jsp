<%@ page language="java" pageEncoding="UTF-8"%>
<HTML>
<HEAD>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#userAction_save_do').on('submit', function(e) {
				e.preventDefault();
				$.ajax({
					type: 'post',
					url: $(this).attr('action'),
					data: $(this).serialize(),
					success: function(response) {
						alert(response);
						// 重新加载数据
						CategoryList();
					}
				});
			});
		});

		function CategoryList(){
			$.get("${pageContext.request.contextPath}/category?type=list",function (data){
				if(data.length>0){
					// 清空原有数据
					$(".cid").empty();
					for (var i=0;i<data.length;i++){
						var html="<option value=\""+data[i].cid+"\">"+data[i].cname+"</option>";
						$(".cid").append(html);
					}
				}
			},"json")
		}
	</script>
</HEAD>

<body>
<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/category?type=add"
	  method="post">
	&nbsp;
	<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
		<tr>
			<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
				height="26">
				<strong><STRONG>添加分类</STRONG>
				</strong>
			</td>
		</tr>

		<tr>
			<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
				分类名称：
			</td>
			<td class="ta_01" bgColor="#ffffff" colspan="3">
				<input type="text" name="cname" value="" id="userAction_save_do_logonName" class="bg"/>
			</td>
		</tr>

		<tr>
			<td class="ta_01" style="WIDTH: 100%" align="center"
				bgColor="#f5fafe" colSpan="4">
				<button type="submit" id="userAction_save_do_submit" value="确定" class="button_ok">
					确定
				</button>

				<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
				<button type="reset" value="重置" class="button_cancel">重置</button>

				<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
				<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
				<span id="Label1"></span>
			</td>
		</tr>
	</table>
</form>
</body>
</HTML>