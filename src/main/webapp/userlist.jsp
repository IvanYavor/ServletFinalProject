
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
<%--<table>--%>
<%--    <tr><th>Name</th><th>Group</th></tr>--%>
<%--    <c:forEach var="i" items="${users}">--%>
<%--    <tr><td>${i.login}<c:out value="${i.login}"/></td><td>${i.password}</td>--%>
<%--        </c:forEach>--%>
<%--</table>--%>
<%--<br>--%>
<%--<br>--%>
<%=request.getAttribute("users")%>
<br>
<c:out value="${users}"/>



<br/>
<a href="./index.jsp">index</a>
<br/>
<a href="${pageContext.request.contextPath}/index.jsp">index</a>
</body>