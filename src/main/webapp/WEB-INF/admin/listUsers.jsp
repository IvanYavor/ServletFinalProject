<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User List</title>
</head>
<body>
<h2>
    List Students <br/>
</h2>
<table>
    <tr>
        <th>Login</th>
        <th>Full Name</th>
        <th>Role</th>
    </tr>
    <c:forEach var="i" items="${users}">
    <tr>
        <td>${i.login}</td>
        <td>${i.fullName}</td>
        <td>${i.role}</td>
        <td><a href="/admin/edit/${i.id}">Rate student</a></td>
        </c:forEach>
</table>

<br/>
<a href="${pageContext.request.contextPath}/admin">profile</a>
</body>
</html>