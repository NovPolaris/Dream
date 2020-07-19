<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%@ include file="/pages/common/head.jsp" %>
</head>

<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">网上书城</span>
    <div>
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
                <c:if test="${'admin'.equals(sessionScope.user.username)}">
                    <a href="pages/manager/manager.jsp">后台管理</a>
                </c:if>
                <c:if test="${not 'admin'.equals(sessionScope.user.username)}">
                    <a href="pages/order/order.jsp">我的订单</a>
                    <a href="cartServlet?action=list&username=${sessionScope.user.username}">购物车</a>
                </c:if>
                <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
            </c:when>
            <c:otherwise>
                <a href="pages/user/login.jsp">登录</a> |
                <a href="pages/user/register.jsp">注册</a> &nbsp;&nbsp;
            </c:otherwise>
        </c:choose>
    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/clientServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice">
                价格：<input id="min" type="text" name="minValue" value="${requestScope.minValue}"> 元 -
                <input id="max" type="text" name="maxValue" value="${requestScope.maxValue}"> 元
                <input type="submit" value="查询"/>
            </form>
        </div>
        <c:if test="${not empty sessionScope.operation}">
            <div style="text-align: center">
                <span>您的购物车中有${sessionScope.totalCountInCart}件商品</span>
                <div>
                    <c:choose>
                        <c:when test="${sessionScope.operation.equals('add')}">
                            您刚刚将<span style="color: red">${sessionScope.lastName}</span>加入到了购物车中
                        </c:when>
                        <c:when test="${sessionScope.operation.equals('delete')}">
                            您刚刚将<span style="color: red">${sessionScope.lastName}</span>从购物车中删除
                        </c:when>
                        <c:when test="${sessionScope.operation.equals('clear')}">
                            您刚刚将购物车清空了
                        </c:when>
                        <c:otherwise>
                            您刚刚更新了<span style="color: red">${sessionScope.lastName}</span>的数量
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </c:if>
        <c:forEach items="${requestScope.page.items}" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${book.imgPath}"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <form action="cartServlet" method="get">
                            <input type="hidden" name="action" value="add">
                            <input type="hidden" name="id" value="${book.id}">
                            <button type="submit">加入购物车</button>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <br>
    <br>
    <%@ include file="/pages/common/page_navigation.jsp" %>
</div>

<div id="bottom">
    <span>尚硅谷书城.Copyright &copy;2015</span>
</div>
</body>
</html>
