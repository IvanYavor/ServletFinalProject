
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Car List</title>
</head>
<body>
<h2>
    List Students <br/>
</h2>
<table>
    <tr><th>Login</th><th>Full Name</th><th>Role</th></tr>
    <c:forEach var="i" items="${users}">
    <tr><td>${i.login}</td><td>${i.fullName}</td><td>${i.role}</td>
        </c:forEach>
</table>

<br/>
<a href="${pageContext.request.contextPath}/index">index</a>
</body>