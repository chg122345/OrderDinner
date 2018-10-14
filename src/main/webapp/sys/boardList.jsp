<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<c:forEach items="${requestScope.tables}" var="table">
			<tr class="TableDetail1">
				<td align="center">${table.id}&nbsp;</td>
				<td align="center">${table.tableName}&nbsp;</td>
				<c:if test="${table.tableStatus == 1}">
					<td align="center">预定</td>
					<td align="center">${table.orderDate}</td>
					<td><a href="#" lang="${table.id}" onClick="changeTable(this);" class="FunctionButton">退桌</a></td>
				</c:if>
				<c:if test="${table.tableStatus == 0}">
					<td align="center">未预定</td>
					<td align="center"></td>
					<td>
						<a href="#" lang="${table.id}" onClick="delTable(this);" class="FunctionButton">删除</a>
					</td>
				</c:if>
			</tr>
		</c:forEach>
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="${pageContext.request.contextPath}/admin/addTable">添加</a></div>
    </div> 
</div>
<script type="text/javascript">
    function delTable(node) {
        var id = node.lang;
        if (window.confirm("您确定要删除一条记录码？")) {
            $.ajax({
                url:'${pageContext.request.contextPath}/admin/delTable',
                data:{id:id},
                success:function (res) {
                    alert(res);
                    window.location.reload();
                }
            });
        }
    }
    function changeTable(node) {
        var id = node.lang;
        if (window.confirm("您确定要退桌吗？")) {
            $.ajax({
                url:'${pageContext.request.contextPath}/admin/changeTable',
                data:{id:id},
                success:function (res) {
                    alert(res);
                    window.location.reload();
                }
            });
        }
    }
</script>
</body>
</html>
