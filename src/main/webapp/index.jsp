<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>
<h1>Welcome page</h1>


<c:if test="${sessionScope.loggedUser}">
    <a href="/user">Profile</a>
</c:if>
<c:if test="${sessionScope.loggedAdmin}">
    <a href="/admin">Profile</a>
</c:if>
<br/>
<c:if test="${!sessionScope.loggedAdmin and !sessionScope.loggedUser}">
<a href="${pageContext.request.contextPath}/login">Log in</a>
</c:if>
<br>
<c:if test="${!sessionScope.loggedAdmin and !sessionScope.loggedUser}">
<a href="${pageContext.request.contextPath}/registration">Registration</a>
</c:if>
<br>
<a href="${pageContext.request.contextPath}/userlist">User List</a>


</body>
</html>
