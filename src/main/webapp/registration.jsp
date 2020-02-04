<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Page</title>

</head>
<body>

<h1>Регистрация в системе</h1><br/>

<c:if test="${loginError}">
    <li>Invalid Login</li>
</c:if>

<c:if test="passwordError">
    <li>Invalid Password</li>
</c:if>

<c:if test="${fullNameError}">
    <li>Invalid Full Name</li>
</c:if>

<c:if test="${userExists}">
    <li>User already exists</li>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/registration">

    <input type="text" name="login"><br/>
    <input type="password" name="password"><br/><br/>
    <input type="text" name="fullName"><br/><br/>
    <input class="button" type="submit" value="Регистрация">

</form>
<br/>
<a href="${pageContext.request.contextPath}/logout">На головну</a>

</body>
</html>