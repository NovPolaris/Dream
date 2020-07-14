<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%@ include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("a.deleteItem").click(function () {
                return confirm("确定要删除【" + $(this).parent().parent().find("td:first").text() + "】吗？");
            });
            $("a.clearCart").click(function () {
                return confirm("确定要清空购物车吗？");
            });
            $(".updateCount").change(function () {
                let name = $(this).parent().parent().find("td:first").text();
                let count = $(this).val();
                let sku = $(this).attr("bookSku");
                let boolean = confirm("确定要将【" + name + "】的数量修改成" + count + "吗？");
                if (boolean) {
                    location.href = "http://localhost:8080/Bookstore/cartServlet?action=updateItem&count=" + count + "&sku=" + sku + "&name=" + name;
                } else {
                    $(this).val(this.defaultValue);
                }
            })
        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>
    <%@ include file="/pages/common/login_success.jsp" %>
</div>

<div id="main">
    <c:choose>
        <c:when test="${empty requestScope.cart.items}">
            <table>
                <tr>
                    <td>商品名称</td>
                    <td>数量</td>
                    <td>单价</td>
                    <td>金额</td>
                    <td>操作</td>
                </tr>
                <tr>
                    <td colspan="5"><a href="index.jsp">亲，当前购物车为空！快跟小伙伴们去浏览商品吧！！！</a></td>
                </tr>
            </table>
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <td>商品名称</td>
                    <td>数量</td>
                    <td>单价</td>
                    <td>金额</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${requestScope.cart.items}" var="item">
                    <tr>
                        <td>${item.name}</td>
                        <td>
                            <input class="updateCount" width="80px" type="text" name="count" bookSku="${item.sku}" value="${item.count}">
                        </td>
                        <td>${item.price}</td>
                        <td>${item.totalPrice}</td>
                        <td><a class="deleteItem" href="cartServlet?action=delete&sku=${item.sku}&name=${item.name}">删除</a></td>
                    </tr>
                </c:forEach>
            </table>

            <div class="cart_info">
                <span class="cart_span">购物车中共有<span class="b_count">${requestScope.cart.totalCount}</span>件商品</span>
                <span class="cart_span">总金额<span class="b_price">${requestScope.cart.totalPrice}</span>元</span>
                <span class="cart_span"><a class="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
                <span class="cart_span"><a href="pages/cart/checkout.jsp">去结账</a></span>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>
