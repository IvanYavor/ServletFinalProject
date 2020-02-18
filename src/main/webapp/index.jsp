<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>
<h1>Welcome page</h1>

<h1>
    <fmt:message key="label.welcome"></fmt:message>
</h1>
<a href="language?locale=en">en</a>
<a href="language?locale=uk">ua</a>

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


</body>
</html>
