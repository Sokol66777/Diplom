<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
    <p>Users page</p>
    <c:forEach var="user" items= "${allUsers}">
        <p><c:out value="username='${user.username}' role='${user.role}' email='${user.email}'"/>
        <button onclick = "location.href='${pageContext.request.contextPath}/user/delete?deleteUsersID=${user.ID}'">delete</button>
        </p>
    </c:forEach>
    <button onclick = "location.href='${pageContext.request.contextPath}/user/welcome'">welcome</button>
    <button onclick = "location.href= '${pageContext.request.contextPath}/user/logout'"> logout </button>

    <c:if test="${totalPages>0}">
        <ul>
            <c:forEach begin="0" end ="${totalPages-1}" var="page">
                    <a href="${pageContext.request.contextPath}/user/allUsers?page=${page}"><c:out value="${page+1}" /></a>
            </c:forEach>
        </ul>
    </c:if>
</body>
</html>