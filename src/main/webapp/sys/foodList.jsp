<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath }/sys/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/sys/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath }/sys/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/sys/style/css/index_1.css" />
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="${pageContext.request.contextPath }/sys/style/images/title_arrow.gif"/> 菜品列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


	<!-- 过滤条件 -->
	<div id="QueryArea">
		<form action="/foodType?method=list" method="get">
			<input type="hidden" name="method" value="search">
			<input type="text" name="keyword" title="请输入菜品名称">
			<input type="submit" value="搜索">
		</form>
	</div>
<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>菜编号</td>
				<td>菜名</td>
				<td>所属菜系</td>
				<td>价格</td>
                <td>会员价格</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
		
			<tr class="TableDetail1">
				<td>1&nbsp;</td>
				<td>白灼虾&nbsp;</td>
				<td>粤菜&nbsp;</td>
				<td>36.0&nbsp;</td>
                <td>23.0&nbsp;</td>
				<td>
					<a href="updateFood.jsp"  class="FunctionButton">更新</a>				
					<a href="/sys/foodList.jsp?method=delete&id=1" onClick="return delConfirm();"class="FunctionButton">删除</a>				
				</td>
			</tr>
        
			<tr class="TableDetail1">
				<td>2&nbsp;</td>
				<td>白切鸡&nbsp;</td>
				<td>粤菜&nbsp;</td>
				<td>68.0&nbsp;</td>
                <td>50.0&nbsp;</td>
				<td>
					<a href="updateFood.jsp"  class="FunctionButton">更新</a>				
					<a href="/sys/foodList.jsp?method=delete&id=2" onClick="return delConfirm();"class="FunctionButton">删除</a>				
				</td>
			</tr>
        
			<tr class="TableDetail1">
				<td>3&nbsp;</td>
				<td>烤乳猪&nbsp;</td>
				<td>粤菜&nbsp;</td>
				<td>68.0&nbsp;</td>
                <td>50.0&nbsp;</td>
				<td>
					<a href="/sys/forward?method=foodSaveOrUpdateUI&id=3"  class="FunctionButton">更新</a>				
					<a href="/sys/foodList.jsp?method=delete&id=3" onClick="return delConfirm();"class="FunctionButton">删除</a>				
				</td>
			</tr>
        
			<tr class="TableDetail1">
				<td>4&nbsp;</td>
				<td>烧鹅&nbsp;</td>
				<td>粤菜&nbsp;</td>
				<td>68.0&nbsp;</td>
                <td>50.0&nbsp;</td>
				<td>
					<a href="/sys/forward?method=foodSaveOrUpdateUI&id=4"  class="FunctionButton">更新</a>				
					<a href="/sys/foodList.jsp?method=delete&id=4" onClick="return delConfirm();"class="FunctionButton">删除</a>				
				</td>
			</tr>
        
			<tr class="TableDetail1">
				<td>5&nbsp;</td>
				<td>猪肉荷兰豆&nbsp;</td>
				<td>粤菜&nbsp;</td>
				<td>68.0&nbsp;</td>
                <td>50.0&nbsp;</td>
				<td>
					<a href="/sys/forward?method=foodSaveOrUpdateUI&id=5"  class="FunctionButton">更新</a>				
					<a href="/sys/foodList.jsp?method=delete&id=5" onClick="return delConfirm();"class="FunctionButton">删除</a>				
				</td>
			</tr>
        
			<tr class="TableDetail1">
				<td>6&nbsp;</td>
				<td>黄埔炒蛋&nbsp;</td>
				<td>粤菜&nbsp;</td>
				<td>68.0&nbsp;</td>
                <td>50.0&nbsp;</td>
				<td>
					<a href="/sys/forward?method=foodSaveOrUpdateUI&id=6"  class="FunctionButton">更新</a>				
					<a href="/sys/foodList.jsp?method=delete&id=6" onClick="return delConfirm();"class="FunctionButton">删除</a>				
				</td>
			</tr>
        
			<tr class="TableDetail1">
				<td>7&nbsp;</td>
				<td>狗肉煲&nbsp;</td>
				<td>粤菜&nbsp;</td>
				<td>68.0&nbsp;</td>
                <td>50.0&nbsp;</td>
				<td>
					<a href="/sys/forward?method=foodSaveOrUpdateUI&id=7"  class="FunctionButton">更新</a>				
					<a href="/sys/foodList.jsp?method=delete&id=7" onClick="return delConfirm();"class="FunctionButton">删除</a>				
				</td>
			</tr>
        
			<tr class="TableDetail1">
				<td>8&nbsp;</td>
				<td>鲫鱼汤&nbsp;</td>
				<td>粤菜&nbsp;</td>
				<td>23.0&nbsp;</td>
                <td>29.0&nbsp;</td>
				<td>
					<a href="/sys/forward?method=foodSaveOrUpdateUI&id=8"  class="FunctionButton">更新</a>				
					<a href="/sys/foodList.jsp?method=delete&id=8" onClick="return delConfirm();"class="FunctionButton">删除</a>				
				</td>
			</tr>
        
			<tr class="TableDetail1">
				<td>9&nbsp;</td>
				<td>酱猪蹄&nbsp;</td>
				<td>川菜&nbsp;</td>
				<td>23.0&nbsp;</td>
                <td>18.0&nbsp;</td>
				<td>
					<a href="/sys/forward?method=foodSaveOrUpdateUI&id=9"  class="FunctionButton">更新</a>				
					<a href="/sys/foodList.jsp?method=delete&id=9" onClick="return delConfirm();"class="FunctionButton">删除</a>				
				</td>
			</tr>
        
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="saveFood.jsp">添加</a></div>
    </div> 
</div>
</body>
</html>
