<%--
  Created by IntelliJ IDEA.
  User: ivan
  Date: 04.02.20
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<form method="post" action="/admin/delete">
    <input type="hidden" name="id" value="${requestScope.user.id}">
    <button type="submit" >delete user</button>
</form>
<br/>
<br/>
<%--<p>${sessionScope.id}</p>--%>
<%--<p>${sessionScope.user.login}</p>--%>
<%--<p>${sessionScope.user.fullName}</p>--%>
<%--<p>${sessionScope.user.role}</p>--%>
<c:if test="${loginError}">
    <li>login exists</li>
</c:if>
<form method="post" action="/admin/editpost">
    <input type="text" name="login" value="${requestScope.user.login}" >
    <input type="hidden" name="id" value="${requestScope.user.id}" >
    <button type="submit">Save</button>
</form>
<p>${requestScope.user.login}</p>
<p>${requestScope.user.fullName}</p>
<p>${requestScope.user.role}</p>

</body>
</html>
