<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
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
			<img border="0" width="13" height="13" src="${pageContext.request.contextPath }/sys/style/images/title_arrow.gif"/> 餐桌列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


<!-- 过滤条件 -->
<div id="QueryArea">
	<form action="/sys/boardList.jsp" method="get">
		<input type="hidden" name="method" value="search">
		<input type="text" name="keyword" title="请输入餐桌名称">
		<input type="submit" value="搜索">
	</form>
</div>


<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>编号</td>
				<td>桌名</td>
				<td>状态</td>
				<td>预定时间</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
		
			<tr class="TableDetail1">
				<td align="center">1&nbsp;</td>
				<td align="center"> 纽约&nbsp;</td>
				<td align="center">预定</td>
				<td align="center">2014-12-08 23:31:12</td>
				<td>
					<a href="/sys/boardList.jsp?method=update&id=1&isBook=0" class="FunctionButton">退桌</a>				
					<a href="/sys/boardList.jsp?method=delete&id=1" onClick="return delConfirm();"class="FunctionButton">删除</a>				
				</td>
			</tr>
        
			<tr class="TableDetail1">
				<td align="center">2&nbsp;</td>
				<td align="center"> 巴黎&nbsp;</td>
				<td align="center">空闲</td>
				<td align="center"></td>
				<td>
					<a href="/sys/boardList.jsp?method=update&id=2&isBook=1" class="FunctionButton">预定</a>				
					<a href="/sys/boardList.jsp?method=delete&id=2" onClick="return delConfirm();"class="FunctionButton">删除</a>				
				</td>
			</tr>
        
			<tr class="TableDetail1">
				<td align="center">3&nbsp;</td>
				<td align="center"> 丹麦&nbsp;</td>
				<td align="center">空闲</td>
				<td align="center"></td>
				<td>
					<a href="/sys/boardList.jsp?method=update&id=3&isBook=1" class="FunctionButton">预定</a>				
					<a href="/sys/boardList.jsp?method=delete&id=3" onClick="return delConfirm();"class="FunctionButton">删除</a>				
				</td>
			</tr>
        
			<tr class="TableDetail1">
				<td align="center">5&nbsp;</td>
				<td align="center"> 伦敦&nbsp;</td>
				<td align="center">空闲</td>
				<td align="center"></td>
				<td>
					<a href="/sys/boardList.jsp?method=update&id=5&isBook=1" class="FunctionButton">预定</a>				
					<a href="/sys/boardList.jsp?method=delete&id=5" onClick="return delConfirm();"class="FunctionButton">删除</a>				
				</td>
			</tr>
        
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="saveBoard.jsp">添加</a></div>
    </div> 
</div>
</body>
</html>
