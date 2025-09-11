<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
    <h2>Welcome, ${sessionScope.username}</h2>

    <table border="1">
        <thead>
            <tr><th>Title</th><th>Description</th></tr>
        </thead>
        <tbody>
            <c:forEach var="record" items="${records}">
                <tr>
                    <td>${record[0]}</td>
                    <td>${record[1]}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>