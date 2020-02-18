<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message page</title>

</head>
<body>
<c:if test="${requestScope.message.text != null}">
    <p>Admin left message: </p>
    <p>text: ${requestScope.message.text}</p>
    <p>date: ${requestScope.message.date}</p></p>
</c:if>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
</html>