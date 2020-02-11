<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>USER</title>

</head>
<body>

<h1>Hello USER!</h1>
<p>You're ${sessionScope.user.fullName}</p>
<p>You're a student</p>
<p>Your login: ${sessionScope.user.login}</p>
<c:if test="${sessionScope.user.speciality.name != null}">
    <p>Speciality: ${sessionScope.user.speciality.name}</>
    <p>Faculty: ${sessionScope.user.speciality.faculty}</p>
    <p>Test: ${sessionScope.user.testScore}</p>
</c:if>

<c:if test="${sessionScope.user.accepted}">
    <h1>You're accepted</h1>
</c:if>
<br />
<a href="${pageContext.request.contextPath}/user/regSpeciality">Registration for speciality</a>
<br/>
<a href="${pageContext.request.contextPath}/user/message">Message From Admin</a>
<br />
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
</html>