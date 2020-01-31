<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Page</title>

</head>
<body>

<h1>Регистрация в системе</h1><br/>
<form method="post" action="${pageContext.request.contextPath}/registration">

    <input type="text" name="username"><br/>
    <input type="password" name="password"><br/><br/>
    <input type="text" name="fullName"><br/><br/>
    <input class="button" type="submit" value="Регистрация">

</form>
<br/>
<a href="${pageContext.request.contextPath}/logout">На головну</a>

</body>
</html>