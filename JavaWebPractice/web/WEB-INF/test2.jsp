<%--
  Created by IntelliJ IDEA.
  User: puyang
  Date: 6/17/20
  Time: 12:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.puyang.test.Student" %>
<%@ page import="com.puyang.test.Address" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>学生信息表</title>
</head>
<body>
<%!
    public List<Student> students = new ArrayList<>();
%>
<%
    for (int i = 0; i < 10; i++) {
        students.add(Student.builder()
                .id("202006150" + i)
                .name("阿" + i)
                .address(Address.builder()
                        .city("City " + i)
                        .province("Province" + i)
                        .country("Country" + i)
                        .build())
                .build());
    }
%>
<table style="border-color:red; border-style: solid">
    <tr>
        <th>学号</th>
        <th>姓名</th>
        <th>地址</th>
    </tr>
    <%
        for (int i = 1; i < 10; i++) {
    %>
    <tr>
        <td>
            <%
                out.print(students.get(i).getId());
            %>
        </td>
        <td>
            <%
                out.print(students.get(i).getName());
            %>
        </td>
        <td>
            <%
                out.print(students.get(i).getAddress());
            %>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
