<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/app/detail/style/css/index.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath }/app/detail/style/js/jquery.js"></script>
    <script type="text/javascript">
        // 删除菜品项
        function removeSorder(node) {
            var cartId = node.lang;
            $.ajax({
                url: '${pageContext.request.contextPath }/delete',
                data: {id: cartId},
                success: function (res) {
                    alert(res);
                    window.location.reload();
                }
            });
        }

        // 下单
        function genernateOrder() {
            window.location.href = "${pageContext.request.contextPath }/order";
        }

        $(document).ready(function () {
            var a = parseFloat(0.0);
            $('.total').each(function () {
                var to = parseFloat($(this).html());
                a += to;
            });
            $('#allTotal').text('￥ ' + a);
        });
    </script>
</head>

<body style="text-align: center">
<div id="all">
    <div id="menu">
        <c:if test="${empty sessionScope.carts}">
            暂无信息
        </c:if>
        <!-- 餐车div -->
        <c:if test="${!empty sessionScope.carts}">
            <div id="count">
                <table align="center" width="100%" id="foods">
                    <tr height="40">
                        <td align="center" width="20%">菜名</td>
                        <td align="center" width="20%">单价</td>
                        <td align="center" width="20%">数量</td>
                        <td align="center" width="20%">小计</td>
                        <td align="center" width="20%">操作</td>
                    </tr>
                    <c:forEach items="${sessionScope.carts}" var="cart">

                        <tr height="60">
                            <td align="center" width="20%">${cart.food.foodName}</td>
                            <td align="center" width="20%">￥${cart.food.price}</td>
                            <td align="center" width="20%">${cart.number}</td>
                            <td align="center" width="20%" class="total">${cart.total}</td>
                            <td align="center" width="20%">
                                <input type="button" value="删除" class="btn_next" lang="${cart.targetId}"
                                       onclick="removeSorder(this)"/>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="6" align="right">总计:
                            <span style="font-size:36px;" id="allTotal"></span>
                            <label
                                    id="counter" style="font-size:36px"></label>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" style="margin-left: 100px; text-align: center;" align="right">
                            <input type="hidden" name="bId" value="">
                            <input type="button" value="下单" class="btn_next" onclick="genernateOrder()"/>
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
            <form action="#" method="post">
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
