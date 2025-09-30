<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma","no-cache"); // HTTP 1.0
    response.setDateHeader ("Expires", 0); // Proxies

    if (session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50">

<!-- Navbar -->
<nav class="bg-indigo-700 text-white shadow-lg">
    <div class="container mx-auto flex justify-between items-center px-6 py-3">
        <h1 class="text-xl font-semibold">🌐 Antonio Web App</h1>
        <div class="flex items-center gap-4">
            <span>Welcome, ${sessionScope.username}</span>
            <a href="logout"
               class="bg-white text-indigo-700 px-3 py-1 rounded-lg hover:bg-indigo-100 transition">
                Logout
            </a>
        </div>
    </div>
</nav>

<!-- Content -->
<div class="container mx-auto px-6 py-8">
    <h2 class="text-2xl font-bold text-gray-800 mb-6">📋 Records</h2>

    <div class="overflow-hidden shadow-md rounded-lg">
        <table class="w-full text-left border-collapse">
            <thead class="bg-indigo-600 text-white">
            <tr>
                <th class="py-3 px-4">Title</th>
                <th class="py-3 px-4">Description</th>
            </tr>
            </thead>
            <tbody class="divide-y divide-gray-200 bg-white">
            <c:forEach var="record" items="${records}">
                <tr class="hover:bg-indigo-50 transition">
                    <td class="py-3 px-4">${record.title}</td>
                    <td class="py-3 px-4">${record.description}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>