<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Speciality Page</title>
</head>
<body>
<form method="post" action="/admin/createSpeciality">
    <label>Speciality Name</label><input type="text" name="specialityName"> <br/>
    <label>Speciality Description</label><input type="text" name="description"> <br/>
    <label>Faculty</label><input type="text" name="faculty"> <br/>
    <button type="submit">Create</button>
</form>
<br/>
<br/>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
</html>
