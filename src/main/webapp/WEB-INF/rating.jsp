<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Car List</title>
</head>
<body>
<h2>
    Rating <br/>
</h2>
<table>
    <tr>
        <th>Login</th>
        <th>Full Name</th>
        <th>Score</th>
        <th>Speciality</th>
        <th>Faculty</th>
    </tr>
    <c:forEach var="student" items="${requestScope.students}">
        <tr>
            <td>${student.login}</td>
            <td>${student.fullName}</td>
            <td>${student.testScore}</td>
            <td>${student.speciality.name}</td>
            <td>${student.speciality.faculty}</td>
        </tr>
    </c:forEach>
</table>

<br/>
<a href="${pageContext.request.contextPath}/admin">profile</a>
</body>
</html>