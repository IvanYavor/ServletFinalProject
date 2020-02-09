<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Speciality page</title>

</head>
<body>

<h1>Registration for speciality</h1>
<c:if test="${requestScope.user.speciality.name == null}">
    <form method="post" action="/user/regSpeciality">
        <c:forEach var="s" items="${requestScope.specialities}">
        <input type="radio" name="speciality_name" value="${s.name}" id="${s.name}" ><label>${s.name}</label>
        <br/>
        </c:forEach>

        <input type="hidden" name="id" value="${sessionScope.user.id}">

        <button type="submit">Apply</button>
    </form>
</c:if>
<c:if test="${requestScope.user.speciality.name != null}">
    <p>You have chosen ${requestScope.user.speciality.name}</p>
</c:if>
<br />
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
</html>