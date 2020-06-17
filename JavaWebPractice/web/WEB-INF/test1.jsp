<%--
  Created by IntelliJ IDEA.
  User: puyang
  Date: 6/15/20
  Time: 2:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>九九乘法表</title>
</head>
<body>
<table>
    <%
        for (int i = 1; i < 10; i++) {
    %>
    <tr>
        <%
            for (int j = 1; j <= i; j++) {
        %>
        <td>
            <%
                out.write(j + "*" + i + "=" + i * j);
            %>
            &emsp;
        </td>
        <%
            }
        %>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>

