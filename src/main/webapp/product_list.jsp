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
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />

<style>
body {
	margin-top: 20px;
	margin: 0 auto;
	width: 100%;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}
#content div{
	height: 240px;
	text-align: center;

}
</style>
</head>

<body>


	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>


	<div class="row" style="width: 1210px; margin: 0 auto;">
		<div class="col-md-12">
			<ol class="breadcrumb">
				<li><a href="#">首页</a></li>
			</ol>
		</div>
		<div id="content">
		</div>

	</div>

	<!--分页 -->
	<div style="width: 380px; margin: 0 auto; margin-top: 50px;">
		<ul class="pagination" style="text-align: center; margin-top: 10px;">


		</ul>
	</div>
	<!-- 分页结束 -->

	<!--商品浏览记录-->
	<div
		style="width: 1210px; margin: 0 auto; padding: 0 9px; border: 1px solid #ddd; border-top: 2px solid #999; height: 246px;">

		<h4 style="width: 50%; float: left; font: 14px/30px 微软雅黑">浏览记录</h4>
		<div style="width: 50%; float: right; text-align: right;">
			<a href="">more</a>
		</div>
		<div style="clear: both;"></div>

		<div style="overflow: hidden;">

			<ul style="list-style: none;">
				<li
					style="width: 150px; height: 216; float: left; margin: 0 8px 0 0; padding: 0 18px 15px; text-align: center;"><img
					src="products/1/cs10001.jpg" width="130px" height="130px" /></li>
			</ul>

		</div>
	</div>


	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>
<script>
	list(1);
	function list(page){
		$.get("${pageContext.request.contextPath}/product?type=productByCid&cid=${cid}&page="+page,function (data){
			$("#content").html("");
			if (data.list.length>0){
				for (var i=0;i<data.list.length;i++){
					var html="<div class=\"col-md-2\">";
					html+="<a href=\"product_info.htm\"> <img src=\""+data.list[i].pimage+"\"";
					html+="width=\"170\" height=\"170\" style=\"display: inline-block;\">";
					html+="</a>";
					html+="<p>";
					html+="<a href=\"product_info.html\" style='color: green'>"+data.list[i].pname+"</a>";
					html+="</p>";
					html+="<p>";
					html+="<font color=\"#FF0000\">商城价：&yen;"+data.list[i].shop_price+"</font>";
					html+="</p>";
					html+="</div>";
					$("#content").append(html);

				}
				var page="";
				//实现分页
				if(data.pageNo==1){
					//如果是第一页，点击上一页，无反映
					page+="<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>";
				}else{
					//如果不是第一页，点击上一页，当前页-1即上一页
					page+="<li><a href=\"javascript:list("+(data.pageNo-1)+")\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>";
				}
				//页码
				for(var i=1;i<=data.pageCount;i++){
					if(data.pageNo==i){
						page+="<li class=\"active\"><a href=\"javascript:list("+i+")\">"+i+"</a></li>";
					}else{
						page+="<li><a href=\"javascript:list("+i+")\">"+i+"</a></li>";
					}
				}
				//下一页
				if(data.pageNo==data.pageCount){
					//如果当前页是最后一页，点击下一页，无反映
					page+="<li class=\"disabled\"><a href=\"#\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li>";
				}else{
					//如果不是最后一页，点击下一页，当前页+1即下一页
					page+="<li> <a href=\"javascript:list("+(data.pageNo+1)+")\" aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span></a> </li>";
				}
				$(".pagination").html(page);
			}
		},"json")
	}
</script>

</body>

</html>