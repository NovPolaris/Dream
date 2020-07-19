<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: puyang
  Date: 6/20/20
  Time: 10:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
    <c:choose>
        <c:when test="${'admin'.equals(sessionScope.user.username)}">
            <a href="pages/manager/manager.jsp">后台管理</a>
        </c:when>
        <c:otherwise>
            <a href="orderServlet?action=showMyOrders">我的订单</a>
        </c:otherwise>
    </c:choose>
    <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>
