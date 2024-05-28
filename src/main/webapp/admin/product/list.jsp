<%@ page language="java" pageEncoding="UTF-8"%>
<HTML>
<HEAD>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="textml; charset=UTF-8">
	<link href="${pageContext.request.contextPath}s/Style1.css"
		  rel="stylesheet" type="texts" />
	<script language="javascript"
			src="${pageContext.request.contextPath}/js/public.js"></script>
	<script src ="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript">
		function addProduct(){
			window.location.href = "${pageContext.request.contextPath}/admin/product/add.jsp";
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
			<td class="ta_01" align="center" bgColor="#afd1f3"><strong>商品列表<>
			</TD>
		</tr>
		<tr>
			<td class="ta_01" align="right">
				<button type="button" id="add" name="add" value="添加"
						class="button_add" onclick="addProduct()">
					&#28155;&#21152;<tton>

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
						<td align="center" width="17%">商品图片</td>
						<td align="center" width="17%">商品名称</td>
						<td align="center" width="17%">商品价格</td>
						<td align="center" width="17%">是否热门</td>
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
		$.get("${pageContext.request.contextPath}/product?type=list",function(data){
			$("#content").empty();
			//console.log(data)
			if(data.length>0){
				for(var i=0;i<data.length;i++){
					var isHot="否";
					if(data[i].is_hot==1){
						isHot="是";
					}
					var html="<tr onmouseover=\"this.style.backgroundColor = 'white'\" onmouseout=\"this.style.backgroundColor = '#F5FAFE';\">"
							+"<td style=\"CURSOR: hand; HEIGHT: 22px\" align=\"center\" width=\"18%\">"+(i+1)+"</td>"
							+"<td style=\"CURSOR: hand; HEIGHT: 22px\" align=\"center\" width=\"17%\"><img width=\"40\" height=\"45\" src=\"${pageContext.request.contextPath}/"+
							data[i].pimage+"\"></td>"
							+"<td style=\"CURSOR: hand; HEIGHT: 22px\" align=\"center\" width=\"17%\">"+data[i].pname+"</td>"
							+"<td style=\"CURSOR: hand; HEIGHT: 22px\" align=\"center\" width=\"17%\">"+data[i].shop_price+"</td>"
							+"<td style=\"CURSOR: hand; HEIGHT: 22px\" align=\"center\" width=\"17%\">"+isHot+"</td>"
							+"<td align=\"center\" style=\"HEIGHT: 22px\"><a href=\"${pageContext.request.contextPath}/product?type=update&pid="+data[i].pid+"\">"
							+"<img src=\"${pageContext.request.contextPath}/images/i_edit.gif\" border=\"0\" style=\"CURSOR: hand\"> </a></td>"
							+"<td align=\"center\" style=\"HEIGHT: 22px\"><a href=\"javascript:void(0)\"onclick=\"del('"+data[i].pid+"')\"> "
							+"<img src=\"${pageContext.request.contextPath}/images/i_del.gif\" width=\"16\" height=\"16\" border=\"0\" style=\"CURSOR: hand\"></a></td>";
					+"</tr>";
					$("#content").append(html);
				}
			}
		},"json")
	}
	function del(pid) {
		if (confirm("是否确认删除")){
			//请求接口，删除数据
			$.post("${pageContext.request.contextPath}/product?type=del",{pid:pid},function(data){
				if(data=="true"){
					list();//删除成功，更新页面
				}
			})
		}

	}


</script>
</body>
</HTML>
