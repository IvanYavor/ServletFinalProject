<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADMIN THE BASIS</title>
</head>
<body>

<h1>Hello ADMIN!</h1>
<p>You're ${sessionScope.user.fullName}</p>
<p>You're a admin</p>
<p>Your login: ${sessionScope.user.login}</p>
<br/>
<a href="${pageContext.request.contextPath}/admin/rating">Rating</a>
<br />
<a href="${pageContext.request.contextPath}/admin/createSpeciality">create new speciality</a>
<br/>
<a href="${pageContext.request.contextPath}/admin/listUsers">All Students</a>
<br/>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
</html>