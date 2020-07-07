<%--
  Created by IntelliJ IDEA.
  User: puyang
  Date: 7/6/20
  Time: 12:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<div id="page_nav">
    <c:if test="${requestScope.page.pageNumber > 1}">
        <a href="${requestScope.page.uri}&pageNumber=1">首页</a>
        <a href="${requestScope.page.uri}&pageNumber=${requestScope.page.pageNumber - 1}">上一页</a>
    </c:if>

    <c:choose>
        <c:when test="${requestScope.page.pageTotal < 6}">
            <c:set var="begin" value="1" />
            <c:set var="end" value="${requestScope.page.pageTotal}" />
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${requestScope.page.pageNumber < 4}">
                    <c:set var="begin" value="1" />
                    <c:set var="end" value="5" />
                </c:when>
                <c:when test="${requestScope.page.pageNumber > requestScope.page.pageNumber - 3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal - 4}" />
                    <c:set var="end" value="${requestScope.page.pageTotal}" />
                </c:when>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNumber - 2}" />
                    <c:set var="end" value="${requestScope.page.pageNumber + 2}" />
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="number">
        <c:choose>
            <c:when test="${number == requestScope.page.pageNumber}">
                【${number}】
            </c:when>
            <c:otherwise>
                <a href="${requestScope.page.uri}&pageNumber=${number}">${number}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${requestScope.page.pageNumber < requestScope.page.pageTotal}">
        <a href="${requestScope.page.uri}&pageNumber=${requestScope.page.pageNumber + 1}">下一页</a>
        <a href="${requestScope.page.uri}&pageNumber=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    <form action="client/clientServlet" method="get">
        <input type="hidden" name="action" value="page">
        共${requestScope.page.pageTotal}页，${requestScope.page.count}条记录 到第<input
            value="${requestScope.page.pageNumber}" name="pageNumber" id="pn_input"/>页
        <button type="submit" class="search_page">确定</button>
    </form>
</div>
