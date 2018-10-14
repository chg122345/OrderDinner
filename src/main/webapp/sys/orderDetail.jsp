<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>订单详情</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript" src="${pageContext.request.contextPath }/sys/style/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/sys/style/js/page_common.js"></script>
    <link href="${pageContext.request.contextPath }/sys/style/css/common_style_blue.css" rel="stylesheet"
          type="text/css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/sys/style/css/index_1.css"/>
</head>
<body>
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">
            <img border="0" width="13" height="13"
                 src="${pageContext.request.contextPath }/sys/style/css/images/title_arrow.gif"/>
        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>

<!--  -->
<div id="MainArea">
    <table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
        <!-- è¡¨å¤´-->
        <thead>
        <tr align="center" valign="middle" id="TableTitle">
            <td>菜名</td>
            <td>单价</td>
            <td>数量</td>
        </tr>
        </thead>
        <tbody id="TableData">
        <c:forEach items="${requestScope.orderDetails}" var="detail">
            <tr height="40" align="center">
                <td>${detail.food.foodName}</td>
                <td>${detail.food.price}</td>
                <td>${detail.foodCount}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div id="TableTail" align="center">
        <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
    </div>
</div>
</body>
</html>
