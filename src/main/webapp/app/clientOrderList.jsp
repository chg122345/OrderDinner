<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/app/detail/style/css/index.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath }/app/detail/style/js/jquery.js"></script>
    <script type="text/javascript">
        // 通知服务员结账
        function callPay(node) {
            var tableId = node.lang;
            alert('尊敬的顾客,您好!您的餐桌是' + tableId + '号，已经通知服务员结账，请稍等!');
            window.location.href = '${pageContext.request.contextPath }/index'
        }

        $(document).ready(function () {
            var a = parseFloat(0.0);
            $('.total').each(function () {
                var to = parseFloat($(this).html());
                a += to;
            });
            $('.allTotal').text(a);
        });
    </script>
</head>

<body style="text-align: center">
<div id="all">
    <div id="menu">
        <!-- 餐车div -->
        <c:if test="${empty sessionScope.order}">
            暂无订单信息
        </c:if>
        <c:if test="${!empty sessionScope.order}">
            <div id="count">
                <table align="center" width="100%">
                    <tr height="40">
                        <td align="center" width="20%">菜名</td>
                        <td align="center" width="20%">单价</td>
                        <td align="center" width="20%">数量</td>
                        <td align="center" width="20%">小计</td>
                    </tr>

                    <c:forEach items="${sessionScope.order.orderDetailList}" var="od">

                        <tr height="60">
                            <td align="center" width="20%">${od.food.foodName}</td>
                            <td align="center" width="20%">￥ ${od.food.price}</td>
                            <td align="center" width="20%">${od.foodCount}</td>
                            <td align="center" width="20%" class="total">${od.food.price*od.foodCount}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="6" align="right">总计:
                            <span style="font-size:36px;">&yen;</span>
                            <label
                                    id="counter" class="allTotal"
                                    style="font-size:36px">${sessionScope.order.totalPrice}</label>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" style="margin-left: 100px; text-align: center;" align="right">
                            <input type="hidden" name="bId" value="">
                            <input type="button" value="结账" class="btn_next" lang="${sessionScope.order.table.id}"
                                   onclick="callPay(this)"/>
                        </td>
                    </tr>
                </table>
            </div>
        </c:if>
    </div>

    <!-- 右边菜系列表，菜品搜索框  -->
    <div id="dish_class">
        <div id="dish_top">
            <ul>
                <li class="dish_num"></li>
                <li>
                    <a href="${pageContext.request.contextPath }/order">
                        <img src="${pageContext.request.contextPath }/app/detail/style/images/call2.gif"/>
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
            <form action="${pageContext.request.contextPath }/search" method="post">
                <table width="166px">
                    <tr>
                        <td>
                            <input type="text" id="dish_name" name="foodName" class="select_value"/>
                            <input type="hidden" value="selectFood" name="method">
                        </td>
                    </tr>
                    <tr>
                        <td><input type="submit" id="sub" value=""/></td>
                    </tr>
                    <tr>
                        <td>
                            <a href="${pageContext.request.contextPath }/look">
                                <img src="${pageContext.request.contextPath }/app/detail/style/images/look.gif"/>
                            </a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

</div>
</body>
</html>
