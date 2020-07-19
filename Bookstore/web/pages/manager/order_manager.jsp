<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>订单管理</title>
    <%@ include file="/pages/common/head.jsp" %>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">订单管理系统</span>
    <%@ include file="/pages/common/management.jsp"%>
</div>

<div id="main">
    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>详情</td>
            <td>发货</td>
        </tr>
        <c:forEach items="${requestScope.orders}" var="order">
            <tr>
                <td>${order.createTime}</td>
                <td>${order.price}</td>
                <td><a href="#">查看详情</a></td>
                <c:choose>
                    <c:when test="${'未付款'.equals(order.status.toString())}">
                        <td>未付款</td>
                    </c:when>
                    <c:when test="${'已付款'.equals(order.status.toString())}">
                        <td>已付款</td>
                    </c:when>
                    <c:when test="${'未发货'.equals(order.status.toString())}">
                        <td><a href="orderServlet?action=shipOrder&orderId=${order.orderId}">点击发货</a></td>
                    </c:when>
                    <c:when test="${'已发货'.equals(order.status.toString())}">
                        <td>已发货</td>
                    </c:when>
                    <c:when test="${'未签收'.equals(order.status.toString())}">
                        <td>未签收</td>
                    </c:when>
                    <c:otherwise>
                        <td>已签收</td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
</div>

<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>