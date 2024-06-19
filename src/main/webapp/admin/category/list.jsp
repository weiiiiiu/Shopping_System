<%@ page language="java" pageEncoding="UTF-8"%>
<HTML>
<HEAD>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/css/Style1.css"
		  rel="stylesheet" type="text/css" />
	<script language="javascript"
			src="${pageContext.request.contextPath}/js/public.js"></script>
	<script src ="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript">
		function addCategory(){
			window.location.href = "${pageContext.request.contextPath}/admin/category/add.jsp";
		}
	</script>
</HEAD>
<body>
<br>
<form id="Form1" name="Form1"
	  action="${pageContext.request.contextPath}/userst.jsp"
	  method="post">
	<table cellSpacing="1" cellPadding="0" width="100%" align="center"
		   bgColor="#f5fafe" border="0">
		<TBODY>
		<tr>
			<td class="ta_01" align="center" bgColor="#afd1f3"><strong>分类列表</strong>
			</TD>
		</tr>
		<tr>
			<td class="ta_01" align="right">
				<button type="button" id="add" name="add" value="添加"
						class="button_add" onclick="addCategory()">
					&#28155;&#21152;</button>

			</td>
		</tr>
		<tr>
			<td class="ta_01" align="center" bgColor="#f5fafe">
				<table cellspacing="0" cellpadding="1" rules="all"
					   bordercolor="gray" border="1" id="DataGrid1"
					   style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
					<tr
							style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

						<td align="center" width="18%">序号</td>
						<td align="center" width="17%">分类名称</td>
						<td width="7%" align="center">编辑</td>
						<td width="7%" align="center">删除</td>
					</tr>
					<tbody id="content">

					</tbody>

				</table>
			</td>
		</tr>

		</TBODY>
	</table>
</form>
<script>
	list();
	function list(){
		$.get("${pageContext.request.contextPath}/category?type=list",function(data){
			$("#content").empty();
			if(data.length>0){
				for(var i=0;i<data.length;i++){
					var html="<tr onmouseover=\"this.style.backgroundColor = 'white'\" onmouseout=\"this.style.backgroundColor = '#F5FAFE';\">"
							+"<td style=\"CURSOR: hand; HEIGHT: 22px\" align=\"center\" width=\"18%\">"+(i+1)+"</td>"
							+"<td style=\"CURSOR: hand; HEIGHT: 22px\" align=\"center\" width=\"17%\">"+data[i].cname+"</td>"
							+"<td align=\"center\" style=\"HEIGHT: 22px\"><a href=\"${pageContext.request.contextPath}/admin/category/edit.jsp?cid="+data[i].cid+"\">"
							+"<img src=\"${pageContext.request.contextPath}/images/i_edit.gif\" border=\"0\" style=\"CURSOR: hand\"> </a></td>"
							+"<td align=\"center\" style=\"HEIGHT: 22px\"><a href=\"javascript:void(0)\"onclick=\"del('"+data[i].cid+"')\"> "
							+"<img src=\"${pageContext.request.contextPath}/images/i_del.gif\" width=\"16\" height=\"16\" border=\"0\" style=\"CURSOR: hand\"></a></td>";
					+"</tr>";
					$("#content").append(html);
				}
			}
		},"json")
	}
	function del(cid) {
		if (confirm("是否确认删除")){
			//请求接口，删除数据
			$.post("${pageContext.request.contextPath}/category?type=del",{cid:cid},function(data){
				if(data=="true"){
					list();//删除成功，更新页面
				}
			})
		}

	}
</script>
</body>
</HTML>