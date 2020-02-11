<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<form method="post" action="/admin/delete">
    <input type="hidden" name="id" value="${requestScope.user.id}">
    <button type="submit" >delete student</button>
</form>
<br/>
<br/>
<c:if test="${requestScope.scoreError}">
    <li>Score out of bounds</li>
</c:if>
<c:if test="${(requestScope.user.speciality.name != null) and (requestScope.user.testScore == 0)}">
<form method="post" action="/admin/edit">
    <input type="hidden" name="id" value="${requestScope.user.id}" >
    <input type="number" name="score" > <label>Score</label>
    <input type="text" name="text_message" > <label>Message</label>
    <button type="submit">Save</button>
</form>
</c:if>
<p>Login: ${requestScope.user.login}</p>
<p>Full name: ${requestScope.user.fullName}</p>
<p>Speciality: ${requestScope.user.speciality.name}</p>
<p>Faculty: ${requestScope.user.speciality.faculty}</p>
<p>Test: ${requestScope.user.testScore}</p>
</body>
</html>
