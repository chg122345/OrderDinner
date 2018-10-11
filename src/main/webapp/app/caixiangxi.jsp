<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath }/app/detail/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/app/detail/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath }/app/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/app/detail/style/css/index_1.css" />
	<link href="${pageContext.request.contextPath }/app/detail/style/css/index.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/app/detail/style/css/dis_message.css" />
</head>
<body style="text-align: center">
	<div id="all">
		<!--左边菜品详细信息 -->
		<div id="menu1">
			<div class="menu2" style="text-align:center;">
				<img src="${pageContext.request.contextPath }/app/detail/style/images/order_detials_bg.png" />
			</div>
			<div class="menu3">
				<div class="menu3_left">
					<img src="${pageContext.request.contextPath }/app/detail/style/images/baizhuoxia.jpg"
						style="width:270px; height:290px;" />
				</div>
				<div class="menu3_right">
					<p>菜名:${requestScope.food.foodName}</p>
					<p>价格:&nbsp;&nbsp;&yen;&nbsp;${requestScope.food.price}</p>
					<p>简介:${requestScope.food.remark}</p>
				</div>
			</div>
			<div class="menu4">
				
				<a id="subCart" lang="${requestScope.food.id}" href="#" onclick="subCart(this)" style="background:url(${pageContext.request.contextPath }/app/detail/style/images/img/order_left_corner_bg.png);">放入餐车</a>
				<a href="#" onclick="javascript:history.go(-1);" style="background:url(${pageContext.request.contextPath }/app/detail/style/images/img/order_left_corner_bg.png);">返回</a>
			</div>
		</div>
		
		<!-- 右边菜系列表，菜品搜索框  -->
		<div id="dish_class">
			<div id="dish_top">
				<ul>
				<li class="dish_num"></li>
					<li>
						<a href="${pageContext.request.contextPath }/order">
							<img src="${pageContext.request.contextPath }/app/detail/style/images/call2.gif" />
						</a>
					</li>
				</ul>
			</div>

			<div id="dish_2">
				<ul>

					<c:forEach var="foodType" items="${sessionScope.listFoodType}">
						<li>
							<a href="${pageContext.request.contextPath }/foodType?page=1&id=${foodType.id}">${foodType.typeName }</a>
							<input type="hidden" name="foodTypeId" value="${foodType.id}">
						</li>
					</c:forEach>
					
				</ul>
			</div>
			<div id="dish_3">
				<!-- 搜索菜品表单  -->
				<form action="#" method="post">
					<table width="166px">
						<tr>
							<td>
								<input type="text" id="dish_name" name="foodName" class="select_value" /> 
							</td>
						</tr>
						<tr>
							<td><input type="submit" id="sub" value="" /></td>
						</tr>
						<tr>
							<td>
                                <a href="${pageContext.request.contextPath }/look">
									<img src="${pageContext.request.contextPath }/app/detail/style/images/look.gif" />
								</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
<script>
    function subCart(node){
        var id = node.lang;
        $.ajax({
			url: "${pageContext.request.contextPath }/clientCart",
			data:{id :id},
			success: function (res) {
				alert(res);
            }
		});

	}
</script>
</body>
</html>
