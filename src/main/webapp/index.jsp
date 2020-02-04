<!DOCTYPE html "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>
<h1>Welcome page</h1>

<br/>
<a href="${pageContext.request.contextPath}/login">Log in</a>
<br>
<a href="${pageContext.request.contextPath}/registration">Registration</a>
<br>
<a href="${pageContext.request.contextPath}/userlist">User List</a>


</body>
</html>
