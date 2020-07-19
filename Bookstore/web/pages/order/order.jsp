<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <%@ include file="/pages/common/head.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">我的订单</span>
    <div>
        <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
        <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
        <a href="index.jsp">返回</a>
    </div>
</div>

<div id="main">
    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>状态</td>
            <td>详情</td>
        </tr>
        <c:forEach items="${requestScope.orders}" var="order">
            <tr>
                <td>${order.createTime}</td>
                <td>${order.price}</td>
                <c:choose>
                    <c:when test="${'未签收'.equals(order.status.toString())}">
                        <td><a href="orderServlet?action=signOrder&orderId=${order.orderId}">签收</a></td>
                    </c:when>
                    <c:otherwise>
                        <td>${order.status}</td>
                    </c:otherwise>
                </c:choose>
                <td><a href="#">查看详情</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>